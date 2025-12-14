package models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class Personagem {
    private static int idCounter = 0;

    private int id;
    private String nome;
    private int vida;
    private int ataque;
    private boolean vivo;
    @JsonIgnore
    private ArrayList<Acao> historicoAcoes;

    public Personagem(String nome, int ataque) {
        this.id = ++idCounter;
        this.nome = nome;
        this.vida = 100;
        this.ataque = ataque;
        this.vivo = true;
        this.historicoAcoes = new ArrayList<>();
    }

    public void receberDano(int dano) {
        this.vida -= dano;

        if (this.vida <= 0) {
            this.vida = 0;
            this.vivo = false;
        }
    }

    public Acao atacar(Personagem alvo, Batalha batalha) {

        String descricao = String.format("%s atacou %s", this.getNome(), alvo.getNome());
        System.out.println("\n☑️" + descricao);

        int dano = this.ataque;
        alvo.receberDano(dano);

        return new Acao(this, alvo, descricao, dano, LocalDateTime.now());
    }

    public Acao atacarDanoEspecifico(Personagem alvo, int dano, String descricao) {
        alvo.receberDano(dano);

        return new Acao(this, alvo, descricao, dano, LocalDateTime.now());
    }

    public abstract String dados();

    public boolean estaVivo() {
        return vida > 0;
    }

    public void registrarAcao(Acao acao) {
        this.historicoAcoes.add(acao);
    }

}
