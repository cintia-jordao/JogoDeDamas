package jogo;
import modelo.*;
public class Tabuleiro {
 private final Peca[][] casas=new Peca[8][8];
 public Peca get(Posicao p){return casas[p.getLinha()][p.getColuna()];}
 public void set(Posicao p,Peca x){casas[p.getLinha()][p.getColuna()]=x;}
 public boolean dentro(Posicao p){return p.getLinha()>=0&&p.getLinha()<8&&p.getColuna()>=0&&p.getColuna()<8;}
 public boolean escura(Posicao p){return (p.getLinha()+p.getColuna())%2==1;}
 public void iniciar(){for(int l=0;l<8;l++)for(int c=0;c<8;c++){Posicao p=new Posicao(l,c);casas[l][c]=null;if(escura(p)&&l<3)casas[l][c]=new PecaComum(Cor.PRETAS);if(escura(p)&&l>4)casas[l][c]=new PecaComum(Cor.BRANCAS);}}
 public int contar(Cor cor){int n=0;for(Peca[] linha:casas)for(Peca p:linha)if(p!=null&&p.getCor()==cor)n++;return n;}
 public String imprimir(){StringBuilder s=new StringBuilder("TABULEIRO_INICIO\n");for(int l=0;l<8;l++){for(int c=0;c<8;c++)s.append(casas[l][c]==null?'.':casas[l][c].simbolo());s.append('\n');}return s.append("TABULEIRO_FIM").toString();}
}