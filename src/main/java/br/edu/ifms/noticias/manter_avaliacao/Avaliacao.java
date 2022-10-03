/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_avaliacao;

import br.edu.ifms.noticias.manter_noticias.LocalDateTimeAttributeConverter;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author santos
 */
@Entity
public class Avaliacao {
    
    @EmbeddedId
    private AvaliacaoId id;
    private String ip;
    
    @Convert(converter = TipoAvaliacaoConverter.class)
    private TipoAvaliacao avaliacao;
    
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime emissao;

    public Avaliacao() {
    }

    public Avaliacao(String ip, TipoAvaliacao avaliacao) {
        this.ip = ip;
        this.avaliacao = avaliacao;
        emissao = LocalDateTime.now();
    }

    public AvaliacaoId getId() {
        return id;
    }

    public void setId(AvaliacaoId id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public TipoAvaliacao getAvaliacao() {
        return avaliacao;
    }
    
    public Boolean isPositivo() {
        return TipoAvaliacao.POSITIVO.equals(this.avaliacao);
    }

    public void setAvaliacao(TipoAvaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDateTime getEmissao() {
        return emissao;
    }

    public void setEmissao(LocalDateTime emissao) {
        this.emissao = emissao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.ip);
        hash = 17 * hash + Objects.hashCode(this.avaliacao);
        hash = 17 * hash + Objects.hashCode(this.emissao);
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
        
        final Avaliacao other = (Avaliacao) obj;
        if (!Objects.equals(this.ip, other.getIp())) {
            return false;
        }
        if (!Objects.equals(this.id, other.getId())) {
            return false;
        }
        if (this.avaliacao != other.getAvaliacao()) {
            return false;
        }
        return Objects.equals(this.emissao, other.getEmissao());
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "id=" + id + ", ip=" + ip + ", avaliacao=" + avaliacao + ", emissao=" + emissao + '}';
    }
    
}
