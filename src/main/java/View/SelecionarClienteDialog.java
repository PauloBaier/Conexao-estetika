package View;

import models.Cliente;
import models.enums.TipoMovimento;
import services.ClienteService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.util.List;

public class SelecionarClienteDialog extends JDialog {
    private ClienteService clienteService;
    private Cliente clienteSelecionado = null;

    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSelecionar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtNome;

    public SelecionarClienteDialog(java.awt.Frame parent, boolean modal, ClienteService clienteService, String nome) {
        super(parent, modal);

        this.clienteService = clienteService;

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSelecionar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        lblNome = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(238, 238, 238));

        lblTitulo.setBackground(new java.awt.Color(31, 111, 95));
        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(31, 111, 95));
        lblTitulo.setText("Clientes:");

        txtNome.addActionListener(this::txtNomeActionPerformed);

        btnPesquisar.setBackground(new java.awt.Color(47, 160, 132));
        btnPesquisar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.setBorderPainted(false);
        btnPesquisar.addActionListener(this::btnPesquisarActionPerformed);

        btnCancelar.setBackground(new java.awt.Color(47, 160, 132));
        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setBorderPainted(false);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnSelecionar.setBackground(new java.awt.Color(47, 160, 132));
        btnSelecionar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSelecionar.setForeground(new java.awt.Color(255, 255, 255));
        btnSelecionar.setText("Selecionar");
        btnSelecionar.setBorderPainted(false);
        btnSelecionar.addActionListener(this::btnSelecionarActionPerformed);

        jSeparator1.setForeground(new java.awt.Color(31, 111, 95));

        jSeparator2.setForeground(new java.awt.Color(31, 111, 95));

        tblClientes.setBackground(new java.awt.Color(232, 245, 242));
        tblClientes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblClientes.setForeground(new java.awt.Color(30, 30, 30));
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                },
                new String[]{
                        "Id", "Nome", "CPF", "Telefone"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblClientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblClientes);
        if (tblClientes.getColumnModel().getColumnCount() > 0) {
            tblClientes.getColumnModel().getColumn(0).setResizable(false);
            tblClientes.getColumnModel().getColumn(0).setPreferredWidth(5);
            tblClientes.getColumnModel().getColumn(1).setResizable(false);
            tblClientes.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblClientes.getColumnModel().getColumn(2).setResizable(false);
            tblClientes.getColumnModel().getColumn(3).setResizable(false);
        }
        tblClientes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt){
                if(evt.getClickCount() == 2){
                    selecionaClienteTabela();
                }
            }
        });

        lblNome.setText("Nome:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnPesquisar))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnSelecionar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCancelar))
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(lblTitulo)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                                        .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTitulo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnPesquisar)
                                        .addComponent(lblNome))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 342, Short.MAX_VALUE)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCancelar)
                                        .addComponent(btnSelecionar))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(113, 113, 113)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(49, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();

        if(nome != null && !nome.isEmpty()){
            txtNome.setText(nome);
            pesquisarCliente(nome);
        }

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {
        String txtNomeInput = txtNome.getText();
        if(!txtNomeInput.isEmpty()){
            pesquisarCliente(txtNome.getText());
        }
        else{
            popularTabela(clienteService.listar());
        }
    }

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {
        if(txtNome.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Campo Vazio!");
        }else{
            pesquisarCliente(txtNome.getText());
        }

    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        clienteSelecionado = null;

        dispose();
    }

    private void btnSelecionarActionPerformed(java.awt.event.ActionEvent evt) {
        int linhaSelecionada = tblClientes.getSelectedRow();

        if(linhaSelecionada != -1){
            clienteSelecionado = clienteService.buscarPorId((long)tblClientes.getModel().getValueAt(linhaSelecionada, 0));
        }

        dispose();
    }

    private void selecionaClienteTabela(){
        int linhaSelecionada = tblClientes.getSelectedRow();

        if(linhaSelecionada != -1){
            clienteSelecionado = clienteService.buscarPorId((long)tblClientes.getModel().getValueAt(linhaSelecionada, 0));
        }

        dispose();
    }

    private void pesquisarCliente(String nome){
        List<Cliente> resultado = clienteService.buscarPorNome(nome);
        popularTabela(resultado);
    }

    private void popularTabela(List<Cliente> clientes){
        DefaultTableModel tableModel = (DefaultTableModel) tblClientes.getModel();
        tableModel.setRowCount(0);

        for(Cliente c:clientes){
            Object[] linha = new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getCpf(),
                    c.getCpf() == null ? "Não Cadastrado" : c.getCpf()
            };

            tableModel.addRow(linha);
        }
    }

    public Cliente getClienteSelecionado(){
        return this.clienteSelecionado;
    }
}
