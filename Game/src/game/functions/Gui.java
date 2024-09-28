package game.functions;

import java.util.Scanner;

public class Gui {
    Functions functions = new Functions(); // Inventário com 10 espaços

    private Scanner scanner;
    private StringBuilder outputBuffer;
    
    Mapa mapa = new Mapa();

    public Gui() {
        scanner = new Scanner(System.in);
        outputBuffer = new StringBuilder();
    }

    public void start() {
        appendOutput("┌─────────────────────────────────────┐");
        appendOutput("│      As Aventuras do Gurilo         │");
        appendOutput("│ Digite 'ajuda' para ver os comandos │");
        appendOutput("└─────────────────────────────────────┘");

        while (true) {
            displayOutput(); // Mostra o conteúdo de saída (se houver)

            String input = getInput().toLowerCase();

            switch (input) {
                case "olhar":
                	System.out.println(mapa.olharMapAtual(functions));
                    break;
                	
                    
                case "status":
                    mostrarStatus(); // Mostra o status
                    break;
                
                case "inventario":
                    mostrarInventario(); // Mostra o inventário
                    break;

                case "pegar":
                    functions.pegarItemProximo(); // Pega itens próximos
                    appendOutput("Você pegou um item.");
                    break;
                    
                case "jogar":
                    appendOutput("Qual item deseja jogar?");
                    String item = getInput();
                    functions.jogarItem(item);
                    appendOutput("Você jogou o item: " + item);
                    break;

                case "mover":
                    appendOutput("Para onde deseja ir? (Floresta, Caverna)");
                    String novoLocal = getInput();
                    functions.moverPara(novoLocal);
                    appendOutput("Você se moveu para: " + novoLocal);
                    break;

                case "sair":
                    appendOutput("Você saiu do jogo.");
                    return;

                case "ajuda":
                    appendOutput(functions.ajuda());
                    break;

                default:
                    appendOutput("Comando inválido. Tente novamente.");
            }
        }
    }

    private void mostrarInventario() {
        String[] inventario = functions.verInventario();
        appendOutput("┌──────────────────────────────┐");
        appendOutput("│ Seu inventário contém:       │");
        boolean vazio = true;

        for (String item : inventario) {
            if (item != null) {
                appendOutput("│ - " + item);
                vazio = false;
            }
        }

        if (vazio) {
            appendOutput("│ Inventário vazio.");
        }

        appendOutput("└──────────────────────────────┘");
    }

    private void mostrarStatus() {
        // Pega os dados de força, saúde e local do jogador nas funções
        int forca = functions.puxarForca();    // Simula a variável força
        int saude = functions.puxarSaude();    // Simula a variável saúde
        String local = mapa.getLocalAtual(); // Simula a variável local atual

        // Interface de status do jogador com ASCII
        appendOutput("┌───────────────────────────────────────────┐");
        appendOutput("│ Status do Jogador                         │");
        appendOutput("├───────────────────────────────────────────┤");
        appendOutput("│ Local: " + local + "                      │");
        appendOutput("│ Força: " + forca + "                      │");
        appendOutput("│ Saúde: " + saude + "                      │");
        appendOutput("└───────────────────────────────────────────┘");
    }

    public void displayOutput() {
        if (outputBuffer.length() > 0) {
            System.out.println(outputBuffer.toString());
            outputBuffer.setLength(0); // Limpa o buffer após exibir
        }
    }

    public String getInput() {
        System.out.print("> "); // Indica o prompt
        return scanner.nextLine();
    }

    public void appendOutput(String text) {
        outputBuffer.append(text).append("\n");
    }
}
