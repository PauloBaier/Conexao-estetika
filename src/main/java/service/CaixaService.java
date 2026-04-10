package service;

import model.Caixa;
import model.enums.StatusCaixa;
import repository.CaixaRepository;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

public class CaixaService {

    private CaixaRepository repository;

    public CaixaService(CaixaRepository repository) {
        this.repository = repository;
    }

    public void abrirCaixa(Caixa caixa) {

        Caixa existente = repository.listarTodos()
                .stream()
                .findFirst()
                .orElse(null);

        if (existente != null && existente.getStatus() == StatusCaixa.ABERTO) {
            throw new IllegalArgumentException("Caixa já está aberto!");
        }

        if (caixa.getValorAbertura() < 0) {
            throw new IllegalArgumentException("Valor de abertura inválido!");
        }

        caixa.setDataAbertura(LocalDate.now());
        caixa.setStatus(StatusCaixa.ABERTO);

        repository.salvar(caixa);
    }

    public void fecharCaixa(Caixa caixa) {

        if (caixa == null) {
            throw new IllegalArgumentException("Caixa não encontrado!");
        }

        if (caixa.getStatus() != StatusCaixa.ABERTO) {
            throw new IllegalArgumentException("Caixa já está fechado!");
        }

        caixa.setDataFechamento(LocalDate.now());
        caixa.setStatus(StatusCaixa.FECHADO);

        repository.atualizar(caixa);
    }


    public Caixa buscar(Long id) {
        return repository.buscarPorId(id);
    }

    public List<Caixa> listar() {
        return repository.listarTodos();
    }

    public void atualizar(Caixa caixa){
        repository.atualizar(caixa);
    }

    public void deletar (Long id){
        repository.deletar(id);
    }
}