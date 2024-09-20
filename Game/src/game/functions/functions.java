package game.functions;

public class functions {
	int saude;
	
	public int puxarSaude() {
		return saude;
	}
	
	public void setarSaude(int saude) {
		this.saude = Math.max(0,  saude);
	}
	
	public void reduzirSaude(int dano) {
		setarSaude(saude - dano);
	}
	
	public void adicionarSaude(int adcSaude) {
		setarSaude(saude + adcSaude);
	}
}