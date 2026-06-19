package controllers.cadastro.impl;

import controllers.cadastro.CadastroController;
import controllers.cadastro.dto.*;
import models.*;
import services.*;

import java.util.List;

public class CadastroControllerImpl implements CadastroController {

    private final ClienteService    clienteService;
    private final EnderecoService   enderecoService;
    private final FornecedorService fornecedorService;
    private final ProdutoService    produtoService;
    private final CategoriaService  categoriaService;

    public CadastroControllerImpl(
            ClienteService    clienteService,
            EnderecoService   enderecoService,
            FornecedorService fornecedorService,
            ProdutoService    produtoService,
            CategoriaService  categoriaService
    ) {
        this.clienteService    = clienteService;
        this.enderecoService   = enderecoService;
        this.fornecedorService = fornecedorService;
        this.produtoService    = produtoService;
        this.categoriaService  = categoriaService;
    }

    // Clientes

    @Override
    public List<ClienteResponse> listarClientes() {
        return clienteService.listarTodos().stream()
                .map(c -> new ClienteResponse(c.getId(), c.getNome(), c.getTelefone(), c.getEmail(), c.getCpf()))
                .toList();
    }

    @Override
    public Cliente buscarClientePorId(Long id) {
        return clienteService.buscarPorId(id);
    }

    @Override
    public void cadastrarCliente(Cliente cliente, Endereco endereco) {
        clienteService.cadastrar(cliente);
        enderecoService.cadastrarEndereco(endereco, cliente.getId());
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        clienteService.atualizar(cliente);
    }

    @Override
    public void deletarCliente(Long id) {
        clienteService.deletar(id);
    }

    // Fornecedores

    @Override
    public List<FornecedorResponse> listarFornecedores() {
        return fornecedorService.listarTodos().stream()
                .map(f -> new FornecedorResponse(f.getId(), f.getNome(), f.getTelefone(), f.getEmail(), f.getCnpj(), f.getRazaoSocial()))
                .toList();
    }

    @Override
    public Fornecedor buscarFornecedorPorId(Long id) {
        return fornecedorService.buscarPorId(id);
    }

    @Override
    public void salvarFornecedor(Fornecedor fornecedor) {
        fornecedorService.salvar(fornecedor);
    }

    @Override
    public void atualizarFornecedor(Fornecedor fornecedor) {
        fornecedorService.atualizar(fornecedor);
    }

    @Override
    public void deletarFornecedor(Fornecedor fornecedor) {
        fornecedorService.delete(fornecedor);
    }

    // Produtos

    @Override
    public List<ProdutoResponse> listarProdutos() {
        return produtoService.listarTodos().stream()
                .map(p -> new ProdutoResponse(
                        p.getId(), p.getNome(), p.getPrecoCompra(), p.getPrecoVenda(),
                        p.getQuantidadeEstoque(), p.getEstoqueMinimo(),
                        p.getCategoria() != null ? p.getCategoria().getNome() : "-"
                ))
                .toList();
    }

    @Override
    public Produto buscarProdutoPorId(Long id) {
        return produtoService.buscarPorId(id);
    }

    @Override
    public void cadastrarProduto(Produto produto) {
        produtoService.salvar(produto);
    }

    @Override
    public void atualizarProduto(Produto produto, String nome, String precoCompra, String precoVenda,
                                  String estoque, String estoqueMinimo, Categoria categoria, Fornecedor fornecedor) {
        produtoService.atualizarDoFormulario(produto, nome, precoCompra, precoVenda, estoque, estoqueMinimo, categoria, fornecedor);
    }

    @Override
    public void deletarProduto(Long id) {
        Produto p = produtoService.buscarPorId(id);
        produtoService.delete(p);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaService.listarTodas();
    }

    @Override
    public List<Fornecedor> listarTodosFornecedores() {
        return fornecedorService.listarTodos();
    }

    // Categorias

    @Override
    public List<CategoriaResponse> listarTodasCategorias() {
        return categoriaService.listarTodas().stream()
                .map(c -> new CategoriaResponse(c.getId(), c.getNome()))
                .toList();
    }

    @Override
    public void cadastrarCategoria(Categoria categoria) {
        categoriaService.cadastrar(categoria);
    }

    @Override
    public void atualizarCategoria(Long id, Categoria categoria) {
        categoriaService.atualizar(id, categoria);
    }

    @Override
    public void deletarCategoria(Long id) {
        categoriaService.deletar(id);
    }
}