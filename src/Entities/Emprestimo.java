package Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    private String nomeAluno;
    private String codigoBarras;
    private String nomeLivro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(String nomeAluno, String codigoBarras, String nomeLivro, String dataEmp, String dataDev) {
        this.nomeAluno = nomeAluno;
        this.codigoBarras = codigoBarras;
        this.nomeLivro = nomeLivro;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataEmprestimo = LocalDate.parse(dataEmp, fmt);
        this.dataDevolucao = LocalDate.parse(dataDev, fmt);
    }

    public String getNomeAluno() { return nomeAluno; }
    public String getCodigoBarras() { return codigoBarras; }
    public String getNomeLivro() { return nomeLivro; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }

    public String toCSV() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return nomeAluno + ";" + codigoBarras + ";" + nomeLivro + ";" + dataEmprestimo.format(fmt) + ";" + dataDevolucao.format(fmt);
    }
}