/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_comentario;

import br.edu.ifms.noticias.manter_avaliacao.TipoAvaliacao;
import br.edu.ifms.noticias.manter_noticias.Noticia;
import br.edu.ifms.noticias.manter_noticias.NoticiaDetalheResponse;
import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author santos
 */
@RestController
@RequestMapping("api/comentario")
public class ComentarioRestController {

    @Autowired
    private ComentarioService service;

    @PostMapping("/noticiaId")
    @Transactional
    public ResponseEntity<ComentarioResponse> cadastrar(
            @PathVariable("noticiaId") Long noticiaId,
            @Valid ComentarioRequest comentario,
            UriComponentsBuilder uriBuilder) {
        Comentario t = service.add(noticiaId, comentario);

        URI uri = uriBuilder.path("/api/comentario/{noticiaId}/{numero}")
                .buildAndExpand(noticiaId,
                        t.getId().getNumero())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(new ComentarioResponse(t));
    }

    @GetMapping("/gostei/{nid}/{comid}")
    @Transactional
    public ResponseEntity<?> gostei(
            @PathVariable("nid") Long nid,
            @PathVariable("comid") Long comid,
            HttpServletRequest request) {
        service.avaliar(nid, comid, TipoAvaliacao.POSITIVO, request);
        
        return ResponseEntity.ok().build();
    }    

    @GetMapping("/naogostei/{nid}/{comid}")
    @Transactional
    public ResponseEntity<?> naoGostei(
            @PathVariable("nid") Long nid,
            @PathVariable("comid") Long comid,
            HttpServletRequest request) {
        service.avaliar(nid, comid, TipoAvaliacao.NEGATIVO, request);
        
        return ResponseEntity.ok().build();
    }
}
