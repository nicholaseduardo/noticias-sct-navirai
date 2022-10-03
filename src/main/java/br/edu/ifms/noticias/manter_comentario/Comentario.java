/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_comentario;

import br.edu.ifms.noticias.manter_avaliacao.Avaliacao;
import br.edu.ifms.noticias.manter_avaliacao.AvaliacaoId;
import br.edu.ifms.noticias.manter_noticias.LocalDateTimeAttributeConverter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author santos
 */
@Entity
public class Comentario implements Serializable {
    
    @EmbeddedId
    private ComentarioId id;
    
    @NotBlank(message = "A descrição é obrigatória!")
    private String descricao;
    
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime emissao;
    
    @OneToMany(mappedBy = "id.comentario",
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private List<Avaliacao> avaliacoes;
    
    /**
     * Número da última avaliação.
     * Armazena o número do última avaliação adicionada ao comentário mesmo que 
     * alguma avaliação já tenha sido removida.
     */
    private AtomicLong ultimaAvaliacao;

    public Comentario() {
        this(null);
    }

    public Comentario(String descricao) {
        this.descricao = descricao;
        emissao = LocalDateTime.now();
        ultimaAvaliacao = new AtomicLong(0);
    }

    public ComentarioId getId() {
        return id;
    }

    public void setId(ComentarioId id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getEmissao() {
        return emissao;
    }

    public void setEmissao(LocalDateTime emissao) {
        this.emissao = emissao;
    }
    
    public Long totalPositivos() {
        return avaliacoes.stream()
                .mapToLong(obj -> obj.isPositivo() ? 1L : 0L)
                .sum();
    }
    
    public Long totalNegativos() {
        List<Avaliacao> lista = getAvaliacoes();
        return lista.stream()
                .mapToLong(obj -> !obj.isPositivo() ? 1L : 0L)
                .sum();
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
    
    public Comentario add(Avaliacao value) {
        value.setId(new AvaliacaoId(ultimaAvaliacao.incrementAndGet(), this));
        
        this.avaliacoes.add(value);
        return this;
    }
    
    public Comentario remove(Avaliacao value) {
        this.avaliacoes.remove(value);
        value.getId().setComentario(null);
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.descricao);
        hash = 11 * hash + Objects.hashCode(this.emissao);
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
        
        final Comentario other = (Comentario) obj;
        if (!Objects.equals(this.descricao, other.getDescricao())) {
            return false;
        }
        if (!Objects.equals(this.id, other.getId())) {
            return false;
        }
        return Objects.equals(this.emissao, other.getEmissao());
    }
    
}
