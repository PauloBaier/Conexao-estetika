package View;

import models.*;
import models.enums.*;
import services.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FinanceiroPanel extends JPanel {

    private final Usuario usuarioLogado;

    // Services injetados pelo construtor — sem dependência de MainFrame
    private final ContaPagarService contaPagarService;
    private final ContaReceberService contaReceberService;
    private final FinanceiroService financeiroService;
    private final CaixaService caixaService;

    private CardLayout cardLayout;
    private JPanel cards;

    // Estado da tela de Entrada — mantido como campos para poder limpar corretamente
    private JTextField fornecedorFieldRef;
    private JTextField produtoFieldRef;
    private DefaultTableModel entradaModelRef;
    private List<ItemVenda> itensEntradaRef;
    private JLabel subtotalEntradaLabelRef;
    private Fornecedor[] fornecedorSelecionadoRef;

    public FinanceiroPanel(
            Usuario usuarioLogado,
            ContaPagarService contaPagarService,
            ContaReceberService contaReceberService,
            FinanceiroService financeiroService,
            CaixaService caixaService
    ) {
        this.usuarioLogado         = usuarioLogado;
        this.contaPagarService     = contaPagarService;
        this.contaReceberService   = contaReceberService;
        this.financeiroService     = financeiroService;
        this.caixaService          = caixaService;

        setLayout(new BorderLayout());
        setBackground(MenuPrincipalView.CONTENT_BG);
        setBorder(new EmptyBorder(24, 24, 24, 24));
        buildUI();
    }

    private void buildUI() {
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        cards.setBackground(MenuPrincipalView.CONTENT_BG);
        cards.add(buildListagemCard(), "listagem");
        add(cards, BorderLayout.CENTER);
        cardLayout.show(cards, "listagem");
    }

    // ─────────────────────────────────────────────
    // CARD PRINCIPAL — abas Contas Pagar / Receber
    // ─────────────────────────────────────────────
    private JPanel buildListagemCard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(MenuPrincipalView.CONTENT_BG);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(MenuPrincipalView.CONTENT_BG);
        header.setBorder(new EmptyBorder(0, 0, 16, 0));

        JLabel title = new JLabel("Financeiro:");
        title.setFont(MenuPrincipalView.FONT_TITLE);
        title.setForeground(MenuPrincipalView.TEXT_DARK);

        JButton entradaBtn = MenuPrincipalView.createAccentButton("Entrada (F1)");
        entradaBtn.addActionListener(e -> cardLayout.show(cards, "entrada"));

        header.add(title, BorderLayout.WEST);
        header.add(entradaBtn, BorderLayout.EAST);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(MenuPrincipalView.FONT_BTN);
        tabs.setBackground(MenuPrincipalView.WHITE);
        tabs.addTab("Contas Pagar",   buildContasPagarTab());
        tabs.addTab("Contas Receber", buildContasReceberTab());

        panel.add(header, BorderLayout.NORTH);
        panel.add(tabs,   BorderLayout.CENTER);

        registerKey(panel, KeyEvent.VK_F1, "abrirEntrada",
                () -> cardLayout.show(cards, "entrada"));

        return panel;
    }

    // ─────────────────────────────────────────────
    // ABA CONTAS A PAGAR
    // ─────────────────────────────────────────────
    private JPanel buildContasPagarTab() {
        String[] cols = {"ID", "Fornecedor", "Descrição", "Emissão", "Vencimento", "Valor", "Status", ""};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(45);
        table.getColumnModel().getColumn(7).setMaxWidth(85);
        table.getColumnModel().getColumn(7).setHeaderValue("");

        table.getColumnModel().getColumn(7).setCellRenderer(
                (t, value, isSelected, hasFocus, row, col) -> {
                    String status = (String) model.getValueAt(row, 6);
                    if (StatusConta.PAGO.name().equals(status)) {
                        JLabel lbl = new JLabel("✔ Pago");
                        lbl.setForeground(MenuPrincipalView.ACCENT_DARK);
                        lbl.setFont(MenuPrincipalView.FONT_SMALL);
                        lbl.setHorizontalAlignment(SwingConstants.CENTER);
                        return lbl;
                    }
                    if (StatusConta.CANCELADO.name().equals(status)) {
                        JLabel lbl = new JLabel("Cancelado");
                        lbl.setForeground(MenuPrincipalView.TEXT_MUTED);
                        lbl.setFont(MenuPrincipalView.FONT_SMALL);
                        lbl.setHorizontalAlignment(SwingConstants.CENTER);
                        return lbl;
                    }
                    return MenuPrincipalView.createAccentButton("Pagar");
                }
        );

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (ContaPagar c : contaPagarService.listar()) {
                model.addRow(new Object[]{
                        c.getId(),
                        c.getFornecedor() != null ? c.getFornecedor().getNome() : "-",
                        c.getDescricao(),
                        c.getDataEmissao()    != null ? c.getDataEmissao().format(fmt)    : "-",
                        c.getDataVencimento() != null ? c.getDataVencimento().format(fmt) : "-",
                        String.format("R$ %.2f", c.getValor()),
                        c.getStatus().name(),
                        "btn"
                });
            }
        };
        refresh.run();

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (col != 7 || row < 0) return;
                String status = (String) model.getValueAt(row, 6);
                if (!StatusConta.PENDENTE.name().equals(status)) return;

                long id = (Long) model.getValueAt(row, 0);
                String fornecedor = (String) model.getValueAt(row, 1);
                String valor = (String) model.getValueAt(row, 5);

                Caixa caixa = caixaService.buscarCaixaAberto();
                if (caixa == null) {
                    JOptionPane.showMessageDialog(FinanceiroPanel.this,
                            "Não há caixa aberto. Abra o caixa antes de pagar contas.",
                            "Caixa fechado", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(FinanceiroPanel.this,
                        String.format("Confirmar pagamento?\n\nFornecedor: %s\nValor: %s\nID: #%d",
                                fornecedor, valor, id),
                        "Pagar conta", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) return;

                try {
                    ContaPagar conta = contaPagarService.buscar(id);
                    financeiroService.pagarConta(conta, caixa, usuarioLogado);
                    refresh.run();
                    JOptionPane.showMessageDialog(FinanceiroPanel.this,
                            "Conta #" + id + " paga com sucesso!", "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FinanceiroPanel.this,
                            "Erro ao pagar conta: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return buildTabLayout(table, refresh);
    }

    // ─────────────────────────────────────────────
    // ABA CONTAS A RECEBER
    // ─────────────────────────────────────────────
    private JPanel buildContasReceberTab() {
        String[] cols = {"ID", "Cliente", "Descrição", "Emissão", "Vencimento", "Valor", "Status", ""};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(45);
        table.getColumnModel().getColumn(7).setMaxWidth(90);
        table.getColumnModel().getColumn(7).setHeaderValue("");

        table.getColumnModel().getColumn(7).setCellRenderer(
                (t, value, isSelected, hasFocus, row, col) -> {
                    String status = (String) model.getValueAt(row, 6);
                    if (StatusConta.PAGO.name().equals(status)) {
                        JLabel lbl = new JLabel("✔ Recebido");
                        lbl.setForeground(MenuPrincipalView.ACCENT_DARK);
                        lbl.setFont(MenuPrincipalView.FONT_SMALL);
                        lbl.setHorizontalAlignment(SwingConstants.CENTER);
                        return lbl;
                    }
                    if (StatusConta.CANCELADO.name().equals(status)) {
                        JLabel lbl = new JLabel("Cancelado");
                        lbl.setForeground(MenuPrincipalView.TEXT_MUTED);
                        lbl.setFont(MenuPrincipalView.FONT_SMALL);
                        lbl.setHorizontalAlignment(SwingConstants.CENTER);
                        return lbl;
                    }
                    return MenuPrincipalView.createAccentButton("Receber");
                }
        );

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (ContaReceber c : contaReceberService.listar()) {
                model.addRow(new Object[]{
                        c.getId(),
                        c.getCliente() != null ? c.getCliente().getNome() : "Sem cliente",
                        c.getDescricao(),
                        c.getDataEmissao()    != null ? c.getDataEmissao().format(fmt)    : "-",
                        c.getDataVencimento() != null ? c.getDataVencimento().format(fmt) : "-",
                        String.format("R$ %.2f", c.getValor()),
                        c.getStatus().name(),
                        "btn"
                });
            }
        };
        refresh.run();

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (col != 7 || row < 0) return;
                String status = (String) model.getValueAt(row, 6);
                if (!StatusConta.PENDENTE.name().equals(status)) return;

                long id = (Long) model.getValueAt(row, 0);
                String cliente = (String) model.getValueAt(row, 1);
                String valor   = (String) model.getValueAt(row, 5);

                Caixa caixa = caixaService.buscarCaixaAberto();
                if (caixa == null) {
                    JOptionPane.showMessageDialog(FinanceiroPanel.this,
                            "Não há caixa aberto. Abra o caixa antes de receber contas.",
                            "Caixa fechado", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(FinanceiroPanel.this,
                        String.format("Confirmar recebimento?\n\nCliente: %s\nValor: %s\nID: #%d",
                                cliente, valor, id),
                        "Receber conta", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) return;

                try {
                    ContaReceber conta = contaReceberService.buscar(id);
                    financeiroService.receberConta(conta, caixa, usuarioLogado);
                    refresh.run();
                    JOptionPane.showMessageDialog(FinanceiroPanel.this,
                            "Conta #" + id + " recebida com sucesso!", "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FinanceiroPanel.this,
                            "Erro ao receber conta: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return buildTabLayout(table, refresh);
    }


    // ─────────────────────────────────────────────
    // HELPERS
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

    private void registerKey(JPanel panel, int keyCode, String actionKey, Runnable action) {
        panel.getInputMap(WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(keyCode, 0), actionKey);
        panel.getActionMap().put(actionKey,
                new AbstractAction() {
                    public void actionPerformed(ActionEvent e) { action.run(); }
                });
    }
}