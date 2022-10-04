/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_noticias;

import br.edu.ifms.noticias.manter_comentario.ComentarioResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1513003
 */
public class NoticiaDetalheResponse extends AbstractNoticiaResponse {
    
    private List<ComentarioResponse> comentarios = new ArrayList();
    private int numeroComentarios;

    public NoticiaDetalheResponse() {
    }

    public NoticiaDetalheResponse(Noticia obj) {
        super(obj);
        this.comentarios = ComentarioResponse.converter(obj.getComentarios());
        this.numeroComentarios = this.comentarios.size();
    }

    public List<ComentarioResponse> getComentarios() {
        return comentarios;
    }

    public int getNumeroComentarios() {
        return numeroComentarios;
    }
    
}
