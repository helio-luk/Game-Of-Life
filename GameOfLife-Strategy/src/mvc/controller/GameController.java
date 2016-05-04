package mvc.controller;

import java.security.InvalidParameterException;

import mvc.model.EstrategiaDeDerivacao;
import mvc.model.GameEngine;
import mvc.model.Statistics;
import mvc.view.GuiView;


/**
 * Classe que atua como um controlador do 
 * padrao MVC, separando os componentes da 
 * camada de apresentacao e model. 
 * 
 * @author rbonifacio
 */
public class GameController {

	private GameEngine engine;
	private GuiView board;
	private Statistics statistics;
	
	public GameEngine getEngine() {
		return engine;
	}
	
	public void setEstrategia(EstrategiaDeDerivacao e){
		engine.setEstrategia(e);
	}
	
	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}
	
	public GuiView getBoard() {
		return board;
	}
	
	public void setBoard(GuiView board) {
		this.board = board;
	}
	
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	public String getStatistics(){
		return this.statistics.display();
	}
	
	public void exit(){
		System.exit(0);
	}
	
	public void start() { //problema qui
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
		}catch(InvalidParameterException e) {System.out.println(e.getMessage());	}
	}
	
	public boolean isCellAlive(int i, int j){		
			return this.engine.isCellAlive(i, j);				
	}
	
	public void makeCellDead(int i, int j){
		try {
			this.engine.makeCellDead(i, j);
		}catch(InvalidParameterException e) {System.out.println(e.getMessage());	}
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
	
	public int numberOfAliveCells() {		
		return engine.numberOfAliveCells();
	}
	
	public void killAllCells(){
		try{			
			this.engine.killAllCells();
		}catch(InvalidParameterException e){System.out.println(e.getMessage());}//TODO:tem q melhorar essa exception
		
	}
	
	
}
