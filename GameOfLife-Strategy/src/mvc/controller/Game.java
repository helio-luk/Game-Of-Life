package mvc.controller;

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
		engine = new GameEngine(10,10, statistics);//TODO: tornar o tabuleiro flexivel
		controller = new GameController();
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		view = new GuiView(controller, engine);//TODO: conferir a classe game controller
		//view.start();//TODO: arrumar isso aqui
		
	}

}
