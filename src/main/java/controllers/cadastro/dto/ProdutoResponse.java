package controllers.cadastro.dto;

public record ProdutoResponse(
        Long id,
        String nome,
        double precoCompra,
        double precoVenda,
        int quantidadeEstoque,
        int estoqueMinimo,
        String categoria
) {}
