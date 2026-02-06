package View;

import Entities.BibliotecaService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuEmprestimos extends JFrame {
    private BibliotecaService service;
    private JFrame telaAnterior;

    public MenuEmprestimos(BibliotecaService service, JFrame telaAnterior){
        this.service = service;
        this.telaAnterior = telaAnterior;

        setTitle("Gestao de Emprestimos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 1, 0, 15));
        painel.setBorder(new EmptyBorder(40, 60, 40, 60));
        setContentPane(painel);

        JButton btnNovo = new JButton("Novo Empréstimo");
        JButton btnDevolver = new JButton("Devolver Livro");
        JButton btnVoltar = new JButton("Voltar");

        btnNovo.addActionListener(e ->new TelaEmprestimo(this.service).setVisible(true));
        btnDevolver.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Tela de Devolução em construção!"));
        btnVoltar.addActionListener(e -> {
            this.dispose();
            this.telaAnterior.setVisible(true);
        });

        painel.add(btnNovo);
        painel.add(btnDevolver);
        painel.add(btnVoltar);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                telaAnterior.setVisible(true);
            }
        });
    }
}
