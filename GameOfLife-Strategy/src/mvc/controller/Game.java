package mvc.controller;

import javax.swing.JOptionPane;

import mvc.model.GameEngine;
import mvc.model.Statistics;
import mvc.view.GuiView;

public class Game {
	
	private GameEngine engine;
	private GameController controller;
	private GuiView view;
	private Statistics statistics;
	
	
	public Game(){
		
		statistics = new Statistics();		
		engine = new GameEngine(getAltura(),getLargura(), statistics);
		controller = new GameController();
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		view = new GuiView(controller, engine);
		controller.setBoard(view);
		
		
	}
	
	public int getAltura(){
		int altura; 		
		altura = Integer.parseInt(JOptionPane.showInputDialog("Altura:"));		
		return altura;
	}
	
	public int getLargura(){
		int largura; 		
		largura = Integer.parseInt(JOptionPane.showInputDialog("Largura:"));		
		return largura;
	}
	
	

}
