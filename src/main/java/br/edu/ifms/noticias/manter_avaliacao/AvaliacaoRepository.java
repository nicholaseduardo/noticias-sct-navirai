/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.noticias.manter_avaliacao;

import br.edu.ifms.noticias.manter_comentario.Comentario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author santos
 */
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, AvaliacaoId> {
    
    public Optional<Avaliacao> findByIdComentarioAndIp(Comentario comentario, String ip);
    
}
