/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_noticias;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
public class NoticiaService {
    
    @Autowired
    private NoticiaRepository repo;
    
    public NoticiaDetalheResponse get(Long id) {
        Noticia obj = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido"));
        return new NoticiaDetalheResponse(obj);
    }
    
    public List<NoticiaResponse> all() {
        List<Noticia> lista = repo.findAll();
        return NoticiaResponse.converter(lista);
    }
    
    public Noticia add(NoticiaRequest request) {
        return request.cadastrar(repo);
    }
    
    public Noticia update(NoticiaRequest request, Long id) {
        return request.atualizar(repo, id);
    }
    
    public void delete(Long id) {
        Noticia noticia = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido!"));
        repo.delete(noticia);
    }
    
}
