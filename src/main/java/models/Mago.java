package models;

import java.time.LocalDateTime;

public class Mago extends Personagem{

    public Mago(String nome, int ataque) {
        super(nome, ataque);
    }

    @Override
    public Acao atacar(Personagem alvo, Batalha batalha) {
        int dano = this.getAtaque();
        String descricao = String.format("O(A) Mago(a) %s atacou %s.", this.getNome(), alvo.getNome());

        if (alvo instanceof Guerreiro){
            return atacarGuerreiro(alvo, dano, batalha);
        }

        if(alvo instanceof Arqueiro){
            dano *= 2;
            descricao = String.format("O(A) Mago(a) %s atacou o(a) arqueiro(a) %s  e deu o dobro de dano!", this.getNome(), alvo.getNome());
        }

        System.out.println("\n☑️" + descricao);
        Acao ataque = super.atacarDanoEspecifico(alvo, dano, descricao);

        consumirVida(batalha);
        return ataque;
    }

    private void consumirVida(Batalha batalha) {
        String descricao = String.format("O(A) Mago(a) %s sofreu autodano de 10.", this.getNome());
        System.out.println("\n☑️" + descricao);
        Acao autoAtaque = new Acao(this, this, descricao, 10, LocalDateTime.now());
        this.receberDano(10);
        this.registrarAcao(autoAtaque);
        batalha.adicionarAcao(autoAtaque);
    }

    private Acao atacarGuerreiro(Personagem alvo, int dano, Batalha batalha){
        Guerreiro alvoGuerreiro = (Guerreiro) alvo;
        alvoGuerreiro.receberDanoTotal(dano);
        String descricao = String.format("O(A) Mago(a) %s ignorou a defesa do(a) Guerreiro(a) %s e deu dano total!", this.getNome(), alvo.getNome());
        System.out.println("\n☑️" + descricao);
        consumirVida(batalha);
        return new Acao(this, alvo, descricao, dano, LocalDateTime.now());
    }

    public String dados(){
        return String.format("\n%d - %s - Mago(a):\n💣Ataque: %d\n✨Status: %s\n🔋Vida: %d", this.getId(), this.getNome(), this.getAtaque(), this.isVivo() ? "Vivo 🩷": "Morto ☠️",this.getVida());
    }
}
