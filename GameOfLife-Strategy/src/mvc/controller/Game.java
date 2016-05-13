package mvc.controller;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import mvc.model.GameEngine;
import mvc.model.Statistics;
import mvc.view.GuiView;
import mvc.model.EstrategiaDeDerivacao;

/**
 * Classe responsável por começar o jogo instanciando as classes de GUIview, controller e engine
 * @author helio
 *
 */

public class Game {
	
	private GameEngine engine;
	private GameController controller;
	private GuiView view;
	private Statistics statistics;
	
	
	public Game(){
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/mvc/model/spring/spring.xml"));
		
		
		statistics = new Statistics();		
<<<<<<< HEAD
		engine = new GameEngine(getAltura(),getLargura(), statistics);
		engine.setEstrategia((EstrategiaDeDerivacao) factory.getBean("conway"));
=======
		engine = new GameEngine(getHeight(),getWidth(), statistics);
>>>>>>> master
		controller = new GameController();
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		view = new GuiView(controller, engine);
		controller.setBoard(view);
		
		
	}
	
	public int getHeight(){
		int height = 31;
		while (height > 30 || height < 1){
			height = Integer.parseInt(JOptionPane.showInputDialog("Height: (Enter a number between 1 and 30)"));		
		}
		return height;
	}
	
	public int getWidth(){
		int width = 31;
		while (width > 30 || width < 1){
			width = Integer.parseInt(JOptionPane.showInputDialog("Width: (Enter a number between 1 and 30)"));		
		}
		return width;
	}
	
	

}
