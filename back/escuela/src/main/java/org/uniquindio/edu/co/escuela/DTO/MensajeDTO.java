package org.uniquindio.edu.co.escuela.DTO;

public record MensajeDTO<T>(
        Boolean error,
        String mensajeError,
        T objeto
) {
}
