# Jogo de Damas — Trabalho Final de POO

## Integrantes
- Cintia de Almeida Jordão
- Diana Ramos Célis
- Julia Cabral de Souza
- Murilo Alcântara Alves
- Yorik Moraes Santos

## Requisitos
Java 8 ou superior. Projeto Java convencional, sem Maven ou Gradle.

## Importar no Eclipse
1. Extraia o ZIP.
2. No Eclipse, acesse **File > Import > General > Existing Projects into Workspace**.
3. Selecione a pasta `JogoDamas` e clique em **Finish**.
4. Abra `src/aplicacao/Main.java` e use **Run As > Java Application**.

## Comandos
- `NOVO Ana Pedro` — inicia a partida; Ana joga com as brancas.
- `MOVER A3 B4` — movimenta uma peça; quando houver captura, o Console informa a cor e a posição da peça capturada.
- `ESTADO` — exibe o tabuleiro e o estado da partida.
- `SALVAR partida.txt` — salva a partida em arquivo texto.
- `CARREGAR partida.txt` — carrega uma partida.
- `SAIR` — encerra o programa.

## Estrutura e POO
- `modelo.Peca`: classe abstrata.
- `modelo.PecaComum` e `modelo.Dama`: herança e sobrescrita de métodos.
- `modelo.Posicao` e `modelo.Cor`: representação de posição e cor.
- `jogo.Tabuleiro`: armazenamento das peças.
- `jogo.Partida`: regras, turnos, capturas, promoção e vitória.
- `jogo.Movimentavel`: interface implementada por `Partida`.
- `persistencia.PersistenciaArquivo`: leitura e gravação de arquivos.
- `aplicacao.Main`: interação pelo console.

Abstração é aplicada na classe `Peca`; encapsulamento protege os atributos; herança especializa as peças; polimorfismo ocorre na chamada de `simbolo()`; a interface `Movimentavel` define um contrato de movimentação.

## Símbolos do tabuleiro
`b` = peça branca; `p` = peça preta; `B` = dama branca; `P` = dama preta; `.` = casa vazia.

## Observação importante
O projeto contém a implementação principal das regras e um roteiro de testes manuais. O grupo deve executar os cenários, preencher os resultados reais e conferir as regras especiais durante a validação final. A interface não é gráfica.
