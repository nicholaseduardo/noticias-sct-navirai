/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_comentario;

import br.edu.ifms.noticias.manter_avaliacao.Avaliacao;
import br.edu.ifms.noticias.manter_avaliacao.AvaliacaoRepository;
import br.edu.ifms.noticias.manter_avaliacao.TipoAvaliacao;
import br.edu.ifms.noticias.manter_noticias.Noticia;
import br.edu.ifms.noticias.manter_noticias.NoticiaRepository;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author santos
 */
@Controller
@RequestMapping("comentario")
public class ComentarioController {

    @Autowired
    private NoticiaRepository noticiaRepo;

    @Autowired
    private ComentarioRepository repo;
    
    @Autowired
    private AvaliacaoRepository avaliacaoRepo;

    @PostMapping("/add/{noticiaId}")
    @Transactional
    public String add(
            @PathVariable("noticiaId") Long noticiaId,
            @Valid Comentario comentario,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "view-noticia";
        }
        Noticia noticia = noticiaRepo.findById(noticiaId)
                .orElseThrow(() -> new IllegalArgumentException("Id inexistente"));
        noticia.add(comentario);
        repo.save(comentario);
        return "redirect:/noticia/view/" + noticiaId;
    }

    @GetMapping("/gostei/{nid}/{comid}")
    public String gostei(
            @PathVariable("nid") Long nid,
            @PathVariable("comid") Long comid,
            Model model,
            HttpServletRequest request) {
        Noticia noticia = noticiaRepo.findById(nid)
                .orElseThrow(() -> new IllegalArgumentException("Id inexistente"));
        Comentario comentario = repo.findById(new ComentarioId(comid, noticia))
                .orElseThrow(() -> new IllegalArgumentException("Id de comentário inválido!"));
        
        Avaliacao avaliacao = new Avaliacao(
                request.getRemoteAddr(),
                TipoAvaliacao.POSITIVO);
        comentario.add(avaliacao);
        avaliacaoRepo.save(avaliacao);
        
        return "redirect:/noticia/view/" + nid;        
    }

    @GetMapping("/naogostei/{nid}/{comid}")
    public String naogostei(
            @PathVariable("nid") Long nid,
            @PathVariable("comid") Long comid,
            Model model,
            HttpServletRequest request) {
        Noticia noticia = noticiaRepo.findById(nid)
                .orElseThrow(() -> new IllegalArgumentException("Id inexistente"));
        Comentario comentario = repo.findById(new ComentarioId(comid, noticia))
                .orElseThrow(() -> new IllegalArgumentException("Id de comentário inválido!"));
        
        Avaliacao avaliacao = new Avaliacao(
                request.getRemoteAddr(),
                TipoAvaliacao.NEGATIVO);
        comentario.add(avaliacao);
        avaliacaoRepo.save(avaliacao);
        
        return "redirect:/noticia/view/" + nid;        
    }

}
