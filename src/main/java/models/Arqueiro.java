package models;

import lombok.Data;

import java.util.Random;

@Data
public class Arqueiro extends Personagem{
    private int ataqueMultiplo;

    static Random aleatorio = new Random();

    public Arqueiro(String nome, int ataque, int ataqueMultiplo) {
        super(nome, ataque);
        this.ataqueMultiplo = ataqueMultiplo;
    }

    @Override
    public Acao atacar(Personagem alvo, Batalha batalha) {
        int dano = this.getAtaque();
        int sorteio = aleatorio.nextInt(2);

        String descricao =  String.format("O(A) arqueiro(a) %s atacou %s com ataque simples.", this.getNome(), alvo.getNome());

        if(sorteio == 1){
            dano *= ataqueMultiplo;
            descricao = String.format("O(A) arqueiro(a) %s atacou %s com o ataque múltiplo! DANO: %d", this.getNome(), alvo.getNome(), dano);
        }

        System.out.println("\n☑️" + descricao);
        return super.atacarDanoEspecifico(alvo,dano, descricao);
    }

    @Override
    public String dados() {
        return String.format("\n%d - %s - Arqueiro(a):\n💣Ataque: %d\n💥Ataque Múltiplo: %d\n✨Status: %s\n🔋Vida: %d\n", this.getId(), this.getNome(), this.getAtaque(), this.getAtaqueMultiplo(), this.isVivo() ? "Vivo 🩷": "Morto ☠️",this.getVida());
    }
}
