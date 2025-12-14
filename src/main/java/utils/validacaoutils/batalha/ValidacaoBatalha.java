package utils.validacaoutils.batalha;
import exceptions.ValidacaoVidaVencedor;
import models.Personagem;

public class ValidacaoBatalha {
    public static void validarVidaVencedor(Personagem vencedor) throws ValidacaoVidaVencedor {
        if(vencedor.getVida() == 0){
            throw new ValidacaoVidaVencedor("✖️ Todos os personagens estão mortos, ninguém ganhou a batalha!");
        }
    }

}
