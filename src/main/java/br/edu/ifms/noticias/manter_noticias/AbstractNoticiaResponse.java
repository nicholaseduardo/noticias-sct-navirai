/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_noticias;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author 1513003
 */
public abstract class AbstractNoticiaResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private String urlImagem;
    private String emissao;

    public AbstractNoticiaResponse() {
    }
    
    public AbstractNoticiaResponse(Noticia obj) {
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
        this.descricao = obj.getDescricao();
        this.urlImagem = obj.getUrlImagem();
        this.emissao = obj.getEmissao()
                .format(DateTimeFormatter
                        .ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public String getEmissao() {
        return emissao;
    }
}
