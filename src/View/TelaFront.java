package View;

import Entities.BibliotecaService;
import javax.swing.*;
import java.awt.*;

public class TelaFront extends JFrame {
    private BibliotecaService service;

    public TelaFront() {
        this.service = new BibliotecaService();
        setTitle("Corumbá Sistemas - Gestão Inteligente");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(Estilos.criarPainelTopo("CORUMBÁ SISTEMAS"), BorderLayout.NORTH);

        JPanel pCentro = new JPanel(new GridBagLayout());
        pCentro.setBackground(Estilos.CINZA_CASCALHO);

        JPanel menu = new JPanel(new GridLayout(4, 1, 0, 20));
        Estilos.configurarPainelCard(menu);

        JButton btnCad = new JButton("CADASTROS");
        JButton btnEmp = new JButton("GESTÃO DE EMPRÉSTIMOS");
        JButton btnCon = new JButton("CONSULTAS E RELATÓRIOS");
        JButton btnSair = new JButton("SAIR");

        Estilos.aplicarEstiloBotao(btnCad);
        Estilos.aplicarEstiloBotao(btnEmp);
        Estilos.aplicarEstiloBotao(btnCon);
        Estilos.aplicarEstiloBotao(btnSair);
        btnSair.setBackground(new Color(100, 100, 100));

        Dimension d = new Dimension(300, 55);
        btnCad.setPreferredSize(d); btnEmp.setPreferredSize(d); btnCon.setPreferredSize(d); btnSair.setPreferredSize(d);

        btnCad.addActionListener(e -> { new MenuCadastros(service, this).setVisible(true); setVisible(false); });
        btnEmp.addActionListener(e -> { new MenuEmprestimos(service, this).setVisible(true); setVisible(false); });
        btnCon.addActionListener(e -> { new MenuConsultas(service, this).setVisible(true); setVisible(false); });
        btnSair.addActionListener(e -> System.exit(0));

        menu.add(btnCad); menu.add(btnEmp); menu.add(btnCon); menu.add(btnSair);
        pCentro.add(menu);
        add(pCentro, BorderLayout.CENTER);
        add(Estilos.criarPainelRodape(), BorderLayout.SOUTH);
    }
}