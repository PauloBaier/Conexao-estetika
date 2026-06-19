package controllers.cadastro;

import controllers.cadastro.dto.*;
import models.*;

import java.util.List;

public interface CadastroController {

    // Clientes
    List<ClienteResponse> listarClientes();
    Cliente buscarClientePorId(Long id);
    void cadastrarCliente(Cliente cliente, Endereco endereco);
    void atualizarCliente(Cliente cliente);
    void deletarCliente(Long id);

    // Fornecedores
    List<FornecedorResponse> listarFornecedores();
    Fornecedor buscarFornecedorPorId(Long id);
    void salvarFornecedor(Fornecedor fornecedor);
    void atualizarFornecedor(Fornecedor fornecedor);
    void deletarFornecedor(Fornecedor fornecedor);

    // Produtos
    List<ProdutoResponse> listarProdutos();
    Produto buscarProdutoPorId(Long id);
    void cadastrarProduto(Produto produto);
    void atualizarProduto(Produto produto, String nome, String precoCompra, String precoVenda,
                          String estoque, String estoqueMinimo, Categoria categoria, Fornecedor fornecedor);
    void deletarProduto(Long id);
    List<Categoria> listarCategorias();
    List<Fornecedor> listarTodosFornecedores();

    // Categorias
    List<CategoriaResponse> listarTodasCategorias();
    void cadastrarCategoria(Categoria categoria);
    void atualizarCategoria(Long id, Categoria categoria);
    void deletarCategoria(Long id);
}