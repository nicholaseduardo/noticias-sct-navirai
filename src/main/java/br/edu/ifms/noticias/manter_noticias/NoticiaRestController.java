/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_noticias;

import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author santos
 */
@RestController
@RequestMapping("api/noticia")
public class NoticiaRestController {
    
    @Autowired
    private NoticiaService service;
    
    @GetMapping()
    public List<NoticiaResponse> listar() {
        return service.all();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<NoticiaDetalheResponse> visualizar(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }
    
    @PostMapping()
    @Transactional
    public ResponseEntity<NoticiaDetalheResponse> cadastrar(
            @Valid NoticiaRequest form,
            UriComponentsBuilder uriBuilder) {
        Noticia t = service.add(form);
        
        URI uri = uriBuilder.path("/api/noticia/{id}")
                .buildAndExpand(t.getId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(new NoticiaDetalheResponse(t));
    }
    
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<NoticiaDetalheResponse> atualizar(
            @PathVariable Long id, 
            @Valid NoticiaRequest form) {
        Noticia t = service.update(form, id);
        if (t != null) {
            return ResponseEntity.ok(new NoticiaDetalheResponse(t));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
