package conexao;

import Config.FlyWayConfig;
import repositories.*;
import services.*;
import View.LoginView;
import View.MenuPrincipalView;

public class Main {

    public static void main(String[] args) {

        VendaRepository vendaRepository = new VendaRepository();
        UsuarioService usuarioService = new UsuarioService(new UsuarioRepository());
        ProdutoService produtoService = new ProdutoService(new ProdutoRepository());
        ContaPagarService contaPagarService = new ContaPagarService(new ContaPagarRepository());
        ContaReceberService contaReceberService = new ContaReceberService(new ContaReceberRepository());
        MovimentacaoCaixaService movimentacaoCaixaService = new MovimentacaoCaixaService(new MovimentacaoCaixaRepository(), new CaixaRepository());
        CaixaService caixaService = new CaixaService(new CaixaRepository());
        FinanceiroService financeiroService= new FinanceiroService(contaReceberService, contaPagarService, movimentacaoCaixaService, caixaService);
        ItemVendaService itemVendaService = new ItemVendaService(new ItemVendaRepository(), produtoService, vendaRepository);
        VendaService vendaService = new VendaService(vendaRepository, caixaService, itemVendaService, contaReceberService, movimentacaoCaixaService, usuarioService, produtoService);
        ClienteService clienteService = new ClienteService(new ClienteRepository());
        EnderecoService enderecoService = new EnderecoService(new EnderecoRepository(), new ClienteRepository());
        FornecedorService fornecedorService = new FornecedorService(new FornecedorRepository());
        CategoriaService categoriaService = new CategoriaService(new CategoriaRepository());
        EntradaEstoqueService entradaEstoqueService = new EntradaEstoqueService(produtoService, contaPagarService);
        RelatorioLocal relatorioLocal = new RelatorioLocal();

        System.setProperty("sun.java2d.uiScale", "1.0");
        FlyWayConfig.migrate();

        // ── Login ────────────────────────────────────────────────────────────
        new LoginView(usuarioService, usuario -> {
            MenuPrincipalView menu = new MenuPrincipalView(
                    usuario,
                    contaPagarService,
                    contaReceberService,
                    financeiroService,
                    caixaService,
                    vendaService,
                    clienteService,
                    produtoService,
                    usuarioService,
                    movimentacaoCaixaService,
                    enderecoService,
                    fornecedorService,
                    categoriaService,
                    entradaEstoqueService,
                    relatorioLocal
            );
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
        });
    }
}