/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_comentario;

import br.edu.ifms.noticias.manter_noticias.Noticia;
import br.edu.ifms.noticias.manter_noticias.NoticiaRepository;

/**
 *
 * @author 1513003
 */
public class ComentarioRequest {
    
    private String descricao;

    public ComentarioRequest() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Comentario cadastrar(
            NoticiaRepository noticiaRepo,
            ComentarioRepository repo, Long id) {
        Noticia noticia = noticiaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inexistente"));
        
        Comentario obj = new Comentario(descricao);
        noticia.add(obj);
        return repo.save(obj);
    }
    
    public Comentario atualizar(ComentarioRepository repo, Long id, Long numero) {
        Comentario obj = repo.findByIdNoticiaIdAndIdNumero(id, numero)
                .orElseThrow(() -> new IllegalArgumentException("Id e Número inválidos"));
        obj.setDescricao(descricao);
        
        return obj;
    }
}
