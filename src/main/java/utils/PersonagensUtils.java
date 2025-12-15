package utils;

import exceptions.*;
import models.*;

import java.util.ArrayList;
import static utils.validacaoutils.personagem.ValidacaoPersonagem.*;
import static utils.EntradaUtils.*;

public class PersonagensUtils {

    public static String menuParaEscolhaTipoPersonagem(){
        return  "1 - ARQUEIRO : Tem 50% de chance de atacar usando o ataque múltiplo (dano = ataque X ataque múltiplo)\n" +
                "2 - GUERREIRO: Possui defesa que diminui o dano recebido\n" +
                "3 - MAGO     : Ignora a defesa dos guerreiros e dá o dobro de dano nos arqueiros\n" +
                "4 - MONGE    : Tem 25% de chance de esquivar o ataque do adversário\n" +
                "5 - EXAUSTO  : A cada ataque, seu ataque cai pela metade\n" +
                "6 - SACERDOTE: O ataque cura o alvo com 50% do dano\n" +
                "7 - LETALIS  : Ataque fatal que zera a vida do oponente";
    }

    public static int escolhaDoPersonagem(){
        try{
            System.out.println("> Escolha um dos personagens abaixo:");
            String menu = menuParaEscolhaTipoPersonagem();
            return receberInteiro(menu);
        }catch (ValidacaoEntrada e){
            System.out.println(e.getMessage());
            return escolhaDoPersonagem();
        }finally{
            limparBuffer();
        }
    }

    public static Personagem criarPersonagemJogo(int escolhaPersonagemNum, ArrayList<Personagem> personagens) throws ValidacaoPersonagemInvalido{
        try{
            String nome = receberString("Informe o nome do Personagem: ");
            validarNomePersonagem(nome, personagens);
            int ataque = receberInteiro("Informe o valor do ataque do Personagem: ");

            switch (escolhaPersonagemNum){
                case 1:
                    int ataqueMultiplo = receberInteiro("Informe o valor do ataque múltiplo do arqueiro:");
                    return new Arqueiro(nome,ataque,ataqueMultiplo);
                case 2:
                    int defesa =  receberInteiro("Informe a defesa do guerreiro:");
                    return new Guerreiro(nome,ataque,defesa);
                case 3:
                    return new Mago(nome,ataque);
                case 4:
                    return new Monge(nome,ataque);
                case 5:
                    return new Exausto(nome, ataque);
                case 6:
                    return new Sacerdote(nome, ataque);
                case 7:
                    return new Letalis(nome, ataque);
                default:
                    throw new ValidacaoPersonagemInvalido("Erro: Personagem inválido");
            }
        }catch (ValidacaoEntrada | ValidacaoNomePersonagem e){
            System.out.println(e.getMessage());
            return criarPersonagemJogo(escolhaPersonagemNum, personagens);
        }finally {
            limparBuffer();
        }
    }
}
