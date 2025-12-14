package utils;

import controller.PersonagemController;
import exceptions.*;
import models.*;
import static utils.EntradaUtils.enterPraContinuar;
import java.util.ArrayList;

public class BatalhaUtils {

    public static void adicionarPersonagemNaBatalha(Batalha batalha, int qtdPersonagens) {
        PersonagemController personagemController = new PersonagemController();
        ArrayList<Personagem> personagens = batalha.getPersonagens();

        for (int i = 0; i < qtdPersonagens; i++) {
            try {
                Personagem novoPersonagem = personagemController.criarPersonagem(personagens);
                batalha.adicionarPersonagem(novoPersonagem);
                System.out.println("Personagem " + novoPersonagem.getNome() + " adicionado na batalha com sucesso.✅\n");

            } catch (Exception e) {
                System.out.println("Erro ao adicionar personagem: " + e.getMessage());
            }
        }
    }

    public static void criarAcao(Personagem atacante, Personagem atacado, Batalha batalha) {
        Acao acao = atacante.atacar(atacado, batalha);
        atacante.registrarAcao(acao);
        batalha.adicionarAcao(acao);
    }

    public static void dueloAteAMorte(Personagem personagem1, Personagem personagem2, Batalha batalha) {

        while (true) {
            if (!personagem1.isVivo() || !personagem2.isVivo()) break;
            batalha.turno(personagem1, personagem2);
            mostrarDadosAposAtaque(personagem1, personagem2);
            enterPraContinuar();

            if (!personagem2.isVivo() || !personagem1.isVivo()) break;
            batalha.turno(personagem2, personagem1);
            mostrarDadosAposAtaque(personagem2, personagem1);
            enterPraContinuar();
        }

        mostarVencedorDuelo(personagem1, personagem2);
    }

    public static void mostarVencedorDuelo(Personagem personagem1, Personagem personagem2) {
        if (!personagem1.isVivo() && !personagem2.isVivo()) {
            System.out.println("✖️" + personagem1.getNome() + " e " + personagem2.getNome() + " morreram durante o duelo. Ninguém ganhou!");
        } else {
            System.out.println(personagem1.isVivo() ? "\n🎉" + personagem1.getNome() + " ganhou o duelo!!!" : "\n🎉" + personagem2.getNome() + " ganhou o duelo!!!");
        }
    }

    public static void mostrarDadosAposAtaque(Personagem personagem1, Personagem personagem2) {
        System.out.println("\nDados após o ataque:");
        System.out.println(personagem1.dados());
        System.out.println(personagem2.dados());
    }

    public static Personagem procurarPersonagemPeloId(int id, ArrayList<Personagem> listaPersonagens) throws ValidacaoPersonagemInvalido {
        for (Personagem personagem : listaPersonagens) {
            if (personagem.getId() == id) {
                return personagem;
            }
        }

        throw new ValidacaoPersonagemInvalido("Erro: Não existe nenhum personagem com o ID informado.");
    }

    public static String msgInicio() {
        return "=====================================================\n" +
                "           ⚔️✨GAME BATALHA BY VITÓRIA♥️🪄\n" +
                "=====================================================\n" +
                "VAMOS COMEÇAR:\n" +
                "> Quantidade de personagens que terá na batalha: ";
    }

    public static String menu() {
        return "\n1 - DUELAR\n" +
                "0 - ENCERRAR BATALHA\n" +
                "Sua escolha >>>";
    }

    public static String opcoes() {
        return "\n\n=================== SOBRE A BATALHA ====================\n" +
                "1 - LISTAR VIVOS E MORTOS 🩷☠️\n" +
                "2 - ESTATÍSTICAS DE UM PERSONAGEM 📈\n" +
                "3 - REPLAY DA BATALHA ⏯️\n" +
                "4 - SALVAR DADOS DA BATALHA EM ARQUIVO JSON 🗂️\n" +
                "0 - SAIR DO JOGO ✖️\n" +
                "SUA ESCOLHA >>>";
    }
}
