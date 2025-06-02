package br.edu.upe.huocbackend.controller.handler;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp,
                            String message,
                            String path) {
}
