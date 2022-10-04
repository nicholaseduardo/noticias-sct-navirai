/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_noticias;

/**
 *
 * @author 1513003
 */
public class NoticiaRequest {
    private String titulo;
    private String descricao;
    private String urlImagem;

    public NoticiaRequest() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
    
    public Noticia cadastrar(NoticiaRepository repo) {
        Noticia obj = new Noticia(titulo, descricao, urlImagem);
        return repo.save(obj);
    }
    
    public Noticia atualizar(NoticiaRepository repo, Long id) {
        Noticia obj = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inexistente"));
        obj.setTitulo(titulo);
        obj.setUrlImagem(urlImagem);
        obj.setDescricao(descricao);
        return obj;
    }
}
