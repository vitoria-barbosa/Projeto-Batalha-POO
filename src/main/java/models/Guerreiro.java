package models;

import lombok.Data;

@Data
public class Guerreiro extends Personagem{
    private int defesa;

    public Guerreiro(String nome, int ataque, int defesa) {
        super(nome, ataque);
        this.defesa = defesa;
    }

    @Override
    public Acao atacar(Personagem alvo, Batalha batalha) {
        double dano = this.getAtaque();

        String descricao =  String.format("O(A) guerreiro(a) %s atacou %s com sua espada.", this.getNome(), alvo.getNome());

        if(this.getVida() < 30){
            System.out.println("O(a) Guerreiro(a) " + this.getNome() + " está com menos de 30% de sua vida e está com ataque bônus!!!");
            System.out.printf("DANO: %.0f + 30%s = %.0f", dano, "%" ,dano * 1.3);
            descricao = String.format("O(A) guerreiro(a) %s atacou %s com ataque bônus!", this.getNome(), alvo.getNome());

            dano *= 1.3;
        }

        System.out.println("\n☑️" + descricao);
        return super.atacarDanoEspecifico(alvo,(int) dano, descricao);
    }

    @Override
    public void receberDano(int dano){

        if(dano > this.defesa){
            int danoFinal = dano - this.defesa;
            System.out.printf("\n☑️O(A) guerreiro(a) diminuiu o dano com sua defesa. DANO: %d - %d = %d", dano, getDefesa(), danoFinal);
            super.receberDano(danoFinal);
        }else{
            System.out.println("\n☑️O(A) guerreiro(a) defendeu o ataque com sua defesa.");
        }
    }

    public void receberDanoTotal(int dano){
        super.receberDano(dano);
    }

    @Override
    public String dados() {
        return String.format("\n%d - %s - Guerreiro(a):\n💣Ataque: %d\n🛡️Defesa: %d\n✨Status: %s\n🔋Vida: %d", this.getId(), this.getNome(), this.getAtaque(), getDefesa(),this.isVivo() ? "Vivo 🩷": "Morto ☠️",this.getVida());
    }
}
