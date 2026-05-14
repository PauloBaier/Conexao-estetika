package View;

import models.Caixa;
import models.Usuario;
import models.Venda;
import models.enums.StatusVenda;
import services.*;

import javax.swing.*;

public class VendasView extends JPanel{
    private Venda vendaAtual;
    private Caixa caixaAtual;

    private VendaService vendaService;
    private CaixaService caixaService;
    private ClienteService clienteService;
    private ProdutoService produtoService;
    private UsuarioService usuarioService;
    private Usuario usuarioLogado;

    private javax.swing.JLabel Titulo;
    private javax.swing.JButton btnAbrirCaixa;
    private javax.swing.JButton btnFecharCaixa;
    private javax.swing.JButton btnFinalizarVenda;
    private javax.swing.JButton btnNovaVenda;
    private javax.swing.JButton btnSangria;
    private javax.swing.JButton btnSuprimento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblProduto;
    private javax.swing.JLabel lblSubtotaValor;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblItenVenda;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JTextField txtUsuario;


    
    public VendasView(VendaService vendaService,
                      CaixaService caixaService,
                      ClienteService clienteService,
                      ProdutoService produtoService,
                      UsuarioService usuarioService,
                      Usuario usuarioLogado
                      ){
        this.vendaService = vendaService;
        this.caixaService = caixaService;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
        this.usuarioLogado = usuarioLogado;

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Titulo = new javax.swing.JLabel();
        btnNovaVenda = new javax.swing.JButton();
        btnAbrirCaixa = new javax.swing.JButton();
        btnFecharCaixa = new javax.swing.JButton();
        btnSuprimento = new javax.swing.JButton();
        btnSangria = new javax.swing.JButton();
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

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

        btnSangria.setBackground(new java.awt.Color(47, 160, 132));
        btnSangria.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSangria.setForeground(new java.awt.Color(255, 255, 255));
        btnSangria.setText("Sangria");
        btnSangria.setBorderPainted(false);
        btnSangria.setPreferredSize(new java.awt.Dimension(120, 34));

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

        txtCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCliente.addActionListener(this::txtClienteActionPerformed);

        txtProduto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(lblSubtotaValor)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSuprimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(btnSuprimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSubtotal)
                        .addComponent(lblSubtotaValor))
                    .addComponent(btnFinalizarVenda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        caixaAtual = caixaService.buscarCaixaAberto();
        if(caixaAtual == null){
            btnNovaVenda.setEnabled(false);
            btnFecharCaixa.setEnabled(false);
            btnSangria.setEnabled(false);
            btnSuprimento.setEnabled(false);
            btnFinalizarVenda.setEnabled(false);
        }
        else{
            btnNovaVenda.setEnabled(true);
            btnAbrirCaixa.setEnabled(false);
            btnFecharCaixa.setEnabled(true);
            btnSangria.setEnabled(true);
            btnSuprimento.setEnabled(true);
            btnFinalizarVenda.setEnabled(false);
            caixaAtual = caixaService.buscarCaixaAberto();
        }
    }

    private void btnNovaVendaActionPerfomed(java.awt.event.ActionEvent evt){

        vendaAtual = new Venda();
        vendaAtual.setUsuario(usuarioLogado);
        vendaAtual.setStatus(StatusVenda.PENDENTE);
        vendaAtual.setValorTotal(0);

        btnNovaVenda.setEnabled(false);
        btnFinalizarVenda.setEnabled(true);
        txtUsuario.setText("ID: " + usuarioLogado.getId() + "Nome: " + usuarioLogado.getNome());
        lblSubtotaValor.setText("" + vendaAtual.getValorTotal());
    }

    private void btnAbrirCaixaActionPerfomed(java.awt.event.ActionEvent evt){
        // Abertura de caixa
        // Falta fazer a tela de abertura
        // e verificação de usuário

        //Login

        //Abrir Tela de Abertura(requisitar valor);
        java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
        java.awt.Frame framePai = (win instanceof java.awt.Frame) ? (java.awt.Frame) win : null;
        AberturaCaixaDialog aberturaCaixaDialog = new AberturaCaixaDialog(framePai, true, usuarioLogado);

        if((caixaAtual = aberturaCaixaDialog.getCaixa()) == null){
            JOptionPane.showMessageDialog(null, "Caixa está fechado!");
        }
        else{
            JOptionPane.showMessageDialog(null, "Caixa ABERTO com sucesso!");
            btnNovaVenda.setEnabled(true);
            btnAbrirCaixa.setEnabled(false);
            btnFecharCaixa.setEnabled(true);
            btnSangria.setEnabled(true);
            btnSuprimento.setEnabled(true);

            caixaService.abrirCaixa(caixaAtual, usuarioLogado);
        }
    }

    private void btnFecharCaixaActionPerfomed(java.awt.event.ActionEvent evt){
        if(caixaService.buscarCaixaAberto() != null){
            caixaService.fecharCaixa(usuarioLogado);

            btnNovaVenda.setEnabled(false);
            btnAbrirCaixa.setEnabled(true);
            btnFecharCaixa.setEnabled(false);
            btnSangria.setEnabled(false);
            btnSuprimento.setEnabled(false);

            JOptionPane.showMessageDialog(null, "Caixa FECHADO com sucesso!");
        }
        else{
            JOptionPane.showMessageDialog(null, "Caixa já está FECHADO!");
        }
    }

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    } 
}