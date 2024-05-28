package org.uniquindio.edu.co.escuela.services.interfaces;

import org.uniquindio.edu.co.escuela.DTO.CursoDTO;
import org.uniquindio.edu.co.escuela.DTO.ExamenHechoDTO;
import org.uniquindio.edu.co.escuela.DTO.ExamenPendienteDTO;
import org.uniquindio.edu.co.escuela.DTO.PreguntaDTO;

import java.util.Date;
import java.util.List;


public interface AlumnoService {

    String guardarPregunta(PreguntaDTO preguntaDTO);



    Float obtenerNotaPresentacionExamen (Long id_presentacion_examen);

    String obtenerNombre(String id, String rol);

    String crearPresentacionExamen (Integer tiempo, Character terminado, String ip_source,
                                    Date fecha_hora_presentacion, Integer id_examen, Integer id_alumno );

    List<CursoDTO> obtenerCursos(String id, String rol);

    List<ExamenPendienteDTO> obtenerExamenesPendiente(String id, Integer idGrupo);

    List<ExamenHechoDTO> obtenerExamenesHechos(String id, Integer idGrupo);
}
