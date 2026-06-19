package controllers.relatorio.dto;

public record ContaPagarRelatorioResponse(
        Long id,
        String fornecedor,
        String descricao,
        String tipoDespesas,
        String dataEmissao,
        String dataVencimento,
        double valor,
        String status
) {}
