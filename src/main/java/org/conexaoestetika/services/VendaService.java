package org.conexaoestetika.services;

import org.conexaoestetika.repositories.CustomizerFactory;

import java.time.LocalDateTime;
import java.util.List;

public class VendaService {


    private VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public void cadastrar(Venda venda) {

        if (venda.getCliente() == null) {
            throw new IllegalArgumentException("A venda precisa ter um cliente.");
        }

        if (venda.getItens() == null || venda.getItens().isEmpty()) {
            throw new IllegalArgumentException("A venda precisa ter pelo menos um item.");
        }

        if (venda.getStatus() == null) {
            throw new IllegalArgumentException("O status da venda deve ser definido.");
        }

        if (venda.getFormaPagamento() == null) {
            throw new IllegalArgumentException("A forma de pagamento da venda deve ser definida.");
        }

        vendaRepository.salvar(venda);
    }

    public Venda buscar(Long id) {
        return vendaRepository.buscarPorId(id);
    }

    public List<Venda> listar() {
        return vendaRepository.listarTodos();
    }

    public void atualizar(Venda venda) {
        vendaRepository.atualizar(venda);
    }

    public void deletar(Long id) {
        vendaRepository.deletar(id);
    }

    public static class RelatorioService {

        private CustomizerFactory.RelatorioRepository repository;
        private ClienteRepository clienteRepo;
        private FornecedorRepository fornecedorRepo;
        private ProdutoRepository produtoRepo;
        private VendaRepository vendaRepo;

        public RelatorioService(CustomizerFactory.RelatorioRepository repository, ClienteRepository clienteRepo,
                                FornecedorRepository fornecedorRepo, ProdutoRepository produtoRepo,
                                VendaRepository vendaRepo) {
            this.repository = repository;
            this.clienteRepo = clienteRepo;
            this.fornecedorRepo = fornecedorRepo;
            this.produtoRepo = produtoRepo;
            this.vendaRepo = vendaRepo;
        }

        public void gerarRelatorioFinal(Relatorio relatorio, Caixa caixa) {
            // Validação Básica
            if (caixa == null) throw new RuntimeException("Caixa não informado!");

            // Cálculos Automáticos baseados nas Movimentações do Caixa
            double entradas = 0;
            double saidas = 0;

            if (caixa.getMovimentacoes() != null) {
                for (MovimentacaoCaixa mov : caixa.getMovimentacoes()) {
                    if (mov.getTipo() == TipoMovimento.ENTRADA) {
                        entradas += mov.getValor();
                    } else {
                        saidas += mov.getValor();
                    }
                }
            }

            relatorio.setCaixa(caixa);
            relatorio.setDataRelatorio(LocalDateTime.now());
            relatorio.setTotalEntradaCaixa(entradas);
            relatorio.setTotalSaidaCaixa(saidas);
            relatorio.setSaldoCaixa(caixa.getValorAbertura() + entradas - saidas);


            relatorio.setTotalClientes(clienteRepo.listar().size());
            relatorio.setTotalFornecedores(fornecedorRepo.listar().size());
            relatorio.setTotalProdutos(produtoRepo.listar().size());

            repository.salvar(relatorio);
        }

        public List<Relatorio> listar() {
            return repository.listar();
        }

        public void deletar(Long id) {
            repository.deletar(id);
        }
    }
}
