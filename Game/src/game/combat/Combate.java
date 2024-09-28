package game.combat;

import java.util.Random;
import java.util.Scanner;

public class Combate {
    private int forcaJogador;
    private int forcaBoss = 3;
    private int saudeJogador;
    private int saudeBoss = 100;
    private int danoEspadaJogador;
    private int danoEspadaBoss = 2;
    private Random random = new Random();

    public Combate(int forcaJogador, int saudeJogador, int danoEspadaJogador) {
        this.forcaJogador = forcaJogador;
        this.saudeJogador = saudeJogador;
        this.danoEspadaJogador = danoEspadaJogador;
    }

    public void iniciarCombate() {
        Scanner scanner = new Scanner(System.in);
        boolean emCombate = true;

        while (emCombate && saudeJogador > 0 && saudeBoss > 0) {
            // Limpa a "tela" (simulação)
            limparTela();

            // Exibe a tabela com as informações de vida do jogador e do boss
            exibirTabelaDeStatus();

            // Opções de ação
            System.out.println("Escolha sua ação: (1) Atacar (2) Defender (3) Esquivar");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    atacar();
                    break;
                case 2:
                    defender();
                    break;
                case 3:
                    esquivar();
                    break;
                default:
                    System.out.println("Escolha inválida.");
            }

            if (saudeBoss > 0) {
                ataqueBoss();
            }

            // Verifica condições de vitória ou derrota
            if (saudeJogador <= 0) {
                System.out.println("Você foi derrotado!");
                emCombate = false;
            } else if (saudeBoss <= 0) {
                System.out.println("Você derrotou o boss!");
                emCombate = false;
            }
            
            // Adiciona uma pausa de 3 segundos antes de limpar a tela
            pausarPorTresSegundos();
            limparTela();
        }
    }

    private void atacar() {
        int danoTotal = calcularDano(forcaJogador, danoEspadaJogador);
        System.out.println("Você ataca e causa " + danoTotal + " de dano.");
        saudeBoss -= danoTotal;
        System.out.println("Vida do boss agora é " + saudeBoss);
    }

    private void defender() {
        System.out.println("Você se defende e reduz o dano recebido.");
        int danoRecebido = forcaBoss / 2 + danoEspadaBoss / 2; // Reduz o dano do boss ao defender
        saudeJogador -= danoRecebido;
        System.out.println("Você recebeu " + danoRecebido + " de dano. Sua saúde agora é " + saudeJogador);
    }

    private void esquivar() {
        System.out.println("Você tenta se esquivar...");
        if (random.nextDouble() > 0.5) {
            System.out.println("Esquiva bem-sucedida! Nenhum dano recebido.");
        } else {
            System.out.println("Você falha ao esquivar e recebe o ataque completo.");
            ataqueBoss();
        }
    }

    private void ataqueBoss() {
        int danoTotal = calcularDano(forcaBoss, danoEspadaBoss);
        System.out.println("O boss ataca e causa " + danoTotal + " de dano.");
        saudeJogador -= danoTotal;
        System.out.println("Sua saúde agora é " + saudeJogador);
    }

    private int calcularDano(int forca, int danoEspada) {
        // Calcula o dano com base na força e dano da espada, com limite de 7
        return random.nextInt(Math.min(forca + danoEspada + 1, 5)); // Máximo de 7 de dano
    }

    private void exibirTabelaDeStatus() {
        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│          Status do Combate                │");
        System.out.println("├───────────────────────────────────────────┤");
        System.out.println("│ Gurillo:                                  │");
        System.out.println("│ Saúde: " + saudeJogador + "                                 │");
        System.out.println("├───────────────────────────────────────────┤");
        System.out.println("│ Boss:                                     │");
        System.out.println("│ Saúde: " + saudeBoss + "                                 │");
        System.out.println("└───────────────────────────────────────────┘");
    }

    private void limparTela() {
        // Simula a limpeza do console adicionando várias quebras de linha
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    private void pausarPorTresSegundos() {
        try {
            // Pausa o programa por 3 segundos (3000 milissegundos)
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Caso ocorra alguma interrupção durante o "sleep", captura a exceção
            Thread.currentThread().interrupt();
            System.out.println("Erro ao pausar a execução.");
        }
    }
}