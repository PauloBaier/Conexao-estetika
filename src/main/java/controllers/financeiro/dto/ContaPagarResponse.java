package controllers.financeiro.dto;

import models.ContaPagar;

public record ContaPagarResponse(
        Long id,
        String fornecedor,
        String descricao,
        String dataEmissao,
        String dataVencimento,
        double valor,
        String status
) {
    public static ContaPagarResponse of(ContaPagar c, java.time.format.DateTimeFormatter fmt) {
        return new ContaPagarResponse(
                c.getId(),
                c.getFornecedor() != null ? c.getFornecedor().getNome() : "-",
                c.getDescricao(),
                c.getDataEmissao()    != null ? c.getDataEmissao().format(fmt)    : "-",
                c.getDataVencimento() != null ? c.getDataVencimento().format(fmt) : "-",
                c.getValor(),
                c.getStatus().name()
        );
    }
}
