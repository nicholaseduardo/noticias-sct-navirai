/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_avaliacao;

import br.edu.ifms.noticias.manter_comentario.Comentario;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author santos
 */
@Embeddable
public class AvaliacaoId implements Serializable {

    private Long numero;
    @ManyToOne
    private Comentario comentario;

    public AvaliacaoId() {
    }

    public AvaliacaoId(Long numero, Comentario comentario) {
        this.numero = numero;
        this.comentario = comentario;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.numero);
        hash = 67 * hash + Objects.hashCode(this.comentario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        
        final AvaliacaoId other = (AvaliacaoId) obj;
        if (!Objects.equals(this.numero, other.getNumero())) {
            return false;
        }
        return Objects.equals(this.comentario, other.getComentario());
    }
    
}
