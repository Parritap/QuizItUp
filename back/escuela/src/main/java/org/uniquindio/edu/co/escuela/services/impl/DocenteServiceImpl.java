package org.uniquindio.edu.co.escuela.services.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.uniquindio.edu.co.escuela.DTO.CursoDTO;
import org.uniquindio.edu.co.escuela.DTO.ExamenDTO;
import org.uniquindio.edu.co.escuela.DTO.PreguntaBancoDTO;
import org.uniquindio.edu.co.escuela.services.interfaces.DocenteService;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DocenteServiceImpl implements DocenteService {

    private final EntityManager entityManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;

   @Override
    @Transactional
    public List<PreguntaBancoDTO> obtenerBancoPreguntas(Long id_tema) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("obtener_banco_preguntas")
                .declareParameters(
                        new SqlParameter("v_id_tema", OracleTypes.NUMBER),
                        new SqlOutParameter("p_preguntas", OracleTypes.CURSOR)
                )
                .returningResultSet("p_preguntas", this::mapRowToPreguntaBancoDTO);

        Map<String, Object> result = jdbcCall.execute(new MapSqlParameterSource("v_id_tema", id_tema));

       System.out.println("llega hasta antes del return dentro del servicio docente");

        return (List<PreguntaBancoDTO>) result.get("p_preguntas");
    }
    @Transactional
    @Override
    public String crearRespuesta(String descripcion, Character esVerdadera, Long id_pregunta) {

            // Crear una consulta para el procedimiento almacenado
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("crear_respuesta");

            // Registrar los parámetros de entrada y salida del procedimiento almacenado
            storedProcedure.registerStoredProcedureParameter("v_descripcion", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("v_es_verdadera", Character.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("v_id_pregunta", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("v_mensaje", String.class, ParameterMode.OUT);

            // Establecer los valores de los parámetros de entrada
            storedProcedure.setParameter("v_descripcion", descripcion);
            storedProcedure.setParameter("v_es_verdadera", esVerdadera);
            storedProcedure.setParameter("v_id_pregunta", id_pregunta);

            // Ejecutar el procedimiento almacenado
            storedProcedure.execute();

            // Obtener el valor del parámetro de salida
            String mensaje = (String) storedProcedure.getOutputParameterValue("v_mensaje");

            // Retornar el mensaje
            return mensaje;
        }

    @Override
    @Transactional
    public String crearExamen(Integer tiempoMax, Integer numeroPreguntas, Integer porcentajeCurso, String nombre, Integer porcentajeAprobatorio, Date fechaHoraInicio, Date fechaHoraFin, Integer numPreguntasAleatorias, Integer idTema, Integer idDocente, Integer idGrupo) {
        // Crear una consulta para el procedimiento almacenado
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("crear_examen");

        // Registrar los parámetros de entrada y salida del procedimiento almacenado
        storedProcedure.registerStoredProcedureParameter("v_tiempo_max", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_numero_preguntas", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_porcentaje_curso", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_nombre", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_porcenjate_aprobatorio", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_fecha_hora_inicio", Date.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_fecha_hora_fin", Date.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_num_preguntas_aleatorias", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_id_tema", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_id_docente", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_id_grupo", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_mensaje", String.class, ParameterMode.OUT);

        // Establecer los valores de los parámetros de entrada
        storedProcedure.setParameter("v_tiempo_max", tiempoMax);
        storedProcedure.setParameter("v_numero_preguntas", numeroPreguntas);
        storedProcedure.setParameter("v_porcentaje_curso", porcentajeCurso);
        storedProcedure.setParameter("v_nombre", nombre);
        storedProcedure.setParameter("v_porcenjate_aprobatorio", porcentajeAprobatorio);
        storedProcedure.setParameter("v_fecha_hora_inicio", fechaHoraInicio);
        storedProcedure.setParameter("v_fecha_hora_fin", fechaHoraFin);
        storedProcedure.setParameter("v_num_preguntas_aleatorias", numPreguntasAleatorias);
        storedProcedure.setParameter("v_id_tema", idTema);
        storedProcedure.setParameter("v_id_docente", idDocente);
        storedProcedure.setParameter("v_id_grupo", idGrupo);

        // Ejecutar el procedimiento almacenado
        storedProcedure.execute();

        // Obtener el valor del parámetro de salida
        String mensaje = (String) storedProcedure.getOutputParameterValue("v_mensaje");

        // Retornar el mensaje
        return mensaje;
    }

    @Override
    @Transactional
    public String crearPregunta(String enunciado, Character esPublica, String tipoPregunta, Integer idTema, Integer idDocente) {
        // Crear una consulta para el procedimiento almacenado
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("crear_pregunta");

        // Registrar los parámetros de entrada y salida del procedimiento almacenado
        storedProcedure.registerStoredProcedureParameter("v_enunciado", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_es_publica", Character.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_tipo_pregunta", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_id_tema", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_id_docente", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_mensaje", String.class, ParameterMode.OUT);

        // Establecer los valores de los parámetros de entrada
        storedProcedure.setParameter("v_enunciado", enunciado);
        storedProcedure.setParameter("v_es_publica", esPublica);
        storedProcedure.setParameter("v_tipo_pregunta", tipoPregunta);
        storedProcedure.setParameter("v_id_tema", idTema);
        storedProcedure.setParameter("v_id_docente", idDocente);

        // Ejecutar el procedimiento almacenado
        storedProcedure.execute();

        // Obtener el valor del parámetro de salida
        String mensaje = (String) storedProcedure.getOutputParameterValue("v_mensaje");

        // Retornar el mensaje
        return mensaje;
    }

    @Override
    @Transactional
    public String calificarExamen(Long idPresentacionExamen) {
        // Crear una consulta para el procedimiento almacenado
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("calificar_examen");

        // Registrar los parámetros de entrada y salida del procedimiento almacenado
        storedProcedure.registerStoredProcedureParameter("v_id_presentacion_examen", Integer.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("v_mensaje", String.class, ParameterMode.OUT);

        // Establecer los valores de los parámetros de entrada
        storedProcedure.setParameter("v_id_presentacion_examen", idPresentacionExamen);

        // Ejecutar el procedimiento almacenado
        storedProcedure.execute();

        // Obtener el valor del parámetro de salida
        String mensaje = (String) storedProcedure.getOutputParameterValue("v_mensaje");

        // Retornar el mensaje
        return mensaje;
    }

    @Override
    @Transactional
    public List<PreguntaBancoDTO> obtenerPreguntasDocente(Long idDocente) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("obtener_preguntas_docente")
                .declareParameters(
                        new SqlParameter("v_id_docente", OracleTypes.NUMBER),
                        new SqlOutParameter("p_preguntas", OracleTypes.CURSOR)
                )
                .returningResultSet("p_preguntas", this::mapRowToPreguntaBancoDTO);

        Map<String, Object> result = jdbcCall.execute(new MapSqlParameterSource("v_id_docente", idDocente));

        return (List<PreguntaBancoDTO>) result.get("p_preguntas");
    }

    @Override
    @Transactional
    public List<ExamenDTO> obtenerExamenesDocente(Long id_docente) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("obtener_examenes_docente")
                .declareParameters(
                        new SqlParameter("v_id_docente", OracleTypes.NUMBER),
                        new SqlOutParameter("p_examenes", OracleTypes.CURSOR)
                )
                .returningResultSet("p_examenes", this::mapRowToExamenDTO);

        Map<String, Object> result = jdbcCall.execute(new MapSqlParameterSource("v_id_docente", id_docente));

        return (List<ExamenDTO>) result.get("p_examenes");
    }

    private ExamenDTO mapRowToExamenDTO(ResultSet rs, int rowNum) throws SQLException {
        return new ExamenDTO(
                rs.getInt("id_examen"),
                rs.getInt("tiempo_max"),
                rs.getInt("numero_preguntas"),
                rs.getInt("porcentaje_curso"),
                rs.getString("nombre"),
                rs.getInt("porcentaje_aprobatorio"),
                rs.getDate("fecha_hora_inicio"),
                rs.getDate("fecha_hora_fin"),
                rs.getInt("num_preguntas_aleatorias"),
                rs.getInt("id_tema"),
                rs.getInt("id_docente"),
                rs.getInt("id_grupo"),
                rs.getString("estado")
        );
    }

    @Override
    public String obtenerNombre(String id, String rol) {
        // Crear una consulta para el procedimiento almacenado
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("get_nombre_usuario");

        // Registrar los parámetros de entrada y salida del procedimiento almacenado
        storedProcedure.registerStoredProcedureParameter("p_id_usuario", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("rol", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("res", String.class, ParameterMode.OUT);

        // Establecer los valores de los parámetros de entrada
        storedProcedure.setParameter("p_id_usuario", id);
        storedProcedure.setParameter("rol", "alumno");

        // Ejecutar el procedimiento almacenado
        storedProcedure.execute();

        String nombre = (String) storedProcedure.getOutputParameterValue("res");

        return nombre;

    }

    @Override
    public List<CursoDTO> obtenerCursos(String id, String rol) {
        // Crear una consulta para el procedimiento almacenado
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("get_grupos_por_usuario");

        // Registrar los parámetros de entrada y salida del procedimiento almacenado
        storedProcedure.registerStoredProcedureParameter("p_id_usuario", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("rol", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("res", String.class, ParameterMode.OUT);

        // Establecer los valores de los parámetros de entrada
        storedProcedure.setParameter("p_id_usuario", id);
        storedProcedure.setParameter("rol", "alumno");

        // Ejecutar el procedimiento almacenado
        storedProcedure.execute();

        String json1 = (String) storedProcedure.getOutputParameterValue("res");

        Gson gson = new Gson();
        Type personListType = new TypeToken<List<CursoDTO>>() {}.getType();

        return gson.fromJson(json1, personListType);
    }


    //Esta vaina es solo para permitir consultar las preguntas por tema, es como un plugin XD
    private PreguntaBancoDTO mapRowToPreguntaBancoDTO(ResultSet rs, int rowNum) throws SQLException {
        return new PreguntaBancoDTO(
                rs.getLong("id_pregunta"),
                rs.getString("enunciado"),
                rs.getString("es_publica").charAt(0),
                rs.getString("tipo_pregunta"),
                rs.getLong("id_tema"),
                rs.getLong("id_docente")
        );
    }
}


