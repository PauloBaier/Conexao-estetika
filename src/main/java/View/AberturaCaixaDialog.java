package View;

import models.Caixa;
import models.Usuario;
import models.enums.StatusCaixa;

import javax.swing.JDialog;
import java.time.LocalDate;
import java.time.LocalTime;

public class AberturaCaixaDialog extends JDialog {
    private Caixa caixa;
    Usuario usuarioLogado;

    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblValorAbertura;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JFormattedTextField txtValorAbertura;

    public AberturaCaixaDialog(java.awt.Frame parent, boolean modal, Usuario usuarioLogado){
        super(parent, modal);

        this.caixa = new Caixa();
        this.usuarioLogado = usuarioLogado;

        pnlBackground = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblValorAbertura = new javax.swing.JLabel();
        txtValorAbertura = new javax.swing.JFormattedTextField();
        btnConfirmar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlBackground.setBackground(new java.awt.Color(238, 238, 238));

        lblTitulo.setBackground(new java.awt.Color(31, 111, 95));
        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(31, 111, 95));
        lblTitulo.setText("Abertura de Caixa");

        lblValorAbertura.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblValorAbertura.setForeground(new java.awt.Color(30, 30, 30));
        lblValorAbertura.setText("Valor de Abertura:");

        txtValorAbertura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getCurrencyInstance())));
        txtValorAbertura.setValue(0.00);

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

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
                pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(lblTitulo)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                .addGap(47, 47, 47)
                                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtValorAbertura, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                                                .addComponent(lblValorAbertura)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)))))
                                .addGap(47, 47, 47))
        );
        pnlBackgroundLayout.setVerticalGroup(
                pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBackgroundLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblTitulo)
                                .addGap(18, 18, 18)
                                .addComponent(lblValorAbertura)
                                .addGap(18, 18, 18)
                                .addComponent(txtValorAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCancelar)
                                        .addComponent(btnConfirmar))
                                .addContainerGap(20, Short.MAX_VALUE))
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

        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void btnConfirmarActionPerfomed(java.awt.event.ActionEvent evt){
        this.caixa.setDataAbertura(LocalDate.now());
        this.caixa.setValorAbertura(((Number)txtValorAbertura.getValue()).doubleValue());
        this.caixa.setSaldoAtual(((Number)txtValorAbertura.getValue()).doubleValue());
        this.caixa.setUsuario(usuarioLogado);
        this.caixa.setStatus(StatusCaixa.ABERTO);

        dispose();
    }

    private void btnCancelarActionPerfomed(java.awt.event.ActionEvent evt){
        caixa = null;

        dispose();
    }

    public Caixa getCaixa(){
        return this.caixa;
    }
}
