package controllers.relatorio.dto;

public record ContaReceberRelatorioResponse(
        Long id,
        String cliente,
        String descricao,
        String dataEmissao,
        String dataVencimento,
        String dataPagamento,
        double valor,
        String status
) {}
