package br.com.gilvaneide.screenmatch.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class AvaliacaoDeserializada extends JsonDeserializer<Double> {
    @Override
    public Double deserialize(JsonParser p, DeserializationContext deserializationContext) throws IOException {
        String valor = p.getText();
        if (valor != null && valor.equalsIgnoreCase("N/A")) {
            return 0.0;
        }
        try {
            return Double.valueOf(valor);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
