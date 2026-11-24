package modelo;
public class Dama extends Peca { public Dama(Cor c){super(c);} public boolean isDama(){return true;} public char simbolo(){return getCor()==Cor.BRANCAS?'B':'P';} }