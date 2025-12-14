package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import models.Batalha;

import java.io.File;
import java.io.IOException;

public class DataProvider {
    private static final String JSON_FILE = "jogo_batalha.json";

    public void salvarSistema(Batalha batalha) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        try {
            objectMapper.writeValue(new File(JSON_FILE), batalha);
            System.out.println("Dados salvos no arquivo '" + JSON_FILE + "'");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados no arquivo JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
