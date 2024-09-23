package game.functions;

import java.util.Scanner;
import game.functions.*;

public class Gui {
    Functions functions = new Functions(10); // Inventário com 10 espaços

    private Scanner scanner;
    private StringBuilder outputBuffer;
    
    Mapa mapa = new Mapa();

    public Gui() {
        scanner = new Scanner(System.in);
        outputBuffer = new StringBuilder();
    }

    public void start() {
        appendOutput("Bem-vindo ao jogo!");

        while (true) {
            displayOutput();

            String input = getInput().toLowerCase();

            switch (input) {
                case "olhar":
                    appendOutput("Você está em " + mapa.getLocalAtual());
                    break;
                
                case "inventario":
                    mostrarInventario();
                    break;

                case "pegar":
                    functions.pegarItemProximo(); // Pega itens próximos
                    break;
                    
                case "jogar":
                    appendOutput("Qual item deseja jogar ?");
                    String item = getInput();
                    functions.jogarItem(item);
                    break;

                case "mover":
                    appendOutput("Para onde deseja ir? (Floresta, Caverna)");
                    String novoLocal = getInput();
                    functions.moverPara(novoLocal);
                    break;

                case "sair":
                    appendOutput("Você saiu do jogo.");
                    return;

                default:
                    appendOutput("Comando inválido. Tente novamente.");
            }
        }
    }

    private void mostrarInventario() {
        String[] inventario = functions.verInventario();
        appendOutput("Seu inventário contém:");
        boolean vazio = true;

        for (String item : inventario) {
            if (item != null) {
                appendOutput("- " + item);
                vazio = false;
            }
        }

        if (vazio) {
            appendOutput("Inventário vazio.");
        }
    }

    public void displayOutput() {
        System.out.println(outputBuffer.toString());
        outputBuffer.setLength(0); // Limpa o buffer após exibir
    }

    public String getInput() {
        System.out.print("> "); // Indica o prompt
        return scanner.nextLine();
    }

    public void appendOutput(String text) {
        outputBuffer.append(text).append("\n");
    }
}
