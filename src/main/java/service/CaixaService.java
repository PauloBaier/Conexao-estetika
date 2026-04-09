package service;

import model.Caixa;
import model.enums.StatusCaixa;
import repository.CaixaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class CaixaService {

    private CaixaRepository repository;

    public CaixaService(CaixaRepository repository) {
        this.repository = repository;
    }

    public void abrirCaixa(Caixa caixa){

        if(caixa.getValorAbertura() < 0){
            throw new IllegalArgumentException("Valor de abertura invalido!");
        }

        caixa.setDataAbertura(LocalDateTime.now());
        caixa.setStatus(StatusCaixa.ABERTO);

        repository.salvar(caixa);
    }

    public void fecharCaixa(Caixa caixa){

        if(caixa.getStatus() != StatusCaixa.ABERTO){
            throw new IllegalArgumentException("Status de abertura invalido!");
        }

        if (caixa.getDataAbertura() == null){
            throw new IllegalArgumentException("Data de abertura invalida!");
        }

        LocalDateTime agora = LocalDateTime.now();

        if(agora.isBefore(caixa.getDataAbertura())){
            throw new IllegalArgumentException("Data de abertura invalida!");
            
        }
        caixa.setDataFechamento(agora);
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

