package org.conexaoestetika;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cadastros <T extends IIdentificador>{
    private List<T> listaCadastros = new ArrayList<>();
    private int ultimoId = 1;

    public void adicionar(T cadastro){
        if(cadastro == null){
            throw new IllegalArgumentException("ERRO: cadastro não pode ser NULL");
        }
        this.listaCadastros.add(cadastro);
    }

    public void remover(int id){
        this.listaCadastros.remove(buscarId(id));
    }

    public T buscarId(int id){
        Optional<T> resultado = this.listaCadastros.stream().filter(c -> c.getId() == id).findFirst();
        if(resultado.isEmpty()){
            throw new RuntimeException("ERRO: Cadastro não encontrado!");
        }
        return resultado.get();
    }

    public List<T> listarTodos(){
        return this.listaCadastros;
    }

    public int getUltimoId(){
        return this.ultimoId + 1;
    }
}
