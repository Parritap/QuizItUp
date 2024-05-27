package org.uniquindio.edu.co.escuela.services.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.uniquindio.edu.co.escuela.DTO.*;
import org.uniquindio.edu.co.escuela.services.interfaces.AlumnoService;

import java.util.Date;

@Service
@AllArgsConstructor
public class AlumnoServiceImp implements AlumnoService {

    private final EntityManager entityManager;

    @Transactional
    public String guardarPregunta(PreguntaDTO preguntaDTO) {
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
        storedProcedure.setParameter("v_enunciado", preguntaDTO.enunciado());
        storedProcedure.setParameter("v_es_publica", preguntaDTO.es_publica());
        storedProcedure.setParameter("v_tipo_pregunta", preguntaDTO.tipo_pregunta());
        storedProcedure.setParameter("v_id_tema", preguntaDTO.id_tema());
        storedProcedure.setParameter("v_id_docente", preguntaDTO.id_docente());

        // Ejecutar el procedimiento almacenado
        storedProcedure.execute();

        String mensaje = (String) storedProcedure.getOutputParameterValue("v_mensaje");

        return mensaje;

    }
    @Transactional
    @Override
    public Float obtenerNotaPresentacionExamen(Long id_presentacion_examen) {
            // Crear una consulta para el procedimiento almacenado
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("obtener_nota");

            // Registrar los parámetros de entrada y salida del procedimiento almacenado
            storedProcedure.registerStoredProcedureParameter("v_id_presentacion_examen", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("v_nota", Float.class, ParameterMode.OUT);

            // Establecer los valores de los parámetros de entrada
            storedProcedure.setParameter("v_id_presentacion_examen", id_presentacion_examen);

            // Ejecutar el procedimiento almacenado
            storedProcedure.execute();

            // Obtener el valor del parámetro de salida
            Float nota = (Float) storedProcedure.getOutputParameterValue("v_nota");

            // Retornar la nota
            return nota != null ? nota : 0.0f;
        }
    @Transactional
    @Override
    public String crearPresentacionExamen(Integer tiempo, Character terminado, String ip_source, Date fecha_hora_presentacion, Integer id_examen, Integer id_alumno) {


            // Crear una consulta para el procedimiento almacenado
            StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("crear_presentacion_examen");

            // Registrar los parámetros de entrada y salida del procedimiento almacenado
            storedProcedure.registerStoredProcedureParameter("v_tiempo", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("v_terminado", Character.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("v_ip", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("v_fecha_hora_presentacion", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("v_id_examen", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("v_id_alumno", Integer.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("v_mensaje", String.class, ParameterMode.OUT);

            // Establecer los valores de los parámetros de entrada
            storedProcedure.setParameter("v_tiempo", tiempo);
            storedProcedure.setParameter("v_terminado", "N"); // Establece el valor predeterminado para 'terminado'
            storedProcedure.setParameter("v_ip", ip_source);
            storedProcedure.setParameter("v_fecha_hora_presentacion", fecha_hora_presentacion);
            storedProcedure.setParameter("v_id_examen", id_examen);
            storedProcedure.setParameter("v_id_alumno", id_alumno);

            // Ejecutar el procedimiento almacenado
            storedProcedure.execute();

            // Obtener el valor del parámetro de salida
            String mensaje = (String) storedProcedure.getOutputParameterValue("v_mensaje");

            // Retornar el mensaje
            return mensaje;
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
    public CursoDTO obtenerCursos(String id, String rol) {

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
        System.out.println(json1);
        JsonParser parser = new JsonParser();

        // Obtain Array
        JsonArray gsonArr = parser.parse(json1).getAsJsonArray();
        CursoDTO curso = null;
        // for each element of array
        for (JsonElement obj : gsonArr) {

            // Object of array
            JsonObject gsonObj = obj.getAsJsonObject();
            String id_grupo = gsonObj.get("id_grupo").getAsString();
            String nombre_grupo = gsonObj.get("nombre_grupo").getAsString();
            String nombre_curso = gsonObj.get("nombre_curso").getAsString();

            curso = new CursoDTO(id_grupo, nombre_grupo, nombre_curso);


        }

        return curso;
    }


}
