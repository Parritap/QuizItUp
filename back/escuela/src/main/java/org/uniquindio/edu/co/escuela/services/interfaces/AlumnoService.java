package org.uniquindio.edu.co.escuela.services.interfaces;

import org.uniquindio.edu.co.escuela.DTO.CursoDTO;
import org.uniquindio.edu.co.escuela.DTO.PreguntaDTO;

import java.util.Date;


public interface AlumnoService {

    String guardarPregunta(PreguntaDTO preguntaDTO);



    Float obtenerNotaPresentacionExamen (Long id_presentacion_examen);

    String obtenerNombre(String id, String rol);

    String crearPresentacionExamen (Integer tiempo, Character terminado, String ip_source,
                                    Date fecha_hora_presentacion, Integer id_examen, Integer id_alumno );

    CursoDTO obtenerCursos(String id, String rol);
}
