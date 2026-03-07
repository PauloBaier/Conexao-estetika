package org.conexaoestetika;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    public Cadastros<Cliente> cadastroClientes = new Cadastros<Cliente>();
    public Cadastros<Fornecedor> cadastroFornecedores = new Cadastros<Fornecedor>();
    public Cadastros<Produto> cadastroProdutos = new Cadastros<Produto>();

    public Financeiro financeiro = new Financeiro();

    public Relatorios relatorios = new Relatorios(financeiro, cadastroProdutos);

    private Venda vendaAtual = null;
    private int proximoIdVenda = 1;


    public boolean novoCadastroCliente(String nome, String telefone, String email, String cpf){
        try{
            Cliente novoCliente = new Cliente(
                    cadastroClientes.getUltimoId() + 1,
                    nome,
                    telefone,
                    email,
                    cpf
                    );

            cadastroClientes.adicionar(novoCliente);
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean novoCadastroFornecedor(String nome, String telefone, String email, String cnpj, String razaoSocial){
        try{
            Fornecedor novoFornecedor = new Fornecedor(
                    cadastroFornecedores.getUltimoId() + 1,
                    nome,
                    telefone,
                    email,
                    cnpj,
                    razaoSocial
            );

            cadastroFornecedores.adicionar(novoFornecedor);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean novoCadastroProduto(String nome, String descricao, double precoCusto, double precoVenda, int qntdEstoque,int estoqueMin, Fornecedor fornecedor){
        try{
            Produto novoProduto = new Produto(
                    cadastroProdutos.getUltimoId() + 1,
                    nome,
                    descricao,
                    precoCusto,
                    precoVenda,
                    qntdEstoque,
                    estoqueMin,
                    fornecedor
            );

            cadastroProdutos.adicionar(novoProduto);
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean novaVenda(Cliente cliente){
        if(vendaAtual != null){
            System.out.println("Já há uma venda em aberto!");
            return false;
        }

        try{
            vendaAtual = new Venda(proximoIdVenda, cliente);
            proximoIdVenda++;
            return true;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public int getIdProximaVenda(){
        return this.proximoIdVenda;
    }

    public boolean adicionarProdutoVenda(Produto produto, int quantidade){
        try{
            vendaAtual.adicionarItem(new ItemVenda(produto, quantidade));
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean alterarStatusVenda(StatusVenda novoStatus){
        try{
            vendaAtual.alterarStatus(novoStatus);
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public List<ItemVenda> getListaItensVendaAtual(){
        return this.vendaAtual.getItens();
    }

    public double getTotalVendaAtual(){
        return this.vendaAtual.calcularTotal();
    }

    public boolean alterarStatusVendaAtual(StatusVenda novoStatus){
        try{
            vendaAtual.alterarStatus(novoStatus);
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public List<ItemVenda> getListaVendaAtual(){
        return this.vendaAtual.getItens();
    }

    public boolean registrarVendaAtual(){
        try{
            financeiro.adicionarContaReceber(new ContaReceber(financeiro.getUltimoIdContaReceber(), vendaAtual.calcularTotal(), LocalDate.now(), vendaAtual, vendaAtual.getCliente()));
            for(ItemVenda itens: vendaAtual.getItens()){
                itens.getProduto().removerEstoque(itens.getQuantidade());
            }
            vendaAtual = null;
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }

    }

    public List<ContaReceber> listaTodasContasReceber(boolean estaPago){
        List<ContaReceber> contasFiltrado = new ArrayList<>();

        for (ContaReceber conta: financeiro.listarTodasContasReceber()){
            if(conta.estaPago() == estaPago){
                contasFiltrado.add(conta);
            }
        }

        return contasFiltrado;
    }

    public boolean receberConta(int contaId){
        try{
            financeiro.alterarStatusContaReceber(contaId, true);
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public List<ContaPagar> listaTodasContasPagar(boolean estaPago){
        List<ContaPagar> contasFiltrado = new ArrayList<>();

        for (ContaPagar conta: financeiro.listarTodasContasPagar()){
            if(conta.estaPago() == estaPago){
                contasFiltrado.add(conta);
            }
        }

        return contasFiltrado;
    }

    public List<ContaPagar> listaContaPagarVencidas(){
        LocalDateTime agora = LocalDateTime.now();
        List<ContaPagar> contasVencidas = new ArrayList<>();

        for(ContaPagar conta: financeiro.listarTodasContasPagar()){
            if(conta.getVencimento().isBefore(ChronoLocalDate.from(agora))){
                contasVencidas.add(conta);
            }
        }

        return contasVencidas;
    }

    public boolean PagarConta(int contaId){
        try{
            financeiro.alterarStatusContaPagar(contaId, true);
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean novaEntrada(double valor, List<ItemVenda> produtos, Fornecedor fornecedor, LocalDate vencimento){
        try{
            financeiro.adicionarContaPagar(new ContaPagar(financeiro.getUltimoIdContaPagar(), valor, LocalDate.now(), produtos, fornecedor, vencimento));
            produtos.forEach(p -> p.getProduto().adicionarEstoque(p.getQuantidade()));
            return true;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public List<ContaReceber> listarContasReceber(LocalDate dataInicial, LocalDate dataFinal, FiltroStatus filtro){
        return relatorios.listarContasReceber(dataInicial, dataFinal, filtro);
    }

    public List<ContaPagar> listarContasPagar(LocalDate dataInicial, LocalDate dataFinal, FiltroStatus filtro){
        return relatorios.listarContasPagar(dataInicial, dataFinal, filtro);
    }

    public List<Produtos> listarProdutosEstoqueAbaixoMin(){
        return relatorios.listarProdutosEstoqueBaixo();
    }
}
