package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaService {
    private List<Aluno> alunos;
    private List<Livro> livros;
    private List<Emprestimo> emprestimos;
    private Scanner sc;


    public BibliotecaService() {
        this.alunos = new ArrayList<>();
        this.livros = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.sc = new Scanner(System.in);

        carregarDados();
    }

    private void carregarDados() {
        GerenciadorDeArquivos.carregarDoCSV(this.alunos);
        GerenciadorDeArquivos.carregarLivros(this.livros);
        GerenciadorDeArquivos.carregarEmprestimos(this.emprestimos);
        System.out.println("Base de dados Carregada");
    }

    public void cadastrarAluno() {
        System.out.println("-----CADASTRAR ALUNO-----");
        System.out.println("Nome do aluno: ");
        String nome = sc.nextLine();
        System.out.println("Matricula: ");
        String matricula = sc.nextLine();
        System.out.println("Classe: ");
        String classe = sc.nextLine();
        System.out.println("Responsavel: ");
        String responsavel = sc.nextLine();
        System.out.println("Telefone: ");
        String telefone = sc.nextLine();

        Aluno novo = new Aluno(nome, matricula, classe, responsavel, telefone);
        alunos.add(novo);
        GerenciadorDeArquivos.salvarNoCsv(novo);
        System.out.println("Aluno cadastrado com sucesso");
        ;
    }

    public void cadastrarLivro() {
        System.out.println("--- CADASTRAR LIVRO ---");
        System.out.println("Código de Barras: ");
        String codigo = sc.nextLine();
        System.out.println("Nome do Livro: ");
        String nome = sc.nextLine();
        System.out.println("Autor: ");
        String autor = sc.nextLine();
        System.out.println("Categoria: ");
        String categoria = sc.nextLine();
        System.out.println("Descrição: ");
        String descricao = sc.nextLine();

        Livro novo = new Livro(codigo, nome, autor, categoria, descricao);
        livros.add(novo);
        GerenciadorDeArquivos.salvarLivro(novo);
        System.out.println("Livro cadastrado com sucesso!");
    }

    public void novoEmprestimo() {
        System.out.println("--- NOVO EMPRÉSTIMO ---");
        System.out.println("Nome do aluno: ");
        String nomeAluno = sc.nextLine();

        System.out.println("Bipe ou digite o Código de Barras do livro: ");
        String codigoBusca = sc.nextLine();

        Livro livroEncontrado = null;
        for (Livro l : livros) {
            if (l.getCodigoBarras().equals(codigoBusca)) {
                livroEncontrado = l;
                break;
            }
        }

        if (livroEncontrado == null) {
            System.out.println("Erro: Livro não encontrado.");
        } else {
            System.out.println("Livro selecionado: " + livroEncontrado.getNome());
            System.out.println("Data do Empréstimo (dd/MM/yyyy): ");
            String dataInicial = sc.nextLine();
            System.out.println("Período (dias): ");

            int dias = 0;
            if (sc.hasNextInt()) {
                dias = sc.nextInt();
                sc.nextLine();
            } else {
                sc.nextLine();
                System.out.println("Entrada de dias inválida. Usando 7 dias padrão.");
                dias = 7;
            }

            Emprestimo emp = new Emprestimo(nomeAluno, livroEncontrado.getCodigoBarras(), livroEncontrado.getNome(), dataInicial, dias);
            emprestimos.add(emp);
            GerenciadorDeArquivos.salvarEmprestimo(emp);
            System.out.println("Empréstimo registrado!");
        }
    }

    public void consultarEmprestimosPorAluno() {
        System.out.println("-----CONSULTAR EMPRESTIMOS-----");
        System.out.println("Nome do aluno (ou parte dele): ");
        String termoBusca = sc.nextLine();
        boolean encontrouAlgum = false;

        System.out.println("Resultados da Busca:");
        for (Emprestimo e : emprestimos) {
            if (e.getNomeAluno().toLowerCase().contains(termoBusca.toLowerCase())) {
                System.out.println(e);
                encontrouAlgum = true;
            }
        }
        if (!encontrouAlgum) {
            System.out.println("Nenhum empréstimo encontrado.");
        }
    }

    public void devolverLivro() {
        System.out.println("--- DEVOLUÇÃO LIVRO ---");
        System.out.println("Nome do aluno: ");
        String nomeAluno = sc.nextLine();
        System.out.println("Digite o nome ou CÓDIGO DE BARRAS do livro: ");
        String termoBusca = sc.nextLine();

        Emprestimo emprestimoParaDevolver = null;

        for (Emprestimo e : emprestimos) {
            boolean alunoConfere = e.getNomeAluno().equalsIgnoreCase(nomeAluno);
            boolean livroConfere = e.getNomeLivro().equalsIgnoreCase(termoBusca) ||
                    e.getCodigoBarras().equalsIgnoreCase(termoBusca);

            if (alunoConfere && livroConfere) {
                emprestimoParaDevolver = e;
                break;
            }
        }

        if (emprestimoParaDevolver != null) {
            emprestimos.remove(emprestimoParaDevolver);
            GerenciadorDeArquivos.atualizarArquivoEmprestimos(emprestimos);
            System.out.println("Livro devolvido com sucesso!");
        } else {
            System.out.println("Erro: Empréstimo não encontrado.");
        }
    }

    public void consultarLivrosCadastrados() {
        System.out.println("Livros cadastrados:");
        for (Livro l : livros) {
            System.out.println(l);
        }
    }

    public void menuAtualizacao() {
        System.out.println("--- MENU ATUALIZACAO ---");
        System.out.println("Digite o que deseja atualizar: ");
        System.out.println("1- Atualizar Livro");
        System.out.println("2- Atualizar Aluno");
        System.out.println("0- Para Sair.");

        int opcao = 0;
        if (sc.hasNextInt()) {
            opcao = sc.nextInt();
            sc.nextLine();
        } else {
            sc.nextLine();
        }
        switch (opcao) {
            case 1:
                atualizarLivro();

            case 2:
                atualizarAluno();

            case 0:
                break;
        }
    }

    public void atualizarAluno() {
        System.out.println("Digite a Matricula ou nome do aluno para atualizar: ");
        String buscaAluno = sc.nextLine();

        Aluno alunoEncontrado = null;

        for (Aluno a : alunos) {
            boolean acheiMatricula = a.getMatricula().equalsIgnoreCase(buscaAluno);
            boolean acheiNome = a.getNome().equalsIgnoreCase(buscaAluno);

            if (acheiMatricula || acheiNome) {
                alunoEncontrado = a;
                break;
            }
        }
        if (alunoEncontrado != null) {
            System.out.println("Aluno encontrado: " + alunoEncontrado.getNome() + " Matricula: " + alunoEncontrado.getMatricula());
            System.out.println("DICA: Deixe em branco e aperte ENTER para manter o valor atual.");
            System.out.println("-------------------------------------------------------------");

            System.out.println("Nome [" + alunoEncontrado.getNome() + "]: ");
            String novoNome = sc.nextLine();
            if (!novoNome.isEmpty()) alunoEncontrado.setNome(novoNome);
            System.out.println("Nome atualizado!!");

            System.out.println("Matricula[" + alunoEncontrado.getMatricula() + "]: ");
            String novoMatricula = sc.nextLine();
            if (!novoMatricula.isEmpty()) alunoEncontrado.setMatricula(novoMatricula);
            System.out.println("Matricula atualizada!!");

            System.out.println("Classe[" + alunoEncontrado.getClasse() + "]: ");
            String novoClasse = sc.nextLine();
            if (!novoClasse.isEmpty()) alunoEncontrado.setClasse(novoClasse);
            System.out.println("Classe atualizada!!");

            System.out.println("Responsável[" + alunoEncontrado.getResponsavel() + "]: ");
            String novoResponsavel = sc.nextLine();
            if (!novoResponsavel.isEmpty()) alunoEncontrado.setResponsavel(novoResponsavel);
            System.out.println("Responsavel atualizado!!");

            System.out.println("Telefone[" + alunoEncontrado.getNumeroTelefone() + "]");
            String novoTelefone = sc.nextLine();
            if (!novoTelefone.isEmpty()) alunoEncontrado.setNumeroTelefone(novoTelefone);
            System.out.println("Telefone atualizado!!");


            GerenciadorDeArquivos.atualizarArquivoAlunos(alunos);
            System.out.println("Dados do Aluno " + alunoEncontrado.getNome() + " atualizado!!");


        } else {
            System.out.println("Erro: Aluno[" + buscaAluno + "] Nao Encontrado!");
        }
    }

    public void atualizarLivro() {
        System.out.println("Digite o Codigo ou nome do livro para atualizar: ");
        String buscaLivro = sc.nextLine();

        Livro livroEncontrado = null;

        for (Livro l : livros) {
            boolean acheiNomeLivro = l.getNome().equalsIgnoreCase(buscaLivro);
            boolean acheiCodigoLivro = l.getCodigoBarras().equalsIgnoreCase(buscaLivro);

            if (acheiNomeLivro || acheiCodigoLivro) {
                livroEncontrado = l;
                break;
            }
        }
        if (livroEncontrado != null) {
            System.out.println("Livro encontrado [" + livroEncontrado.getNome() + "] Código[" + livroEncontrado.getCodigoBarras() + "]");
            System.out.println("DICA: Deixe em branco e aperte ENTER para manter o valor atual.");
            System.out.println("-------------------------------------------------------------");

            System.out.println("Código [" + livroEncontrado.getCodigoBarras() + "]: ");
            String novoCodigo = sc.nextLine();
            if (!novoCodigo.isEmpty()) livroEncontrado.setCodigoBarras(novoCodigo);
            System.out.println("Codigo atualizado!!");

            System.out.println("Nome [" + livroEncontrado.getNome() + "]: ");
            String novoNome = sc.nextLine();
            if (!novoNome.isEmpty()) livroEncontrado.setNome(novoNome);
            System.out.println("Nome atualizado!!");

            System.out.println("Autor [" + livroEncontrado.getAutor() + "]: ");
            String novoAutor = sc.nextLine();
            if (!novoAutor.isEmpty()) livroEncontrado.setAutor(novoAutor);
            System.out.println("Autor atualizado!!");

            System.out.println("Descrição [" + livroEncontrado.getDescricao() + "]: ");
            String novoDescricao = sc.nextLine();
            if (!novoDescricao.isEmpty()) livroEncontrado.setDescricao(novoDescricao);
            System.out.println("Descricao atualizada!!");

            GerenciadorDeArquivos.atualizarArquivoLivros(livros);
            System.out.println("Livro atualizado!!");

        } else {
            System.out.println("Livro[" + buscaLivro + "] Nao Encontrado!");
        }

    }


    public void salvarAlunoInterface(String nome, String matricula, String classe, String responsavel, String telefone) {
        Aluno novoAluno = new Aluno(nome, matricula, classe, responsavel, telefone);
        alunos.add(novoAluno);
        GerenciadorDeArquivos.salvarNoCsv(novoAluno);

    }

    public void salvarLivroInterface(String codigo, String titulo, String autor, String categoria, String descricao) {
        Livro novoLivro = new Livro(codigo, titulo, autor, categoria, descricao);
        livros.add(novoLivro);
        GerenciadorDeArquivos.salvarLivro(novoLivro);
    }

    public boolean emprestarLivroInterface(String nomeAluno, String codigoLivro, String dataInicial, int dias) {
        Livro livroEncontrado = buscarLivroPorTermo(codigoLivro);
        if (livroEncontrado == null) {
            return false;
        }
        Emprestimo emp = new Emprestimo(nomeAluno, livroEncontrado.getCodigoBarras(), livroEncontrado.getNome(), dataInicial, dias);

        emprestimos.add(emp);
        GerenciadorDeArquivos.salvarEmprestimo(emp);
        return true;
    }

    public Livro buscarLivroPorTermo(String termo) {
        for (Livro l : livros) {
            if (l.getCodigoBarras().equalsIgnoreCase(termo) || l.getNome().equalsIgnoreCase(termo)) {
                return l;
            }
        }
        return null;
    }

    public boolean devolverLivroInterface(String nomeAluno, String termoBusca) {
        Emprestimo emprestimoParaDevolver = null;


        for (Emprestimo e : emprestimos) {
            boolean alunoConfere = e.getNomeAluno().equalsIgnoreCase(nomeAluno);

            boolean livroConfere = e.getNomeLivro().equalsIgnoreCase(termoBusca) ||
                    e.getCodigoBarras().equalsIgnoreCase(termoBusca);

            if (alunoConfere && livroConfere) {
                emprestimoParaDevolver = e;
                break;
            }
        }
        if (emprestimoParaDevolver != null) {
            emprestimos.remove(emprestimoParaDevolver);
            GerenciadorDeArquivos.atualizarArquivoEmprestimos(emprestimos);
            return true;
        }
        return false;
    }
}

