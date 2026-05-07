package View;

import Entities.BibliotecaService;
import javax.swing.*;
import java.awt.*;

public class MenuEmprestimos extends JFrame {
    private BibliotecaService service;
    private JFrame telaAnterior;

    public MenuEmprestimos(BibliotecaService service, JFrame telaAnterior){
        this.service = service;
        this.telaAnterior = telaAnterior;

        setTitle("Gestão de Empréstimos - Corumbá Sistemas");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());
        add(Estilos.criarPainelTopo("GESTÃO DE EMPRÉSTIMOS"), BorderLayout.NORTH);

        JPanel painelFundo = new JPanel(new GridBagLayout());
        painelFundo.setBackground(Estilos.CINZA_CASCALHO);

        JPanel painelCard = new JPanel(new GridLayout(3, 1, 0, 20));
        Estilos.configurarPainelCard(painelCard);

        JButton btnNovo = new JButton("NOVO EMPRÉSTIMO");
        JButton btnDevolver = new JButton("DEVOLVER LIVRO");
        JButton btnVoltar = new JButton("VOLTAR");

        Estilos.aplicarEstiloBotao(btnNovo);
        Estilos.aplicarEstiloBotao(btnDevolver);
        Estilos.aplicarEstiloBotao(btnVoltar);
        btnVoltar.setBackground(Color.GRAY);

        Dimension btnSize = new Dimension(300, 55);
        btnNovo.setPreferredSize(btnSize);
        btnDevolver.setPreferredSize(btnSize);
        btnVoltar.setPreferredSize(btnSize);

        btnNovo.addActionListener(e -> new TelaEmprestimo(this.service).setVisible(true));
        btnDevolver.addActionListener(e -> new TelaDevolucao(this.service).setVisible(true));

        btnVoltar.addActionListener(e -> {
            this.dispose();
            this.telaAnterior.setVisible(true);
        });

        painelCard.add(btnNovo);
        painelCard.add(btnDevolver);
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