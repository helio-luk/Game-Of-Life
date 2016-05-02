package mvc.view;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import mvc.view.Janela_GUI;
//import controller
//import GameEngine

public class View implements Runnable{
	
	protected GameController controller;
	private Janela_GUI frame;
	private String statisticsMessage;
	
	public View(GameController controller, GameEngine engine) {
		this.controller = controller;
		engine.register(this);
		SwingUtilities.invokeLater(this);
	}

	public void update() {
		frame.updateTabuleiro();
		statisticsMessage = controller.getStatistics();
	}

	@Override
	public void start() {}

	@Override
	public void showStatistics() {
		JOptionPane.showMessageDialog(null, statisticsMessage);
	}

	@Override
	public void run() {
		frame = new Janela_GUI(controller);
	}

	
}
