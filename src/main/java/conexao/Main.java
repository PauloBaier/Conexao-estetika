package conexao;

import java.util.Scanner;

import Config.FlyWayConfig;
import models.Usuario;
import repositories.*;
import services.*;
import View.MenuPrincipalView;

public class Main {


    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        VendaRepository vendaRepository = new VendaRepository();

        Usuario usuarioLogado;

        UsuarioService usuarioService = new UsuarioService(new UsuarioRepository());
        ProdutoService produtoService = new ProdutoService(new ProdutoRepository());
        ContaPagarService contaPagarService = new ContaPagarService(new ContaPagarRepository());
        ContaReceberService contaReceberService = new ContaReceberService(new ContaReceberRepository());
        MovimentacaoCaixaService movimentacaoCaixaService = new MovimentacaoCaixaService(new MovimentacaoCaixaRepository(), new CaixaRepository());
        FinanceiroService financeiroService = new FinanceiroService(contaReceberService, contaPagarService, movimentacaoCaixaService);
        CaixaService caixaService = new CaixaService(new CaixaRepository());
        ItemVendaService itemVendaService = new ItemVendaService(new ItemVendaRepository(), produtoService, vendaRepository);
        VendaService vendaService = new VendaService(vendaRepository, caixaService, itemVendaService, contaReceberService, movimentacaoCaixaService, usuarioService);
        ClienteService clienteService = new ClienteService(new ClienteRepository());


        // Apenas para teste Excluir essa Linha quando Login Estiver implementado
        usuarioLogado = usuarioService.buscarPorId(2L);

        System.setProperty("sun.java2d.uiScale", "1.0");

        FlyWayConfig.migrate();

        //PedirLogin Antes de Abrir o programa

            MenuPrincipalView menu = new MenuPrincipalView(usuarioLogado,
                                                        contaPagarService,
                                                        contaReceberService,
                                                        financeiroService,
                                                        caixaService,
                                                        vendaService,
                                                        clienteService,
                                                        produtoService,
                                                        usuarioService
        );
        menu.setVisible(true);
    }
}