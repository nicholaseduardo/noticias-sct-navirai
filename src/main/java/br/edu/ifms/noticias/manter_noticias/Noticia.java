/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_noticias;

import br.edu.ifms.noticias.manter_comentario.Comentario;
import br.edu.ifms.noticias.manter_comentario.ComentarioId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.Type;

/**
 *
 * @author santos
 */
@Entity
public class Noticia implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "Título é obrigatório")
    private String titulo;
    
    @Lob
    @Type(type = "text")
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
    
    @NotBlank(message = "URL da Imagem é obrigatória")
    private String urlImagem;
    
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime emissao;
    
    @OneToMany(mappedBy = "id.noticia",
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private List<Comentario> comentarios;
    
    /**
     * Número do último comentário.
     * Armazena o número do último comentário adicionado na notícia mesmo que 
     * algum comentário tenha sido removido.
     */
    private AtomicLong ultimoComentario;

    public Noticia() {
        this(null, null, null);
    }

    public Noticia(String titulo, String descricao, String urlImagem) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.urlImagem = urlImagem;
        
        emissao = LocalDateTime.now();
        comentarios = new ArrayList();
        ultimoComentario = new AtomicLong(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public LocalDateTime getEmissao() {
        return emissao;
    }

    public void setEmissao(LocalDateTime emissao) {
        this.emissao = emissao;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
        int size = comentarios.size();
        ultimoComentario.set(comentarios.get(size - 1).getId().getNumero());
    }
    
    public Noticia add(Comentario value) {
        value.setId(new ComentarioId(ultimoComentario.incrementAndGet(), this));
        
        this.comentarios.add(value);
        return this;
    }
    
    public Noticia remove(Comentario value) {
        this.comentarios.remove(value);
        value.getId().setNoticia(null);
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.titulo);
        hash = 97 * hash + Objects.hashCode(this.descricao);
        hash = 97 * hash + Objects.hashCode(this.urlImagem);
        hash = 97 * hash + Objects.hashCode(this.emissao);
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
        
        final Noticia other = (Noticia) obj;
        if (!Objects.equals(this.titulo, other.getTitulo())) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.getDescricao())) {
            return false;
        }
        if (!Objects.equals(this.urlImagem, other.getUrlImagem())) {
            return false;
        }
        if (!Objects.equals(this.id, other.getId())) {
            return false;
        }
        return Objects.equals(this.emissao, other.getEmissao());
    }
    
}
