/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_comentario;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author santos
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, ComentarioId> {
    
    public Optional<Comentario> findByIdNoticiaIdAndIdNumero(Long noticiaId, Long numero);
    
}
