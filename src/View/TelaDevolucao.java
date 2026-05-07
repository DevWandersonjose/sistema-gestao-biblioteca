package View;

import Entities.BibliotecaService;
import javax.swing.*;
import java.awt.*;

public class TelaDevolucao extends JFrame {
    private BibliotecaService service;
    private JTextField txtNomeAluno;
    private JTextField txtLivro;

    public TelaDevolucao(BibliotecaService service) {
        this.service = service;

        setTitle("Devolução - Corumbá Sistemas");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(Estilos.criarPainelTopo("DEVOLUÇÃO DE LIVRO"), BorderLayout.NORTH);

        JPanel painelFundo = new JPanel(new GridBagLayout());
        painelFundo.setBackground(Estilos.CINZA_CASCALHO);

        JPanel painelCard = new JPanel(new BorderLayout());
        Estilos.configurarPainelCard(painelCard);

        JPanel painelForm = new JPanel(new GridLayout(2, 2, 10, 20));
        painelForm.setBackground(Estilos.BRANCO_PURO);

        txtNomeAluno = new JTextField();
        txtLivro = new JTextField();

        painelForm.add(criarLabel("Nome do Aluno:"));
        painelForm.add(txtNomeAluno);
        painelForm.add(criarLabel("Livro (Nome/Código):"));
        painelForm.add(txtLivro);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.setBackground(Estilos.BRANCO_PURO);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JButton btnConfirmar = new JButton("CONFIRMAR DEVOLUÇÃO");
        JButton btnCancelar = new JButton("CANCELAR");

        Estilos.aplicarEstiloBotao(btnConfirmar);
        Estilos.aplicarEstiloBotao(btnCancelar);
        btnCancelar.setBackground(Color.GRAY);

        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        painelCard.add(painelForm, BorderLayout.CENTER);
        painelCard.add(painelBotoes, BorderLayout.SOUTH);
        painelFundo.add(painelCard);
        add(painelFundo, BorderLayout.CENTER);
        add(Estilos.criarPainelRodape(), BorderLayout.SOUTH);

        btnConfirmar.addActionListener(e -> realizarDevolucao());
        btnCancelar.addActionListener(e -> dispose());
    }

    private JLabel criarLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(Estilos.FONTE_SUBTITULO);
        lbl.setForeground(Estilos.AZUL_OCEANO);
        return lbl;
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