/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.edu.ifms.noticias.manter_avaliacao;

import javax.persistence.AttributeConverter;

/**
 *
 * @author santos
 */
public class TipoAvaliacaoConverter implements AttributeConverter<TipoAvaliacao, String> {

    @Override
    public String convertToDatabaseColumn(TipoAvaliacao tipo) {
        if (tipo == null) {
            return null;
        }
        return tipo.toString();
    }

    @Override
    public TipoAvaliacao convertToEntityAttribute(String tipo) {
        if (tipo == null) {
            return null;
        }
        return TipoAvaliacao.valueOf(tipo);
    }

}
