package View;

import models.*;
import models.enums.*;
import services.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RelatoriosPanel extends JPanel {

    private final RelatorioLocal    relatorio;
    private final ProdutoService    produtoService;

    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatoriosPanel(
            RelatorioLocal  relatorio,
            ProdutoService  produtoService
    ) {
        this.relatorio      = relatorio;
        this.produtoService = produtoService;

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

    // ─────────────────────────────────────────────
    // ABA — CONTAS A RECEBER
    // ─────────────────────────────────────────────
    private JPanel buildContasReceberTab() {

        String[] cols = {
                "ID", "Cliente", "Descrição",
                "Emissão", "Vencimento", "Pagamento",
                "Valor", "Status"
        };

        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(45);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (ContaReceber c : relatorio.contasReceber(null, null, null)) {
                model.addRow(new Object[]{
                        c.getId(),
                        c.getCliente() != null ? c.getCliente().getNome() : "Sem cliente",
                        c.getDescricao(),
                        c.getDataEmissao()    != null ? c.getDataEmissao().format(fmt)    : "-",
                        c.getDataVencimento() != null ? c.getDataVencimento().format(fmt) : "-",
                        c.getDataPagamento()  != null ? c.getDataPagamento().format(fmt)  : "-",
                        String.format("R$ %.2f", c.getValor()),
                        c.getStatus().name()
                });
            }
        };

        refresh.run();

        return buildTabLayout(table, refresh);
    }

    // ─────────────────────────────────────────────
    // ABA — CONTAS A PAGAR
    // ─────────────────────────────────────────────
    private JPanel buildContasPagarTab() {

        String[] cols = {
                "ID", "Fornecedor", "Descrição", "Tipo Despesa",
                "Emissão", "Vencimento", "Valor", "Status"
        };

        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(45);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (ContaPagar c : relatorio.contasPagar(null, null, null)) {
                model.addRow(new Object[]{
                        c.getId(),
                        c.getFornecedor() != null ? c.getFornecedor().getNome() : "-",
                        c.getDescricao(),
                        c.getTipoDespesas(),
                        c.getDataEmissao()    != null ? c.getDataEmissao().format(fmt)    : "-",
                        c.getDataVencimento() != null ? c.getDataVencimento().format(fmt) : "-",
                        String.format("R$ %.2f", c.getValor()),
                        c.getStatus().name()
                });
            }
        };

        refresh.run();

        return buildTabLayout(table, refresh);
    }

    // ─────────────────────────────────────────────
    // ABA — CONTAS VENCIDAS
    // ─────────────────────────────────────────────
    private JPanel buildContasVencidasTab() {

        String[] cols = {
                "ID", "Fornecedor", "Descrição",
                "Emissão", "Vencimento", "Dias em Atraso",
                "Valor", "Status"
        };

        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(45);

        Runnable refresh = () -> {
            model.setRowCount(0);
            LocalDate hoje = LocalDate.now();

            for (ContaPagar c : relatorio.contasPagar(null, null, null)) {
                if (c.getDataVencimento() == null) continue;
                if (c.getStatus() == StatusConta.PAGO)      continue;
                if (!c.getDataVencimento().isBefore(hoje))  continue;

                long dias = java.time.temporal.ChronoUnit.DAYS
                        .between(c.getDataVencimento(), hoje);

                model.addRow(new Object[]{
                        c.getId(),
                        c.getFornecedor() != null ? c.getFornecedor().getNome() : "-",
                        c.getDescricao(),
                        c.getDataEmissao().format(fmt),
                        c.getDataVencimento().format(fmt),
                        dias + " dias",
                        String.format("R$ %.2f", c.getValor()),
                        c.getStatus().name()
                });
            }
        };

        refresh.run();

        return buildTabLayout(table, refresh);
    }

    // ─────────────────────────────────────────────
    // ABA — ESTOQUE BAIXO
    // ─────────────────────────────────────────────
    private JPanel buildEstoqueBaixoTab() {

        String[] cols = {
                "ID", "Produto", "Categoria",
                "Estoque Atual", "Estoque Mínimo",
                "Preço Venda"
        };

        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(45);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (Produto p : relatorio.produtosEstoqueBaixo()) {
                model.addRow(new Object[]{
                        p.getId(),
                        p.getNome(),
                        p.getCategoria() != null ? p.getCategoria().getNome() : "-",
                        p.getQuantidadeEstoque(),
                        p.getEstoqueMinimo(),
                        String.format("R$ %.2f", p.getPrecoVenda())
                });
            }
        };

        refresh.run();

        return buildTabLayout(table, refresh);
    }

    // ─────────────────────────────────────────────
    // ABA — TODOS OS PRODUTOS
    // ─────────────────────────────────────────────
    private JPanel buildTodosProdutosTab() {

        String[] cols = {
                "ID", "Produto", "Categoria",
                "Preço Compra", "Preço Venda",
                "Estoque", "Mínimo"
        };

        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(45);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (Produto p : produtoService.listarTodos()) {
                model.addRow(new Object[]{
                        p.getId(),
                        p.getNome(),
                        p.getCategoria() != null ? p.getCategoria().getNome() : "-",
                        String.format("R$ %.2f", p.getPrecoCompra()),
                        String.format("R$ %.2f", p.getPrecoVenda()),
                        p.getQuantidadeEstoque(),
                        p.getEstoqueMinimo()
                });
            }
        };

        refresh.run();

        return buildTabLayout(table, refresh);
    }

    // ─────────────────────────────────────────────
    // HELPER — layout de aba com botão atualizar
    // ─────────────────────────────────────────────
    private JPanel buildTabLayout(JTable table, Runnable refresh) {

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(MenuPrincipalView.WHITE);
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));

        JPanel btnBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnBar.setBackground(MenuPrincipalView.WHITE);

        JButton refreshBtn = MenuPrincipalView.createOutlineButton("↻ Atualizar");
        refreshBtn.addActionListener(e -> refresh.run());
        btnBar.add(refreshBtn);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(MenuPrincipalView.BORDER_COLOR));

        panel.add(btnBar, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }
}