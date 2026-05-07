package View;

import Entities.BibliotecaService;
import javax.swing.*;
import java.awt.*;

public class MenuCadastros extends JFrame {
    private BibliotecaService service;
    private JFrame telaAnterior;

    public MenuCadastros(BibliotecaService service, JFrame telaAnterior){
        this.service = service;
        this.telaAnterior = telaAnterior;

        setTitle("Menu de Cadastros - Corumbá Sistemas");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());
        add(Estilos.criarPainelTopo("MENU DE CADASTROS"), BorderLayout.NORTH);

        JPanel painelFundo = new JPanel(new GridBagLayout());
        painelFundo.setBackground(Estilos.CINZA_CASCALHO);

        JPanel painelCard = new JPanel(new GridLayout(3, 1, 0, 20));
        Estilos.configurarPainelCard(painelCard);

        JButton btnAluno = new JButton("CADASTRAR ALUNO");
        JButton btnLivro = new JButton("CADASTRAR LIVRO");
        JButton btnVoltar = new JButton("VOLTAR");

        Estilos.aplicarEstiloBotao(btnAluno);
        Estilos.aplicarEstiloBotao(btnLivro);
        Estilos.aplicarEstiloBotao(btnVoltar);
        btnVoltar.setBackground(Color.GRAY);

        Dimension btnSize = new Dimension(300, 55);
        btnAluno.setPreferredSize(btnSize);
        btnLivro.setPreferredSize(btnSize);
        btnVoltar.setPreferredSize(btnSize);

        btnAluno.addActionListener(e -> new TelaCadastroAluno(this.service).setVisible(true));
        btnLivro.addActionListener(e -> new TelaCadastroLivro(this.service).setVisible(true));

        btnVoltar.addActionListener(e -> {
            this.dispose();
            this.telaAnterior.setVisible(true);
        });

        painelCard.add(btnAluno);
        painelCard.add(btnLivro);
        painelCard.add(btnVoltar);

        painelFundo.add(painelCard);
        add(painelFundo, BorderLayout.CENTER);
        add(Estilos.criarPainelRodape(), BorderLayout.SOUTH);

        addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                telaAnterior.setVisible(true);
            }
        });
    }
}