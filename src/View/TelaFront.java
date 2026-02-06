package View;

import Entities.BibliotecaService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaFront extends JFrame {
    private BibliotecaService service;


    public TelaFront() {
        this.service = new BibliotecaService();

        setTitle("Biblioteca Logosófica - Menu Principal");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 1, 0, 15));
        painel.setBorder(new EmptyBorder(30, 50, 30, 50));
        setContentPane(painel);


        JButton btnCadastros = new JButton("Cadastros");
        JButton btnEmprestimos = new JButton("Empréstimos / Devolução");
        JButton btnConsultas = new JButton("Consultas");
        JButton btnSair = new JButton("Sair");


        Font fonte = new Font("Arial", Font.BOLD, 16);
        btnCadastros.setFont(fonte);
        btnEmprestimos.setFont(fonte);
        btnConsultas.setFont(fonte);
        btnSair.setFont(fonte);

        btnCadastros.addActionListener(e -> {
            new MenuCadastros(this.service, this).setVisible(true);
            this.setVisible(false); // Esconde a tela principal
        });

        btnEmprestimos.addActionListener(e -> {
            new MenuEmprestimos(this.service, this).setVisible(true);
            this.setVisible(false);
        });

         btnConsultas.addActionListener(e -> {
            new MenuConsultas(this.service, this).setVisible(true);
            this.setVisible(false);
        });

        btnSair.addActionListener(e -> System.exit(0));

        painel.add(btnCadastros);
        painel.add(btnEmprestimos);
        painel.add(btnConsultas);
        painel.add(btnSair);
    }
}