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

    public String getCodigoBarras() { return codigoBarras; }
    public String getNome() { return nome; }
    public String getAutor() { return autor; }
    public String getCategoria() { return categoria; }
    public String getDescricao() { return descricao; }
}