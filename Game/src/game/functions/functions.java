package game.functions;

public class functions {
	int saude = 100;
	int forca = 1;
	String armas;
	String[] inventario;
	
	
	// SISTEMA DE SAUDE
	public int puxarSaude() {
		return saude;
	}
	
	public void setarSaude(int saude) {
		this.saude = Math.max(0,  saude);
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
		this.forca = (forca + adcForca);
	}
	
	public void removerForca(int rmForca) {
		this.forca = (forca - rmForca);
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
	
	public void desequiparArma(String arma) {
		if (armas != null) {
			armas = null;
		} else {
			System.out.println("Você não tem nenhuma arma equipada.");
		}
	}
	
	
}