package Entities;

public class Impressora {

    public void saudacao(){
        System.out.println("Digite a opção desejada: ");
    }

    public void menu(){
        System.out.println("1- Cadastrar aluno");
        System.out.println("2- Cadastrar livro");
        System.out.println("3- Emprestar livro");
        System.out.println("4- Consultar Emprestimo");
        System.out.println("5- Consultar Livro");
        System.out.println("6- Devolver Livro");
        System.out.println("7- Atualizar Cadastro");
        System.out.println("0- Sair");
    }

    public void check(){
        System.out.println("Operação efetuada com sucesso");
    }
}