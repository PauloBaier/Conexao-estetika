package view;
import models.Usuario;
import services.UsuarioService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;


public class LoginView extends JFrame {

    // Paleta
    private static final Color SIDEBAR_BG   = new Color(0x00695C);
    private static final Color ACCENT       = new Color(0x00897B);
    private static final Color ACCENT_DARK  = new Color(0x00695C);
    private static final Color GOLD         = new Color(0xC9A84C);
    private static final Color WHITE        = Color.WHITE;
    private static final Color CONTENT_BG   = new Color(0xEFF6F5);
    private static final Color TEXT_DARK    = new Color(0x1A2E2A);
    private static final Color TEXT_MUTED   = new Color(0x6B8C87);
    private static final Color BORDER_COLOR = new Color(0xCCDEDC);
    private static final Color ERROR_COLOR  = new Color(0xC0392B);

    // Fontes 
    private static final Font FONT_BRAND  = new Font("Serif",       Font.BOLD,  22);
    private static final Font FONT_TITLE  = new Font("SansSerif",   Font.BOLD,  20);
    private static final Font FONT_LABEL  = new Font("SansSerif",   Font.PLAIN, 13);
    private static final Font FONT_FIELD  = new Font("SansSerif",   Font.PLAIN, 14);
    private static final Font FONT_BTN    = new Font("SansSerif",   Font.BOLD,  14);
    private static final Font FONT_SMALL  = new Font("SansSerif",   Font.PLAIN, 11);

    private final UsuarioService usuarioService;
    private final LoginCallback  onLoginSuccess;

    private JTextField     emailField;
    private JPasswordField senhaField;
    private JLabel         erroLabel;
    private int            tentativas = 0;

    @FunctionalInterface
    public interface LoginCallback {
        void onSuccess(Usuario usuario);
    }

    public LoginView(UsuarioService usuarioService, LoginCallback onLoginSuccess) {
        this.usuarioService  = usuarioService;
        this.onLoginSuccess  = onLoginSuccess;

        setTitle("Conexão Estétika – Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(860, 540);
        setLocationRelativeTo(null);
        setUndecorated(true);          // janela sem borda nativa
        setShape(new RoundRectangle2D.Double(0, 0, 860, 540, 20, 20));

        buildUI();
        setVisible(true);
    }

    private void buildUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(WHITE);
        setContentPane(root);

        // ── Painel esquerdo (sidebar / branding) ──────────────────────────
        root.add(buildSidebar(), BorderLayout.WEST);

        // Painel direito 
        root.add(buildForm(), BorderLayout.CENTER);

        // Barra de título customizada (arrastar janela) ─
        addDragSupport(root);
    }

    //  Sidebar
    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                // gradiente vertical
                GradientPaint gp = new GradientPaint(
                        0, 0, ACCENT_DARK,
                        0, getHeight(), new Color(0x004D40)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        sidebar.setPreferredSize(new Dimension(300, 0));
        sidebar.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx  = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 12, 0);

        // Ícone / logo
        JLabel iconLbl = new JLabel("✦") {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                int size = Math.min(getWidth(), getHeight());
                g2.setColor(GOLD);
                g2.fillOval(0, 0, size, size);
                g2.setColor(WHITE);
                g2.setFont(new Font("Serif", Font.BOLD, size / 2));
                FontMetrics fm = g2.getFontMetrics();
                String txt = "CE";
                int x = (size - fm.stringWidth(txt)) / 2;
                int y = (size + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(txt, x, y);
                g2.dispose();
            }
        };
        iconLbl.setPreferredSize(new Dimension(80, 80));

        gbc.gridy  = 0;
        gbc.insets = new Insets(0, 0, 20, 0);
        sidebar.add(iconLbl, gbc);

        // Nome do sistema
        JLabel brandLbl = new JLabel("CONEXÃO");
        brandLbl.setFont(FONT_BRAND);
        brandLbl.setForeground(WHITE);
        gbc.gridy  = 1;
        gbc.insets = new Insets(0, 0, 2, 0);
        sidebar.add(brandLbl, gbc);

        JLabel brand2Lbl = new JLabel("ESTÉTIKA");
        brand2Lbl.setFont(new Font("Serif", Font.BOLD, 18));
        brand2Lbl.setForeground(GOLD);
        gbc.gridy  = 2;
        gbc.insets = new Insets(0, 0, 32, 0);
        sidebar.add(brand2Lbl, gbc);

        // Separador decorativo
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(255, 255, 255, 60));
        sep.setPreferredSize(new Dimension(160, 1));
        gbc.gridy  = 3;
        gbc.insets = new Insets(0, 0, 28, 0);
        sidebar.add(sep, gbc);

        
        JLabel slogan = new JLabel("<html><center>Sistema de Gestão<br>de Estética</center></html>");
        slogan.setFont(FONT_SMALL);
        slogan.setForeground(new Color(255, 255, 255, 170));
        slogan.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy  = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        sidebar.add(slogan, gbc);

        return sidebar;
    }

    //Formulário
    private JPanel buildForm() {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(CONTENT_BG);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(WHITE);
        card.setBorder(new EmptyBorder(40, 44, 40, 44));
        card.setPreferredSize(new Dimension(420, 400));

        // Botão fechar 
        JPanel closeBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        closeBar.setBackground(WHITE);
        JButton closeBtn = new JButton("✕");
        closeBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        closeBtn.setForeground(TEXT_MUTED);
        closeBtn.setBorderPainted(false);
        closeBtn.setContentAreaFilled(false);
        closeBtn.setFocusPainted(false);
        closeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeBtn.addActionListener(e -> System.exit(0));
        closeBar.add(closeBtn);
        card.add(closeBar);

        card.add(Box.createVerticalStrut(4));

        // Título
        JLabel titleLbl = new JLabel("Bem-vindo de volta");
        titleLbl.setFont(FONT_TITLE);
        titleLbl.setForeground(TEXT_DARK);
        titleLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(titleLbl);

        card.add(Box.createVerticalStrut(4));

        JLabel subtitleLbl = new JLabel("Faça login para acessar o sistema");
        subtitleLbl.setFont(FONT_SMALL);
        subtitleLbl.setForeground(TEXT_MUTED);
        subtitleLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(subtitleLbl);

        card.add(Box.createVerticalStrut(28));

        card.add(buildFieldLabel("E-mail"));
        card.add(Box.createVerticalStrut(6));
        emailField = buildTextField("seu@email.com");
        card.add(emailField);

        card.add(Box.createVerticalStrut(16));

        card.add(buildFieldLabel("Senha"));
        card.add(Box.createVerticalStrut(6));
        senhaField = buildPasswordField();
        card.add(senhaField);

        card.add(Box.createVerticalStrut(8));

        erroLabel = new JLabel(" ");
        erroLabel.setFont(FONT_SMALL);
        erroLabel.setForeground(ERROR_COLOR);
        erroLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(erroLabel);

        card.add(Box.createVerticalStrut(20));

        JButton btnEntrar = buildLoginButton();
        card.add(btnEntrar);

        ActionListener doLogin = e -> realizarLogin();
        emailField.addActionListener(doLogin);
        senhaField.addActionListener(doLogin);
        btnEntrar.addActionListener(doLogin);

        wrapper.add(card);
        return wrapper;
    }

    private JLabel buildFieldLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(TEXT_DARK);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JTextField buildTextField(String placeholder) {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty() && !isFocusOwner()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(TEXT_MUTED);
                    g2.setFont(FONT_FIELD.deriveFont(Font.ITALIC));
                    g2.drawString(placeholder, 10, getHeight() / 2 + 5);
                    g2.dispose();
                }
            }
        };
        styleField(field);
        return field;
    }

    private JPasswordField buildPasswordField() {
        JPasswordField field = new JPasswordField();
        styleField(field);
        return field;
    }

    private void styleField(JTextField field) {
        field.setFont(FONT_FIELD);
        field.setForeground(TEXT_DARK);
        field.setBackground(WHITE);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(6, 10, 6, 10)
        ));
        field.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(ACCENT, 2, true),
                        new EmptyBorder(5, 9, 5, 9)
                ));
            }
            @Override public void focusLost(FocusEvent e) {
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                        new EmptyBorder(6, 10, 6, 10)
                ));
            }
        });
    }

    private JButton buildLoginButton() {
        JButton btn = new JButton("Entrar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? ACCENT_DARK : ACCENT);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(WHITE);
                g2.setFont(FONT_BTN);
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth()  - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        return btn;
    }

    //  Lógica de login 
    private static final int MAX_TENTATIVAS = 3;

    private void realizarLogin() {
        String email = emailField.getText().trim();
        String senha = new String(senhaField.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            mostrarErro("Preencha e-mail e senha.");
            return;
        }

        try {
            Usuario usuario = usuarioService.autenticarComLimiteTentativas(email, senha, tentativas, MAX_TENTATIVAS);
            dispose();
            onLoginSuccess.onSuccess(usuario);
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Acesso bloqueado",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (Exception ex) {
            tentativas++;
            mostrarErro("Credenciais inválidas. Tentativa " + tentativas + "/" + MAX_TENTATIVAS + ".");
            senhaField.setText("");
        }
    }

    private void mostrarErro(String msg) {
        erroLabel.setText(msg);
    }

    private void addDragSupport(JPanel root) {
        int[] origin = new int[2];
        root.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                origin[0] = e.getX();
                origin[1] = e.getY();
            }
        });
        root.addMouseMotionListener(new MouseMotionAdapter() {
            @Override public void mouseDragged(MouseEvent e) {
                Point loc = getLocation();
                setLocation(loc.x + e.getX() - origin[0],
                            loc.y + e.getY() - origin[1]);
            }
        });
    }
}