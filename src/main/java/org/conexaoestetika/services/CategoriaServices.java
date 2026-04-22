package org.conexaoestetika.services;

import jakarta.persistence.EntityManager;
import org.conexaoestetika.models.Categoria;
import org.conexaoestetika.repositories.CategoriaRepository;
import org.conexaoestetika.repositories.IRepository;

import java.util.List;

public class CategoriaServices {
    CategoriaRepository categoria;
    private Object repository;

    public CategoriaServices(CategoriaRepository categoria) {
        this.categoria = categoria;
    }

    public void cadastrar(Categoria categoria){

        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {

            throw new IllegalArgumentException("Nome da categoria não pode ser vazio");
        }
        if(categoria.getNome() == null || categoria.getNome().trim().length() < 3 ){
            throw new IllegalArgumentException("Não pode ter menos de 3 caracteres");
        }
        if (categoria.getNome() == null || categoria.getNome().trim().length() > 100){
            throw new IllegalArgumentException("Não pode ser mais de 100 Caracteres ");
        }

        this.categoria.salvar(categoria);

    }
    public List<Categoria> listarTodas(){
        return this.categoria.listarTodos();
    }

    public void deletar(long id){
        if(id <= 0 )
            throw new IllegalArgumentException("Id invalido");

        Categoria existente = this.categoria.buscarPorId(id);

        if(existente == null){
            throw new RuntimeException("Categoria com ID:" + id + "não encontrado");

        }
    }

    public void atualizar(long id, Categoria categoriaAtualizada) {
        if (id <= 0)
            throw new IllegalArgumentException("ID inválido.");

        Categoria existente = this.categoria.buscarPorId(id);

        if (existente == null)
            throw new RuntimeException("Categoria com ID " + id + " não encontrada.");

        if (categoriaAtualizada.getNome() == null || categoriaAtualizada.getNome().trim().isEmpty())
            throw new IllegalArgumentException("Nome não pode ser vazio.");

        if (categoriaAtualizada.getNome().trim().length() < 3)
            throw new IllegalArgumentException("Nome não pode ter menos de 3 caracteres.");

        if (categoriaAtualizada.getNome().trim().length() > 100)
            throw new IllegalArgumentException("Nome não pode ter mais de 100 caracteres.");

        existente.setNome(categoriaAtualizada.getNome().trim());
        existente.setDescricao(categoriaAtualizada.getDescricao());

        this.categoria.atualizar(existente);
    }
}

