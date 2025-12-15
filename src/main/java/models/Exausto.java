package models;

public class Exausto extends Personagem{
    public Exausto(String nome, int ataque) {
        super(nome, ataque);
    }

    @Override
    public Acao atacar(Personagem alvo, Batalha batalha){
        String descricao = String.format("O(A) Exausto(a) %s atacou %s e diminuiu o valor do seu ataque pela metade!", this.getNome(), alvo.getNome());

        Acao acao = super.atacarDanoEspecifico(alvo, this.getAtaque(), descricao);
        System.out.println("☑️" + descricao);

        int ataqueNovo = this.getAtaque()/2;
        if( ataqueNovo >= 1){
            this.setAtaque(ataqueNovo);
        }

        return acao;
    }

    public String dados(){
        return String.format("\n%d - %s - Exausto(a):\n💣Ataque: %d\n✨Status: %s\n🔋Vida: %d", this.getId(), this.getNome(), this.getAtaque(), this.isVivo() ? "Vivo 🩷": "Morto ☠️",this.getVida());
    }
}
