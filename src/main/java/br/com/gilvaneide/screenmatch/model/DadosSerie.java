package br.com.gilvaneide.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeassons") Integer totalTemporadas,
                         @JsonAlias("imdbRating") String avaliacao) {
}
