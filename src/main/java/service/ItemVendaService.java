package service;

import model.ItemVenda;
import repository.ItemVendaRepository;
import mode.enums.StatusVenda;


import java.util.List;

public class ItemVendaService {

    private ItemVendaRepository itemVendaRepository;
    private ProdutoService produtoService;

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

        if (item.getProduto().getEstoque() < item.getQuantidade()) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }

   
        item.getProduto().setEstoque(item.getProduto().getEstoque() - item.getQuantidade());

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
        if (item.getProduto().getEstoque() < diferenca) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }

        item.getProduto().setEstoque(
            item.getProduto().getEstoque() - diferenca
        );

    } else if (diferenca < 0) {
        item.getProduto().setEstoque(
            item.getProduto().getEstoque() + Math.abs(diferenca)
        );
    }

    produtoService.atualizar(item.getProduto());

    item.setTotalItem(item.getPrecoUnitario() * item.getQuantidade());
    itemVendaRepository.atualizar(item);
}

 public void deletar(Long id) {

        ItemVenda item = itemVendaRepository.buscarPorId(id);
        item.getProduto().setEstoque(item.getProduto().getEstoque() + item.getQuantidade());

        produtoService.atualizar(item.getProduto());
        itemVendaRepository.deletar(id);
}

public void cancelarVenda(Long vendaId) {

    Venda venda = vendaRepository.buscarPorId(vendaId);

    if (venda == null) {
        throw new IllegalArgumentException("Venda não encontrada.");
    }

    if (venda.getStatus() == StatusVenda.CANCELADA) {
        throw new IllegalArgumentException("Venda já está cancelada.");
    }

    if (venda.getStatus() == StatusVenda.FINALIZADA) {
        throw new IllegalArgumentException("Não é possível cancelar uma venda finalizada.");
    }

    List<ItemVenda> itens = itemVendaRepository.buscarPorVenda(vendaId);

    for (ItemVenda item : itens) {
        item.getProduto().setEstoque(
        item.getProduto().getEstoque() + item.getQuantidade()
    );

        produtoService.atualizar(item.getProduto());
    }

    venda.setStatus(StatusVenda.CANCELADA);
    vendaRepository.atualizar(venda);
}
}
