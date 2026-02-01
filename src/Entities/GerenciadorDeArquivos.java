package Entities;

import Entities.Aluno;
import Entities.Emprestimo;
import Entities.Livro;

import java.io.*;
import java.util.List;

public class GerenciadorDeArquivos {


    private static final String CAMINHO_ALUNOS = "alunos.csv";

    public static void salvarNoCsv(Aluno aluno) {
        boolean arquivoExiste = new File(CAMINHO_ALUNOS).exists();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ALUNOS, true))) {
            if (!arquivoExiste) {
                bw.write("Nome;Matricula;Classe;Responsavel;Telefone");
                bw.newLine();
            }
            String linha = aluno.getNome() + ";" +
                    aluno.getMatricula() + ";" +
                    aluno.getClasse() + ";" +
                    aluno.getResponsavel() + ";" +
                    aluno.getNumeroTelefone();

            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no CSV de Alunos: " + e.getMessage());
        }
    }

    public static void carregarDoCSV(List<Aluno> lista) {
        File arquivo = new File(CAMINHO_ALUNOS);
        if (!arquivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ALUNOS))) {
            String linha = br.readLine();
            linha = br.readLine();

            while (linha != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 5) {
                    Aluno a = new Aluno(dados[0], dados[1], dados[2], dados[3], dados[4]);
                    lista.add(a);
                }
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler no CSV de Alunos: " + e.getMessage());
        }
    }


    private static final String CAMINHO_LIVROS = "livros.csv";

    public static void salvarLivro(Livro livro) {
        boolean arquivoExiste = new File(CAMINHO_LIVROS).exists();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_LIVROS, true))) {
            if (!arquivoExiste) {

                bw.write("Codigo;Nome;Autor;Categoria;Descrição");
                bw.newLine();
            }

            // CORREÇÃO: Adicionado o getCodigoBarras() no início
            String linha = livro.getCodigoBarras() + ";" +
                    livro.getNome() + ";" +
                    livro.getAutor() + ";" +
                    livro.getCategoria() + ";" +
                    livro.getDescricao();

            bw.write(linha);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no CSV de Livros: " + e.getMessage());
        }
    }

    public static void carregarLivros(List<Livro> lista) {
        File arquivo = new File(CAMINHO_LIVROS);
        if (!arquivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_LIVROS))) {
            String linha = br.readLine();
            linha = br.readLine();

            while (linha != null) {
                String[] dados = linha.split(";");

                if (dados.length >= 5) {

                    Livro l = new Livro(dados[0], dados[1], dados[2], dados[3], dados[4]);
                    lista.add(l);
                }
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler no CSV de Livros: " + e.getMessage());
        }
    }


    private static final String CAMINHO_EMPRESTIMOS = "emprestimos.csv";

    public static void salvarEmprestimo(Emprestimo emp) {
        boolean arquivoExiste = new File(CAMINHO_EMPRESTIMOS).exists();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_EMPRESTIMOS, true))) {
            if (!arquivoExiste) {
                bw.write("NomeAluno;CodigoBarras;NomeLivro;DataEmprestimo;DataDevolucao");
                bw.newLine();
            }
            // Usa o método toCSV que criamos na classe Emprestimo
            bw.write(emp.toCSV());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar Empréstimo: " + e.getMessage());
        }
    }

    public static void carregarEmprestimos(List<Emprestimo> lista) {
        File arquivo = new File(CAMINHO_EMPRESTIMOS);
        if (!arquivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_EMPRESTIMOS))) {
            String linha = br.readLine();
            linha = br.readLine();

            while (linha != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 5) {
                    // Atenção para a ordem dos parâmetros aqui, deve bater com o construtor 2 de Emprestimo
                    Emprestimo emp = new Emprestimo(dados[0], dados[1], dados[2], dados[3], dados[4]);
                    lista.add(emp);
                }
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler Empréstimos: " + e.getMessage());
        }
    }

    public static void atualizarArquivoEmprestimos(List<Emprestimo> lista) {
        try (BufferedWriter bw  = new BufferedWriter(new FileWriter(CAMINHO_EMPRESTIMOS, false))){
            bw.write("NomeAluno;codigoBarras;NomeLivro;DataEmprestimo;DataDevolucao");
            bw.newLine();
            for (Emprestimo e : lista) {
                bw.write(e.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar arquivo Emprestimo: " + e.getMessage());
        }

    }
    public static void atualizarArquivoAlunos(List<Aluno> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ALUNOS, false))) { // false = sobrescrever
            bw.write("Nome;Matricula;Classe;Responsavel;Telefone");
            bw.newLine();
            for (Aluno a : lista) {
                String linha = a.getNome() + ";" +
                        a.getMatricula() + ";" +
                        a.getClasse() + ";" +
                        a.getResponsavel() + ";" +
                        a.getNumeroTelefone();
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar arquivo de Alunos: " + e.getMessage());
        }
    }

    public static void atualizarArquivoLivros(List<Livro> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_LIVROS, false))){
            bw.write("Codigo;Nome;Autor;Categoria;Descrição");
            bw.newLine();
            for (Livro l : lista) {
                String linha = l.getCodigoBarras() + ";" +
                        l.getNome() + ";" +
                        l.getAutor() + ";" +
                        l.getCategoria() + ";" +
                        l.getDescricao();
                bw.write(linha);
                bw.newLine();
            }
        }catch (IOException e) {
            System.out.println("Erro ao atualizar arquivo de Livros: " + e.getMessage());
        }
    }
}