package Entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {
    private List<Aluno> alunos = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public BibliotecaService() {
        GerenciadorDeArquivos.carregarDoCSV(alunos);
        GerenciadorDeArquivos.carregarLivros(livros);
        GerenciadorDeArquivos.carregarEmprestimos(emprestimos);
    }

    public void salvarAlunoInterface(String n, String m, String c, String r, String t) {
        Aluno a = new Aluno(n, m, c, r, t);
        alunos.add(a);
        GerenciadorDeArquivos.salvarNoCsv(a);
    }

    public boolean alunoJaCadastrado(String nome, String mat) {
        return alunos.stream().anyMatch(a -> a.getNome().equalsIgnoreCase(nome) || a.getMatricula().equals(mat));
    }

    public void salvarLivroInterface(String c, String t, String a, String cat, String d) {
        Livro l = new Livro(c, t, a, cat, d);
        livros.add(l);
        GerenciadorDeArquivos.salvarLivro(l);
    }

    public boolean emprestarLivroInterface(String aluno, String codLivro, String dataEmp, int dias) {
        Livro l = livros.stream().filter(liv -> liv.getCodigoBarras().equals(codLivro) || liv.getNome().equalsIgnoreCase(codLivro)).findFirst().orElse(null);
        if (l == null) return false;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dtEmp = LocalDate.parse(dataEmp, fmt);
        LocalDate dtDev = dtEmp.plusDays(dias);

        Emprestimo emp = new Emprestimo(aluno, l.getCodigoBarras(), l.getNome(), dataEmp, dtDev.format(fmt));
        emprestimos.add(emp);
        GerenciadorDeArquivos.salvarEmprestimo(emp);
        return true;
    }

    public boolean devolverLivroInterface(String aluno, String livro) {
        Emprestimo emp = emprestimos.stream().filter(e -> e.getNomeAluno().equalsIgnoreCase(aluno) && (e.getNomeLivro().equalsIgnoreCase(livro) || e.getCodigoBarras().equals(livro))).findFirst().orElse(null);
        if (emp == null) return false;
        emprestimos.remove(emp);
        GerenciadorDeArquivos.atualizarArquivoEmprestimos(emprestimos);
        return true;
    }

    public List<Aluno> getTodosAlunos() { return alunos; }
    public List<Livro> getTodosLivros() { return livros; }
    public List<Emprestimo> getTodosEmprestimos() { return emprestimos; }
}