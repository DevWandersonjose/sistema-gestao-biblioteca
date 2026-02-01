package Entities;

public class Aluno {
    private String nome;
    private String matricula;
    private String classe;
    private String responsavel;
    private String numeroTelefone;

    // Construtor Completo
    public Aluno(String nome, String matricula, String classe, String responsavel, String numeroTelefone) {
        this.nome = nome;
        this.matricula = matricula;
        this.classe = classe;
        this.responsavel = responsavel;
        this.numeroTelefone = numeroTelefone;
    }

    // Construtor Vazio (Útil para frameworks ou inicialização posterior)
    public Aluno() {}

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getClasse() { return classe; }
    public void setClasse(String classe) { this.classe = classe; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public String getNumeroTelefone() { return numeroTelefone; }
    public void setNumeroTelefone(String numeroTelefone) { this.numeroTelefone = numeroTelefone; }

    // Opcional: toString para facilitar testes no console
    @Override
    public String toString() {
        return "Aluno: " + nome + " | Matrícula: " + matricula;
    }
}