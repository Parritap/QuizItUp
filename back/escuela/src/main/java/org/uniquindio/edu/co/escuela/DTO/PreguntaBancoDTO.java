package org.uniquindio.edu.co.escuela.DTO;

public record PreguntaBancoDTO(

        Long id_pregunta,
        String enunciado,
        Character es_publica,
        String tipo_pregunta,
        Long id_tema,
        Long id_docente

) {
}
