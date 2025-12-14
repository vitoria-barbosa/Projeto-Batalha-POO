package models;

import java.util.Random;

public class Monge extends Personagem {
    static Random aleatorio = new Random();

    public Monge(String nome, int ataque) {
        super(nome, ataque);
    }

    @Override
    public void receberDano(int dano){
        int esquivar = aleatorio.nextInt(4);

        if (esquivar == 1) {
            System.out.printf("O(A) monge %s esquivou!\n", this.getNome());
            return;
        }

        super.receberDano(dano);
    }

    public String dados(){
        return String.format("\n%d - %s - Monge:\n💣Ataque: %d\n✨Status: %s\n🔋Vida: %d", this.getId(), this.getNome(), this.getAtaque(), this.isVivo() ? "Vivo 🩷": "Morto ☠️",this.getVida());
    }
}
