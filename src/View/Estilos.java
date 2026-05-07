package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.URL;

public class Estilos {
    public static final Color AZUL_OCEANO   = Color.decode("#003B5C");
    public static final Color AZUL_TURQUESA = Color.decode("#00A8E8");
    public static final Color CINZA_CASCALHO= Color.decode("#F3F4F6");
    public static final Color BRANCO_PURO   = Color.decode("#FFFFFF");

    public static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 28);
    public static final Font FONTE_SUBTITULO = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONTE_BOTAO  = new Font("Segoe UI", Font.BOLD, 16);

    public static Image getLogo() {
        try {
            URL url = Estilos.class.getResource("/imagens/logo.png");
            if (url == null) return null;
            return new ImageIcon(url).getImage();
        } catch (Exception e) { return null; }
    }

    public static void aplicarEstiloBotao(JButton btn) {
        btn.setFont(FONTE_BOTAO);
        btn.setBackground(AZUL_TURQUESA);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(0, 140, 200), 1, true),
                BorderFactory.createEmptyBorder(12, 25, 12, 25)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void configurarPainelCard(JPanel painel) {
        painel.setBackground(BRANCO_PURO);
        painel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(30, 40, 30, 40)
        ));
    }

    public static JPanel criarPainelTopo(String titulo) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 15));
        p.setBackground(AZUL_OCEANO);
        p.setPreferredSize(new Dimension(0, 100));
        Image logo = getLogo();
        if (logo != null) {
            p.add(new JLabel(new ImageIcon(logo.getScaledInstance(60, 60, Image.SCALE_SMOOTH))));
        }
        JLabel lbl = new JLabel(titulo.toUpperCase());
        lbl.setFont(FONTE_TITULO);
        lbl.setForeground(Color.WHITE);
        lbl.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 2, 0, 0, AZUL_TURQUESA),
                BorderFactory.createEmptyBorder(0, 20, 0, 0)
        ));
        p.add(lbl);
        return p;
    }

    public static JPanel criarPainelRodape() {
        JPanel p = new JPanel();
        p.setBackground(AZUL_OCEANO);
        p.setPreferredSize(new Dimension(0, 30));
        JLabel lbl = new JLabel("© 2026 Corumbá Sistemas - Soluções em Tecnologia");
        lbl.setForeground(new Color(200, 200, 200));
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        p.add(lbl);
        return p;
    }
}