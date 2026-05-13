package View;

import models.*;
import services.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EntradaFinanceiroPanel extends JPanel {

    private final ProdutoService produtoService;
    private final FornecedorService fornecedorService;
    private final EntradaEstoqueService entradaEstoqueService;

    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public EntradaFinanceiroPanel(
            ProdutoService produtoService,
            FornecedorService fornecedorService,
            EntradaEstoqueService entradaEstoqueService
    ) {
        this.produtoService        = produtoService;
        this.fornecedorService     = fornecedorService;
        this.entradaEstoqueService = entradaEstoqueService;

        setLayout(new BorderLayout());
        setBackground(MenuPrincipalView.CONTENT_BG);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        buildUI();
    }

    private void buildUI() {
        add(buildCard(), BorderLayout.CENTER);
    }

    // ─────────────────────────────────────────────
    // CARD PRINCIPAL
    // ─────────────────────────────────────────────
    private JPanel buildCard() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(MenuPrincipalView.CONTENT_BG);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(MenuPrincipalView.CONTENT_BG);
        header.setBorder(new EmptyBorder(0, 0, 16, 0));

        JLabel title = new JLabel("Entrada de Estoque");
        title.setFont(MenuPrincipalView.FONT_TITLE);
        title.setForeground(MenuPrincipalView.TEXT_DARK);

        header.add(title, BorderLayout.WEST);

        panel.add(header,          BorderLayout.NORTH);
        panel.add(buildFormulario(), BorderLayout.CENTER);

        return panel;
    }

    // ─────────────────────────────────────────────
    // FORMULÁRIO DE ENTRADA
    // ─────────────────────────────────────────────
    private JPanel buildFormulario() {

        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(MenuPrincipalView.WHITE);
        panel.setBorder(new EmptyBorder(16, 16, 16, 16));

        // ── Fornecedor, valor e vencimento ────────
        JPanel topFields = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        topFields.setBackground(MenuPrincipalView.WHITE);

        List<Fornecedor> fornecedores = fornecedorService.listarTodos();
        JComboBox<Fornecedor> cbFornecedor = new JComboBox<>(fornecedores.toArray(new Fornecedor[0]));
        cbFornecedor.setFont(MenuPrincipalView.FONT_BODY);
        cbFornecedor.setPreferredSize(new Dimension(200, 30));
        cbFornecedor.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Fornecedor f) setText(f.getNome());
                return this;
            }
        });

        JTextField tfValor      = styledTextField(10);
        JTextField tfVencimento = styledTextField(10);
        tfVencimento.setToolTipText("dd/MM/yyyy");

        topFields.add(label("Fornecedor:"));
        topFields.add(cbFornecedor);
        topFields.add(label("Valor Total (R$):"));
        topFields.add(tfValor);
        topFields.add(label("Vencimento (dd/MM/yyyy):"));
        topFields.add(tfVencimento);

        // ── Tabela de itens ───────────────────────
        String[] cols = { "Produto", "Quantidade" };

        DefaultTableModel itemModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return true; }
        };

        JTable itemTable = new JTable(itemModel);
        MenuPrincipalView.setupTable(itemTable);

        // editor e renderer da coluna Produto (combobox)
        List<Produto> produtos = produtoService.listarTodos();
        JComboBox<Produto> cbProduto = new JComboBox<>(produtos.toArray(new Produto[0]));
        cbProduto.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Produto p) setText(p.getNome());
                return this;
            }
        });
        itemTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbProduto));

        itemTable.getColumnModel().getColumn(0).setCellRenderer(
                (t, value, isSelected, hasFocus, row, col) -> {
                    String text = value instanceof Produto p ? p.getNome() : String.valueOf(value);
                    JLabel lbl = new JLabel(text);
                    lbl.setFont(MenuPrincipalView.FONT_BODY);
                    lbl.setBorder(new EmptyBorder(0, 8, 0, 0));
                    lbl.setOpaque(true);
                    lbl.setBackground(isSelected ? MenuPrincipalView.ACCENT_LIGHT : MenuPrincipalView.WHITE);
                    return lbl;
                }
        );

        JScrollPane scroll = new JScrollPane(itemTable);
        scroll.setBorder(BorderFactory.createLineBorder(MenuPrincipalView.BORDER_COLOR));
        scroll.setPreferredSize(new Dimension(0, 200));

        // ── Botões de itens ───────────────────────
        JPanel itemBtnBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        itemBtnBar.setBackground(MenuPrincipalView.WHITE);

        JButton btnAdd = MenuPrincipalView.createOutlineButton("+ Adicionar Item");
        JButton btnRem = MenuPrincipalView.createOutlineButton("− Remover Item");

        btnAdd.addActionListener(e -> {
            if (produtos.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Nenhum produto cadastrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            itemModel.addRow(new Object[]{ produtos.get(0), 1 });
        });

        btnRem.addActionListener(e -> {
            int row = itemTable.getSelectedRow();
            if (row >= 0) itemModel.removeRow(row);
        });

        itemBtnBar.add(btnAdd);
        itemBtnBar.add(btnRem);

        // ── Botão confirmar ───────────────────────
        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        bottomBar.setBackground(MenuPrincipalView.WHITE);

        JButton btnConfirmar = MenuPrincipalView.createAccentButton("Confirmar Entrada");

        btnConfirmar.addActionListener(e -> {

            if (itemTable.isEditing()) itemTable.getCellEditor().stopCellEditing();

            Fornecedor fornecedor = (Fornecedor) cbFornecedor.getSelectedItem();
            if (fornecedor == null) {
                showWarning("Selecione um fornecedor.");
                return;
            }

            double valor;
            try {
                valor = Double.parseDouble(tfValor.getText().trim().replace(",", "."));
                if (valor <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                showWarning("Informe um valor total válido.");
                return;
            }

            LocalDate vencimento;
            try {
                vencimento = LocalDate.parse(tfVencimento.getText().trim(), fmt);
            } catch (Exception ex) {
                showWarning("Informe a data de vencimento no formato dd/MM/yyyy.");
                return;
            }

            if (itemModel.getRowCount() == 0) {
                showWarning("Adicione ao menos um item.");
                return;
            }

            List<ItemVenda> itens = new ArrayList<>();
            for (int r = 0; r < itemModel.getRowCount(); r++) {
                Object prodObj = itemModel.getValueAt(r, 0);
                Object qtdObj  = itemModel.getValueAt(r, 1);

                if (!(prodObj instanceof Produto)) {
                    showWarning("Linha " + (r + 1) + ": produto inválido.");
                    return;
                }

                int qtd;
                try {
                    qtd = Integer.parseInt(String.valueOf(qtdObj));
                    if (qtd <= 0) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    showWarning("Linha " + (r + 1) + ": quantidade inválida.");
                    return;
                }

                ItemVenda item = new ItemVenda();
                item.setProduto((Produto) prodObj);
                item.setQuantidade(qtd);
                itens.add(item);
            }

            try {
                entradaEstoqueService.registrarEntradaEstoque(itens, fornecedor, valor, vencimento);

                JOptionPane.showMessageDialog(this,
                        "Entrada registrada com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                itemModel.setRowCount(0);
                tfValor.setText("");
                tfVencimento.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Erro: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        bottomBar.add(btnConfirmar);

        // ── Monta layout ──────────────────────────
        JPanel itemArea = new JPanel(new BorderLayout(0, 6));
        itemArea.setBackground(MenuPrincipalView.WHITE);
        itemArea.add(itemBtnBar, BorderLayout.NORTH);
        itemArea.add(scroll,     BorderLayout.CENTER);

        panel.add(topFields, BorderLayout.NORTH);
        panel.add(itemArea,  BorderLayout.CENTER);
        panel.add(bottomBar, BorderLayout.SOUTH);

        return panel;
    }

    // ─────────────────────────────────────────────
    // HELPERS
    // ─────────────────────────────────────────────
    private JLabel label(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(MenuPrincipalView.FONT_SMALL);
        lbl.setForeground(MenuPrincipalView.TEXT_MUTED);
        return lbl;
    }

    private JTextField styledTextField(int cols) {
        JTextField tf = new JTextField(cols);
        tf.setFont(MenuPrincipalView.FONT_BODY);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(MenuPrincipalView.BORDER_COLOR),
                new EmptyBorder(4, 8, 4, 8)
        ));
        return tf;
    }

    private void showWarning(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}
