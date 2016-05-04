package mvc.controller;

import java.security.InvalidParameterException;

import mvc.model.GameEngine;
import mvc.model.Statistics;
import mvc.view.GameView;

/**
 * Classe que atua como um controlador do 
 * padrao MVC, separando os componentes da 
 * camada de apresentacao e model. 
 * 
 * @author rbonifacio
 */
public class GameController {

	private GameEngine engine;
	private GameView board;
	private Statistics statistics;
	
	public GameEngine getEngine() {
		return engine;
	}
	
	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}
	
	public GameView getBoard() {
		return board;
	}
	
	public void setBoard(GameView board) {
		this.board = board;
	}
	
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	public String getStatistics(){
		return this.statistics.display();
	}
	
	
	public void start() {
		board.update();
	}
	
	public void halt() {
		//oops, nao muito legal fazer sysout na classe Controller
		System.out.println("\n \n");
		statistics.display();
		System.exit(0);
	}
	
	public void makeCellAlive(int i, int j) {
		try {
			engine.makeCellAlive(i, j);
			board.update();
		}
		catch(InvalidParameterException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean isCellAlive(int i, int j){		
		try{
			return this.engine.isCellAlive(i, j);
		}catch(InvalidParameterException e){System.out.println(e.getMessage());}
		return false; //TODO: eu acho que tem um problema com excessão aqui		
	}
	
	public void makeCellDead(int i, int j){
		
	}
	
	public void nextGeneration() {
		engine.nextGeneration();
		board.update();
	}
	
	public int getAlturaTabuleiro(){
		return this.engine.getHeight();//TODO: padronizar nomes
	}
	
	public int getLarguraTabuleiro(){
		return this.engine.getWidth();//TODO:padronizar nomes
	}
	
	
}
