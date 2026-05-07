package View;

import Entities.BibliotecaService;
import javax.swing.*;
import java.awt.*;

public class TelaCadastroAluno extends JFrame {
    private BibliotecaService service;

    private JTextField txtNome = new JTextField(20);
    private JTextField txtMatricula = new JTextField(10);
    private JTextField txtClasse = new JTextField(10);
    private JTextField txtResponsavel  = new JTextField(20);
    private JTextField txtTelefone =  new JTextField(15);

    public TelaCadastroAluno(BibliotecaService serviceRecebido) {
        this.service = serviceRecebido;

        setTitle("Novo Aluno - Corumbá Sistemas");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        add(Estilos.criarPainelTopo("NOVO ALUNO"), BorderLayout.NORTH);

        JPanel painelFundo = new JPanel(new GridBagLayout());
        painelFundo.setBackground(Estilos.CINZA_CASCALHO);

        JPanel painelCard = new JPanel(new BorderLayout());
        Estilos.configurarPainelCard(painelCard);

        JPanel painelForm = new JPanel(new GridLayout(6, 2, 10, 20));
        painelForm.setBackground(Estilos.BRANCO_PURO);

        painelForm.add(criarLabel("Nome Completo:"));
        painelForm.add(txtNome);
        painelForm.add(criarLabel("Matrícula:"));
        painelForm.add(txtMatricula);
        painelForm.add(criarLabel("Classe/Turma:"));
        painelForm.add(txtClasse);
        painelForm.add(criarLabel("Responsável:"));
        painelForm.add(txtResponsavel);
        painelForm.add(criarLabel("Telefone:"));
        painelForm.add(txtTelefone);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.setBackground(Estilos.BRANCO_PURO);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JButton btnSalvar = new JButton("SALVAR REGISTRO");
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
        String nome = txtNome.getText();
        String matricula = txtMatricula.getText();
        String classe = txtClasse.getText();
        String responsavel = txtResponsavel.getText();
        String telefone = txtTelefone.getText();

        if (nome.isEmpty() || matricula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome e Matrícula são obrigatórios!");
            return;
        }
        if(service.alunoJaCadastrado(nome, matricula)){
            JOptionPane.showMessageDialog(this, "ERRO: Aluno já cadastrado!!");
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