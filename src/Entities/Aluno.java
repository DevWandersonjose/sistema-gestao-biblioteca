package Entities;

public class Aluno {
    private String nome;
    private String matricula;
    private String classe;
    private String responsavel;
    private String numeroTelefone;

    public Aluno(String nome, String matricula, String classe, String responsavel, String numeroTelefone) {
        this.nome = nome;
        this.matricula = matricula;
        this.classe = classe;
        this.responsavel = responsavel;
        this.numeroTelefone = numeroTelefone;
    }

    public String getNome() { return nome; }
    public String getMatricula() { return matricula; }
    public String getClasse() { return classe; }
    public String getResponsavel() { return responsavel; }
    public String getNumeroTelefone() { return numeroTelefone; }
}