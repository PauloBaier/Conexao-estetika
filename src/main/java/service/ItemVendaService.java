package service;

import model.ItemVenda;
import model.Produto;
import repository.ItemVendaRepository;
import model.enums.StatusVenda;
import model.Venda;
import repository.VendaRepository;
import jakarta.persistence.*;


import java.util.List;

public class ItemVendaService {

    private ItemVendaRepository itemVendaRepository;
    private ProdutoService produtoService;
    private VendaRepository vendaRepository;

    public ItemVendaService(ItemVendaRepository itemVendaRepository, ProdutoService produtoService, VendaRepository vendaRepository) {
        this.itemVendaRepository = itemVendaRepository;
        this.produtoService = produtoService;
        this.vendaRepository = vendaRepository;
    }

    public ItemVendaService(ItemVendaRepository itemVendaRepository, ProdutoService produtoService) {
        this.itemVendaRepository = itemVendaRepository;
        this.produtoService = produtoService;
    }

    public void cadastrar(ItemVenda item) {

        if (item.getProduto() == null) {
            throw new IllegalArgumentException("O item precisa ter um produto associado.");
        }

        if (item.getVenda() == null) {
            throw new IllegalArgumentException("O item precisa ter uma venda associada.");
        }

        if (item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        if (item.getPrecoUnitario() <= 0) {
            throw new IllegalArgumentException("O preço unitário deve ser maior que zero.");
        }

        if (item.getProduto().getQuantidadeEstoque() < item.getQuantidade()) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }

        item.getProduto().setQuantidadeEstoque(
                item.getProduto().getQuantidadeEstoque() - item.getQuantidade()
        );

        produtoService.atualizar(item.getProduto());

        item.setTotalItem(item.getPrecoUnitario() * item.getQuantidade());
        itemVendaRepository.salvar(item);
    }

    public ItemVenda buscar(Long id) {
        return itemVendaRepository.buscarPorId(id);
    }

    public List<ItemVenda> listar() {
        return itemVendaRepository.listarTodos();
    }

    public void atualizar(ItemVenda item) {

        ItemVenda antigo = itemVendaRepository.buscarPorId(item.getId());
        int diferenca = item.getQuantidade() - antigo.getQuantidade();

        if (item.getProduto() == null) {
            throw new IllegalArgumentException("O item precisa ter um produto associado.");
        }

        if (item.getVenda() == null) {
            throw new IllegalArgumentException("O item precisa ter uma venda associada.");
        }

        if (item.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        if (item.getPrecoUnitario() <= 0) {
            throw new IllegalArgumentException("O preço unitário deve ser maior que zero.");
        }

        if (diferenca > 0) {
            if (item.getProduto().getQuantidadeEstoque() < diferenca) {
                throw new IllegalArgumentException("Estoque insuficiente.");
            }

            item.getProduto().setQuantidadeEstoque(
                    item.getProduto().getQuantidadeEstoque() - diferenca
            );

        } else if (diferenca < 0) {
            item.getProduto().setQuantidadeEstoque(
                    item.getProduto().getQuantidadeEstoque() + Math.abs(diferenca)
            );
        }

        produtoService.atualizar(item.getProduto());

        item.setTotalItem(item.getPrecoUnitario() * item.getQuantidade());
        itemVendaRepository.atualizar(item);
    }

    public void deletar(Long id) {

        ItemVenda item = itemVendaRepository.buscarPorId(id);
        item.getProduto().setQuantidadeEstoque(item.getProduto().getQuantidadeEstoque() + item.getQuantidade());

        produtoService.atualizar(item.getProduto());
        itemVendaRepository.deletar(id);
    }

    public void cancelarVenda(Long vendaId) {

        Venda venda = vendaRepository.buscarPorId(vendaId);

        if (venda == null) {
            throw new IllegalArgumentException("Venda não encontrada.");
        }

        if (venda.getStatus() == StatusVenda.CANCELADO) {
            throw new IllegalArgumentException("Venda já está cancelada.");
        }

        if (venda.getStatus() == StatusVenda.PAGO) {
            throw new IllegalArgumentException("Não é possível cancelar uma venda finalizada.");
        }

        List<ItemVenda> itens = itemVendaRepository.buscarPorVenda(vendaId);

        for (ItemVenda item : itens) {

            Produto produto = item.getProduto();

            produto.setQuantidadeEstoque(
                    produto.getQuantidadeEstoque() + item.getQuantidade()
            );

            produtoService.atualizar(produto);
        }

        venda.setStatus(StatusVenda.CANCELADO);
        vendaRepository.atualizar(venda);
    }
}

