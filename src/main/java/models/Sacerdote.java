package models;
//Sacerdote: o ataque cura o alvo no valor de 50% do “dano”;
public class Sacerdote extends Personagem{
    public Sacerdote(String nome, int ataque) {
        super(nome, ataque);
    }

    @Override
    public Acao atacar(Personagem alvo, Batalha batalha) {
        int cura = this.getAtaque() / 2;

        String descricao = String.format("O(A) sacerdote(isa) %s atacou e curou %s com 50%s do seu ataque (%d).", this.getNome(), alvo.getNome(), "%", cura);
        Acao acao = super.atacarDanoEspecifico(alvo, this.getAtaque(), descricao);

        int vidaAlvo = alvo.getVida();
        if(vidaAlvo + cura <= 100){
            alvo.setVida(vidaAlvo + cura);
        }

        System.out.println("☑️" + descricao);

        return acao;
    }

    public String dados(){
        return String.format("\n%d - %s - Sacerdote(isa):\n💣Ataque: %d\n✨Status: %s\n🔋Vida: %d", this.getId(), this.getNome(), this.getAtaque(), this.isVivo() ? "Vivo 🩷": "Morto ☠️",this.getVida());
    }
}
