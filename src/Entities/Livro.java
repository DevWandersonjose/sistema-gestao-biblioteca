package Entities;

public class Livro {
    private String codigoBarras;
    private String nome;
    private String autor;
    private String categoria;
    private String descricao;

    public Livro(String codigoBarras, String nome, String autor, String categoria, String descricao) {
        this.codigoBarras = codigoBarras;
        this.nome = nome;
        this.autor = autor;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCodigoBarras() { return codigoBarras; }

    // --- CORREÇÃO AQUI ---
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras; // Faltava o "this."
    }

    @Override
    public String toString() {
        return "Cód: " + codigoBarras + " | Livro: " + nome + " (" + autor + ")";
    }
}