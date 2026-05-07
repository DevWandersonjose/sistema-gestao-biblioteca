package View;

import Entities.Aluno;
import Entities.BibliotecaService;
import Entities.Livro;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaConsultaSimples extends JFrame {

    public TelaConsultaSimples(BibliotecaService service, String tipo) {
        setTitle("Consulta de " + tipo + " - Corumbá Sistemas");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(Estilos.criarPainelTopo("CONSULTA DE " + tipo), BorderLayout.NORTH);

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabela = new JTable(modelo);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabela.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.getViewport().setBackground(Estilos.BRANCO_PURO);
        add(scroll, BorderLayout.CENTER);

        if(tipo.equals("ALUNOS")){
            modelo.addColumn("Nome");
            modelo.addColumn("Matrícula");
            modelo.addColumn("Classe");
            modelo.addColumn("Responsável");
            modelo.addColumn("Telefone");
            for(Aluno a : service.getTodosAlunos()){
                modelo.addRow(new Object[]{a.getNome(), a.getMatricula(), a.getClasse(), a.getResponsavel(), a.getNumeroTelefone()});
            }
        }else{
            modelo.addColumn("Código");
            modelo.addColumn("Título");
            modelo.addColumn("Autor");
            modelo.addColumn("Categoria");
            for(Livro l : service.getTodosLivros()){
                modelo.addRow(new Object[]{l.getCodigoBarras(), l.getNome(), l.getAutor(), l.getCategoria()});
            }
        }

        JPanel painelSul = new JPanel(new BorderLayout());
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.setBackground(Estilos.CINZA_CASCALHO);

        JButton btnFechar = new JButton("FECHAR");
        Estilos.aplicarEstiloBotao(btnFechar);
        btnFechar.setBackground(Color.GRAY);
        btnFechar.addActionListener(e -> dispose());

        painelBotoes.add(btnFechar);
        painelSul.add(painelBotoes, BorderLayout.CENTER);
        painelSul.add(Estilos.criarPainelRodape(), BorderLayout.SOUTH);

        add(painelSul, BorderLayout.SOUTH);
    }
}