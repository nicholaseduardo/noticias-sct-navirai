/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_comentario;

import br.edu.ifms.noticias.manter_noticias.Noticia;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author santos
 */
@Embeddable
public class ComentarioId implements Serializable {
    
    private Long numero;
    
    @ManyToOne
    private Noticia noticia;

    public ComentarioId() {
    }

    public ComentarioId(Long numero, Noticia noticia) {
        this.numero = numero;
        this.noticia = noticia;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.numero);
        hash = 89 * hash + Objects.hashCode(this.noticia);
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
        
        final ComentarioId other = (ComentarioId) obj;
        if (!Objects.equals(this.numero, other.getNumero())) {
            return false;
        }
        return Objects.equals(this.noticia, other.getNoticia());
    }
    
    
}
