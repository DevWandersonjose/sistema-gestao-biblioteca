package View;

import Entities.BibliotecaService;
import javax.swing.*;
import java.awt.*;

public class MenuConsultas extends JFrame {
    private BibliotecaService service;
    private JFrame telaAnterior;

    public MenuConsultas(BibliotecaService service, JFrame telaAnterior){
        this.service = service;
        this.telaAnterior = telaAnterior;

        setTitle("Consultas - Corumbá Sistemas");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());
        add(Estilos.criarPainelTopo("CONSULTAS E RELATÓRIOS"), BorderLayout.NORTH);

        JPanel painelFundo = new JPanel(new GridBagLayout());
        painelFundo.setBackground(Estilos.CINZA_CASCALHO);

        JPanel painelCard = new JPanel(new GridLayout(4, 1, 0 , 20));
        Estilos.configurarPainelCard(painelCard);

        JButton btnAlunos = new JButton("LISTAR ALUNOS");
        JButton btnLivros = new JButton("LISTAR LIVROS");
        JButton btnRelatorio = new JButton("RELATÓRIO EMPRÉSTIMOS");
        JButton btnVoltar = new JButton("VOLTAR");

        Estilos.aplicarEstiloBotao(btnAlunos);
        Estilos.aplicarEstiloBotao(btnLivros);
        Estilos.aplicarEstiloBotao(btnRelatorio);
        Estilos.aplicarEstiloBotao(btnVoltar);
        btnVoltar.setBackground(Color.GRAY);

        Dimension btnSize = new Dimension(300, 55);
        btnAlunos.setPreferredSize(btnSize);
        btnLivros.setPreferredSize(btnSize);
        btnRelatorio.setPreferredSize(btnSize);
        btnVoltar.setPreferredSize(btnSize);

        btnAlunos.addActionListener(e -> new TelaConsultaSimples(service, "ALUNOS").setVisible(true));
        btnLivros.addActionListener(e -> new TelaConsultaSimples(service, "LIVROS").setVisible(true));
        btnRelatorio.addActionListener(e -> new TelaRelatorioEmprestimos(service).setVisible(true));

        btnVoltar.addActionListener(e -> {
            this.dispose();
            this.telaAnterior.setVisible(true);
        });

        painelCard.add(btnAlunos);
        painelCard.add(btnLivros);
        painelCard.add(btnRelatorio);
        painelCard.add(btnVoltar);

        painelFundo.add(painelCard);
        add(painelFundo, BorderLayout.CENTER);
        add(Estilos.criarPainelRodape(), BorderLayout.SOUTH);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                telaAnterior.setVisible(true);
            }
        });
    }
}