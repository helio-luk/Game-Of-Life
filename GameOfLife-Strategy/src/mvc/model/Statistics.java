package mvc.model;

/**
 * Essa tambem eh uma classe com baixa coesao, 
 * pois mustura caracteristicas de Model (as propriedades) 
 * com caracteristicas de view (metodo display())
 * 
 * Nao eh uma boa implementacao.
 * 
 * @author rodrigobonifacio
 */
public class Statistics {
	private int revivedCells;
	private int killedCells;
	
	public Statistics() {
		revivedCells = 0;
		killedCells = 0;
	}

	public int getRevivedCells() {
		return revivedCells;
	}

	public void recordRevive() {
		this.revivedCells++;
	}

	public int getKilledCells() {
		return killedCells;
	}

	public void recordKill() {
		this.killedCells++;
	}
	
	public String display() {
		String statistics = "Revived Cells: "+ revivedCells + " Killed Cells: "+ killedCells;
		return statistics;		
	}

}
