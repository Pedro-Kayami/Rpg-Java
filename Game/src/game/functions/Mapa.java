package game.functions;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import game.combat.Combate;

import java.util.ArrayList;
import java.util.Arrays;

public class Mapa {
    private HashMap<String, List<String>> locais; // Cada local tem uma lista de itens
    private String localAtual;
    String[] localModelagem = {"Poção","Nada","Matéria","boss"};

    public Mapa() {
        locais = new HashMap<>();

        // Definindo alguns locais e itens associados
        List<String> principadoModelagem = new ArrayList<>();
        principadoModelagem.add("Linha");
        principadoModelagem.add("Entidade");

        List<String> capitalRobotica = new ArrayList<>();
        capitalRobotica.add("Led");
        capitalRobotica.add("Pilha");

        locais.put("Principado da Modelagem", principadoModelagem);
        locais.put("Capital da Robótica", capitalRobotica);

        localAtual = "Principado da Modelagem"; // Local inicial do jogador
    }

    public String getLocalAtual() {
        return localAtual;
    }

    public void moverPara(String novoLocal) {
        if (locais.containsKey(novoLocal)) {
            localAtual = novoLocal;
            System.out.println("Você se moveu para: " + novoLocal);
        } else {
            System.out.println("Esse local não existe.");
        }
    }

    public List<String> getItensNoLocal() {
        return locais.getOrDefault(localAtual, new ArrayList<>());
    }
    
    public String olharMapAtual(Functions personagem) {
        if (localAtual.equals("Principado da Modelagem")) {
            Random random = new Random();
            if (localModelagem == null || localModelagem.length == 0) {
                return "Não achou nada, continue procurando";
            }

            int index = random.nextInt(localModelagem.length);

            String itemSorteado = localModelagem[index];
            localModelagem = removerItem(localModelagem, index);

            if (itemSorteado.equals("Matéria")) {
                personagem.adicionarForca(1);
                System.out.println(personagem.puxarForca());
            } else if (itemSorteado.equals("boss")) {
                System.out.println("Você encontrou o boss!");

                // Iniciar combate
                Combate combate = new Combate(personagem.puxarForca(), personagem.puxarSaude(), 10); // Exemplo: dano da espada = 10
                combate.iniciarCombate();
            }else if(itemSorteado.equals("Poção")) {
            	personagem.adicionarSaude(20);
            }

            if (itemSorteado == null || itemSorteado.isEmpty()) {
                return "Você não acha nada";
            } else {
                return itemSorteado;
            }
        }
        return "Você não acha nada";
    }


    public static String[] removerItem(String[] array, int index) {
        if (array == null || array.length == 0 || index < 0 || index >= array.length) {
            throw new IllegalArgumentException("Índice inválido ou array vazio.");
        }

        // Caso o array tenha apenas um elemento, retorne um array vazio
        if (array.length == 1) {
            return new String[0];
        }

        // Cria um novo array com um tamanho menor
        String[] newArray = new String[array.length - 1];

        // Copia os elementos antes do índice
        System.arraycopy(array, 0, newArray, 0, index);

        // Copia os elementos depois do índice
        System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);

        return newArray;
    }




    public void removerItemDoLocal(String item) {
        List<String> itens = locais.get(localAtual);
        if (itens != null) {
            itens.remove(item);
        }
    }
}