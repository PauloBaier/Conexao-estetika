package View;

import models.Usuario;
import services.*;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalView extends JFrame{
    public static final java.awt.Color CONTENT_BG = new java.awt.Color(238, 238, 238);
    public static final java.awt.Color WHITE = java.awt.Color.WHITE;
    public static final java.awt.Color TEXT_DARK = new java.awt.Color(33, 33, 33);
    public static final java.awt.Color TEXT_MUTED = new java.awt.Color(117, 117, 117);
    public static final java.awt.Color ACCENT_DARK = new java.awt.Color(31, 111, 95);
    public static final java.awt.Color BORDER_COLOR = new java.awt.Color(210, 210, 210);

    public static final java.awt.Font FONT_TITLE = new java.awt.Font("Arial", java.awt.Font.BOLD, 24);
    public static final java.awt.Font FONT_BTN = new java.awt.Font("Arial", java.awt.Font.BOLD, 14);
    public static final java.awt.Font FONT_SMALL = new java.awt.Font("Arial", java.awt.Font.PLAIN, 12);

    private javax.swing.JButton btnCadastros;
    private javax.swing.JButton btnFinanceiro;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JButton btnUsuario;
    private javax.swing.JButton btnVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlBarraLateral;
    private javax.swing.JPanel pnlCardsContainer;
    private java.awt.CardLayout cardContainerLayout;

    private Usuario usuarioLogado;
    private final ContaPagarService contaPagarService;
    private final ContaReceberService contaReceberService;
    private final FinanceiroService financeiroService;
    private final CaixaService caixaService;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;
    private final MovimentacaoCaixaService movimentacaoCaixaService;

    public MenuPrincipalView(Usuario usuarioLogado,
                             ContaPagarService contaPagarService,
                             ContaReceberService contaReceberService,
                             FinanceiroService financeiroService,
                             CaixaService caixaService,
                             VendaService vendaService,
                             ClienteService clienteService,
                             ProdutoService produtoService,
                             UsuarioService usuarioService,
                             MovimentacaoCaixaService movimentacaoCaixaService
                             ){

        this.usuarioLogado       = usuarioLogado;
        this.contaPagarService   = contaPagarService;
        this.contaReceberService = contaReceberService;
        this.financeiroService   = financeiroService;
        this.caixaService        = caixaService;
        this.clienteService      = clienteService;
        this.produtoService      = produtoService;
        this.usuarioService      = usuarioService;
        this.movimentacaoCaixaService = movimentacaoCaixaService;

        pnlBackground = new javax.swing.JPanel();
        pnlBarraLateral = new javax.swing.JPanel();
        btnVenda = new javax.swing.JButton();
        btnCadastros = new javax.swing.JButton();
        btnFinanceiro = new javax.swing.JButton();
        btnRelatorio = new javax.swing.JButton();
        btnUsuario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pnlCardsContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);


        pnlBackground.setBackground(new java.awt.Color(238, 238, 238));

        pnlBarraLateral.setBackground(new java.awt.Color(31, 111, 95));

        btnVenda.setBackground(new java.awt.Color(47, 160, 132));
        btnVenda.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnVenda.setForeground(new java.awt.Color(238, 238, 238));
        btnVenda.setText("Venda");
        btnVenda.setBorderPainted(false);
        btnVenda.addActionListener(this::btnVendaActionPerformed);

        btnCadastros.setBackground(new java.awt.Color(47, 160, 132));
        btnCadastros.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCadastros.setForeground(new java.awt.Color(238, 238, 238));
        btnCadastros.setText("Cadastros");
        btnCadastros.setBorderPainted(false);
        btnCadastros.addActionListener(this::btnCadastrosActionPerformed);

        btnFinanceiro.setBackground(new java.awt.Color(47, 160, 132));
        btnFinanceiro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnFinanceiro.setForeground(new java.awt.Color(238, 238, 238));
        btnFinanceiro.setText("Financeiro");
        btnFinanceiro.setBorderPainted(false);
        btnFinanceiro.addActionListener(this::btnFinanceiroActionPerformed);

        btnRelatorio.setBackground(new java.awt.Color(47, 160, 132));
        btnRelatorio.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnRelatorio.setForeground(new java.awt.Color(238, 238, 238));
        btnRelatorio.setText("Relatório");
        btnRelatorio.setBorderPainted(false);
        btnRelatorio.addActionListener(this::btnRelatorioActionPerformed);

        btnUsuario.setBackground(new java.awt.Color(31, 111, 95));
        btnUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnUsuario.setForeground(new java.awt.Color(235, 235, 235));
        btnUsuario.setIcon(new javax.swing.ImageIcon("C:\\Users\\paulo\\OneDrive\\Documents\\Imagens\\usuario-icon.png")); // NOI18N
        btnUsuario.setText("Usuário");
        btnUsuario.setBorderPainted(false);
        btnUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\paulo\\OneDrive\\Documents\\Imagens\\logo-conexao-estetika.png")); // NOI18N

        javax.swing.GroupLayout pnlBarraLateralLayout = new javax.swing.GroupLayout(pnlBarraLateral);
        pnlBarraLateral.setLayout(pnlBarraLateralLayout);
        pnlBarraLateralLayout.setHorizontalGroup(
                pnlBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBarraLateralLayout.createSequentialGroup()
                                .addGroup(pnlBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlBarraLateralLayout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(btnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(pnlBarraLateralLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(pnlBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnCadastros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnFinanceiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnRelatorio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        pnlBarraLateralLayout.setVerticalGroup(
                pnlBarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBarraLateralLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(btnVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCadastros, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnFinanceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                                .addComponent(btnUsuario)
                                .addGap(34, 34, 34))
        );

        pnlCardsContainer.setBackground(new java.awt.Color(238, 238, 238));
        cardContainerLayout = new java.awt.CardLayout();
        pnlCardsContainer.setLayout(cardContainerLayout);

        pnlCardsContainer.add(new VendasView(vendaService, caixaService, clienteService, produtoService, usuarioService, usuarioLogado, movimentacaoCaixaService), "telaVenda");
        pnlCardsContainer.add(new FinanceiroPanel(usuarioLogado, contaPagarService, contaReceberService, financeiroService, caixaService), "telaFinanceiro");

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
                pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(pnlBarraLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlCardsContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        pnlBackgroundLayout.setVerticalGroup(
                pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlBarraLateral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlCardsContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }                      

    private void btnVendaActionPerformed(java.awt.event.ActionEvent evt) {                                         
        cardContainerLayout.show(pnlCardsContainer, "telaVenda");
        
    }                                        

    private void btnCadastrosActionPerformed(java.awt.event.ActionEvent evt) { 
    }                                            

    private void btnFinanceiroActionPerformed(java.awt.event.ActionEvent evt) {                                              
        cardContainerLayout.show(pnlCardsContainer, "telaFinanceiro");
    }                                             

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }

    public static void setupTable(JTable table) {
        table.setRowHeight(28);
        table.setFont(FONT_SMALL);
        table.getTableHeader().setFont(FONT_BTN);
        table.getTableHeader().setBackground(CONTENT_BG);
        table.getTableHeader().setForeground(TEXT_DARK);
        table.setGridColor(BORDER_COLOR);
        table.setSelectionBackground(new java.awt.Color(200, 230, 220));
        table.setSelectionForeground(TEXT_DARK);
        table.setShowGrid(true);
        table.setFillsViewportHeight(true);
    }

    public static JButton createAccentButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(ACCENT_DARK);
        btn.setForeground(WHITE);
        btn.setFont(FONT_SMALL);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static JButton createOutlineButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(WHITE);
        btn.setForeground(ACCENT_DARK);
        btn.setFont(FONT_SMALL);
        btn.setBorder(BorderFactory.createLineBorder(ACCENT_DARK));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }
}