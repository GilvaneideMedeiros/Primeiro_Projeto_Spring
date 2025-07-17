package br.com.gilvaneide.screenmatch;

// Importações necessárias para a classe
import br.com.gilvaneide.screenmatch.model.DadosSerie;  // Classe que representa os dados de uma série
import br.com.gilvaneide.screenmatch.model.DadosEpisodio;  // Classe que representa os dados de um episódio
import br.com.gilvaneide.screenmatch.model.DadosTemporada;
import br.com.gilvaneide.screenmatch.service.ConsumoApi;  // Classe para consumir APIs externas
import br.com.gilvaneide.screenmatch.service.ConverteDados;  // Classe para converter JSON em objetos Java
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

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
    /**
     * Método executado após a inicialização do Spring.
     * Contém a lógica principal da aplicação.
     */
    @Override
    public void run(String... args) throws Exception {
        var consumoApi = new ConsumoApi(); // Cria uma instância para consumir APIs

        var jsonSerie = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=9de8e5d7");  //Obtém dados de uma série específica da API do OMDB
        System.out.println("\nJSON bruto da série: " + jsonSerie);
        ConverteDados conversor = new ConverteDados(); // Cria um conversor de JSON para objetos Java
        DadosSerie dados = conversor.obterDados(jsonSerie, DadosSerie.class); //Converte o JSON da série para um objeto DadosSerie
        System.out.println("\nDados da série convertidos: " + dados);

        var jsonEpisodio = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=9de8e5d7"); //Obtém dados de um episódio específico da API do OMDB
        System.out.println("\nJSON bruto do episódio: " + jsonEpisodio);
        DadosEpisodio dadosEpisodio = conversor.obterDados(jsonEpisodio, DadosEpisodio.class);  // Converte o JSON do episódio para um objeto DadosEpisodio
        System.out.println("\nDados do episódio convertidos: " + dadosEpisodio);

        List<DadosTemporada> temporadas = new ArrayList<>(); //Cria lista com os dados obtidos de uma temporada específica da API do OMDB
        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            var jsonTemporada = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=9de8e5d7");
            DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporada, DadosTemporada.class); // 6. Converte o JSON da temporada para um objeto DadosTemporada
            temporadas.add(dadosTemporada); // Adiciona a temporada ao array de temporadas
           }

        System.out.println("\nDADOS DE TODAS AS TEMPORADAS: ");
        temporadas.forEach(System.out::println);
    }
}




