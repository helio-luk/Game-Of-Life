package mvc.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import mvc.controller.GameController;

//import controller

public class Estat_GUI extends JPanel{
	private GameController controller;
	private JLabel text;
	
	public Estat_GUI(GameController controller) {
		super();
		this.controller = controller;
		text = new JLabel(controller.getStatistics());
		text.setVisible(false);
		add(text);
		setVisible(true);
	}

	public void update() {
		text.setText(controller.getStatistics());
	}
}
