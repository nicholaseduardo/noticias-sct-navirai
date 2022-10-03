/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_comentario;

import br.edu.ifms.noticias.manter_avaliacao.Avaliacao;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author 1513003
 */
public class ComentarioDto {
    private Long numero;
    private String descricao;
    private String emissao;
    private Long gostei;
    private Long naoGostei;

    public ComentarioDto() {
    }
    
    public ComentarioDto(Comentario obj) {
        this.numero = obj.getId().getNumero();
        this.descricao = obj.getDescricao();
        this.emissao = obj.getEmissao()
                .format(DateTimeFormatter
                        .ofPattern("dd/MM/yyyy HH:mm:ss"));
        List<Avaliacao> avaliacoes = obj.getAvaliacoes();
        gostei = avaliacoes.stream()
                .mapToLong(o -> o.isPositivo() ? 1L : 0L)
                .sum();
        naoGostei = avaliacoes.stream()
                .mapToLong(o -> o.isPositivo() ? 0L : 1L)
                .sum();
    }

    public Long getNumero() {
        return numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEmissao() {
        return emissao;
    }

    public Long getGostei() {
        return gostei;
    }

    public Long getNaoGostei() {
        return naoGostei;
    }
    
    public static List<ComentarioDto> converter(List<Comentario> lista) {
        List<ComentarioDto> l = lista.stream().map(ComentarioDto::new)
                .collect(Collectors.toList());
        return l;
    }
    
}
