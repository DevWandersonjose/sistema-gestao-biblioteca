package View;

import Entities.BibliotecaService;
import Entities.Livro;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroLivro extends javax.swing.JFrame {
    private BibliotecaService service;

    private JTextField txtCodigo = new JTextField();
    private JTextField txtTitulo =  new JTextField();
    private JTextField txtAutor = new JTextField();
    private JTextField txtCategoria =  new JTextField();
    private JTextField txtDescricao = new JTextField();

    public TelaCadastroLivro(BibliotecaService service) {
        this.service = service;

        setTitle("Novo Livro");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 5, 5));
        setLocationRelativeTo(null);

        add(new JLabel("Codigo:"));
        add(txtCodigo);
        add(new JLabel("Titulo:"));
        add(txtTitulo);
        add(new JLabel("Autor:"));
        add(txtAutor);
        add(new JLabel("Categoria:"));
        add(txtCategoria);
        add(new JLabel("Descricao:"));
        add(txtDescricao);

        JButton btnSalvar = new JButton("Salvar");
        add(btnSalvar);
        JButton btnCancelar = new JButton("Cancelar");
        add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarDados());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void salvarDados() {

        String codigo = txtCodigo.getText();
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String categoria = txtCategoria.getText();
        String descricao = txtDescricao.getText();

        if (titulo.isEmpty() || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Titulo e Código sao obrigatorios!");
            return;
        }
        service.salvarLivroInterface(codigo, titulo, autor, categoria, descricao);
        JOptionPane.showMessageDialog(this, "Livro salvo com sucesso!");


        txtCodigo.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtCategoria.setText("");
        txtDescricao.setText("");



    }
}
