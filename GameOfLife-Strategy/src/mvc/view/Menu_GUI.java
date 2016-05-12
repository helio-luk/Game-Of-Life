package mvc.view;


import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import mvc.controller.GameController;



public class Menu_GUI extends JPanel implements ActionListener{

	private GameController controller;
	
	private String[] estrategia = { "Conway", "High Life", "Life Free or Die" };
	
	private JButton proxGer;
	private JButton animar;
	private JButton parar;
	private boolean stopStatus;
	
	private JComboBox<String> boxEstrategia;
	
	private Timer timer; //evitar o bloqueio do Event Dispatch Thread durante animacao
	private int displayedGenerations, generations;
	
	public Menu_GUI(GameController controller) {
		super();
		this.controller = controller;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
		setProxGerButton();
		setAnimarButton();
		setPararButton();
		setBoxEstrategia();

	}
	
	private void setProxGerButton(){
		proxGer = new JButton("Next Generation");
		proxGer.addActionListener(this);		
		add(proxGer);
	}
	
	private void setPararButton() {
		parar = new JButton("Stop animation");
		parar.addActionListener(this);
		add(parar);
	}
	
	private void setAnimarButton(){		
		animar = new JButton("Animate");
		animar.addActionListener(this);
		add(animar);
	}
	
	private void setBoxEstrategia(){
		boxEstrategia = new JComboBox<String>(estrategia);
		boxEstrategia.addActionListener(this);
		add(boxEstrategia);
	}

	@Override
	public void actionPerformed(ActionEvent event){
		if ( event.getSource() == proxGer ){
			nextGeneration();
		}
		else if ( event.getSource() == boxEstrategia ){
			changeStrategy();
		}
		else if ( event.getSource() == animar ){
			animate();
		}
		else if ( event.getSource() == parar ){
			stopStatus = true;
		}
		else if ( event.getSource() == timer ){
			animationTimer();
		}
	}

	public void animationTimer(){
		nextGeneration();
		if (controller.numberOfAliveCells() == 0){
			JOptionPane.showMessageDialog(null, "No more cells alive!");
			timer.stop();
		}
		else if(stopStatus){
			timer.stop();
		}
	}

	public void animate() {
		stopStatus = false;
		timer = new Timer(200, this);
		timer.setRepeats(true);
		timer.start();
	}

	public void nextGeneration() {
		controller.nextGeneration();
	}

	public void changeStrategy() {
		int estrategia = boxEstrategia.getSelectedIndex() + 1;		
		controller.changeStrategy(estrategia);
		
		
		//estrategia invalida n�o tratada, por ser escolhida entre op��es pre-definidas
	}
}
