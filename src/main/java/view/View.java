package view;

import controller.*;
import exceptions.*;
import models.*;
import services.DataProvider;

import java.util.ArrayList;

import static utils.EntradaUtils.*;
import static utils.BatalhaUtils.*;
import static utils.validacaoutils.batalha.ValidacaoBatalha.validarVidaVencedor;

public class View {
    private static Batalha batalha = new Batalha();
    private static BatalhaController batalhaController = new BatalhaController();
    private static PersonagemController personagemController = new PersonagemController();
    private static DataProvider dataProvider = new DataProvider();

    public void iniciarJogo() {
        try {
            int qtdPersonagens = receberInteiro(msgInicio());
            batalhaController.criarBatalhaComPersonagens(batalha, qtdPersonagens);
        } catch (ValidacaoEntrada e) {
            System.out.println(e.getMessage());
            iniciarJogo();
        }
    }

    public void iniciarDuelos() {
        try {
            System.out.println("================ ⚔️ INICIAR DUELOS ⚔️ ================");
            System.out.println("OBJETIVO: Duelar com todos os personagens até restar\n" +
                               "somente um vivo ou escolher encerrar a batalha.");

            int contagemDuelos = 0;
            int qtdPersonagens = batalha.getPersonagens().size();

            while (contagemDuelos < qtdPersonagens - 1) {
                int opcao = receberInteiro(menu());
                if (opcao == 1) {
                    duelar();
                    contagemDuelos += 1;
                } else return;
            }

            elegerVencedor();

        } catch (ValidacaoEntrada e) {
            System.out.println(e.getMessage());
            iniciarDuelos();
        }
    }

    public void elegerVencedor() {
        try {
            Personagem vencedor = batalha.procurarVencedor();
            validarVidaVencedor(vencedor);
            System.out.println("\n✨ BATALHA FINALIZADA ✨");
            System.out.println("🏆" + vencedor.getNome() + " GANHOU A BATALHA!!! 🏆");
        } catch (ValidacaoVidaVencedor e) {
            System.out.println(e.getMessage());
        }
    }

    public void finalizarBatalha() {
        try {
            int opcao = receberInteiro(opcoes());

            while (opcao != 0) {
                switch (opcao) {
                    case 1:
                        listarVivosEMortos();
                        break;
                    case 2:
                        mostrarEstatisticasPersonagem();
                        break;
                    case 3:
                        batalhaController.listarAcoes(batalha);
                        break;
                    case 4:
                        dataProvider.salvarSistema(batalha);
                        break;
                }

                opcao = receberInteiro(opcoes());
            }
            System.out.println("\n>> Obrigada por jogar! :) 🩷");
        } catch (ValidacaoEntrada e) {
            System.out.println(e.getMessage());
            finalizarBatalha();
        }
    }

    public void duelar() {
        try {
            listarPersonagensVivos(batalha.getPersonagens());
            int idPersonagem1 = receberInteiro("\nDigite o número do 1° personagem que vai duelar:");
            int idPersonagem2 = receberInteiro("Digite o número do 2° personagem que vai duelar:");
            limparBuffer();

            batalhaController.iniciarDuelo(idPersonagem1, idPersonagem2, batalha);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            duelar();
        }
    }

    public void listarVivosEMortos() {
        ArrayList<Personagem> personagens = batalha.getPersonagens();

        listarPersonagensVivos(personagens);
        listarPersonagensMortos(personagens);
    }

    public void listarPersonagensVivos(ArrayList<Personagem> personagens){
        System.out.println("\n>>> ♥️ PERSONAGEM(S) VIVO(S):");
        for (Personagem personagem : personagens) {
            if (personagem.isVivo()) {
                System.out.println(personagem.dados());
            }
        }
    }

    public void listarPersonagensMortos(ArrayList<Personagem> personagens){
        System.out.println(">>> ☠️ PERSONAGEM(S) MORTO(S)");
        for (Personagem personagem : personagens) {
            if (!personagem.isVivo()) {
                System.out.println(personagem.dados());
            }
        }
    }

    public void mostrarEstatisticasPersonagem() {
        batalhaController.listarPersonagens(batalha);

        try {
            int idPersonagem = receberInteiro("\nId do Personagem que quer ver as estatísticas:");
            Personagem personagem = procurarPersonagemPeloId(idPersonagem, batalha.getPersonagens());

            System.out.println("\n>>> 📈 ESTATÍSTICAS DO PERSONAGEM <<<");
            System.out.println(personagem.dados());
            System.out.println(">> 💥QUANTIDADE DE ATAQUES:" + personagem.getHistoricoAcoes().size());
            System.out.println(">> 💥DANO TOTAL CAUSADO: " + somarDano(personagem));
            System.out.println(">> 💥AÇÕES DO PERSONAGEM DURANTE A BATALHA:");
            personagemController.listarAcoesDoPersonagem(personagem);

        } catch (ValidacaoEntrada | ValidacaoPersonagemInvalido e) {
            System.out.println(e.getMessage());
        }
    }

    public int somarDano(Personagem personagem) {
        int danoTotal = 0;
        for (Acao acao : batalha.getAcoes()) {
            if (acao.getOrigem().getNome().equals(personagem.getNome())) {
                danoTotal += acao.getValorDano();
            }
        }

        return danoTotal;
    }
}
