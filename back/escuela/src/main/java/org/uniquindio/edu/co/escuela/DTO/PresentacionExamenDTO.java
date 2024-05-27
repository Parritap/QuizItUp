package org.uniquindio.edu.co.escuela.DTO;

import java.util.Date;

public record PresentacionExamenDTO(


        Integer tiempo,
        Character terminado,
         String ipSource,
        Date fechaHoraPresentacion,
         Integer idExamen,
        Integer idAlumno

) {
}
