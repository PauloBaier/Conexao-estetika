package controllers.relatorio.dto;

public record ProdutoRelatorioResponse(
        Long id,
        String nome,
        String categoria,
        double precoCompra,
        double precoVenda,
        int quantidadeEstoque,
        int estoqueMinimo
) {}
