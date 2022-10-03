/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_noticias;

import br.edu.ifms.noticias.manter_comentario.ComentarioDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1513003
 */
public class NoticiaDetalheDto extends AbstractNoticiaDto {
    
    private String descricao;
    private List<ComentarioDto> comentarios = new ArrayList();

    public NoticiaDetalheDto() {
    }

    public NoticiaDetalheDto(Noticia obj) {
        super(obj);
        this.descricao = obj.getDescricao();
        this.comentarios = ComentarioDto.converter(obj.getComentarios());
    }

    public String getDescricao() {
        return descricao;
    }

    public List<ComentarioDto> getComentarios() {
        return comentarios;
    }
    
}
