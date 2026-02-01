package View;

import Entities.BibliotecaService;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaEmprestimo extends JFrame {

    private BibliotecaService service;

    private JTextField txtNomeAluno;
    private JTextField txtCodigoLivro;
    private JTextField txtData;
    private JTextField txtDias;

    public TelaEmprestimo(BibliotecaService service) {
        this.service = service;

        setTitle("Novo Empréstimo");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null);

        txtNomeAluno = new JTextField();
        txtCodigoLivro = new JTextField();
        txtData = new JTextField();
        txtDias = new JTextField();


        txtData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtDias.setText("7");

        add(new JLabel("  Nome do Aluno:"));
        add(txtNomeAluno);

        add(new JLabel("  Cód. Barras ou Nome Livro:"));
        add(txtCodigoLivro);

        add(new JLabel("  Data Empréstimo:"));
        add(txtData);

        add(new JLabel("  Dias para devolver:"));
        add(txtDias);

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnConfirmar);
        add(btnCancelar);

        btnConfirmar.addActionListener(e -> confirmarEmprestimo());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void confirmarEmprestimo() {
        String aluno = txtNomeAluno.getText();
        String livro = txtCodigoLivro.getText();
        String data = txtData.getText();
        String diasTexto = txtDias.getText();

        if (aluno.isEmpty() || livro.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha Aluno e Livro!");
            return;
        }

        try {
            int dias = Integer.parseInt(diasTexto);

            boolean sucesso = service.emprestarLivroInterface(aluno, livro, data, dias);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Empréstimo registrado com sucesso!");
                dispose(); // Fecha a janela
            } else {
                JOptionPane.showMessageDialog(this, "ERRO: Livro não encontrado na biblioteca!");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O campo 'Dias' deve ser um número!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro na data ou dados inválidos.");
        }
    }
}