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

    public void removerItemDoLocal(String item) {
        List<String> itens = locais.get(localAtual);
        if (itens != null) {
            itens.remove(item);
        }
    }
}
