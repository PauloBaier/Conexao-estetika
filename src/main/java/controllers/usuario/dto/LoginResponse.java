package controllers.usuario.dto;

public record LoginResponse(
        Long id,
        String nome,
        String email,
        String perfil
) {}
