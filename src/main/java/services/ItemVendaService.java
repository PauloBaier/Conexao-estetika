package services;

import models.ItemVenda;
import models.Produto;
import models.Venda;
import models.enums.StatusVenda;
import repositories.ItemVendaRepository;
import repositories.VendaRepository;

import java.util.List;

public class ItemVendaService {

    private final ItemVendaRepository itemVendaRepository;
    private final ProdutoService produtoService;
    private final VendaRepository vendaRepository;

    public ItemVendaService(ItemVendaRepository itemVendaRepository,
                            ProdutoService produtoService,
                            VendaRepository vendaRepository) {
        this.itemVendaRepository = itemVendaRepository;
        this.produtoService = produtoService;
        this.vendaRepository = vendaRepository;
    }

    public void cadastrar(ItemVenda item) {
        validarItem(item);

        Produto produto = produtoService.buscarPorId(item.getProduto().getId());

        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        if (produto.getQuantidadeEstoque() < item.getQuantidade()) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }

        produto.setQuantidadeEstoque(
                produto.getQuantidadeEstoque() - item.getQuantidade()
        );

        produtoService.atualizar(produto);

        item.setProduto(produto);
        item.setTotalItem(item.getPrecoUnitario() * item.getQuantidade());

        itemVendaRepository.salvar(item);
    }

    public ItemVenda buscar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return itemVendaRepository.buscarPorId(id);
    }

    public List<ItemVenda> listar() {
        return itemVendaRepository.listarTodos();
    }

    public void atualizar(ItemVenda item) {
        if (item == null || item.getId() == null) {
            throw new IllegalArgumentException("Item inválido.");
        }

        ItemVenda antigo = itemVendaRepository.buscarPorId(item.getId());

        if (antigo == null) {
            throw new IllegalArgumentException("Item de venda não encontrado.");
        }

        validarItem(item);

        Produto produto = produtoService.buscarPorId(item.getProduto().getId());

        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        int diferenca = item.getQuantidade() - antigo.getQuantidade();

        if (diferenca > 0) {
            if (produto.getQuantidadeEstoque() < diferenca) {
                throw new IllegalArgumentException("Estoque insuficiente.");
            }

            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - diferenca);

        } else if (diferenca < 0) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + Math.abs(diferenca));
        }

        produtoService.atualizar(produto);

        item.setProduto(produto);
        item.setTotalItem(item.getPrecoUnitario() * item.getQuantidade());

        itemVendaRepository.atualizar(item);
    }

    public void deletar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        ItemVenda item = itemVendaRepository.buscarPorId(id);

        if (item == null) {
            throw new IllegalArgumentException("Item de venda não encontrado.");
        }

        Produto produto = produtoService.buscarPorId(item.getProduto().getId());

        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + item.getQuantidade());
        produtoService.atualizar(produto);

        itemVendaRepository.deletar(id);
    }

    public void cancelarVenda(Long vendaId) {
        if (vendaRepository == null) {
            throw new IllegalStateException("VendaRepository não foi configurado neste service.");
        }

        if (vendaId == null || vendaId <= 0) {
            throw new IllegalArgumentException("ID da venda inválido.");
        }

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
            Produto produto = produtoService.buscarPorId(item.getProduto().getId());

            if (produto != null) {
                produto.setQuantidadeEstoque(
                        produto.getQuantidadeEstoque() + item.getQuantidade()
                );
                produtoService.atualizar(produto);
            }
        }

        venda.setStatus(StatusVenda.CANCELADO);
        vendaRepository.atualizar(venda);
    }

    private void validarItem(ItemVenda item) {
        if (item == null) {
            throw new IllegalArgumentException("Item inválido.");
        }

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
    }
}