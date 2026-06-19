package View;

import models.enums.FormaPagamento;

import javax.swing.*;
import java.util.Arrays;

public class PagamentoDialog extends JDialog {
    private double valorTotal;

    private String formaPagamento;

    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JComboBox<String> cboFormaPagamento;
    private javax.swing.JLabel lblFormaPagamento;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTroco;
    private javax.swing.JLabel lblTrocoValor;
    private javax.swing.JLabel lblValorPago;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JFormattedTextField txtValorPago;
    private javax.swing.JFormattedTextField txtValorTotal;

    public PagamentoDialog(java.awt.Frame parent, boolean modal, double valorTotal){
        super(parent, modal);
        this.valorTotal = valorTotal;

        pnlBackground = new javax.swing.JPanel();
        cboFormaPagamento = new javax.swing.JComboBox<>();
        lblFormaPagamento = new javax.swing.JLabel();
        lblValorTotal = new javax.swing.JLabel();
        txtValorPago = new javax.swing.JFormattedTextField();
        lblValorPago = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        txtValorTotal = new javax.swing.JFormattedTextField();
        lblTrocoValor = new javax.swing.JLabel();
        lblTroco = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlBackground.setBackground(new java.awt.Color(238, 238, 238));

        DefaultComboBoxModel<String> cboModel = new javax.swing.DefaultComboBoxModel<>(Arrays.stream(FormaPagamento.values()).map(Enum::name).toArray(String[]::new));
        cboModel.insertElementAt("PAGAMENTO PENDENTE", 0);
        cboFormaPagamento.setModel(cboModel);
        cboFormaPagamento.setSelectedIndex(1);
        cboFormaPagamento.addActionListener(this::cboFormaPagamentoActionEvent);


        lblFormaPagamento.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblFormaPagamento.setForeground(new java.awt.Color(30, 30, 30));
        lblFormaPagamento.setText("Forma de Pagamento:");

        lblValorTotal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblValorTotal.setForeground(new java.awt.Color(30, 30, 30));
        lblValorTotal.setText("Valor Total:");

        txtValorPago.setForeground(new java.awt.Color(30, 30, 30));
        txtValorPago.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        txtValorPago.setValue(0.00);
        txtValorPago.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                javax.swing.SwingUtilities.invokeLater(() -> txtValorPago.selectAll());
            }
        });
        txtValorPago.addActionListener(this::txtValorPagoActionPerformed);

        lblValorPago.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblValorPago.setForeground(new java.awt.Color(30, 30, 30));
        lblValorPago.setText("Valor Pago");

        lblTitulo.setBackground(new java.awt.Color(255, 255, 255));
        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(31, 111, 95));
        lblTitulo.setText("PAGAMENTO:");

        txtValorTotal.setForeground(new java.awt.Color(30, 30, 30));
        txtValorTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        txtValorTotal.setValue(0.00);
        txtValorTotal.setEditable(false);

        lblTrocoValor.setBackground(new java.awt.Color(255, 255, 255));
        lblTrocoValor.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTrocoValor.setForeground(new java.awt.Color(31, 111, 95));
        lblTrocoValor.setText("0,00");

        lblTroco.setBackground(new java.awt.Color(255, 255, 255));
        lblTroco.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTroco.setForeground(new java.awt.Color(31, 111, 95));
        lblTroco.setText("Troco: R$");

        btnConfirmar.setBackground(new java.awt.Color(47, 160, 132));
        btnConfirmar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnConfirmar.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmar.setText("Confirmar");
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.addActionListener(this::btnConfirmarActionPerformed);

        btnCancelar.setBackground(new java.awt.Color(47, 160, 132));
        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
                pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                                .addComponent(lblTitulo)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                                                        .addGap(78, 78, 78)
                                                                                        .addComponent(lblValorTotal))
                                                                                .addComponent(lblValorPago, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                        .addComponent(lblFormaPagamento, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(cboFormaPagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(txtValorPago)
                                                                        .addComponent(txtValorTotal)))))
                                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnConfirmar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnCancelar))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBackgroundLayout.createSequentialGroup()
                                                .addGap(112, 112, 112)
                                                .addComponent(lblTroco)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                                .addComponent(lblTrocoValor)))
                                .addGap(26, 26, 26))
        );
        pnlBackgroundLayout.setVerticalGroup(
                pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lblTitulo)
                                .addGap(38, 38, 38)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblFormaPagamento))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblValorTotal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblValorPago))
                                .addGap(30, 30, 30)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTroco)
                                        .addComponent(lblTrocoValor))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnConfirmar)
                                        .addComponent(btnCancelar))
                                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        txtValorTotal.setValue(valorTotal);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void txtValorPagoActionPerformed(java.awt.event.ActionEvent evt) {
        calcularTroco();
    }

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {
        String opcaoSelecionada = cboFormaPagamento.getSelectedItem().toString();

        if(opcaoSelecionada.equals("DINHEIRO")) {
            double valorPago = ((Number)txtValorPago.getValue()).doubleValue();
            if(valorPago < valorTotal) {
                JOptionPane.showMessageDialog(this,
                        "O valor pago (R$ " + valorPago + ") não pode ser menor que o total (R$ " + valorTotal + ")!",
                        "Pagamento Inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        formaPagamento = opcaoSelecionada;
        dispose();
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        formaPagamento = null;
        dispose();
    }

    private void cboFormaPagamentoActionEvent(java.awt.event.ActionEvent evt){
        String opcaoSelecionada = cboFormaPagamento.getSelectedItem().toString();

        if(opcaoSelecionada.equals("DINHEIRO")){
            txtValorPago.setEnabled(true);
        }
        else{
            txtValorPago.setEnabled(false);
        }
    }

    private void calcularTroco(){
        double valorPago = ((Number)txtValorPago.getValue()).doubleValue();

        if(valorPago < valorTotal){
            JOptionPane.showMessageDialog(null, "Valor pago não poder ser menor que valor TOTAL!");
            txtValorPago.setValue(0.00);
        }
        else{
            lblTrocoValor.setText(String.format("%.2f", (valorPago - valorTotal)));
        }
    }

    public String getFormaPagamento(){
        return this.formaPagamento;
    }

}