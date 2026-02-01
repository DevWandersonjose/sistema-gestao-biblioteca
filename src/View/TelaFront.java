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

// --- MUDANÇA PRINCIPAL AQUI ---
        // Criamos um painel principal para aplicar margens
        JPanel painelPrincipal = new JPanel();

        // GridLayout: 4 linhas, 1 coluna, 15px de espaço vertical entre botões
        painelPrincipal.setLayout(new GridLayout(4, 1, 0, 15));

        // EmptyBorder(Top, Left, Bottom, Right) -> Margem de 40px nos lados e 20px em cima/baixo
        painelPrincipal.setBorder(new EmptyBorder(20, 60, 20, 60));

        // Adiciona o painel à janela
        setContentPane(painelPrincipal);
        // ------------------------------
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

}
