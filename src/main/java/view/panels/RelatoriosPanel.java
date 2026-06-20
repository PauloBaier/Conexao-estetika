package view.panels;

import controllers.relatorio.RelatorioController;
import controllers.relatorio.dto.*;
import view.MenuPrincipalView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RelatoriosPanel extends JPanel {

    private final RelatorioController relatorioController;

    public RelatoriosPanel(RelatorioController relatorioController) {
        this.relatorioController = relatorioController;

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

        JLabel title = new JLabel("Relatórios");
        title.setFont(MenuPrincipalView.FONT_TITLE);
        title.setForeground(MenuPrincipalView.TEXT_DARK);
        header.add(title, BorderLayout.WEST);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(MenuPrincipalView.FONT_BTN);
        tabs.setBackground(MenuPrincipalView.WHITE);

        tabs.addTab("Contas a Receber",  buildContasReceberTab());
        tabs.addTab("Contas a Pagar",    buildContasPagarTab());
        tabs.addTab("Contas Vencidas",   buildContasVencidasTab());
        tabs.addTab("Estoque Baixo",     buildEstoqueBaixoTab());
        tabs.addTab("Todos os Produtos", buildTodosProdutosTab());

        panel.add(header, BorderLayout.NORTH);
        panel.add(tabs,   BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildContasReceberTab() {
        String[] cols = { "ID", "Cliente", "Descrição", "Emissão", "Vencimento", "Pagamento", "Valor", "Status" };
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(45);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (ContaReceberRelatorioResponse c : relatorioController.listarContasReceber()) {
                model.addRow(new Object[]{
                        c.id(), c.cliente(), c.descricao(),
                        c.dataEmissao(), c.dataVencimento(), c.dataPagamento(),
                        String.format("R$ %.2f", c.valor()), c.status()
                });
            }
        };
        refresh.run();
        return buildTabLayout(table, refresh);
    }

    private JPanel buildContasPagarTab() {
        String[] cols = { "ID", "Fornecedor", "Descrição", "Tipo Despesa", "Emissão", "Vencimento", "Valor", "Status" };
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(45);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (ContaPagarRelatorioResponse c : relatorioController.listarContasPagar()) {
                model.addRow(new Object[]{
                        c.id(), c.fornecedor(), c.descricao(), c.tipoDespesas(),
                        c.dataEmissao(), c.dataVencimento(),
                        String.format("R$ %.2f", c.valor()), c.status()
                });
            }
        };
        refresh.run();
        return buildTabLayout(table, refresh);
    }

    private JPanel buildContasVencidasTab() {
        String[] cols = { "ID", "Fornecedor", "Descrição", "Emissão", "Vencimento", "Dias em Atraso", "Valor", "Status" };
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(45);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (ContaPagarVencidaResponse c : relatorioController.listarContasVencidas()) {
                model.addRow(new Object[]{
                        c.id(), c.fornecedor(), c.descricao(),
                        c.dataEmissao(), c.dataVencimento(),
                        c.diasEmAtraso() + " dias",
                        String.format("R$ %.2f", c.valor()), c.status()
                });
            }
        };
        refresh.run();
        return buildTabLayout(table, refresh);
    }

    private JPanel buildEstoqueBaixoTab() {
        String[] cols = { "ID", "Produto", "Categoria", "Estoque Atual", "Estoque Mínimo", "Preço Venda" };
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(45);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (ProdutoRelatorioResponse p : relatorioController.listarEstoqueBaixo()) {
                model.addRow(new Object[]{
                        p.id(), p.nome(), p.categoria(),
                        p.quantidadeEstoque(), p.estoqueMinimo(),
                        String.format("R$ %.2f", p.precoVenda())
                });
            }
        };
        refresh.run();
        return buildTabLayout(table, refresh);
    }

    private JPanel buildTodosProdutosTab() {
        String[] cols = { "ID", "Produto", "Categoria", "Preço Compra", "Preço Venda", "Estoque", "Mínimo" };
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(45);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (ProdutoRelatorioResponse p : relatorioController.listarTodosProdutos()) {
                model.addRow(new Object[]{
                        p.id(), p.nome(), p.categoria(),
                        String.format("R$ %.2f", p.precoCompra()),
                        String.format("R$ %.2f", p.precoVenda()),
                        p.quantidadeEstoque(), p.estoqueMinimo()
                });
            }
        };
        refresh.run();
        return buildTabLayout(table, refresh);
    }

    private JPanel buildTabLayout(JTable table, Runnable refresh) {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(MenuPrincipalView.WHITE);
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel btnBar = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));
        btnBar.setBackground(MenuPrincipalView.WHITE);
        JButton refreshBtn = MenuPrincipalView.createOutlineButton("↻ Atualizar");
        refreshBtn.addActionListener(e -> refresh.run());
        btnBar.add(refreshBtn);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(javax.swing.BorderFactory.createLineBorder(MenuPrincipalView.BORDER_COLOR));

        panel.add(btnBar, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }
}