package Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    private String nomeAluno;
    private String nomeLivro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String codigoBarras;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public Emprestimo(String nomeAluno, String codigoBarras, String nomeLivro, String dataInicialTexto, int diasParaDevolver) {
        this.nomeAluno = nomeAluno;
        this.codigoBarras = codigoBarras;
        this.nomeLivro = nomeLivro;

        this.dataEmprestimo = LocalDate.parse(dataInicialTexto, FMT);

        this.dataDevolucao = this.dataEmprestimo.plusDays(diasParaDevolver);
    }

    public Emprestimo(String nomeAluno, String codigoBarras, String nomeLivro, String dataEmp, String dataDev) {
        this.nomeAluno = nomeAluno;
        this.codigoBarras = codigoBarras;
        this.nomeLivro = nomeLivro;
        this.dataEmprestimo = LocalDate.parse(dataEmp, FMT);
        this.dataDevolucao = LocalDate.parse(dataDev, FMT);
    }

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

    public String getNomeAluno() { return nomeAluno; }
    public void setNomeAluno(String nomeAluno) { this.nomeAluno = nomeAluno; }

    public String getNomeLivro() { return nomeLivro; }
    public void setNomeLivro(String nomeLivro) { this.nomeLivro = nomeLivro; }

    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }

    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
}