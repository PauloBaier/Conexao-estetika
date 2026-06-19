package view.dialogs;

import models.enums.TipoMovimento;

import javax.swing.*;

public class MovimentacaoCaixaDialog extends JDialog {
    private Double valor;
    private String descricao;

    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblValor;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JFormattedTextField txtValor;

    public MovimentacaoCaixaDialog(java.awt.Frame parent, boolean modal, TipoMovimento tipoMov){
        super(parent, modal);

        valor = 0.0;
        descricao = "";

        pnlBackground = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        txtValor = new javax.swing.JFormattedTextField();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblDescricao = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlBackground.setBackground(new java.awt.Color(238, 238, 238));

        lblTitulo.setBackground(new java.awt.Color(31, 111, 95));
        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(31, 111, 95));
        lblTitulo.setText(tipoMov == TipoMovimento.ENTRADA? "SUPRIMENTO" : "SANGRIA");

        lblValor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblValor.setForeground(new java.awt.Color(30, 30, 30));
        lblValor.setText("Valor:");

        txtValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
    new javax.swing.text.NumberFormatter(java.text.NumberFormat.getNumberInstance())));
        txtValor.setValue(0.00);
        txtValor.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                javax.swing.SwingUtilities.invokeLater(() -> txtValor.selectAll());
            }
        });

        btnConfirmar.setBackground(new java.awt.Color(47, 160, 132));
        btnConfirmar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnConfirmar.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmar.setText("Confirma");
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(this::btnConfirmarActionPerfomed);

        btnCancelar.setBackground(new java.awt.Color(47, 160, 132));
        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(this::btnCancelarActionPerfomed);

        lblDescricao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblDescricao.setForeground(new java.awt.Color(30, 30, 30));
        lblDescricao.setText("Descrição:");

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
                pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(lblTitulo)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBackgroundLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBackgroundLayout.createSequentialGroup()
                                                .addGap(47, 47, 47)
                                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(txtValor, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                                .addComponent(lblValor)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE))
                                                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                                .addComponent(lblDescricao)
                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGap(47, 47, 47))
        );
        pnlBackgroundLayout.setVerticalGroup(
                pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblTitulo)
                                .addGap(18, 18, 18)
                                .addComponent(lblValor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDescricao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCancelar)
                                        .addComponent(btnConfirmar))
                                .addGap(31, 31, 31))
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

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void btnConfirmarActionPerfomed(java.awt.event.ActionEvent evt){
        double v = ((Number)txtValor.getValue()).doubleValue();
        if(v <= 0){
            JOptionPane.showMessageDialog(this, "Informe um valor maior que zero!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String desc = txtDescricao.getText().trim();
        if(desc.isEmpty()){
            JOptionPane.showMessageDialog(this, "Informe uma descrição!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        this.valor = v;
        this.descricao = desc;
        dispose();
    }

    private void btnCancelarActionPerfomed(java.awt.event.ActionEvent evt){
        this.valor = null;
        this.descricao = null;

        dispose();
    }

    public Double getValor(){
        return this.valor;
    }

    public String getDescricao(){
        return this.descricao;
    }
}