package View;

import Entities.BibliotecaService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaFront extends JFrame {
    private BibliotecaService service;


    public TelaFront() {
        this.service = new BibliotecaService();

        setTitle("Biblioteca Logosófica");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));


        JPanel painelPrincipal = new JPanel();


        painelPrincipal.setLayout(new GridLayout(4, 1, 0, 15));


        painelPrincipal.setBorder(new EmptyBorder(20, 60, 20, 60));


        setContentPane(painelPrincipal);

        JButton btnAluno = new JButton("Cadastrar Aluno");
        JButton btnLivro = new JButton("Cadastrar Livro");
        JButton btnEmprestimo = new JButton("Novo Emprestimo");
        JButton btnSair = new JButton("Sair");


        Font fonteBotoes = new Font("Arial", Font.BOLD, 20);
        btnAluno.setFont(fonteBotoes);
        btnLivro.setFont(fonteBotoes);
        btnEmprestimo.setFont(fonteBotoes);
        btnSair.setFont(fonteBotoes);

        btnAluno.addActionListener(e -> abrirCadastroAluno());
        btnLivro.addActionListener(e -> abrirCadastroLivro());
        btnEmprestimo.addActionListener(e -> abrirNovoEmprestimo());
        btnSair.addActionListener(e -> System.exit(0));

        add(btnAluno);
        add(btnLivro);
        add(btnEmprestimo);
        add(btnSair);

        }
    private void abrirCadastroAluno(){
        TelaCadastroAluno telaAluno = new TelaCadastroAluno(this.service);
        telaAluno.setVisible(true);
    }

    private void abrirCadastroLivro(){
        TelaCadastroLivro telaLivro = new TelaCadastroLivro(this.service);
        telaLivro.setVisible(true);
    }

    private void abrirNovoEmprestimo(){
        TelaEmprestimo telaEmp = new TelaEmprestimo(this.service);
        telaEmp.setVisible(true);
    }

}
