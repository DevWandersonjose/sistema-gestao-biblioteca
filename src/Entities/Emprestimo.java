package Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    private String nomeAluno;
    private String nomeLivro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String codigoBarras;

    // Formatador estático para evitar recriar a cada chamada (Melhoria de performance)
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Construtor 1: Novo Empréstimo (Calcula a data de devolução)
    public Emprestimo(String nomeAluno, String codigoBarras, String nomeLivro, String dataInicialTexto, int diasParaDevolver) {
        this.nomeAluno = nomeAluno;
        this.codigoBarras = codigoBarras;
        this.nomeLivro = nomeLivro;

        // Converte a String recebida para LocalDate
        this.dataEmprestimo = LocalDate.parse(dataInicialTexto, FMT);

        // Calcula a devolução automaticamente
        this.dataDevolucao = this.dataEmprestimo.plusDays(diasParaDevolver);
    }

    // Construtor 2: Carregar do CSV (Recebe as duas datas prontas)
    // CORREÇÃO: Removido o parâmetro extra "String dado" que não era usado
    public Emprestimo(String nomeAluno, String codigoBarras, String nomeLivro, String dataEmp, String dataDev) {
        this.nomeAluno = nomeAluno;
        this.codigoBarras = codigoBarras;
        this.nomeLivro = nomeLivro;
        this.dataEmprestimo = LocalDate.parse(dataEmp, FMT);
        this.dataDevolucao = LocalDate.parse(dataDev, FMT);
    }

    // Método para formatar a linha do CSV
    public String toCSV() {
        return nomeAluno + ";" + codigoBarras + ";" + nomeLivro + ";" +
                dataEmprestimo.format(FMT) + ";" + dataDevolucao.format(FMT);
    }

    @Override
    public String toString() {
        return "Livro: " + nomeLivro + " | Aluno: " + nomeAluno +
                " | Empréstimo: " + dataEmprestimo.format(FMT) +
                " | Devolução: " + dataDevolucao.format(FMT);
    }

    // Getters e Setters
    public String getNomeAluno() { return nomeAluno; }
    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }

    public String getNomeLivro() { return nomeLivro; }
    public void setNomeLivro(String nomeLivro) { this.nomeLivro = nomeLivro; }

    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }

    // Adicionei Getters para as datas, caso precise verificar atrasos depois
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
}