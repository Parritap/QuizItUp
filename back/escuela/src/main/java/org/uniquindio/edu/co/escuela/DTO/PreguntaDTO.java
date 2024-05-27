package org.uniquindio.edu.co.escuela.DTO;

public record PreguntaDTO(
        String enunciado,
        Character es_publica,
        String tipo_pregunta,
        Integer id_tema,
        Integer id_docente
) {

}
