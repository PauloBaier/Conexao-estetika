package com.conexao.view;

import javax.swing.*;

public class MenuPrincipalView extends JFrame{
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

    public MenuPrincipalView(){
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

        pnlCardsContainer.add(new VendasView(), "telaVenda");


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
        // TODO add your handling code here:
    }                                             

    private void btnRelatorioActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                    
}