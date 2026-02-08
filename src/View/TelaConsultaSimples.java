package View;

import Entities.Aluno;
import Entities.BibliotecaService;
import Entities.Livro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaConsultaSimples extends JFrame {

    public TelaConsultaSimples(BibliotecaService service, String tipo) {
        setTitle("Consulta de " + tipo);
        setSize(700, 400);
        setLocationRelativeTo(null);

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll,  BorderLayout.CENTER);

        if(tipo.equals("ALUNOS")){
            modelo.addColumn("Nome");
            modelo.addColumn("Matricula");
            modelo.addColumn("Classe");
            modelo.addColumn("Responsavel");
            modelo.addColumn("Telefone");

            for(Aluno a : service.getTodosAlunos()){
                modelo.addRow(new Object[]{a.getNome(), a.getMatricula(), a.getClasse(), a.getResponsavel(), a.getNumeroTelefone()});

            }
        }else{
            modelo.addColumn("Codigo");
            modelo.addColumn("Titulo");
            modelo.addColumn("Autor");
            modelo.addColumn("Categoria");

            for(Livro l : service.getTodosLivros()){
                modelo.addRow(new Object[]{l.getCodigoBarras(), l.getNome(), l.getAutor(), l.getCategoria()});

            }
        }
    }
}
