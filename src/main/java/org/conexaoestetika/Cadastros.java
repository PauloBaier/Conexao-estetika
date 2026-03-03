package org.conexaoestetika;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cadastros <T extends IIdentificador>{
    List<T> listaCadastros = new ArrayList<>();

    public void adicionar(T cadastro){
        this.listaCadastros.add(cadastro);
    }

    public void remover(int id){
        Optional<T> resultado = this.listaCadastros.stream().filter(c -> c.getId() == id).findFirst();
        if(resultado.isEmpty()){
            throw new RuntimeException("ERRO: Cadastro não encontrado!");
        }

        this.listaCadastros.remove(resultado.get());
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
}
