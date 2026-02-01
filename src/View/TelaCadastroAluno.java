package View;

import Entities.BibliotecaService;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroAluno extends javax.swing.JFrame {
    private BibliotecaService service;

    private JTextField txtNome = new JTextField(20);
    private JTextField txtMatricula = new JTextField(10);
    private JTextField txtClasse = new JTextField(10);
    private JTextField txtResponsavel  = new JTextField(20);
    private JTextField txtTelefone =  new JTextField(15);


    public TelaCadastroAluno(BibliotecaService serviceRecebido) {
        this.service = serviceRecebido;

        setTitle("Novo Aluno");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 5, 5));
        setLocationRelativeTo(null);


        add(new JLabel("Nome:"));
        add(txtNome);
        add(new JLabel("Matricula:"));
        add(txtMatricula);
        add(new JLabel("Classe:"));
        add(txtClasse);
        add(new JLabel("Responsavel:"));
        add(txtResponsavel);
        add(new JLabel("Telefone:"));
        add(txtTelefone);

        JButton btnSalvar = new JButton("Salvar");
        add(btnSalvar);
        JButton btnCancelar = new JButton("Cancelar");
        add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarDados());
        btnCancelar.addActionListener(e -> dispose());



    }

    private void salvarDados() {
        String nome = txtNome.getText();
        String matricula = txtMatricula.getText();
        String classe = txtClasse.getText();
        String responsavel = txtResponsavel.getText();
        String telefone = txtTelefone.getText();

        if (nome.isEmpty() || matricula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome e Matricula sao obrigatorios!");
            return;
        }
        service.salvarAlunoInterface(nome, matricula, classe, responsavel, telefone);
        JOptionPane.showMessageDialog(this, "Aluno salvo com sucesso!");

        txtNome.setText("");
        txtMatricula.setText("");
        txtClasse.setText("");
        txtResponsavel.setText("");
        txtTelefone.setText("");

    }


}
