package jogo;
import java.util.*;
import modelo.*;
public class Partida implements Movimentavel {
 private Tabuleiro tabuleiro=new Tabuleiro(); private String brancas,pretas,status="EM_ANDAMENTO"; private Cor turno=Cor.BRANCAS;
 private Posicao sequencia; private final Set<Posicao> pendentes=new HashSet<Posicao>();
 public void nova(String b,String p){brancas=b;pretas=p;turno=Cor.BRANCAS;status="EM_ANDAMENTO";sequencia=null;pendentes.clear();tabuleiro.iniciar();}
 public Tabuleiro getTabuleiro(){return tabuleiro;} public Cor getTurno(){return turno;} public String getStatus(){return status;} public String getNome(Cor c){return c==Cor.BRANCAS?brancas:pretas;}
 public boolean movimentoGeometricoValido(Posicao a,Posicao b){return Math.abs(a.getLinha()-b.getLinha())==Math.abs(a.getColuna()-b.getColuna())&& !a.equals(b);}
 private Posicao vitima(Posicao a,Posicao b){int dl=Integer.signum(b.getLinha()-a.getLinha()),dc=Integer.signum(b.getColuna()-a.getColuna());int l=a.getLinha()+dl,c=a.getColuna()+dc;while(l!=b.getLinha()&&c!=b.getColuna()){Posicao q=new Posicao(l,c);if(tabuleiro.get(q)!=null)return q;l+=dl;c+=dc;}return null;}
 private List<Posicao> capturas(Posicao a,Peca p){List<Posicao> r=new ArrayList<Posicao>();int[] d={-1,1};if(!p.isDama()){for(int x:d)for(int y:d){Posicao v=new Posicao(a.getLinha()+x,a.getColuna()+y),f=new Posicao(a.getLinha()+2*x,a.getColuna()+2*y);if(tabuleiro.dentro(v)&&tabuleiro.dentro(f)&&tabuleiro.get(f)==null&&tabuleiro.get(v)!=null&&tabuleiro.get(v).getCor()!=p.getCor()&&!pendentes.contains(v))r.add(f);}}else{for(int x:d)for(int y:d){boolean viu=false;for(int l=a.getLinha()+x,c=a.getColuna()+y;l>=0&&l<8&&c>=0&&c<8;l+=x,c+=y){Posicao q=new Posicao(l,c);Peca alvo=tabuleiro.get(q);if(alvo!=null){if(viu||alvo.getCor()==p.getCor()||pendentes.contains(q))break;viu=true;}else if(viu)r.add(q);}}}return r;}
 private boolean haCaptura(){for(int l=0;l<8;l++)for(int c=0;c<8;c++){Posicao q=new Posicao(l,c);Peca p=tabuleiro.get(q);if(p!=null&&p.getCor()==turno&&!capturas(q,p).isEmpty())return true;}return false;}
 private int maxCapturas(Posicao a,Peca p){int max=0;for(Posicao b:capturas(a,p)){Posicao v=vitima(a,b);Peca alvo=tabuleiro.get(v);tabuleiro.set(a,null);tabuleiro.set(b,p);pendentes.add(v);int n=1+maxCapturas(b,p);pendentes.remove(v);tabuleiro.set(b,null);tabuleiro.set(a,p);tabuleiro.set(v,alvo);max=Math.max(max,n);}return max;}
 public String mover(String origem,String destino){
  if(!"EM_ANDAMENTO".equals(status))return "A partida já terminou.";
  Posicao a=Posicao.ler(origem),b=Posicao.ler(destino);
  if(!tabuleiro.dentro(a)||!tabuleiro.dentro(b)||!tabuleiro.escura(a)||!tabuleiro.escura(b))return "Use somente casas escuras.";
  Peca p=tabuleiro.get(a);if(p==null)return "Não existe peça na origem.";if(p.getCor()!=turno)return "Essa peça não pertence ao jogador da vez.";
  if(sequencia!=null&&!sequencia.equals(a))return "Continue com a mesma peça: "+sequencia;
  if(tabuleiro.get(b)!=null)return "Destino ocupado.";if(!movimentoGeometricoValido(a,b))return "Movimento diagonal inválido.";
  int dl=b.getLinha()-a.getLinha(),dc=b.getColuna()-a.getColuna();Posicao v=vitima(a,b);boolean captura=false;
  if(v!=null&&tabuleiro.get(v)!=null&&tabuleiro.get(v).getCor()!=turno&&!pendentes.contains(v)){
   if(p.isDama()){int l=a.getLinha()+Integer.signum(dl),c=a.getColuna()+Integer.signum(dc);boolean bloqueio=false;while(l!=b.getLinha()&&c!=b.getColuna()){Posicao q=new Posicao(l,c);if(!q.equals(v)&&tabuleiro.get(q)!=null)bloqueio=true;l+=Integer.signum(dl);c+=Integer.signum(dc);}captura=!bloqueio;}
   else captura=Math.abs(dl)==2&&Math.abs(dc)==2;
  }
  if(!p.isDama()&&!captura){int dir=turno==Cor.BRANCAS?-1:1;if(Math.abs(dl)!=1||Math.abs(dc)!=1||Integer.signum(dl)!=dir)return "Peça comum só anda uma casa diagonal para frente.";}
  if(haCaptura()&&!captura&&sequencia==null)return "Captura obrigatória.";
  if(captura&&sequencia==null){int max=0;for(int l=0;l<8;l++)for(int c=0;c<8;c++){Posicao q=new Posicao(l,c);Peca x=tabuleiro.get(q);if(x!=null&&x.getCor()==turno)max=Math.max(max,maxCapturas(q,x));}if(max>1&&maxCapturas(a,p)<max)return "Escolha uma sequência com o maior número de capturas: "+max;}
  Peca pecaCapturada = captura ? tabuleiro.get(v) : null;
  if(captura)pendentes.add(v);tabuleiro.set(a,null);tabuleiro.set(b,p);
  String avisoCaptura = "";
  if(captura) {
   avisoCaptura = "CAPTURA REALIZADA! Peça " + (pecaCapturada.getCor()==Cor.BRANCAS?"BRANCA":"PRETA")
       + " capturada na posição " + v + ".";
  }
  if(captura&&!capturas(b,p).isEmpty()){sequencia=b;return avisoCaptura+" Continue com a mesma peça.";}
  for(Posicao q:pendentes)tabuleiro.set(q,null);pendentes.clear();sequencia=null;
  if(!p.isDama()&&((turno==Cor.BRANCAS&&b.getLinha()==0)||(turno==Cor.PRETAS&&b.getLinha()==7)))tabuleiro.set(b,new Dama(turno));
  turno=turno.oposta();if(tabuleiro.contar(turno)==0||!temMovimento(turno))status=turno==Cor.BRANCAS?"VITORIA_PRETAS":"VITORIA_BRANCAS";
  return captura ? avisoCaptura+" Peça removida do tabuleiro. Vez de "+turno+"."
                 : "Movimento realizado. Vez de "+turno+".";
 }
 private boolean temMovimento(Cor cor){for(int l=0;l<8;l++)for(int c=0;c<8;c++){Posicao a=new Posicao(l,c);Peca p=tabuleiro.get(a);if(p!=null&&p.getCor()==cor){if(!capturas(a,p).isEmpty())return true;for(int x:new int[]{-1,1})for(int y:new int[]{-1,1}){int nl=l+x,nc=c+y;if(nl>=0&&nl<8&&nc>=0&&nc<8&&tabuleiro.get(new Posicao(nl,nc))==null&&(p.isDama()||Integer.signum(x)==(cor==Cor.BRANCAS?-1:1)))return true;}}}return false;}
 public String estado(){return tabuleiro.imprimir()+"\nTURNO="+turno+"\nSTATUS="+status+"\nBRANCAS="+brancas+"\nPRETAS="+pretas;}
 public void restaurar(Tabuleiro t,String b,String p,Cor c,String s){tabuleiro=t;brancas=b;pretas=p;turno=c;status=s;sequencia=null;pendentes.clear();}
}