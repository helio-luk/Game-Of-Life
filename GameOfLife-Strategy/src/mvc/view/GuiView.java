package mvc.view;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import mvc.controller.GameController;
import mvc.model.GameEngine;
import mvc.view.Janela_GUI;
//import controller
//import GameEngine

public class GuiView implements Runnable{
	
	protected GameController controller;
	protected GameEngine engine;
	private Janela_GUI frame;
	private String statisticsMessage;
	
	public GuiView(GameController controller, GameEngine engine) {
		this.controller = controller;
		this.engine = engine;
		SwingUtilities.invokeLater(this);
	}

	public void update() {
		frame.updateTabuleiro();
		statisticsMessage = controller.getStatistics();
	}

		
	public void showStatistics() {
		JOptionPane.showMessageDialog(null, statisticsMessage);
	}

	@Override
	public void run() {
		frame = new Janela_GUI(controller);
	}

	
}
