package controllers.cadastro.dto;

public record ClienteResponse(
        Long id,
        String nome,
        String telefone,
        String email,
        String cpf
) {}
