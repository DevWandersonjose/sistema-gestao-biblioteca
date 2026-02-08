package View;

import Entities.BibliotecaService;
import Entities.Emprestimo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TelaRelatorioEmprestimos extends JFrame {
    private BibliotecaService service;
    private DefaultTableModel modelo;
    private JTable tabela;

    public TelaRelatorioEmprestimos(BibliotecaService service) {
        this.service = service;

        setTitle("Relatório de Empréstimos");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelFiltros = new JPanel();
        JButton btnTodos = new JButton("Todos");
        JButton btnVencidos = new JButton("Vencidos (Atrasados)");
        JButton btnProximos = new JButton("Próximos a vencer (3 dias)");

        painelFiltros.add(btnTodos);
        painelFiltros.add(btnVencidos);
        painelFiltros.add(btnProximos);

        String[] colunas = {"Aluno", "Livro", "Data Emp.", "Devolução", "Status"};
        modelo = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);

        JPanel painelSul = new JPanel();
        JButton btnFechar = new JButton("Fechar");
        painelSul.add(btnFechar);

        add(painelFiltros, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelSul, BorderLayout.SOUTH);

        btnTodos.addActionListener(e -> carregarDados(0));
        btnVencidos.addActionListener(e -> carregarDados(1));
        btnProximos.addActionListener(e -> carregarDados(2));
        btnFechar.addActionListener(e -> dispose());

        carregarDados(0);
    }

    private void carregarDados(int tipoFiltro) {
        modelo.setRowCount(0);
        List<Emprestimo> lista = service.getTodosEmprestimos();
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Emprestimo e : lista) {
            LocalDate dataDev = e.getDataDevolucao();
            String status = "Em dia";
            boolean adicionar = false;

            long diasRestantes = ChronoUnit.DAYS.between(hoje, dataDev);

            if (dataDev.isBefore(hoje)) {
                status = "Vencido";
                if (tipoFiltro == 0 || tipoFiltro == 1) adicionar = true;

            } else if (diasRestantes <= 3) {
                status = "Atenção (" + diasRestantes + " dias)";
                if (tipoFiltro == 0 || tipoFiltro == 2) adicionar = true;

            } else {
                status = "Regular";
                if (tipoFiltro == 0) adicionar = true;
            }

            if (adicionar) {
                Object[] linha = {
                        e.getNomeAluno(),
                        e.getNomeLivro(),
                        e.getDataEmprestimo().format(formatter),
                        e.getDataDevolucao().format(formatter),
                        status
                };
                modelo.addRow(linha);
            }
        }
    }
}