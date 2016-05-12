package mvc.controller;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import mvc.model.GameEngine;
import mvc.model.Statistics;
import mvc.view.GuiView;
import mvc.model.EstrategiaDeDerivacao;

public class Game {
	
	private GameEngine engine;
	private GameController controller;
	private GuiView view;
	private Statistics statistics;
	
	
	public Game(){
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/mvc/model/spring/spring.xml"));
		
		
		statistics = new Statistics();		
		engine = new GameEngine(getAltura(),getLargura(), statistics);
		engine.setEstrategia((EstrategiaDeDerivacao) factory.getBean("conway"));
		controller = new GameController();
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		view = new GuiView(controller, engine);
		controller.setBoard(view);
		
		
	}
	
	public int getAltura(){
		int altura = 31;
		while (altura > 30 || altura < 1){
			altura = Integer.parseInt(JOptionPane.showInputDialog("Height: (Enter a number between 1 and 30)"));		
		}
		return altura;
	}
	
	public int getLargura(){
		int largura = 31;
		while (largura > 30 || largura < 1){
			largura = Integer.parseInt(JOptionPane.showInputDialog("Width: (Enter a number between 1 and 30)"));		
		}
		return largura;
	}
	
	

}
