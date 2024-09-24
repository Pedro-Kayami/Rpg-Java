package game.functions;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Mapa {
    private HashMap<String, List<String>> locais; // Cada local tem uma lista de itens
    private String localAtual;
    String[] localModelagem = {"Inimigo","Poção","Nada","Matéria"};

    public Mapa() {
        locais = new HashMap<>();

        // Definindo alguns locais e itens associados
        List<String> florestaItens = new ArrayList<>();
        florestaItens.add("Espada");
        florestaItens.add("Escudo");

        List<String> cavernaItens = new ArrayList<>();
        cavernaItens.add("Poção");

        locais.put("Floresta", florestaItens);
        locais.put("Caverna", cavernaItens);

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
    
    public String olharMapAtual() {
        // Verifica se o local atual é "Principado da Modelagem"
        if (localAtual.equals("Principado da Modelagem")) {
            if (localModelagem == null || localModelagem.length == 0) {
                return "Não achou nada"; // Evita exceção e retorna uma mensagem
            }

            // Sorteia um índice aleatório
            Random random = new Random();
            int index = random.nextInt(localModelagem.length);

            // Obtém o item sorteado
            String itemSorteado = localModelagem[index];

            // Remove o item sorteado do array
            localModelagem = removerItem(localModelagem, index);

            // Verifica se o item sorteado é vazio ou nulo
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
