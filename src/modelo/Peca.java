package modelo;
public abstract class Peca { private final Cor cor; protected Peca(Cor c){cor=c;} public Cor getCor(){return cor;} public abstract boolean isDama(); public abstract char simbolo(); }