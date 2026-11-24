package modelo;
public class Posicao {
 private final int linha,coluna;
 public Posicao(int l,int c){linha=l;coluna=c;}
 public int getLinha(){return linha;} public int getColuna(){return coluna;}
 public static Posicao ler(String s){if(s==null||!s.matches("(?i)[A-H][1-8]"))throw new IllegalArgumentException("Coordenada inválida. Use A1 até H8.");return new Posicao(8-Character.getNumericValue(s.charAt(1)),Character.toUpperCase(s.charAt(0))-'A');}
 public String toString(){return ""+(char)('A'+coluna)+(8-linha);}
 public boolean equals(Object o){if(!(o instanceof Posicao))return false;Posicao p=(Posicao)o;return linha==p.linha&&coluna==p.coluna;}
 public int hashCode(){return linha*31+coluna;}
}