/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.configuration;

import br.edu.ifms.noticias.manter_noticias.Noticia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.edu.ifms.noticias.manter_noticias.NoticiaRepository;

/**
 *
 * @author santos
 */
@Configuration
public class CarregarBancoDados {

    private static final Logger log = LoggerFactory.getLogger(CarregarBancoDados.class);

    @Bean
    CommandLineRunner initDatabase(NoticiaRepository repository) {
        return args -> {
            log.info("Pre-carregamento "
                    + repository.save(
                            new Noticia("Teste 1", "Descricao 1", "https://checamos.afp.com/sites/default/files/styles/list_xl/public/medias/factchecking/g2/2022-10/dd69522ba403ada4df3beb8ea798ada4.jpeg?itok=u-Lp5iAt")));
            log.info("Pre-carregamento "
                    + repository.save(
                            new Noticia("Teste 2", "Descricao 2", "https://static.poder360.com.br/2022/10/TRE-Urnas-ZonasEleitorais-Escolas-Votacao-DF-Eleicoes2022-01.out_.2022-8-848x477.jpg")
                    ));
        };
    }

}
