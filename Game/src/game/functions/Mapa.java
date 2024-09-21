package game.functions;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Mapa {
    private HashMap<String, List<String>> locais; // Cada local tem uma lista de itens
    private String localAtual;

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

        localAtual = "Floresta"; // Local inicial do jogador
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

    public void removerItemDoLocal(String item) {
        List<String> itens = locais.get(localAtual);
        if (itens != null) {
            itens.remove(item);
        }
    }
}
