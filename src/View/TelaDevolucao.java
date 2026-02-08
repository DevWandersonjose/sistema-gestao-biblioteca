package View;

import Entities.BibliotecaService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaDevolucao extends JFrame {
    private BibliotecaService service;

    private JTextField txtNomeAluno;
    private JTextField txtLivro;

    public TelaDevolucao(BibliotecaService service) {
        this.service = service;

        setTitle("Devolução de Livro");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Layout da Janela Principal

        JPanel painelCampos = new JPanel(new GridLayout(0, 2, 10, 10));
        painelCampos.setBorder(new EmptyBorder(30, 20, 30, 20));

        txtNomeAluno = new JTextField();
        txtLivro = new JTextField();

        painelCampos.add(new JLabel("Nome do Aluno:"));
        painelCampos.add(txtNomeAluno);

        painelCampos.add(new JLabel("Nome/Código do Livro:"));
        painelCampos.add(txtLivro);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 10, 0));
        painelBotoes.setPreferredSize(new Dimension(0, 60));
        painelBotoes.setBorder(new EmptyBorder(0, 20, 20, 20));

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);
        // -----------------------------------------------------------

        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        btnConfirmar.addActionListener(e -> realizarDevolucao());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void realizarDevolucao() {
        String aluno = txtNomeAluno.getText();
        String livro = txtLivro.getText();

        if (aluno.isEmpty() || livro.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos");
            return;
        }

        boolean sucesso = service.devolverLivroInterface(aluno, livro);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Livro devolvido com sucesso!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "ERRO: Empréstimo não encontrado");
        }
    }
}