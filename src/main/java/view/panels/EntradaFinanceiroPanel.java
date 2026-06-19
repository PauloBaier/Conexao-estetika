package view.panels;

import controllers.entrada.EntradaController;
import controllers.entrada.dto.FornecedorEntradaResponse;
import controllers.entrada.dto.ProdutoEntradaResponse;
import models.*;
import view.MenuPrincipalView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EntradaFinanceiroPanel extends JPanel {

    private final EntradaController entradaController;
    private final Runnable onVoltar;

    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public EntradaFinanceiroPanel(EntradaController entradaController) {
        this(entradaController, null);
    }

    public EntradaFinanceiroPanel(EntradaController entradaController, Runnable onVoltar) {
        this.entradaController = entradaController;
        this.onVoltar          = onVoltar;

        setLayout(new BorderLayout());
        setBackground(MenuPrincipalView.CONTENT_BG);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        buildUI();
    }

    private void buildUI() {
        add(buildCard(), BorderLayout.CENTER);
    }

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

        if (onVoltar != null) {
            JButton btnVoltar = MenuPrincipalView.createOutlineButton("← Voltar");
            btnVoltar.addActionListener(e -> onVoltar.run());
            header.add(btnVoltar, BorderLayout.EAST);
        }

        panel.add(header,           BorderLayout.NORTH);
        panel.add(buildFormulario(), BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildFormulario() {
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(MenuPrincipalView.WHITE);
        panel.setBorder(new EmptyBorder(16, 16, 16, 16));

        JPanel topFields = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        topFields.setBackground(MenuPrincipalView.WHITE);

        // Carrega fornecedores via controller
        List<FornecedorEntradaResponse> fornecedorDtos = entradaController.listarFornecedores();
        JComboBox<String> cbFornecedor = new JComboBox<>();
        for (FornecedorEntradaResponse f : fornecedorDtos) cbFornecedor.addItem(f.nome());
        cbFornecedor.setFont(MenuPrincipalView.FONT_BODY);
        cbFornecedor.setPreferredSize(new Dimension(200, 30));

        JTextField tfValor      = styledTextField(10);
        JTextField tfVencimento = styledTextField(10);
        tfVencimento.setToolTipText("dd/MM/yyyy");

        topFields.add(label("Fornecedor:"));
        topFields.add(cbFornecedor);
        topFields.add(label("Valor Total (R$):"));
        topFields.add(tfValor);
        topFields.add(label("Vencimento (dd/MM/yyyy):"));
        topFields.add(tfVencimento);

        // Tabela de itens
        String[] cols = { "Produto", "Quantidade" };
        DefaultTableModel itemModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return true; }
        };
        JTable itemTable = new JTable(itemModel);
        MenuPrincipalView.setupTable(itemTable);

        // Carrega produtos via controller
        List<ProdutoEntradaResponse> produtoDtos = entradaController.listarProdutos();
        JComboBox<String> cbProduto = new JComboBox<>();
        for (ProdutoEntradaResponse p : produtoDtos) cbProduto.addItem(p.nome());
        itemTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(cbProduto));

        itemTable.getColumnModel().getColumn(0).setCellRenderer(
                (t, value, isSelected, hasFocus, row, col) -> {
                    JLabel lbl = new JLabel(value != null ? value.toString() : "");
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

        JPanel itemBtnBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        itemBtnBar.setBackground(MenuPrincipalView.WHITE);

        JButton btnAdd = MenuPrincipalView.createOutlineButton("+ Adicionar Item");
        JButton btnRem = MenuPrincipalView.createOutlineButton("− Remover Item");

        btnAdd.addActionListener(e -> {
            if (produtoDtos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum produto cadastrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            itemModel.addRow(new Object[]{ produtoDtos.get(0).nome(), 1 });
        });
        btnRem.addActionListener(e -> {
            int row = itemTable.getSelectedRow();
            if (row >= 0) itemModel.removeRow(row);
        });

        itemBtnBar.add(btnAdd);
        itemBtnBar.add(btnRem);

        JPanel bottomBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        bottomBar.setBackground(MenuPrincipalView.WHITE);

        JButton btnConfirmar = MenuPrincipalView.createAccentButton("Confirmar Entrada");
        btnConfirmar.addActionListener(e -> {
            if (itemTable.isEditing()) itemTable.getCellEditor().stopCellEditing();

            int fornIdx = cbFornecedor.getSelectedIndex();
            if (fornIdx < 0 || fornecedorDtos.isEmpty()) {
                showWarning("Selecione um fornecedor."); return;
            }
            Fornecedor fornecedor = entradaController.buscarFornecedorPorIndice(fornIdx);

            double valor;
            try {
                valor = Double.parseDouble(tfValor.getText().trim().replace(",", "."));
                if (valor <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                showWarning("Informe um valor total válido."); return;
            }

            LocalDate vencimento;
            String textoData = tfVencimento.getText().trim();
            try {
                vencimento = LocalDate.parse(textoData, fmt);
            } catch (Exception ex) {
                showWarning(String.format("A data '%s' é inválida ou não existe.\nUse o formato DD/MM/YYYY.", textoData));
                return;
            }

            if (itemModel.getRowCount() == 0) {
                showWarning("Adicione ao menos um item."); return;
            }

            List<ItemVenda> itens = new ArrayList<>();
            for (int r = 0; r < itemModel.getRowCount(); r++) {
                Object prodObj = itemModel.getValueAt(r, 0);
                Object qtdObj  = itemModel.getValueAt(r, 1);

                // Encontra o índice do produto pelo nome no combo
                String nomeProd = prodObj != null ? prodObj.toString() : "";
                int prodIdx = -1;
                for (int i = 0; i < produtoDtos.size(); i++) {
                    if (produtoDtos.get(i).nome().equals(nomeProd)) { prodIdx = i; break; }
                }
                if (prodIdx < 0) { showWarning("Linha " + (r + 1) + ": produto inválido."); return; }

                int qtd;
                try {
                    qtd = Integer.parseInt(String.valueOf(qtdObj));
                    if (qtd <= 0) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    showWarning("Linha " + (r + 1) + ": quantidade inválida."); return;
                }

                ItemVenda item = new ItemVenda();
                item.setProduto(entradaController.buscarProdutoPorIndice(prodIdx));
                item.setQuantidade(qtd);
                itens.add(item);
            }

            try {
                entradaController.registrarEntrada(itens, fornecedor, valor, vencimento);
                JOptionPane.showMessageDialog(this, "Entrada registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                itemModel.setRowCount(0);
                tfValor.setText("");
                tfVencimento.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        bottomBar.add(btnConfirmar);

        JPanel itemArea = new JPanel(new BorderLayout(0, 6));
        itemArea.setBackground(MenuPrincipalView.WHITE);
        itemArea.add(itemBtnBar, BorderLayout.NORTH);
        itemArea.add(scroll,     BorderLayout.CENTER);

        panel.add(topFields, BorderLayout.NORTH);
        panel.add(itemArea,  BorderLayout.CENTER);
        panel.add(bottomBar, BorderLayout.SOUTH);
        return panel;
    }

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