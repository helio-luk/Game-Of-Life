package mvc.view;
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

//import na controller
//import no pack com as estrategias

public class Menu_GUI extends JPanel implements ActionListener{

	private GameController controller;
	
	private String[] estrategia = { "Conway", "High Life", "Life Free or Die" };
	
	private JButton proxGer;
	private JButton animar;
	private JButton estatistica;
	private JButton mataTodas;
	private JButton sair;
	
	private JComboBox<String> boxEstrategia;
	
	private Timer timer; //evitar o bloqueio do Event Dispatch Thread durante animacao
	private int displayedGenerations, generations;
	
	public Menu_GUI(GameController controller) {
		super();
		this.controller = controller;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
		setProxGerButton();
		setAnimarButton();
		setMataTodasButton();
		setEstatisticaButton();
		setBoxEstrategia();
		setSairButton();
	}
	
	private void setProxGerButton(){
		proxGer = new JButton("   Próxima geração  ");
		proxGer.addActionListener(this);		
		add(proxGer);
	}
	
	private void setAnimarButton(){		
		animar = new JButton("          Animação         ");
		animar.addActionListener(this);
		add(animar);
	}
	
	private void setMataTodasButton(){
		mataTodas = new JButton("Matar todas células ");
		mataTodas.addActionListener(this);
		add(mataTodas);
	}
	
	private void setEstatisticaButton(){
		estatistica = new JButton("         Estatística         ");
		estatistica.addActionListener(this);
		add(estatistica);
	}
	
	private void setBoxEstrategia(){
		boxEstrategia = new JComboBox<String>(estrategia);
		boxEstrategia.addActionListener(this);
		add(boxEstrategia);
	}
	
	private void setSairButton(){
		sair = new JButton("             Fechar             ");
		sair.addActionListener(this);
		add(sair);
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
		else if ( event.getSource() == estatistica ){
			displayStatistics();
		}
		else if ( event.getSource() == mataTodas ){
			killAllCells();
		}
		else if ( event.getSource() == sair ){
			exit();
		}
		else if ( event.getSource() == timer ){
			animationTimer();
		}
	}

	public void animationTimer(){
		nextGeneration();
		displayedGenerations++;
		if ((controller.numberOfAliveCells() == 0) || (displayedGenerations == generations)){
			if (controller.numberOfAliveCells() == 0){
				JOptionPane.showMessageDialog(null, "Não há mais células vivas!");
			}
			timer.stop();
		}
	}

	public void killAllCells() {
		controller.killAllCells();
	}

	public void displayStatistics() {
		JOptionPane.showMessageDialog(null, controller.getStatistics());
	}

	public void animate() {
		try {
			generations = Integer.parseInt(JOptionPane.showInputDialog("Deseja ver quantas gerações?"));
			displayedGenerations = 0;
			timer = new Timer(200, this);
			timer.setRepeats(true);
			timer.start();
		}
		catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "O valor fornecido não é um número válido de gerações.");
			animate();
		}
	}

	public void nextGeneration() {
		controller.nextGeneration();
	}

	public void changeStrategy() {
		int estrategia = boxEstrategia.getSelectedIndex() + 1;
		controller.changeStrategy(Strategies.getStrategy(estrategia));
		// estrategia invalida não tratada, por ser escolhida entre opções pre-definidas
	}
	
	public void exit() {
		controller.exit();
	}
}
