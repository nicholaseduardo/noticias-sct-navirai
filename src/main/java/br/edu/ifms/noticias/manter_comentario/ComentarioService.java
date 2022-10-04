/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_comentario;

import br.edu.ifms.noticias.manter_avaliacao.Avaliacao;
import br.edu.ifms.noticias.manter_avaliacao.AvaliacaoId;
import br.edu.ifms.noticias.manter_avaliacao.AvaliacaoRepository;
import br.edu.ifms.noticias.manter_avaliacao.TipoAvaliacao;
import br.edu.ifms.noticias.manter_noticias.Noticia;
import br.edu.ifms.noticias.manter_noticias.NoticiaRepository;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
public class ComentarioService {
    @Autowired
    private NoticiaRepository noticiaRepo;

    @Autowired
    private ComentarioRepository repo;
    
    @Autowired
    private AvaliacaoRepository avaliacaoRepo;
    
    public void add(Long noticiaId, ComentarioRequest request) {
        request.cadastrar(noticiaRepo, repo, noticiaId);
    }
    
    public Boolean avaliar(Long nid, Long comid, TipoAvaliacao tipo,
            HttpServletRequest request) {
        Noticia noticia = noticiaRepo.findById(nid)
                .orElseThrow(() -> new IllegalArgumentException("Id inexistente"));
        Comentario comentario = repo.findById(new ComentarioId(comid, noticia))
                .orElseThrow(() -> new IllegalArgumentException("Id de comentário inválido!"));
        
        String ip = request.getRemoteAddr();
        
        Optional<Avaliacao> op = avaliacaoRepo
                .findByIdComentarioAndIp(comentario, ip);
        if (op.isPresent()) {
            return false;
        }
        Avaliacao avaliacao = new Avaliacao(ip, tipo);
        comentario.add(avaliacao);
        avaliacaoRepo.save(avaliacao);
        repo.save(comentario);
        return true;
    }
}
