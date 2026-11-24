package aplicacao;
import java.util.Scanner;
import jogo.Partida;
import persistencia.PersistenciaArquivo;
public class Main {
 public static void main(String[] args){
  Scanner sc=new Scanner(System.in);Partida p=new Partida();
  System.out.println("=== JOGO DE DAMAS ===");
  System.out.println("Comandos: NOVO Ana Pedro | MOVER A3 B4 | ESTADO | SALVAR partida.txt | CARREGAR partida.txt | SAIR");
  while(true){System.out.print("> ");String linha=sc.nextLine().trim();if(linha.isEmpty())continue;String[] a=linha.split("\\s+");String cmd=a[0].toUpperCase();
   try{
    if(cmd.equals("NOVO")&&a.length==3){p.nova(a[1],a[2]);System.out.println("Partida iniciada. Brancas começam.");System.out.println(p.estado());}
    else if(cmd.equals("MOVER")&&a.length==3)System.out.println(p.mover(a[1],a[2]));
    else if(cmd.equals("ESTADO"))System.out.println(p.estado());
    else if(cmd.equals("SALVAR")&&a.length==2){PersistenciaArquivo.salvar(p,a[1]);System.out.println("Partida salva.");}
    else if(cmd.equals("CARREGAR")&&a.length==2){PersistenciaArquivo.carregar(p,a[1]);System.out.println("Partida carregada.");System.out.println(p.estado());}
    else if(cmd.equals("SAIR")){System.out.println("Até logo!");break;}
    else System.out.println("Comando inválido. Confira os exemplos.");
   }catch(Exception e){System.out.println("Erro: "+e.getMessage());}
  }sc.close();
 }
}