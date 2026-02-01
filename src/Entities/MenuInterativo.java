package Entities;

import java.util.Scanner;

public class MenuInterativo {
    private BibliotecaService service;
    private Scanner sc;
    private Impressora impressora;

    // --- CORREÇÃO: O Construtor padrão agora inicializa tudo ---
    public MenuInterativo() {
        this.service = new BibliotecaService(); // Carrega os arquivos aqui
        this.sc = new Scanner(System.in);
        this.impressora = new Impressora();
    }

    public void iniciar() {
        boolean executando = true;
        while (executando) {
            impressora.saudacao();
            impressora.menu();

            int n = 0;
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                sc.nextLine(); // Consumir quebra de linha
            } else {
                sc.next(); // Limpar entrada inválida
            }

            // ATENÇÃO: Verifique se os nomes dos métodos abaixo batem
            // exatamente com os nomes dentro da sua classe BibliotecaService.
            switch (n) {
                case 1:
                    service.cadastrarAluno();
                    break;
                case 2:
                    service.cadastrarLivro();
                    break;
                case 3:
                    service.novoEmprestimo();
                    break;
                case 4:
                    // Se no Service o nome for 'consultarEmprestimos', mude aqui:
                    service.consultarEmprestimosPorAluno();
                    break;
                case 5:
                    // Se no Service o nome for 'listarLivros', mude aqui:
                    service.consultarLivrosCadastrados();
                    break;
                case 6:
                    service.devolverLivro();
                    break;
                case 7:
                    // O submenu de atualização fica melhor aqui no MenuInterativo
                    // para decidir qual método do service chamar
                    executarMenuAtualizacao();
                    break;
                case 0:
                    executando = false;
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        }
    }

    // Lógica do submenu visual
    private void executarMenuAtualizacao() {
        System.out.println("--- ATUALIZAR ---");
        System.out.println("1 - Aluno");
        System.out.println("2 - Livro");
        System.out.print("Opção: ");

        int op = 0;
        if (sc.hasNextInt()) {
            op = sc.nextInt();
            sc.nextLine();
        } else {
            sc.next();
        }

        if (op == 1) {
            service.atualizarAluno();
        } else if (op == 2) {
            service.atualizarLivro();
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}