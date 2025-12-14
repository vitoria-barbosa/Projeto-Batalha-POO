package models;

import lombok.Data;

import java.util.ArrayList;

import static utils.BatalhaUtils.*;

@Data
public class Batalha {
    private ArrayList<Personagem> personagens;
    private ArrayList<Acao> acoes;

    public Batalha() {
        this.personagens = new ArrayList<>();
        this.acoes = new ArrayList<>();
    }

    public void turno(Personagem atacante, Personagem atacado) {
        criarAcao(atacante, atacado, this); // faz o ataque e cria a ação
    }

    public Personagem procurarVencedor() {
        Personagem personagemComMaiorVida = null;

        for (Personagem personagem : this.personagens) {
            if (personagemComMaiorVida == null) {
                personagemComMaiorVida = personagem;
            }

            if (personagem.getVida() > personagemComMaiorVida.getVida()) {
                personagemComMaiorVida = personagem;
            }
        }
        return personagemComMaiorVida;
    }

    public void adicionarPersonagem(Personagem personagem) {
        this.personagens.add(personagem);
    }

    public void adicionarAcao(Acao acao) {
        this.acoes.add(acao);
    }
}