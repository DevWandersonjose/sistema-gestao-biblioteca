package View;

import Entities.BibliotecaService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuCadastros extends JFrame {
    private BibliotecaService service;
    private JFrame telaAnterior;

    public MenuCadastros(BibliotecaService service, JFrame telaAnterior){
        this.service = service;
        this.telaAnterior = telaAnterior;

        setTitle("Menu de Cadastros");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 1, 0, 15));
        painel.setBorder(new EmptyBorder(40, 60, 40, 60));
        setContentPane(painel);

        JButton btnAluno = new JButton("Cadastrar Aluno");
        JButton btnLivro = new JButton("Cadastrar Livro");
        JButton btnVoltar = new JButton("Voltar");

        btnAluno.addActionListener(e -> new TelaCadastroAluno(this.service).setVisible(true));
        btnLivro.addActionListener(e -> new TelaCadastroLivro(this.service).setVisible(true));

        btnVoltar.addActionListener(e -> {
            this.dispose();
            this.telaAnterior.setVisible(true);
        });
        painel.add(btnAluno);
        painel.add(btnLivro);
        painel.add(btnVoltar);

        addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
                telaAnterior.setVisible(true);
            }
        });
    }
}
