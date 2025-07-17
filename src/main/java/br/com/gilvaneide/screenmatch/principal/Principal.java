package br.com.gilvaneide.screenmatch.principal;

import br.com.gilvaneide.screenmatch.model.DadosSerie;
import br.com.gilvaneide.screenmatch.model.DadosTemporada;
import br.com.gilvaneide.screenmatch.service.ConsumoApi;
import br.com.gilvaneide.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIC_KEY = "&apikey=9de8e5d7";

    public void exibMenu() {
        System.out.println("\nDigite o nome da série para busca: ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + APIC_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();
        //Cria lista com os dados obtidos de uma temporada específica da API do OMDB
        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            var jsonTemporada = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + APIC_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporada, DadosTemporada.class); // 6. Converte o JSON da temporada para um objeto DadosTemporada
            temporadas.add(dadosTemporada); // Adiciona a temporada ao array de temporadas
        }
//
//        System.out.println("\nDADOS DE TODAS AS TEMPORADAS: ");
//        temporadas.forEach(System.out::println);

//        for (int i = 0; i < dados.totalTemporadas(); i++) {
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size(); j++) {
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        System.out.println("\n=== Títulos dos Episódios por Temporada ===");
        temporadas.forEach(t -> {
            System.out.println("\n** Temporada " + t.numero() + " **");
            t.episodios().forEach(e -> System.out.println("  - " + e.titulo()));
        });
    }
}

