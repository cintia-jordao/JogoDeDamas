package modelo;
public class PecaComum extends Peca { public PecaComum(Cor c){super(c);} public boolean isDama(){return false;} public char simbolo(){return getCor()==Cor.BRANCAS?'b':'p';} }