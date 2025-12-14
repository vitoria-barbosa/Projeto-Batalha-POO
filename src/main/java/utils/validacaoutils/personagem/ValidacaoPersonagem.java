package utils.validacaoutils.personagem;

import exceptions.*;
import models.Personagem;
import java.util.ArrayList;

public class ValidacaoPersonagem {

    public static void validarPersonagemVivo(Personagem personagem) throws ValidacaoPersonagemMorto {
        if(!personagem.estaVivo()){
            throw new ValidacaoPersonagemMorto("Erro: Personagem " + personagem.getNome() + " está morto(a) e não pode duelar mais.");
        }
    }

    public static void validarAtaqueASiMesmo(Personagem origem, Personagem alvo) throws ValidacaoAtaquePersonagem {
        if(origem.equals(alvo)){
            throw new ValidacaoAtaquePersonagem("Erro: Personagem " + origem.getNome() + " não pode duelar contra si mesmo(a).");
        }
    }

    public static void validarNomePersonagem(String nome, ArrayList<Personagem> personagens) throws ValidacaoNomePersonagem {
        for(Personagem personagem : personagens){
            if(personagem.getNome().equals(nome)){
                throw new ValidacaoNomePersonagem("Erro: Já existe um personagem com o nome " + nome + ".");
            }
        }
    }

}
