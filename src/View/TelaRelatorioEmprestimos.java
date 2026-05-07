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

    public TelaRelatorioEmprestimos(BibliotecaService service) {
        this.service = service;

        setTitle("Relatório - Corumbá Sistemas");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelNorteCompleto = new JPanel(new BorderLayout());
        painelNorteCompleto.add(Estilos.criarPainelTopo("RELATÓRIO DE EMPRÉSTIMOS"), BorderLayout.NORTH);

        JPanel painelFiltros = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        painelFiltros.setBackground(Estilos.CINZA_CASCALHO);

        JButton btnTodos = new JButton("Todos");
        JButton btnVencidos = new JButton("Vencidos");
        JButton btnProximos = new JButton("A Vencer (3 dias)");

        Estilos.aplicarEstiloBotao(btnTodos);
        Estilos.aplicarEstiloBotao(btnVencidos);
        Estilos.aplicarEstiloBotao(btnProximos);

        btnVencidos.setBackground(new Color(205, 92, 92));
        btnProximos.setBackground(new Color(255, 165, 0));

        painelFiltros.add(btnTodos);
        painelFiltros.add(btnVencidos);
        painelFiltros.add(btnProximos);

        painelNorteCompleto.add(painelFiltros, BorderLayout.SOUTH);
        add(painelNorteCompleto, BorderLayout.NORTH);

        String[] colunas = {"Aluno", "Livro", "Data Emp.", "Devolução", "Status"};
        modelo = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modelo);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabela.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

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

        btnTodos.addActionListener(e -> carregarDados(0));
        btnVencidos.addActionListener(e -> carregarDados(1));
        btnProximos.addActionListener(e -> carregarDados(2));

        carregarDados(0);
    }

    private void carregarDados(int tipoFiltro) {
        modelo.setRowCount(0);
        List<Emprestimo> lista = service.getTodosEmprestimos();
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Emprestimo e : lista) {
            LocalDate dataDev = e.getDataDevolucao();
            String status;
            boolean adicionar = false;
            long diasRestantes = ChronoUnit.DAYS.between(hoje, dataDev);

            if (dataDev.isBefore(hoje)) {
                status = "VENCIDO";
                if (tipoFiltro == 0 || tipoFiltro == 1) adicionar = true;
            } else if (diasRestantes <= 3) {
                status = "ATENÇÃO (" + diasRestantes + " dias)";
                if (tipoFiltro == 0 || tipoFiltro == 2) adicionar = true;
            } else {
                status = "REGULAR";
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