package conexao;

import java.util.Scanner;

import models.Usuario;
import repositories.CaixaRepository;
import repositories.ContaPagarRepository;
import repositories.ContaReceberRepository;
import repositories.MovimentacaoCaixaRepository;
import services.*;
import view.MenuPrincipalView;

public class Main {


    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Usuario usuarioLogado = new Usuario();
        ContaPagarService contaPagarService = new ContaPagarService(new ContaPagarRepository());
        ContaReceberService contaReceberService = new ContaReceberService(new ContaReceberRepository());
        MovimentacaoCaixaService movimentacaoCaixaService = new MovimentacaoCaixaService(new MovimentacaoCaixaRepository(), new CaixaRepository());
        FinanceiroService financeiroService = new FinanceiroService(contaReceberService, contaPagarService, movimentacaoCaixaService);
        CaixaService caixaService = new CaixaService(new CaixaRepository());

        System.setProperty("sun.java2d.uiScale", "1.0");

        //FlyWayConfig.migrate();
        MenuPrincipalView menu = new MenuPrincipalView(usuarioLogado, contaPagarService, contaReceberService, financeiroService, caixaService);
        menu.setVisible(true);
    }
}