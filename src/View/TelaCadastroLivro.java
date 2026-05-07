package View;

import Entities.BibliotecaService;
import javax.swing.*;
import java.awt.*;

public class TelaCadastroLivro extends JFrame {
    private BibliotecaService service;

    private JTextField txtCodigo = new JTextField();
    private JTextField txtTitulo =  new JTextField();
    private JTextField txtAutor = new JTextField();
    private JTextField txtCategoria =  new JTextField();
    private JTextField txtDescricao = new JTextField();

    public TelaCadastroLivro(BibliotecaService service) {
        this.service = service;

        setTitle("Novo Livro - Corumbá Sistemas");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(Estilos.criarPainelTopo("NOVO LIVRO"), BorderLayout.NORTH);

        JPanel painelFundo = new JPanel(new GridBagLayout());
        painelFundo.setBackground(Estilos.CINZA_CASCALHO);

        JPanel painelCard = new JPanel(new BorderLayout());
        Estilos.configurarPainelCard(painelCard);

        JPanel painelForm = new JPanel(new GridLayout(6, 2, 10, 20));
        painelForm.setBackground(Estilos.BRANCO_PURO);

        painelForm.add(criarLabel("Código de Barras:"));
        painelForm.add(txtCodigo);
        painelForm.add(criarLabel("Título da Obra:"));
        painelForm.add(txtTitulo);
        painelForm.add(criarLabel("Autor:"));
        painelForm.add(txtAutor);
        painelForm.add(criarLabel("Categoria:"));
        painelForm.add(txtCategoria);
        painelForm.add(criarLabel("Descrição:"));
        painelForm.add(txtDescricao);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.setBackground(Estilos.BRANCO_PURO);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JButton btnSalvar = new JButton("SALVAR LIVRO");
        JButton btnCancelar = new JButton("CANCELAR");

        Estilos.aplicarEstiloBotao(btnSalvar);
        Estilos.aplicarEstiloBotao(btnCancelar);
        btnCancelar.setBackground(Color.GRAY);

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        painelCard.add(painelForm, BorderLayout.CENTER);
        painelCard.add(painelBotoes, BorderLayout.SOUTH);
        painelFundo.add(painelCard);
        add(painelFundo, BorderLayout.CENTER);
        add(Estilos.criarPainelRodape(), BorderLayout.SOUTH);

        btnSalvar.addActionListener(e -> salvarDados());
        btnCancelar.addActionListener(e -> dispose());
    }

    private JLabel criarLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(Estilos.FONTE_SUBTITULO);
        lbl.setForeground(Estilos.AZUL_OCEANO);
        return lbl;
    }

    private void salvarDados() {
        String codigo = txtCodigo.getText();
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String categoria = txtCategoria.getText();
        String descricao = txtDescricao.getText();

        if (titulo.isEmpty() || codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Título e Código são obrigatórios!");
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