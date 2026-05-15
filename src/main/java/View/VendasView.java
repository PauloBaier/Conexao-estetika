package View;

import models.*;
import models.enums.TipoMovimento;
import services.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VendasView extends JPanel{
    private Venda vendaAtual;
    private Caixa caixaAtual;

    private VendaService vendaService;
    private CaixaService caixaService;
    private ClienteService clienteService;
    private ProdutoService produtoService;
    private UsuarioService usuarioService;
    private Usuario usuarioLogado;
    private MovimentacaoCaixaService movimentacaoCaixaService;

    private javax.swing.JLabel Titulo;
    private javax.swing.JButton btnAbrirCaixa;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnFecharCaixa;
    private javax.swing.JButton btnFinalizarVenda;
    private javax.swing.JButton btnNovaVenda;
    private javax.swing.JButton btnSangria;
    private javax.swing.JButton btnSuprimento;
    private javax.swing.JButton btnSaldoCaixa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblProduto;
    private javax.swing.JLabel lblSubtotaValor;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblItenVenda;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JTextField txtUsuario;

    java.awt.Frame framePai;


    
    public VendasView(VendaService vendaService,
                      CaixaService caixaService,
                      ClienteService clienteService,
                      ProdutoService produtoService,
                      UsuarioService usuarioService,
                      Usuario usuarioLogado,
                      MovimentacaoCaixaService movimentacaoCaixaService
                      ){
        this.vendaService = vendaService;
        this.caixaService = caixaService;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
        this.usuarioLogado = usuarioLogado;
        this.movimentacaoCaixaService = movimentacaoCaixaService;

        Titulo = new javax.swing.JLabel();
        btnNovaVenda = new javax.swing.JButton();
        btnAbrirCaixa = new javax.swing.JButton();
        btnFecharCaixa = new javax.swing.JButton();
        btnSuprimento = new javax.swing.JButton();
        btnSangria = new javax.swing.JButton();
        btnSaldoCaixa = new javax.swing.JButton();
        lblSubtotal = new javax.swing.JLabel();
        lblSubtotaValor = new javax.swing.JLabel();
        btnFinalizarVenda = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        lblUsuario = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblProduto = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtCliente = new javax.swing.JTextField();
        txtProduto = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItenVenda = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
setBackground(new java.awt.Color(238, 238, 238));
        setPreferredSize(new java.awt.Dimension(638, 638));

        Titulo.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        Titulo.setForeground(new java.awt.Color(31, 111, 95));
        Titulo.setText("Venda:");

        btnNovaVenda.setBackground(new java.awt.Color(47, 160, 132));
        btnNovaVenda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnNovaVenda.setForeground(new java.awt.Color(255, 255, 255));
        btnNovaVenda.setText("Nova Venda");
        btnNovaVenda.setBorderPainted(false);
        btnNovaVenda.setPreferredSize(new java.awt.Dimension(100, 34));
        btnNovaVenda.addActionListener(this::btnNovaVendaActionPerfomed);

        btnAbrirCaixa.setBackground(new java.awt.Color(47, 160, 132));
        btnAbrirCaixa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAbrirCaixa.setForeground(new java.awt.Color(255, 255, 255));
        btnAbrirCaixa.setText("Abrir Caixa");
        btnAbrirCaixa.setBorderPainted(false);
        btnAbrirCaixa.setPreferredSize(new java.awt.Dimension(120, 34));
        btnAbrirCaixa.addActionListener(this::btnAbrirCaixaActionPerfomed);

        btnFecharCaixa.setBackground(new java.awt.Color(47, 160, 132));
        btnFecharCaixa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnFecharCaixa.setForeground(new java.awt.Color(255, 255, 255));
        btnFecharCaixa.setText("Fechar Caixa");
        btnFecharCaixa.setBorderPainted(false);
        btnFecharCaixa.setPreferredSize(new java.awt.Dimension(120, 34));
        btnFecharCaixa.addActionListener(this::btnFecharCaixaActionPerfomed);

        btnSuprimento.setBackground(new java.awt.Color(47, 160, 132));
        btnSuprimento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSuprimento.setForeground(new java.awt.Color(255, 255, 255));
        btnSuprimento.setText("Suprimento");
        btnSuprimento.setBorderPainted(false);
        btnSuprimento.setPreferredSize(new java.awt.Dimension(120, 34));
        btnSuprimento.addActionListener(this::btnSuprimentoActionPerfomed);

        btnSangria.setBackground(new java.awt.Color(47, 160, 132));
        btnSangria.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSangria.setForeground(new java.awt.Color(255, 255, 255));
        btnSangria.setText("Sangria");
        btnSangria.setBorderPainted(false);
        btnSangria.setPreferredSize(new java.awt.Dimension(120, 34));
        btnSangria.addActionListener(this::btnSangriaActionPerfomed);

        btnSaldoCaixa.setBackground(new java.awt.Color(31, 111, 95));
        btnSaldoCaixa.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
        btnSaldoCaixa.setForeground(new java.awt.Color(255, 255, 255));
        btnSaldoCaixa.setText("💰 Saldo");
        btnSaldoCaixa.setBorderPainted(false);
        btnSaldoCaixa.setPreferredSize(new java.awt.Dimension(110, 34));
        btnSaldoCaixa.addActionListener(this::btnSaldoCaixaActionPerformed);

        lblSubtotal.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(31, 111, 95));
        lblSubtotal.setText("Subtotal: R$");

        lblSubtotaValor.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblSubtotaValor.setForeground(new java.awt.Color(31, 111, 95));
        lblSubtotaValor.setText("0,00");

        btnFinalizarVenda.setBackground(new java.awt.Color(47, 160, 132));
        btnFinalizarVenda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnFinalizarVenda.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizarVenda.setText("Finalizar Venda");
        btnFinalizarVenda.setBorderPainted(false);
        btnFinalizarVenda.setPreferredSize(new java.awt.Dimension(120, 34));
        btnFinalizarVenda.addActionListener(this::btnFinalizarVendaActionPerfomed);

        jPanel1.setBackground(new java.awt.Color(232, 245, 242));

        jSeparator1.setBackground(new java.awt.Color(31, 111, 95));
        jSeparator1.setForeground(new java.awt.Color(31, 111, 95));

        lblUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(30, 30, 30));
        lblUsuario.setText("Usuário:");

        lblCliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(30, 30, 30));
        lblCliente.setText("Cliente:");

        lblProduto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblProduto.setForeground(new java.awt.Color(30, 30, 30));
        lblProduto.setText("Produto:");

        txtUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtUsuario.setEditable(false);

        txtCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCliente.addActionListener(this::txtClienteActionPerformed);
        txtCliente.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(vendaAtual.getCliente() != null) {
                    String nomeCliente = vendaAtual.getCliente().getNome();
                    txtCliente.setText(nomeCliente != null ? nomeCliente : "");
                }
            }
        });

        txtProduto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtProduto.addActionListener(this::txtProdutoActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUsuario)
                    .addComponent(lblCliente)
                    .addComponent(lblProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtProduto))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliente)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProduto)
                    .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblItenVenda.setBackground(new java.awt.Color(232, 245, 242));
        tblItenVenda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblItenVenda.setForeground(new java.awt.Color(30, 30, 30));
        tblItenVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
            },
            new String [] {
                "Id", "Nome", "Quantidade", "Preço Un.", "Valor Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItenVenda.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblItenVenda);
        if (tblItenVenda.getColumnModel().getColumnCount() > 0) {
            tblItenVenda.getColumnModel().getColumn(0).setResizable(false);
            tblItenVenda.getColumnModel().getColumn(0).setPreferredWidth(10);
            tblItenVenda.getColumnModel().getColumn(1).setResizable(false);
            tblItenVenda.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblItenVenda.getColumnModel().getColumn(2).setResizable(false);
            tblItenVenda.getColumnModel().getColumn(2).setPreferredWidth(20);
            tblItenVenda.getColumnModel().getColumn(3).setResizable(false);
            tblItenVenda.getColumnModel().getColumn(3).setPreferredWidth(20);
            tblItenVenda.getColumnModel().getColumn(4).setResizable(false);
            tblItenVenda.getColumnModel().getColumn(4).setPreferredWidth(20);
        }
        tblItenVenda.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType() == TableModelEvent.UPDATE){
                    int linhaAlterada = e.getFirstRow();
                    int coluna = e.getColumn();

                    if(coluna == 2){
                        quantidadeTabelaAtualizada(linhaAlterada, coluna);
                    }
                }
            }
        });

        btnCancelar.setBackground(new java.awt.Color(47, 160, 132));
        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.setPreferredSize(new java.awt.Dimension(120, 34));
        btnCancelar.addActionListener(this::btnCancelarActionPerfomed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(btnNovaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(Titulo))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnAbrirCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnFecharCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnSangria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblSubtotal)
                                                                .addGap(39, 39, 39)
                                                                .addComponent(lblSubtotaValor)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnSuprimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnSaldoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(btnFinalizarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))
                                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(Titulo)
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnNovaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAbrirCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnFecharCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSangria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSuprimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSaldoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblSubtotal)
                                                .addComponent(lblSubtotaValor))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnFinalizarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );

        java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
        framePai = (win instanceof java.awt.Frame) ? (java.awt.Frame) win : null;

        caixaAtual = caixaService.buscarCaixaAberto();

        if(caixaAtual == null){
            btnNovaVenda.setEnabled(false);
            btnAbrirCaixa.setEnabled(true);
            btnFecharCaixa.setEnabled(false);
            btnSangria.setEnabled(false);
            btnSuprimento.setEnabled(false);
            btnSaldoCaixa.setEnabled(false);
            btnFinalizarVenda.setEnabled(false);
        }
        else{
            btnNovaVenda.setEnabled(true);
            btnAbrirCaixa.setEnabled(false);
            btnFecharCaixa.setEnabled(true);
            btnSangria.setEnabled(true);
            btnSuprimento.setEnabled(true);
            btnSaldoCaixa.setEnabled(true);
            btnFinalizarVenda.setEnabled(false);
            caixaAtual = caixaService.buscarCaixaAberto();
        }

        txtCliente.setEnabled(false);
        txtProduto.setEnabled(false);

        btnCancelar.setEnabled(false);
    }

    /**
     * Se o usuário logado for FUNCIONÁRIO, abre um diálogo pedindo email e senha
     * de um ADMIN ou GERENTE para autorizar a operação de caixa.
     * Retorna o usuário autorizado, ou null se cancelado/inválido.
     * Se o usuário já for ADMIN/GERENTE, retorna ele mesmo sem pedir credenciais.
     */
    private models.Usuario autorizarOperacaoCaixa() {
        boolean eFuncionario = usuarioLogado.getPerfil() == models.enums.TipoUsuario.FUNCIONARIO;

        if (!eFuncionario) {
            return usuarioLogado; // ADMIN/GERENTE já tem permissão
        }

        // Pede credenciais de ADMIN ou GERENTE
        JTextField tfEmail = new JTextField();
        JPasswordField tfSenha = new JPasswordField();

        JPanel panel = new JPanel(new java.awt.GridLayout(5, 1, 4, 4));
        panel.add(new JLabel("Operação requer autorização de Gerente ou Administrador."));
        panel.add(new JLabel("E-mail:"));
        panel.add(tfEmail);
        panel.add(new JLabel("Senha:"));
        panel.add(tfSenha);

        int resultado = JOptionPane.showConfirmDialog(
                framePai, panel, "Autorização necessária",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE
        );

        if (resultado != JOptionPane.OK_OPTION) return null;

        String email = tfEmail.getText().trim();
        String senha = new String(tfSenha.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(framePai, "Preencha e-mail e senha.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            models.Usuario autorizador = usuarioService.autenticar(email, senha);

            if (autorizador.getPerfil() == models.enums.TipoUsuario.FUNCIONARIO) {
                JOptionPane.showMessageDialog(framePai,
                        "Usuário '" + autorizador.getNome() + "' não tem permissão para autorizar esta operação.",
                        "Acesso negado", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            return autorizador;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(framePai,
                    "Autenticação falhou: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void btnNovaVendaActionPerfomed(java.awt.event.ActionEvent evt){

        vendaAtual = vendaService.iniciar(usuarioLogado);

        btnNovaVenda.setEnabled(false);
        btnFinalizarVenda.setEnabled(true);
        btnCancelar.setEnabled(true);
        txtUsuario.setText("ID: " + usuarioLogado.getId() + "Nome: " + usuarioLogado.getNome());
        lblSubtotaValor.setText("" + vendaAtual.getValorTotal());

        txtCliente.setEnabled(true);
        txtProduto.setEnabled(true);

        popularTabelaItens();
    }

    private void btnAbrirCaixaActionPerfomed(java.awt.event.ActionEvent evt){
        models.Usuario autorizador = autorizarOperacaoCaixa();
        if (autorizador == null) return;

        AberturaCaixaDialog aberturaCaixaDialog = new AberturaCaixaDialog(framePai, true, autorizador);
        Double valorAbertura = aberturaCaixaDialog.getValorAbertura();

        if(valorAbertura == null){
            JOptionPane.showMessageDialog(null, "Operação cancelada.");
        }
        else{
            try {
                Caixa novoCaixa = caixaService.abrirCaixaComValor(valorAbertura, autorizador);
                caixaAtual = novoCaixa;
                JOptionPane.showMessageDialog(null, "Caixa ABERTO com sucesso!");
                btnNovaVenda.setEnabled(true);
                btnAbrirCaixa.setEnabled(false);
                btnFecharCaixa.setEnabled(true);
                btnSangria.setEnabled(true);
                btnSuprimento.setEnabled(true);
                btnSaldoCaixa.setEnabled(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(framePai, "Erro ao abrir caixa: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void btnFecharCaixaActionPerfomed(java.awt.event.ActionEvent evt){
        models.Usuario autorizador = autorizarOperacaoCaixa();
        if (autorizador == null) return;

        try{
            if(caixaService.buscarCaixaAberto() != null){
                if(vendaAtual != null && !vendaAtual.getItens().isEmpty()){
                    JOptionPane.showMessageDialog(framePai, "Finalize ou cancele a venda em andamento antes de fechar o caixa!", "Atenção", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                caixaService.fecharCaixa(autorizador, vendaAtual);

                btnNovaVenda.setEnabled(false);
                btnAbrirCaixa.setEnabled(true);
                btnFecharCaixa.setEnabled(false);
                btnSangria.setEnabled(false);
                btnSuprimento.setEnabled(false);
                btnSaldoCaixa.setEnabled(false);

                JOptionPane.showMessageDialog(null, "Caixa FECHADO com sucesso!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Caixa já está FECHADO!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void btnSangriaActionPerfomed(java.awt.event.ActionEvent evt){
        models.Usuario autorizador = autorizarOperacaoCaixa();
        if (autorizador == null) return;

        MovimentacaoCaixaDialog movimentacaoCaixaDialog = new MovimentacaoCaixaDialog(framePai, true, TipoMovimento.SAIDA);
        Double valorSangria = movimentacaoCaixaDialog.getValor();
        String descricao = movimentacaoCaixaDialog.getDescricao();

        if(valorSangria != null && descricao != null){
            try {
                movimentacaoCaixaService.registrarSangria(caixaAtual, valorSangria, descricao, autorizador);
                JOptionPane.showMessageDialog(framePai, "SANGRIA lançada com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(framePai, "Erro na sangria: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(framePai, "Operação Cancelada!");
        }
    }

    private void btnSuprimentoActionPerfomed(java.awt.event.ActionEvent evt){
        models.Usuario autorizador = autorizarOperacaoCaixa();
        if (autorizador == null) return;

        MovimentacaoCaixaDialog movimentacaoCaixaDialog = new MovimentacaoCaixaDialog(framePai, true, TipoMovimento.ENTRADA);
        Double valorSuprimento = movimentacaoCaixaDialog.getValor();
        String descricao = movimentacaoCaixaDialog.getDescricao();

        if(valorSuprimento != null && descricao != null){
            try {
                movimentacaoCaixaService.registrarSuprimento(caixaAtual, valorSuprimento, descricao, autorizador);
                JOptionPane.showMessageDialog(framePai, "SUPRIMENTO lançado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(framePai, "Erro no suprimento: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(framePai, "Operação Cancelada!");
        }
    }

    private void btnCancelarActionPerfomed(java.awt.event.ActionEvent evt){
        if(vendaAtual == null){
            limparVenda();
            return;
        }

        int opcao = JOptionPane.showConfirmDialog(framePai, "Deseja cancelar a venda?", "Cancelar", JOptionPane.YES_NO_OPTION);

        if(opcao == JOptionPane.YES_OPTION){
            try {
                vendaService.cancelar(vendaAtual);
                JOptionPane.showMessageDialog(framePai, "Venda cancelada com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(framePai, "Erro ao cancelar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
            limparVenda();
        }
    }

    private void btnFinalizarVendaActionPerfomed(java.awt.event.ActionEvent evt){
        PagamentoDialog pagamentoMenu = new PagamentoDialog(framePai, true, vendaAtual.getValorTotal());
        String formaPagamento = pagamentoMenu.getFormaPagamento();

        if(formaPagamento != null){
            try {
                vendaService.aplicarPagamento(vendaAtual, formaPagamento);
                vendaService.cadastrar(vendaAtual);
                JOptionPane.showMessageDialog(framePai, "Venda finalizada com sucesso!");
                limparVenda();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(framePai, "Erro ao finalizar venda: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {
        if(vendaAtual == null) return;
        SelecionarClienteDialog selecionarClienteMenu = new SelecionarClienteDialog(framePai, true, clienteService, txtCliente.getText());
        if(selecionarClienteMenu.getClienteSelecionado() != null){
            vendaAtual.setCliente(selecionarClienteMenu.getClienteSelecionado());
            txtCliente.setText(vendaAtual.getCliente().getNome());
        }
        else{
            JOptionPane.showMessageDialog(null, "Nenhum cliente foi selecionado!");
        }
    }

    private void txtProdutoActionPerformed(java.awt.event.ActionEvent evt) {
        if(vendaAtual == null) return;
        SelecionarProdutosDialog selecionarProdutoMenu = new SelecionarProdutosDialog(framePai, true, produtoService, txtProduto.getText());
        if(selecionarProdutoMenu.getProdutoSelecionado() != null){
            try{
                Produto produto = selecionarProdutoMenu.getProdutoSelecionado();
                int quantidade = selecionarProdutoMenu.getQuantidade();

                if(produtoService.estoqueSuficiente(vendaAtual, produto, quantidade, vendaService)){
                    vendaAtual.adicionarItem(produto, quantidade);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Estoque Insuficiente!");
                }

            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

            popularTabelaItens();
            atualizarSubTotal();
        }
        else{
            JOptionPane.showMessageDialog(null, "Nenhum Produto foi selecionado!");
        }
    }

    private void quantidadeTabelaAtualizada(int linha, int coluna){
        int quantidade = (int)tblItenVenda.getValueAt(linha, coluna);
        Long produtoId = (Long)tblItenVenda.getValueAt(linha, 0);
        Produto produto = produtoService.buscarPorId(produtoId);

        int contagem = 0;

        for(int i = 0; i < tblItenVenda.getRowCount(); i++){
            if(tblItenVenda.getValueAt(i, 0) == produtoId){
                contagem += (int)tblItenVenda.getValueAt(i, 2);
            }
        }

        if((contagem + quantidade) <= produto.getQuantidadeEstoque()){
            vendaAtual.getItens().get(linha).setQuantidade(quantidade);
            popularTabelaItens();
            atualizarSubTotal();
        }else{
            JOptionPane.showMessageDialog(null, "Estoque Insuficiente!");
            popularTabelaItens();
            atualizarSubTotal();
        }
    }

    private void btnSaldoCaixaActionPerformed(java.awt.event.ActionEvent evt){
        Caixa caixa = caixaService.buscarCaixaAberto();

        if(caixa == null){
            JOptionPane.showMessageDialog(framePai, "Nenhum caixa aberto no momento.", "Saldo do Caixa", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String mensagem = String.format(
            "<html><body style='font-family:Arial; padding:8px'>" +
            "<b style='font-size:14px; color:#1F6F5F'>Saldo do Caixa</b><br><br>" +
            "&#128176; <b>Saldo Atual:</b>&nbsp; R$ %.2f<br><br>" +
            "&#128194; <b>Valor de Abertura:</b>&nbsp; R$ %.2f<br><br>" +
            "&#128197; <b>Aberto em:</b>&nbsp; %s" +
            "</body></html>",
            caixa.getSaldoAtual(),
            caixa.getValorAbertura(),
            caixa.getDataAbertura() != null ? caixa.getDataAbertura().toString() : "-"
        );

        JOptionPane.showMessageDialog(framePai, mensagem, "Saldo do Caixa", JOptionPane.INFORMATION_MESSAGE);
    }

    private void popularTabelaItens(){
        DefaultTableModel tableModel = (DefaultTableModel) tblItenVenda.getModel();
        tableModel.setRowCount(0);

        for (ItemVenda item: vendaAtual.getItens()){
            Object[] linha = new Object[]{
                    item.getProduto().getId(),
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getPrecoUnitario(),
                    item.getTotalItem()
            };

            tableModel.addRow(linha);
        }
    }

    private void atualizarSubTotal(){
        double soma = 0;

        for(ItemVenda item: vendaAtual.getItens()){
            soma += item.getTotalItem();
        }

        lblSubtotaValor.setText("" + soma);
    }

    private void limparVenda(){
        vendaAtual = null;

        btnNovaVenda.setEnabled(true);
        btnCancelar.setEnabled(false);
        btnFinalizarVenda.setEnabled(false);

        DefaultTableModel tableModel = (DefaultTableModel) tblItenVenda.getModel();
        tableModel.setRowCount(0);

        txtCliente.setText("");
        txtUsuario.setText("");

        txtCliente.setEnabled(false);
        txtProduto.setEnabled(false);

        lblSubtotaValor.setText("0,00");
    }
}