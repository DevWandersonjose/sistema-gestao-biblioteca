package Entities;

import java.io.*;
import java.util.List;

public class GerenciadorDeArquivos {
    private static final String DIR = "db";
    private static final String PATH_A = DIR + File.separator + "alunos.csv";
    private static final String PATH_L = DIR + File.separator + "livros.csv";
    private static final String PATH_E = DIR + File.separator + "emprestimos.csv";

    private static void checkDir() {
        File d = new File(DIR);
        if (!d.exists()) d.mkdir();
    }

    public static void salvarNoCsv(Aluno a) {
        checkDir();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_A, true))) {
            bw.write(a.getNome() + ";" + a.getMatricula() + ";" + a.getClasse() + ";" + a.getResponsavel() + ";" + a.getNumeroTelefone());
            bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void carregarDoCSV(List<Aluno> list) {
        File f = new File(PATH_A);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_A))) {
            String l;
            while ((l = br.readLine()) != null) {
                String[] d = l.split(";", -1);
                if (d.length >= 5) list.add(new Aluno(d[0], d[1], d[2], d[3], d[4]));
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void salvarLivro(Livro l) {
        checkDir();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_L, true))) {
            bw.write(l.getCodigoBarras() + ";" + l.getNome() + ";" + l.getAutor() + ";" + l.getCategoria() + ";" + l.getDescricao());
            bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void carregarLivros(List<Livro> list) {
        File f = new File(PATH_L);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_L))) {
            String l;
            while ((l = br.readLine()) != null) {
                String[] d = l.split(";", -1);
                if (d.length >= 5) list.add(new Livro(d[0], d[1], d[2], d[3], d[4]));
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void salvarEmprestimo(Emprestimo emp) {
        checkDir();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_E, true))) {
            bw.write(emp.toCSV());
            bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void carregarEmprestimos(List<Emprestimo> list) {
        File f = new File(PATH_E);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_E))) {
            String l;
            while ((l = br.readLine()) != null) {
                String[] d = l.split(";", -1);
                if (d.length >= 5) list.add(new Emprestimo(d[0], d[1], d[2], d[3], d[4]));
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void atualizarArquivoEmprestimos(List<Emprestimo> list) {
        checkDir();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_E, false))) {
            for (Emprestimo e : list) {
                bw.write(e.toCSV());
                bw.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}