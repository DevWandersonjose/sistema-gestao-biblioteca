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

        setTitle("Empréstimo - Corumbá Sistemas");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(Estilos.criarPainelTopo("REGISTRAR EMPRÉSTIMO"), BorderLayout.NORTH);

        JPanel painelFundo = new JPanel(new GridBagLayout());
        painelFundo.setBackground(Estilos.CINZA_CASCALHO);

        JPanel painelCard = new JPanel(new BorderLayout());
        Estilos.configurarPainelCard(painelCard);

        JPanel painelForm = new JPanel(new GridLayout(5, 2, 10, 20));
        painelForm.setBackground(Estilos.BRANCO_PURO);

        txtNomeAluno = new JTextField();
        txtCodigoLivro = new JTextField();
        txtData = new JTextField();
        txtDias = new JTextField();

        txtData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtDias.setText("7");

        painelForm.add(criarLabel("Nome do Aluno:"));
        painelForm.add(txtNomeAluno);
        painelForm.add(criarLabel("Cód. Barras ou Livro:"));
        painelForm.add(txtCodigoLivro);
        painelForm.add(criarLabel("Data Empréstimo:"));
        painelForm.add(txtData);
        painelForm.add(criarLabel("Dias para devolver:"));
        painelForm.add(txtDias);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.setBackground(Estilos.BRANCO_PURO);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JButton btnConfirmar = new JButton("CONFIRMAR");
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

        btnConfirmar.addActionListener(e -> confirmarEmprestimo());
        btnCancelar.addActionListener(e -> dispose());
    }

    private JLabel criarLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(Estilos.FONTE_SUBTITULO);
        lbl.setForeground(Estilos.AZUL_OCEANO);
        return lbl;
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
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "ERRO: Livro não encontrado ou sem estoque!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O campo 'Dias' deve ser um número!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro na data ou dados inválidos.");
        }
    }
}