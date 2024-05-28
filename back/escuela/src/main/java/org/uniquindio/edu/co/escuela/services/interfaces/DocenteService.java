package org.uniquindio.edu.co.escuela.services.interfaces;

import org.uniquindio.edu.co.escuela.DTO.CursoDTO;
import org.uniquindio.edu.co.escuela.DTO.ExamenDTO;
import org.uniquindio.edu.co.escuela.DTO.PreguntaBancoDTO;

import java.util.Date;
import java.util.List;

public interface DocenteService {



    List<PreguntaBancoDTO> obtenerBancoPreguntas (Long id_tema);

    String crearRespuesta (String descripcion, Character esVerdadera, Long id_pregunta );

    String crearExamen (Integer tiempo_max, Integer numero_preguntas,
                        Integer porcentajeCurso, String nombre, Integer porcentaje_aprobatorio,
                        Date fecha_hora_inicio, Date fecha_hora_fin, Integer num_preguntas_aleatorias,
                        Integer id_tema, Integer id_docente, Integer id_grupo);

    String crearPregunta (String enunciado, Character es_publica, String tipoPregunta,
                          Integer id_tema, Integer id_docente);


    String calificarExamen (Long id_presentacion_examen);

    List <PreguntaBancoDTO> obtenerPreguntasDocente(Long id_docente);

    List <ExamenDTO> obtenerExamenesDocente (Long id_docente);

    String obtenerNombre(String id, String rol);

    List<CursoDTO> obtenerCursos(String id, String rol);


}
