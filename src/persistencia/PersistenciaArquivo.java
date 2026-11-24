package persistencia;
import java.io.*;
import jogo.*;
import modelo.*;
public class PersistenciaArquivo {
 public static void salvar(Partida p,String nome)throws IOException{
  try(BufferedWriter w=new BufferedWriter(new FileWriter(nome))){w.write(p.getNome(Cor.BRANCAS));w.newLine();w.write(p.getNome(Cor.PRETAS));w.newLine();w.write(p.getTurno().name());w.newLine();w.write(p.getStatus());w.newLine();
   for(int l=0;l<8;l++){StringBuilder s=new StringBuilder();for(int c=0;c<8;c++){Peca x=p.getTabuleiro().get(new Posicao(l,c));s.append(x==null?'.':x.simbolo());}w.write(s.toString());w.newLine();}
  }
 }
 public static void carregar(Partida p,String nome)throws Exception{
  try(BufferedReader r=new BufferedReader(new FileReader(nome))){String b=r.readLine(),pr=r.readLine(),t=r.readLine(),st=r.readLine();if(b==null||pr==null||t==null||st==null)throw new IOException("Arquivo incompleto.");
   Cor cor=Cor.valueOf(t);if(!st.equals("EM_ANDAMENTO"))throw new IOException("Status inválido.");Tabuleiro tab=new Tabuleiro();
   for(int l=0;l<8;l++){String linha=r.readLine();if(linha==null||linha.length()!=8)throw new IOException("Tabuleiro inválido.");for(int c=0;c<8;c++){Posicao q=new Posicao(l,c);char x=linha.charAt(c);if(x=='b')tab.set(q,new PecaComum(Cor.BRANCAS));else if(x=='p')tab.set(q,new PecaComum(Cor.PRETAS));else if(x=='B')tab.set(q,new Dama(Cor.BRANCAS));else if(x=='P')tab.set(q,new Dama(Cor.PRETAS));else if(x!='.')throw new IOException("Símbolo inválido.");}}
   p.restaurar(tab,b,pr,cor,st);
  }
 }
}