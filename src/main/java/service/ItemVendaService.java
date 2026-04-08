package service;

import model.ItemVenda;
import repository.ItemVendaRepository;

import java.util.List;

public class ItemVendaService {

    private ItemVendaRepository repository = new ItemVendaRepository();

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

        item.setTotalItem(item.getPrecoUnitario() * item.getQuantidade());
        repository.salvar(item);
    }

    public ItemVenda buscar(Long id) {
        return repository.buscarPorId(id);
    }

    public List<ItemVenda> listar() {
        return repository.listarTodos();
    }

    public void atualizar(ItemVenda item) {

        if (item.getProduto() == null) {
            throw new IllegalArgumentException("O item precisa ter um produto associado.");
        }

        if (item.getVenda() == null) {
            throw new IllegalArgumentException("O item precisa ter uma venda associada.");
        }

        item.setTotalItem(item.getPrecoUnitario() * item.getQuantidade());
        repository.atualizar(item);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}