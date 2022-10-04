/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_noticias;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 1513003
 */
public class NoticiaResponse extends AbstractNoticiaResponse {

    public NoticiaResponse() {
    }
    
    public NoticiaResponse(Noticia obj) {
        super(obj);
    }
    
    public static List<NoticiaResponse> converter(List<Noticia> lista) {
        return lista.stream().map(NoticiaResponse::new)
                .collect(Collectors.toList());
    }
}
