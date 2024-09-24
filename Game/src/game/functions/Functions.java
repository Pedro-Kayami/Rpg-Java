package game.functions;

import java.util.List;

public class Functions {
    int saude = 100;
    int forca = 1;
    String armas;
    String[] inventario;
    private Mapa mapa;

    public Functions(int tamanhoInventario) {
        inventario = new String[tamanhoInventario];
        mapa = new Mapa(); // Inicializa o sistema de mapa
    }

    // Método para pegar itens próximos
    public void pegarItemProximo() {
        String localAtual = mapa.getLocalAtual();
        System.out.println("Você está no local: " + localAtual);
        
        List<String> itensNoLocal = mapa.getItensNoLocal();

        if (itensNoLocal.isEmpty()) {
            System.out.println("Não há itens próximos para pegar.");
            return;
        }

        System.out.println("Itens próximos: " + itensNoLocal);
        String itemParaPegar = itensNoLocal.get(0); // Pega o primeiro item da lista

        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] == null) {
                inventario[i] = itemParaPegar;
                mapa.removerItemDoLocal(itemParaPegar);
                System.out.println("Item {" + itemParaPegar + "} adicionado ao inventário.");
                return;
            }
        }
        System.out.println("Inventário cheio! Não foi possível pegar o item.");
    }

    // Sistema de movimentação do jogador
    public void moverPara(String local) {
        mapa.moverPara(local);
    }


    
    // SISTEMA DE SAÚDE
    public int puxarSaude() {
        System.out.println(saude);
        return saude;
    }
    
    public void setarSaude(int saude) {
        this.saude = Math.max(0, saude);
    }
    
    public void dano(int dano) {
        setarSaude(saude - dano);
    }
    
    public void adicionarSaude(int adcSaude) {
        setarSaude(saude + adcSaude);
    }

    // SISTEMA DE FORÇA 
    public int puxarForca() {
        return forca;
    }
    
    public void adicionarForca(int adcForca) {
        this.forca += adcForca;
    }
    
    public void removerForca(int rmForca) {
        this.forca -= rmForca;
    }
    
    public void resetarForca() {
        forca = 1;
    }

    // SISTEMA DE ARMAS
    public void equiparArma(String arma) {
        boolean armaEncontrada = false;

        for (String item : inventario) {
            if (item != null && item.equals(arma)) {
                armaEncontrada = true;
                break;
            }
        }

        if (armaEncontrada) {
            armas = arma;
            System.out.println("{" + armas + "} Equipada com sucesso!");
        } else {
            System.out.println("Arma não encontrada no inventário!");
        }
    }
    
    public void desequiparArma() {
        if (armas != null) {
            System.out.println("Arma {" + armas + "} desequipada.");
            armas = null;
        } else {
            System.out.println("Você não tem nenhuma arma equipada.");
        }
    }

    // SISTEMA DE INVENTÁRIO
    public String[] verInventario() {
    	return inventario;
    }
    
    public void pegarItem(String item) {
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] == null) { 
                inventario[i] = item; 
                System.out.println("Item {" + item + "} adicionado ao inventário.");
                return;
            }
        }
        System.out.println("Inventário cheio! Não foi possível adicionar o item.");
    }
    
    public void jogarItem(String item) {
        for (int i = 0; i < inventario.length; i++) {
            if (item.equals(inventario[i])) { 
                inventario[i] = null; 
                System.out.println("Item {" + item + "} removido do inventário.");
                return;
            }
        }
        System.out.println("Item {" + item + "} não encontrado no inventário.");
    }
    
    public String ajuda() {
    	StringBuilder ajuda = new StringBuilder();
    	ajuda.append("olhar: Você olha ao redor de você\n");
    	ajuda.append("status: Mostra o seu status\n");
    	ajuda.append("inventario: Mostra seu inventario\n");
    	ajuda.append("pegar: Você pega um item do mapa\n");
    	ajuda.append("jogar: Você joga um item fora\n");
    	ajuda.append("mover: Você se move de mapa\n");
    	ajuda.append("sair: Você fecha o jogo\n");
    	return ajuda.toString();
    }
}
