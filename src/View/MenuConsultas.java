package View;

import Entities.BibliotecaService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuConsultas extends JFrame {
    private BibliotecaService service;
    private JFrame telaAnterior;

    public MenuConsultas(BibliotecaService service, JFrame telaAnterior){
        this.service = service;
        this.telaAnterior = telaAnterior;

        setTitle("Consultas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(4, 1, 0 , 15));
        painel.setBorder(new EmptyBorder(40, 60, 40, 60));
        setContentPane(painel);

        JButton btnListarAlunos = new JButton("Listar Alunos");
        JButton btnListarLivros = new JButton("Listar Livros");
        JButton btnListarEmprestimos = new JButton("Listar Emprestimos");
        JButton btnVoltar = new JButton("Voltar");

        btnListarAlunos.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Serviço em construção!"));

        btnListarLivros.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Serviço em construção!"));

        btnListarEmprestimos.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Serviço em Construção!"));

        btnVoltar.addActionListener(e -> {
            this.dispose();
            this.telaAnterior.setVisible(true);
        });
    }
}
