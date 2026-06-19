package services;

import models.Categoria;
import repositories.CategoriaRepository;

import java.util.List;

public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public void cadastrar(Categoria categoria) {
        validarCategoria(categoria);
        categoriaRepository.salvar(categoria);
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.listarTodos();
    }

    public Categoria buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return categoriaRepository.buscarPorId(id);
    }

    public void atualizar(Long id, Categoria categoriaAtualizada) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Categoria existente = categoriaRepository.buscarPorId(id);

        if (existente == null) {
            throw new RuntimeException("Categoria com ID " + id + " não encontrada.");
        }

        validarCategoria(categoriaAtualizada);

        existente.setNome(categoriaAtualizada.getNome().trim());
        categoriaRepository.atualizar(existente);
    }

    public void deletar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Categoria existente = categoriaRepository.buscarPorId(id);

        if (existente == null) {
            throw new RuntimeException("Categoria com ID " + id + " não encontrada.");
        }

        categoriaRepository.deletar(id);
    }

    private void validarCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria inválida.");
        }

        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da categoria não pode ser vazio.");
        }

        if (categoria.getNome().trim().length() < 3) {
            throw new IllegalArgumentException("Nome não pode ter menos de 3 caracteres.");
        }

        if (categoria.getNome().trim().length() > 100) {
            throw new IllegalArgumentException("Nome não pode ter mais de 100 caracteres.");
        }
    }
}