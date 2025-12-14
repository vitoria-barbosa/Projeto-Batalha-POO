package controller;

import exceptions.*;
import models.Acao;
import models.Batalha;
import models.Personagem;

import java.util.ArrayList;
import java.util.Comparator;

import static utils.BatalhaUtils.*;
import static utils.validacaoutils.personagem.ValidacaoPersonagem.validarAtaqueASiMesmo;
import static utils.validacaoutils.personagem.ValidacaoPersonagem.validarPersonagemVivo;

public class BatalhaController {

    public void criarBatalhaComPersonagens(Batalha batalha, int qtdPersonagens) {
        try {
            adicionarPersonagemNaBatalha(batalha, qtdPersonagens);
        } catch (Exception e) {
            System.out.println("Erro: erro ao criar batalha com personagens");
        }
    }

    public void iniciarDuelo(int idPersonagem1, int idPersonagem2, Batalha batalha) throws ValidacaoDueloInvalido{
        try {
            Personagem personagem1 = procurarPersonagemPeloId(idPersonagem1, batalha.getPersonagens());
            Personagem personagem2 = procurarPersonagemPeloId(idPersonagem2, batalha.getPersonagens());

            validarPersonagemVivo(personagem1); // valida se os personagens que o usuário escolheu não já não estão mortos.
            validarPersonagemVivo(personagem2);
            validarAtaqueASiMesmo(personagem1, personagem2);

            dueloAteAMorte(personagem1, personagem2, batalha);
        } catch (Exception e) {
            throw new ValidacaoDueloInvalido("Duelo inválido - " + e.getMessage());
        }
    }

    public void listarPersonagens(Batalha batalha) {
        for (Personagem personagem : batalha.getPersonagens()) {
            System.out.println(personagem.dados());
        }
    }

    public void listarAcoes(Batalha batalha) {
        ArrayList<Acao> acoes = batalha.getAcoes();
        acoes.sort(Comparator.comparing(Acao::getId));

        for (Acao acao : acoes) {
            System.out.println(acao.mostrarInfo());
        }
    }
}
