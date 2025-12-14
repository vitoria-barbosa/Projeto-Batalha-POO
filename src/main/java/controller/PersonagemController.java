package controller;

import exceptions.ValidacaoPersonagemInvalido;
import models.Acao;
import models.Personagem;

import java.util.ArrayList;
import java.util.Comparator;

import static utils.PersonagensUtils.*;

public class PersonagemController {
    // GET
    public Personagem criarPersonagem(ArrayList<Personagem> personagens) {
        try {
            int escolhaPersonagem = escolhaDoPersonagem();
            return criarPersonagemJogo(escolhaPersonagem, personagens);
        } catch (ValidacaoPersonagemInvalido e) {
            System.out.println(e.getMessage());
            return criarPersonagem(personagens);
        }
    }

    // READ
    public void listarAcoesDoPersonagem(Personagem personagem){
        ArrayList<Acao> listaAcoes = personagem.getHistoricoAcoes();
        listaAcoes.sort(Comparator.comparingInt(Acao::getId));
        for (Acao acao : listaAcoes){
            System.out.println(acao.mostrarInfo());
        }
    }
}
