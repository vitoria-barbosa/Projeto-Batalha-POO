package models;

public class Letalis extends Personagem{
    public Letalis(String nome, int ataque) {
        super(nome, ataque);
    }

    @Override
    public Acao atacar(Personagem alvo, Batalha batalha) {
        int danoLetal = alvo.getVida();

        String descricao = String.format("O(A) Letalis %s atacou letalmente e matou %s", this.getNome(), alvo.getNome());
        System.out.println("☑️" + descricao);

        return super.atacarDanoEspecifico(alvo, danoLetal, descricao);
    }

    public String dados(){
        return String.format("\n%d - %s - Letalis:\n💣Ataque: %d\n✨Status: %s\n🔋Vida: %d", this.getId(), this.getNome(), this.getAtaque(), this.isVivo() ? "Vivo 🩷": "Morto ☠️",this.getVida());
    }
}
