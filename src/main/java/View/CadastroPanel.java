<<<<<<< HEAD
=======
package View;
>>>>>>> ViewMenuPrincipal
import models.*;
import services.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class CadastroPanel extends JPanel {

    // ── Services ─────────────────────────────────────────────────────────────
    private final ClienteService    clienteService;
    private final EnderecoService   enderecoService;
    private final FornecedorService fornecedorService;
    private final ProdutoService    produtoService;
    private final CategoriaService  categoriaService;

    public CadastroPanel(
            ClienteService    clienteService,
            EnderecoService   enderecoService,
            FornecedorService fornecedorService,
            ProdutoService    produtoService,
            CategoriaService  categoriaService
    ) {
        this.clienteService    = clienteService;
        this.enderecoService   = enderecoService;
        this.fornecedorService = fornecedorService;
        this.produtoService    = produtoService;
        this.categoriaService  = categoriaService;

        setLayout(new BorderLayout());
        setBackground(MenuPrincipalView.CONTENT_BG);
        setBorder(new EmptyBorder(24, 24, 24, 24));

        buildUI();
    }

    private void buildUI() {
        

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(MenuPrincipalView.CONTENT_BG);
        header.setBorder(new EmptyBorder(0, 0, 16, 0));

        JLabel titleLbl = new JLabel("Cadastro:");
        titleLbl.setFont(MenuPrincipalView.FONT_TITLE);
        titleLbl.setForeground(MenuPrincipalView.TEXT_DARK);
        header.add(titleLbl, BorderLayout.WEST);

        // abas
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(MenuPrincipalView.FONT_BTN);
        tabs.setBackground(MenuPrincipalView.WHITE);

        tabs.addTab("Clientes",    buildClienteTab());
        tabs.addTab("Fornecedores", buildFornecedorTab());
        tabs.addTab("Produtos",    buildProdutoTab());
        tabs.addTab("Categorias",  buildCategoriaTab());

        add(header, BorderLayout.NORTH);
        add(tabs,   BorderLayout.CENTER);
    }
// aba cliente
    private JPanel buildClienteTab() {

        String[] cols = { "ID", "Nome", "Telefone", "E-mail", "CPF" };

        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(50);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (Cliente c : clienteService.listarTodos()) {
                model.addRow(new Object[]{
                        c.getId(),
                        c.getNome(),
                        c.getTelefone(),
                        c.getEmail(),
                        c.getCpf()
                });
            }
        };
        refresh.run();

        // botões
        JButton btnNovo     = MenuPrincipalView.createAccentButton("Novo Cadastro");
        JButton btnAtualizar = MenuPrincipalView.createOutlineButton("Atualizar Cadastro");
        JButton btnDeletar   = MenuPrincipalView.createDangerButton("Deletar Cadastro");
        JButton btnRefresh   = MenuPrincipalView.createOutlineButton("↻ Atualizar");

        btnNovo.addActionListener(e -> {
            dialogNovoCliente(refresh);
        });

        btnAtualizar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { aviso("Selecione um cliente na tabela."); return; }
            Long id = (Long) model.getValueAt(row, 0);
            Cliente c = clienteService.buscarPorId(id);
            if (c != null) dialogEditarCliente(c, refresh);
        });

        btnDeletar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { aviso("Selecione um cliente na tabela."); return; }
            Long id = (Long) model.getValueAt(row, 0);
            if (confirmar("Deseja realmente deletar este cliente?")) {
                try {
                    clienteService.deletar(id);
                    refresh.run();
                    sucesso("Cliente removido com sucesso!");
                } catch (Exception ex) {
                    erro(ex.getMessage());
                }
            }
        });

        btnRefresh.addActionListener(e -> refresh.run());

        return buildTabLayout(table, refresh, btnNovo, btnAtualizar, btnDeletar, btnRefresh);
    }

    private void dialogNovoCliente(Runnable refresh) {
        JDialog dlg = createDialog("Novo Cliente", 420, 520);

        JTextField fNome     = field();
        JTextField fTelefone = field();
        JTextField fEmail    = field();
        JTextField fCpf      = field();
        JTextField fRua      = field();
        JTextField fBairro   = field();
        JTextField fNumero   = field();
        JTextField fCep      = field();

        JPanel form = buildForm(
                "Nome *",      fNome,
                "Telefone *",  fTelefone,
                "E-mail *",    fEmail,
                "CPF (11 dígitos) *", fCpf,
                "── Endereço ──", null,
                "Rua *",       fRua,
                "Bairro *",    fBairro,
                "Número *",    fNumero,
                "CEP (8 dígitos) *", fCep
        );

        JButton btnSalvar = MenuPrincipalView.createAccentButton("Salvar");
        btnSalvar.addActionListener(e -> {
            try {
                Cliente c = new Cliente();
                c.setNome(fNome.getText().trim());
                c.setTelefone(fTelefone.getText().trim());
                c.setEmail(fEmail.getText().trim());
                c.setCpf(fCpf.getText().trim());
                clienteService.cadastrar(c);

                Endereco end = new Endereco();
                end.setRua(fRua.getText().trim());
                end.setBairro(fBairro.getText().trim());
                end.setNumero(fNumero.getText().trim());
                end.setCep(fCep.getText().trim());
                enderecoService.cadastrarEndereco(end, c.getId());

                refresh.run();
                sucesso("Cliente cadastrado com sucesso!");
                dlg.dispose();
            } catch (Exception ex) {
                erro(ex.getMessage());
            }
        });

        layoutDialog(dlg, form, btnSalvar);
    }

    private void dialogEditarCliente(Cliente c, Runnable refresh) {
        JDialog dlg = createDialog("Atualizar Cliente", 420, 380);

        JTextField fNome     = field(c.getNome());
        JTextField fTelefone = field(c.getTelefone());
        JTextField fEmail    = field(c.getEmail());
        JTextField fCpf      = field(c.getCpf());

        JPanel form = buildForm(
                "Nome *",     fNome,
                "Telefone *", fTelefone,
                "E-mail *",   fEmail,
                "CPF (11 dígitos) *", fCpf
        );

        JButton btnSalvar = MenuPrincipalView.createAccentButton("Salvar Alterações");
        btnSalvar.addActionListener(e -> {
            try {
                c.setNome(fNome.getText().trim());
                c.setTelefone(fTelefone.getText().trim());
                c.setEmail(fEmail.getText().trim());
                c.setCpf(fCpf.getText().trim());
                clienteService.atualizar(c);
                refresh.run();
                sucesso("Cliente atualizado com sucesso!");
                dlg.dispose();
            } catch (Exception ex) {
                erro(ex.getMessage());
            }
        });

        layoutDialog(dlg, form, btnSalvar);
    }
// fornecedor
    private JPanel buildFornecedorTab() {

        String[] cols = { "ID", "Nome", "Telefone", "E-mail", "CNPJ", "Razão Social" };

        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(50);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (Fornecedor f : fornecedorService.listarTodos()) {
                model.addRow(new Object[]{
                        f.getId(),
                        f.getNome(),
                        f.getTelefone(),
                        f.getEmail(),
                        f.getCnpj(),
                        f.getRazaoSocial()
                });
            }
        };
        refresh.run();

        JButton btnNovo      = MenuPrincipalView.createAccentButton("Novo Cadastro");
        JButton btnAtualizar = MenuPrincipalView.createOutlineButton("Atualizar Cadastro");
        JButton btnDeletar   = MenuPrincipalView.createDangerButton("Deletar Cadastro");
        JButton btnRefresh   = MenuPrincipalView.createOutlineButton("↻ Atualizar");

        btnNovo.addActionListener(e -> dialogNovoFornecedor(refresh));

        btnAtualizar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { aviso("Selecione um fornecedor na tabela."); return; }
            Long id = (Long) model.getValueAt(row, 0);
            Fornecedor f = fornecedorService.buscarPorId(id);
            if (f != null) dialogEditarFornecedor(f, refresh);
        });

        btnDeletar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { aviso("Selecione um fornecedor na tabela."); return; }
            Long id = (Long) model.getValueAt(row, 0);
            if (confirmar("Deseja realmente deletar este fornecedor?")) {
                try {
                    Fornecedor f = fornecedorService.buscarPorId(id);
                    fornecedorService.delete(f);
                    refresh.run();
                    sucesso("Fornecedor removido com sucesso!");
                } catch (Exception ex) {
                    erro(ex.getMessage());
                }
            }
        });

        btnRefresh.addActionListener(e -> refresh.run());

        return buildTabLayout(table, refresh, btnNovo, btnAtualizar, btnDeletar, btnRefresh);
    }

    private void dialogNovoFornecedor(Runnable refresh) {
        JDialog dlg = createDialog("Novo Fornecedor", 420, 440);

        JTextField fNome    = field();
        JTextField fTel     = field();
        JTextField fEmail   = field();
        JTextField fCnpj    = field();
        JTextField fRazao   = field();

        JPanel form = buildForm(
                "Nome *",          fNome,
                "Telefone *",      fTel,
                "E-mail *",        fEmail,
                "CNPJ (14 dígitos) *", fCnpj,
                "Razão Social *",  fRazao
        );

        JButton btnSalvar = MenuPrincipalView.createAccentButton("Salvar");
        btnSalvar.addActionListener(e -> {
            try {
                Fornecedor f = new Fornecedor();
                f.setNome(fNome.getText().trim());
                f.setTelefone(fTel.getText().trim());
                f.setEmail(fEmail.getText().trim());
                f.setCnpj(fCnpj.getText().trim());
                f.setRazaoSocial(fRazao.getText().trim());
                fornecedorService.salvar(f);
                refresh.run();
                sucesso("Fornecedor cadastrado com sucesso!");
                dlg.dispose();
            } catch (Exception ex) {
                erro(ex.getMessage());
            }
        });

        layoutDialog(dlg, form, btnSalvar);
    }

    private void dialogEditarFornecedor(Fornecedor f, Runnable refresh) {
        JDialog dlg = createDialog("Atualizar Fornecedor", 420, 440);

        JTextField fNome  = field(f.getNome());
        JTextField fTel   = field(f.getTelefone());
        JTextField fEmail = field(f.getEmail());
        JTextField fCnpj  = field(f.getCnpj());
        JTextField fRazao = field(f.getRazaoSocial());

        JPanel form = buildForm(
                "Nome *",          fNome,
                "Telefone *",      fTel,
                "E-mail *",        fEmail,
                "CNPJ (14 dígitos) *", fCnpj,
                "Razão Social *",  fRazao
        );

        JButton btnSalvar = MenuPrincipalView.createAccentButton("Salvar Alterações");
        btnSalvar.addActionListener(e -> {
            try {
                f.setNome(fNome.getText().trim());
                f.setTelefone(fTel.getText().trim());
                f.setEmail(fEmail.getText().trim());
                f.setCnpj(fCnpj.getText().trim());
                f.setRazaoSocial(fRazao.getText().trim());
                fornecedorService.atualizar(f);
                refresh.run();
                sucesso("Fornecedor atualizado com sucesso!");
                dlg.dispose();
            } catch (Exception ex) {
                erro(ex.getMessage());
            }
        });

        layoutDialog(dlg, form, btnSalvar);
    }

    private JPanel buildProdutoTab() {

        String[] cols = { "ID", "Nome", "Preço Compra", "Preço Venda", "Estoque", "Estoque Mín.", "Categoria" };

        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(50);

        // Destaca linha vermelha se estoque ≤ mínimo
        table.setDefaultRenderer(Object.class, (t, value, isSelected, hasFocus, row, col) -> {
            JLabel lbl = new JLabel(value != null ? value.toString() : "");
            lbl.setOpaque(true);
            lbl.setBorder(new EmptyBorder(0, 8, 0, 8));

            int estoque  = 0;
            int estoqueMin = 0;
            try {
                estoque    = Integer.parseInt(model.getValueAt(row, 4).toString());
                estoqueMin = Integer.parseInt(model.getValueAt(row, 5).toString());
            } catch (Exception ignored) {}

            boolean baixo = estoque <= estoqueMin;

            if (isSelected) {
                lbl.setBackground(MenuPrincipalView.ACCENT);
                lbl.setForeground(MenuPrincipalView.WHITE);
            } else if (baixo) {
                lbl.setBackground(new Color(0xFFF3F3));
                lbl.setForeground(new Color(0xC0392B));
            } else {
                lbl.setBackground(row % 2 == 0 ? MenuPrincipalView.WHITE : MenuPrincipalView.CONTENT_BG);
                lbl.setForeground(MenuPrincipalView.TEXT_DARK);
            }
            return lbl;
        });

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (Produto p : produtoService.listarTodos()) {
                model.addRow(new Object[]{
                        p.getId(),
                        p.getNome(),
                        String.format("R$ %.2f", p.getPrecoCompra()),
                        String.format("R$ %.2f", p.getPrecoVenda()),
                        p.getQuantidadeEstoque(),
                        p.getEstoqueMinimo(),
                        p.getCategoria() != null ? p.getCategoria().getNome() : "-"
                });
            }
        };
        refresh.run();

        JButton btnNovo      = MenuPrincipalView.createAccentButton("Novo Cadastro");
        JButton btnAtualizar = MenuPrincipalView.createOutlineButton("Atualizar Cadastro");
        JButton btnDeletar   = MenuPrincipalView.createDangerButton("Deletar Cadastro");
        JButton btnRefresh   = MenuPrincipalView.createOutlineButton("↻ Atualizar");

        btnNovo.addActionListener(e -> dialogNovoProduto(refresh));

        btnAtualizar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { aviso("Selecione um produto na tabela."); return; }
            Long id = (Long) model.getValueAt(row, 0);
            Produto p = produtoService.buscarPorId(id);
            if (p != null) dialogEditarProduto(p, refresh);
        });

        btnDeletar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { aviso("Selecione um produto na tabela."); return; }
            Long id = (Long) model.getValueAt(row, 0);
            if (confirmar("Deseja realmente deletar este produto?")) {
                try {
                    Produto p = produtoService.buscarPorId(id);
                    produtoService.delete(p);
                    refresh.run();
                    sucesso("Produto removido com sucesso!");
                } catch (Exception ex) {
                    erro(ex.getMessage());
                }
            }
        });

        btnRefresh.addActionListener(e -> refresh.run());

        return buildTabLayout(table, refresh, btnNovo, btnAtualizar, btnDeletar, btnRefresh);
    }

    private void dialogNovoProduto(Runnable refresh) {
        JDialog dlg = createDialog("Novo Produto", 440, 520);

        JTextField fNome     = field();
        JTextField fCompra   = field();
        JTextField fVenda    = field();
        JTextField fEstoque  = field();
        JTextField fMinimo   = field();

        List<Categoria> categorias = categoriaService.listarTodas();
        JComboBox<String> cbCategoria = new JComboBox<>();
        for (Categoria cat : categorias) cbCategoria.addItem(cat.getId() + " – " + cat.getNome());

        List<Fornecedor> fornecedores = fornecedorService.listarTodos();
        JComboBox<String> cbFornecedor = new JComboBox<>();
        for (Fornecedor f : fornecedores) cbFornecedor.addItem(f.getId() + " – " + f.getNome());

        styleCombo(cbCategoria);
        styleCombo(cbFornecedor);

        JPanel form = buildForm(
                "Nome *",            fNome,
                "Preço de Compra *", fCompra,
                "Preço de Venda *",  fVenda,
                "Qtd. Estoque *",    fEstoque,
                "Estoque Mínimo *",  fMinimo,
                "Categoria *",       cbCategoria,
                "Fornecedor *",      cbFornecedor
        );

        JButton btnSalvar = MenuPrincipalView.createAccentButton("Salvar");
        btnSalvar.addActionListener(e -> {
            try {
                if (categorias.isEmpty()) { aviso("Cadastre uma categoria primeiro."); return; }
                if (fornecedores.isEmpty()) { aviso("Cadastre um fornecedor primeiro."); return; }

                Categoria cat = categorias.get(cbCategoria.getSelectedIndex());
                Fornecedor forn = fornecedores.get(cbFornecedor.getSelectedIndex());

                Produto p = new Produto();
                p.setNome(fNome.getText().trim());
                p.setPrecoCompra(Double.parseDouble(fCompra.getText().trim().replace(",", ".")));
                p.setPrecoVenda(Double.parseDouble(fVenda.getText().trim().replace(",", ".")));
                p.setQuantidadeEstoque(Integer.parseInt(fEstoque.getText().trim()));
                p.setEstoqueMinimo(Integer.parseInt(fMinimo.getText().trim()));
                p.setCategoria(cat);
                p.adicionarFornecedor(forn);

                produtoService.salvar(p);
                refresh.run();
                sucesso("Produto cadastrado com sucesso!");
                dlg.dispose();
            } catch (NumberFormatException ex) {
                erro("Valores numéricos inválidos. Use ponto (.) como separador decimal.");
            } catch (Exception ex) {
                erro(ex.getMessage());
            }
        });

        layoutDialog(dlg, form, btnSalvar);
    }

    private void dialogEditarProduto(Produto p, Runnable refresh) {
        JDialog dlg = createDialog("Atualizar Produto", 440, 520);

        JTextField fNome    = field(p.getNome());
        JTextField fCompra  = field(String.valueOf(p.getPrecoCompra()));
        JTextField fVenda   = field(String.valueOf(p.getPrecoVenda()));
        JTextField fEstoque = field(String.valueOf(p.getQuantidadeEstoque()));
        JTextField fMinimo  = field(String.valueOf(p.getEstoqueMinimo()));

        List<Categoria> categorias = categoriaService.listarTodas();
        JComboBox<String> cbCategoria = new JComboBox<>();
        int catIdx = 0;
        for (int i = 0; i < categorias.size(); i++) {
            Categoria cat = categorias.get(i);
            cbCategoria.addItem(cat.getId() + " – " + cat.getNome());
            if (p.getCategoria() != null && cat.getId().equals(p.getCategoria().getId())) catIdx = i;
        }
        cbCategoria.setSelectedIndex(catIdx);
        styleCombo(cbCategoria);

        List<Fornecedor> fornecedores = fornecedorService.listarTodos();
        JComboBox<String> cbFornecedor = new JComboBox<>();
        int fornIdx = 0;
        for (int i = 0; i < fornecedores.size(); i++) {
            Fornecedor f = fornecedores.get(i);
            cbFornecedor.addItem(f.getId() + " – " + f.getNome());
            if (!p.getFornecedores().isEmpty() && f.getId().equals(p.getFornecedores().get(0).getId())) fornIdx = i;
        }
        cbFornecedor.setSelectedIndex(fornIdx);
        styleCombo(cbFornecedor);

        JPanel form = buildForm(
                "Nome *",            fNome,
                "Preço de Compra *", fCompra,
                "Preço de Venda *",  fVenda,
                "Qtd. Estoque *",    fEstoque,
                "Estoque Mínimo *",  fMinimo,
                "Categoria *",       cbCategoria,
                "Fornecedor *",      cbFornecedor
        );

        JButton btnSalvar = MenuPrincipalView.createAccentButton("Salvar Alterações");
        btnSalvar.addActionListener(e -> {
            try {
                p.setNome(fNome.getText().trim());
                p.setPrecoCompra(Double.parseDouble(fCompra.getText().trim().replace(",", ".")));
                p.setPrecoVenda(Double.parseDouble(fVenda.getText().trim().replace(",", ".")));
                p.setQuantidadeEstoque(Integer.parseInt(fEstoque.getText().trim()));
                p.setEstoqueMinimo(Integer.parseInt(fMinimo.getText().trim()));

                if (!categorias.isEmpty())
                    p.setCategoria(categorias.get(cbCategoria.getSelectedIndex()));

                if (!fornecedores.isEmpty()) {
                    p.getFornecedores().clear();
                    p.adicionarFornecedor(fornecedores.get(cbFornecedor.getSelectedIndex()));
                }

                produtoService.atualizar(p);
                refresh.run();
                sucesso("Produto atualizado com sucesso!");
                dlg.dispose();
            } catch (NumberFormatException ex) {
                erro("Valores numéricos inválidos. Use ponto (.) como separador decimal.");
            } catch (Exception ex) {
                erro(ex.getMessage());
            }
        });

        layoutDialog(dlg, form, btnSalvar);
    }
// categoria
    private JPanel buildCategoriaTab() {

        String[] cols = { "ID", "Nome" };

        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        MenuPrincipalView.setupTable(table);
        table.getColumnModel().getColumn(0).setMaxWidth(60);

        Runnable refresh = () -> {
            model.setRowCount(0);
            for (Categoria c : categoriaService.listarTodas()) {
                model.addRow(new Object[]{ c.getId(), c.getNome() });
            }
        };
        refresh.run();

        JButton btnNovo      = MenuPrincipalView.createAccentButton("Novo Cadastro");
        JButton btnAtualizar = MenuPrincipalView.createOutlineButton("Atualizar Cadastro");
        JButton btnDeletar   = MenuPrincipalView.createDangerButton("Deletar Cadastro");
        JButton btnRefresh   = MenuPrincipalView.createOutlineButton("↻ Atualizar");

        btnNovo.addActionListener(e -> {
            JDialog dlg = createDialog("Nova Categoria", 380, 220);
            JTextField fNome = field();
            JPanel form = buildForm("Nome da Categoria *", fNome);
            JButton btnSalvar = MenuPrincipalView.createAccentButton("Salvar");
            btnSalvar.addActionListener(ev -> {
                try {
                    Categoria cat = new Categoria();
                    cat.setNome(fNome.getText().trim());
                    categoriaService.cadastrar(cat);
                    refresh.run();
                    sucesso("Categoria cadastrada com sucesso!");
                    dlg.dispose();
                } catch (Exception ex) { erro(ex.getMessage()); }
            });
            layoutDialog(dlg, form, btnSalvar);
        });

        btnAtualizar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { aviso("Selecione uma categoria na tabela."); return; }
            Long id   = (Long)   model.getValueAt(row, 0);
            String nm = (String) model.getValueAt(row, 1);

            JDialog dlg = createDialog("Atualizar Categoria", 380, 220);
            JTextField fNome = field(nm);
            JPanel form = buildForm("Nome da Categoria *", fNome);
            JButton btnSalvar = MenuPrincipalView.createAccentButton("Salvar Alterações");
            btnSalvar.addActionListener(ev -> {
                try {
                    Categoria cat = new Categoria();
                    cat.setNome(fNome.getText().trim());
                    categoriaService.atualizar(id, cat);
                    refresh.run();
                    sucesso("Categoria atualizada com sucesso!");
                    dlg.dispose();
                } catch (Exception ex) { erro(ex.getMessage()); }
            });
            layoutDialog(dlg, form, btnSalvar);
        });

        btnDeletar.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { aviso("Selecione uma categoria na tabela."); return; }
            Long id = (Long) model.getValueAt(row, 0);
            if (confirmar("Deseja realmente deletar esta categoria?")) {
                try {
                    categoriaService.deletar(id);
                    refresh.run();
                    sucesso("Categoria removida com sucesso!");
                } catch (Exception ex) { erro(ex.getMessage()); }
            }
        });

        btnRefresh.addActionListener(e -> refresh.run());

        return buildTabLayout(table, refresh, btnNovo, btnAtualizar, btnDeletar, btnRefresh);
    }

    private JPanel buildTabLayout(JTable table, Runnable refresh,
                                  JButton btnNovo, JButton btnAtualizar,
                                  JButton btnDeletar, JButton btnRefresh) {

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(MenuPrincipalView.WHITE);
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));

        // Barra de botões
        JPanel btnBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnBar.setBackground(MenuPrincipalView.WHITE);
        btnBar.add(btnNovo);
        btnBar.add(btnAtualizar);
        btnBar.add(btnDeletar);
        btnBar.add(btnRefresh);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(MenuPrincipalView.BORDER_COLOR));

        panel.add(btnBar, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JDialog createDialog(String title, int w, int h) {
        JDialog dlg = new JDialog(
                (Frame) SwingUtilities.getWindowAncestor(this),
                title, true
        );
        dlg.setSize(w, h);
        dlg.setLocationRelativeTo(this);
        dlg.setResizable(false);
        dlg.getContentPane().setBackground(MenuPrincipalView.CONTENT_BG);
        dlg.setLayout(new BorderLayout());
        return dlg;
    }

  
    private JPanel buildForm(Object... pairs) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(MenuPrincipalView.WHITE);
        panel.setBorder(new EmptyBorder(16, 20, 8, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx   = 0;
        gbc.insets  = new Insets(4, 0, 4, 0);

        int row = 0;
        for (int i = 0; i < pairs.length; i += 2) {
            String label = pairs[i].toString();

            if (i + 1 >= pairs.length || pairs[i + 1] == null) {
                // Separador / título de seção
                JLabel lbl = new JLabel(label);
                lbl.setFont(new Font("SansSerif", Font.BOLD, 11));
                lbl.setForeground(MenuPrincipalView.TEXT_MUTED);
                gbc.gridy  = row++;
                gbc.insets = new Insets(12, 0, 2, 0);
                panel.add(lbl, gbc);
                gbc.insets = new Insets(4, 0, 4, 0);
                continue;
            }

            JLabel lbl = new JLabel(label);
            lbl.setFont(MenuPrincipalView.FONT_LABEL);
            lbl.setForeground(MenuPrincipalView.TEXT_DARK);
            gbc.gridy = row++;
            panel.add(lbl, gbc);

            JComponent comp = (JComponent) pairs[i + 1];
            gbc.gridy = row++;
            panel.add(comp, gbc);
        }

        return panel;
    }

    private void layoutDialog(JDialog dlg, JPanel form, JButton btnSalvar) {
        JScrollPane scroll = new JScrollPane(form);
        scroll.setBorder(null);
        scroll.setBackground(MenuPrincipalView.WHITE);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 8));
        footer.setBackground(MenuPrincipalView.CONTENT_BG);

        JButton btnCancelar = MenuPrincipalView.createOutlineButton("Cancelar");
        btnCancelar.addActionListener(e -> dlg.dispose());

        footer.add(btnCancelar);
        footer.add(btnSalvar);

        dlg.add(scroll,  BorderLayout.CENTER);
        dlg.add(footer,  BorderLayout.SOUTH);
        dlg.setVisible(true);
    }

    private JTextField field() {
        return field("");
    }

    private JTextField field(String value) {
        JTextField tf = new JTextField(value);
        tf.setFont(MenuPrincipalView.FONT_FIELD != null
                ? MenuPrincipalView.FONT_FIELD
                : new Font("SansSerif", Font.PLAIN, 13));
        tf.setForeground(MenuPrincipalView.TEXT_DARK);
        tf.setBackground(MenuPrincipalView.WHITE);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(MenuPrincipalView.BORDER_COLOR, 1, true),
                new EmptyBorder(5, 8, 5, 8)
        ));
        tf.setPreferredSize(new Dimension(0, 34));
        // realce de foco
        tf.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                tf.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(MenuPrincipalView.ACCENT, 2, true),
                        new EmptyBorder(4, 7, 4, 7)
                ));
            }
            @Override public void focusLost(FocusEvent e) {
                tf.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(MenuPrincipalView.BORDER_COLOR, 1, true),
                        new EmptyBorder(5, 8, 5, 8)
                ));
            }
        });
        return tf;
    }

    private void styleCombo(JComboBox<?> cb) {
        cb.setFont(new Font("SansSerif", Font.PLAIN, 13));
        cb.setBackground(MenuPrincipalView.WHITE);
        cb.setForeground(MenuPrincipalView.TEXT_DARK);
        cb.setPreferredSize(new Dimension(0, 34));
    }

    
    private void aviso(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    private void sucesso(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void erro(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private boolean confirmar(String msg) {
        return JOptionPane.showConfirmDialog(this, msg, "Confirmar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> ViewMenuPrincipal
