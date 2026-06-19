package controllers.financeiro.dto;

import models.ContaReceber;

public record ContaReceberResponse(
        Long id,
        String cliente,
        String descricao,
        String dataEmissao,
        String dataVencimento,
        double valor,
        String status
) {
    public static ContaReceberResponse of(ContaReceber c, java.time.format.DateTimeFormatter fmt) {
        return new ContaReceberResponse(
                c.getId(),
                c.getCliente() != null ? c.getCliente().getNome() : "Sem cliente",
                c.getDescricao(),
                c.getDataEmissao()    != null ? c.getDataEmissao().format(fmt)    : "-",
                c.getDataVencimento() != null ? c.getDataVencimento().format(fmt) : "-",
                c.getValor(),
                c.getStatus().name()
        );
    }
}