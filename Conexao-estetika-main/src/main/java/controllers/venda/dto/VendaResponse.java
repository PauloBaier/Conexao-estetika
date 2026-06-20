package controllers.venda.dto;

public record VendaResponse(
        Long id,
        String status,
        String formaPagamento,
        double valorTotal
) {}