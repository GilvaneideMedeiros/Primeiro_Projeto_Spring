package br.com.gilvaneide.screenmatch;

// Importações necessárias para a classe

import br.com.gilvaneide.screenmatch.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Screenmatch.
 * Implementa CommandLineRunner para permitir a execução de código após a inicialização do Spring.
 */
@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

    /**
     * Método principal que inicia a aplicação Spring Boot.
     * @param args Argumentos de linha de comando
     */
    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);  // Inicializa a aplicação Spring Boot
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.exibMenu();

//        List<DadosTemporada> temporadas = new ArrayList<>(); //Cria lista com os dados obtidos de uma temporada específica da API do OMDB
//        for (int i = 1; i <= dados.totalTemporadas(); i++) {
//            var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=9de8e5d7");
//            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class); // 6. Converte o JSON da temporada para um objeto DadosTemporada
//            temporadas.add(dadosTemporada); // Adiciona a temporada ao array de temporadas
//           }
//
//        System.out.println("\nDADOS DE TODAS AS TEMPORADAS: ");
//        temporadas.forEach(System.out::println);
    }
}




