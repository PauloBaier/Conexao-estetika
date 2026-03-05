package org.conexaoestetika;

import java.util.List;

public class Sistema {
    private Cadastros<Cliente> cadastroClientes = new Cadastros<Cliente>();
    private Cadastros<Fornecedor> cadastroFornecedores = new Cadastros<Fornecedor>();
    private Cadastros<Produto> cadastroProdutos = new Cadastros<Produto>();


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

    public boolean novoCadastroProduto(String nome, String descricao, double precoCusto, double precoVenda, int qntdEstoque, Fornecedor fornecedor){
        try{
            Produto novoProduto = new Produto(
                    cadastroProdutos.getUltimoId() + 1,
                    nome,
                    descricao,
                    precoCusto,
                    precoVenda,
                    qntdEstoque,
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

    public List<Cliente> listaClientes(){
        return cadastroClientes.listarTodos();
    }

    public List<Fornecedor> listaFornecedores(){
        return cadastroFornecedores.listarTodos();
    }

    public List<Produto> listaProdutos(){
        return cadastroProdutos.listarTodos();
    }

    public boolean removerCliente(int id){
        try{
            cadastroClientes.remover(id);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean removerFornecedor(int id){
        try{
            cadastroFornecedores.remover(id);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean removerProduto(int id){
        try{
            cadastroProdutos.remover(id);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }


    public int getUltimoId(){
        return cadastroClientes.getUltimoId();
    }

    public List<Fornecedor> listarFornecedores(){
        return cadastroFornecedores.listarTodos();
    }
}
