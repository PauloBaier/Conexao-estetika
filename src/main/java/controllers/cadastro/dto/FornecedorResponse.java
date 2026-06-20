package controllers.cadastro.dto;

public record FornecedorResponse(
        Long id,
        String nome,
        String telefone,
        String email,
        String cnpj,
        String razaoSocial
) {}
