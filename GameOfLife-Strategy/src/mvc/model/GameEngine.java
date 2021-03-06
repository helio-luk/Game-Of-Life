package mvc.model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

/**
 * Representa um ambiente (environment) do jogo GameOfLife.
 * 
 * Essa implementacao segue o padrao de projeto Strategy, e a 
 * classe GameEngine possui uma referencia para uma estrategia de 
 * derivacao que pode ser alterada durante a execucao do jogo. 
 * @author rbonifacio
 */
@SuppressWarnings("deprecation")
public class GameEngine {
	private int height;
	private int width;
	private Cell[][] cells;
	private Statistics statistics;
	private EstrategiaDeDerivacao strategy;
	

	/**
	 * Construtor da classe Environment.
	 * 
	 * @param height
	 *            dimensao vertical do ambiente
	 * @param width
	 *            dimentsao horizontal do ambiente
	 */
	public GameEngine(int height, int width, Statistics statistics) {
		this.height = height;
		this.width = width;

		cells = new Cell[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = new Cell();
			}
		}
		
		this.statistics = statistics;
		
		//instaniando a estrategia conway como default usando spring
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/mvc/model/spring/spring.xml"));		
		ListaEstrategias list =  (ListaEstrategias) factory.getBean("lista");			
		ArrayList <EstrategiaDeDerivacao> strategyList = new ArrayList <EstrategiaDeDerivacao>(0);		
		strategyList = list.getLista();	
		
		setEstrategia(strategyList.get(0));		

	}
	
	public void setEstrategia(EstrategiaDeDerivacao e) {
		strategy = e;
	}

	public EstrategiaDeDerivacao getEstrategia() {
		return strategy;
	}
	
	/**
	 * Calcula uma nova geracao do ambiente. Essa implementacao delega para 
	 * a estrategia de derivacao a logica necessaria para identificar 
	 * se uma celula deve se tornar viva ou ser mantida viva na proxima 
	 * geracao. 
	 */
	public void nextGeneration() {
		List<Cell> mustRevive = new ArrayList<Cell>();
		List<Cell> mustKill = new ArrayList<Cell>();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				if (strategy.shouldRevive(i, j, this)) {
					mustRevive.add(cells[i][j]);
				} 
				else if ((!strategy.shouldKeepAlive(i, j, this)) && cells[i][j].isAlive()) {
					mustKill.add(cells[i][j]);
				}
			}
		}
		
		updateStatistics(mustRevive, mustKill);
	}

	/*
	 * Metodo auxiliar que atualiza as estatisticas das celulas 
	 * que foram mortas ou se tornaram vivas entre duas geracoes. 
	 */
	private void updateStatistics(List<Cell> mustRevive, List<Cell> mustKill) {
		for (Cell cell : mustRevive) {
			cell.revive();
			statistics.recordRevive();
		}
		
		for (Cell cell : mustKill) {
			cell.kill();
			statistics.recordKill();
		}
	}
	
	/**
	 * Torna a celula de posicao (i, j) viva
	 * 
	 * @param i posicao vertical da celula
	 * @param j posicao horizontal da celula
	 * 
	 * @throws InvalidParameterException caso a posicao (i, j) nao seja valida.
	 */
	public void makeCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			cells[i][j].revive();
			statistics.recordRevive();
		}
		else {
			new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}
	
	public void makeCellDead(int i, int j) throws InvalidParameterException{
		
		if(validPosition(i, j)){			
			cells[i][j].kill();
			statistics.recordKill();			
		}else{
			new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
		
	}
	
	public void killAllCells(){//TODO:acho que tinha que tratar a exception aqui
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(isCellAlive(i,j))
					makeCellDead(i, j);
			}
		}		
	}
	
	/**
	 * Verifica se uma celula na posicao (i, j) estah viva.
	 * 
	 * @param i Posicao vertical da celula
	 * @param j Posicao horizontal da celula
	 * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
	 * 
	 * @throws InvalidParameterException caso a posicao (i,j) nao seja valida. 
	 */
	public boolean isCellAlive(int i, int j) throws InvalidParameterException {
		if(validPosition(i, j)) {
			return cells[i][j].isAlive();
		}
		else {			
			throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
		}
	}

	/**
	 * Retorna o numero de celulas vivas no ambiente. 
	 * Esse metodo eh particularmente util para o calculo de 
	 * estatisticas e para melhorar a testabilidade.
	 * 
	 * @return  numero de celulas vivas.
	 */
	public int numberOfAliveCells() {
		int aliveCells = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(isCellAlive(i,j)) {
					aliveCells++;
				}
			}
		}
		return aliveCells;
	}
	
	public int normalizar(int x){
		if(x >= height){
			return x - height;
		}
		else if (x < 0){
			return x + height;
		}
		
		return x;
	}
	
	/*
	 * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
	 * de referencia identificada pelos argumentos (i,j).
	 */
	public int numberOfNeighborhoodAliveCells(int i, int j) {
		int alive = 0;
		for (int a = i - 1; a <= i + 1; a++) {
			for (int b = j - 1; b <= j + 1; b++) {
				
				
				int aux_a = normalizar(a);
				int aux_b = normalizar(b);
				
				if (validPosition(aux_a, aux_b)  && (!(aux_a==i && aux_b == j)) && cells[aux_a][aux_b].isAlive()) {
					alive++;
				}
			}
		}
		return alive;
	}

	/*
	 * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
	 */
	private boolean validPosition(int a, int b) {
		return a >= 0 && a < height && b >= 0 && b < width;
	}

	/* Metodos de acesso as propriedades height e width */
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
