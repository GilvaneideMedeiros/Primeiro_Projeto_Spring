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
        // Inicializa a aplicação Spring Boot
        SpringApplication.run(ScreenmatchApplication.class, args);
    }

    /**
     * Método executado após a inicialização do Spring.
     * Contém a lógica principal da aplicação.
     */
    @Override
    public void run(String... args) throws Exception {
        // Cria uma instância para consumir APIs
        var consumoApi = new ConsumoApi();
        
        // 1. Obtém dados de uma série específica da API do OMDB
        var jsonSerie = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=9de8e5d7");
        System.out.println("\nJSON bruto da série: " + jsonSerie);
        
        // Cria um conversor de JSON para objetos Java
        ConverteDados conversor = new ConverteDados();
        
        // 2. Converte o JSON da série para um objeto DadosSerie
        DadosSerie dados = conversor.obterDados(jsonSerie, DadosSerie.class);
        System.out.println("\nDados da série convertidos: " + dados);
        
        // 3. Obtém dados de um episódio específico da API do OMDB
        var jsonEpisodio = consumoApi.obterDados(
            "https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=9de8e5d7");
        System.out.println("\nJSON bruto do episódio: " + jsonEpisodio);
        
        // 4. Converte o JSON do episódio para um objeto DadosEpisodio
        DadosEpisodio dadosEpisodio = conversor.obterDados(jsonEpisodio, DadosEpisodio.class);
        System.out.println("\nDados do episódio convertidos: " + dadosEpisodio);

        // 5. Obtém dados de uma temporada específica da API do OMDB
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            var jsonTemporada = consumoApi.obterDados(
                "https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=9de8e5d7");

            // 6. Converte o JSON da temporada para um objeto DadosTemporada
            DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporada, DadosTemporada.class);
            // Adiciona a temporada ao array de temporadas
            temporadas.add(dadosTemporada);
           }

        System.out.println("\nDADOS DE TODAS AS TEMPORADAS: ");
        temporadas.forEach(System.out::println);
    }
}




