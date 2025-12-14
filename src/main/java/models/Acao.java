package models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Acao {
    private static int idCounter = 0;

    private int id;
    private Personagem origem;
    private Personagem alvo;
    private String descricao;
    private int valorDano;
    private LocalDateTime dataHora;

    public Acao(Personagem origem, Personagem alvo, String descricao, int valorDano, LocalDateTime dataHora) {
        this.id = ++idCounter;
        this.origem = origem;
        this.alvo = alvo;
        this.descricao = descricao;
        this.valorDano = valorDano;
        this.dataHora = dataHora;
    }

    public String mostrarInfo() {
        return String.format("\n💥Ação %d:\n" +
                        "Origem: %s\n" +
                        "Alvo: %s\n" +
                        "Descrição: %s\n" +
                        "Dano: %d\n" +
                        "Data-Hora: %s",
                this.id, this.origem.getNome(), this.alvo.getNome(),
                this.descricao, this.valorDano, this.dataHora);
    }
}
