package controllers.relatorio.dto;

public record ContaPagarVencidaResponse(
        Long id,
        String fornecedor,
        String descricao,
        String dataEmissao,
        String dataVencimento,
        long diasEmAtraso,
        double valor,
        String status
) {}
