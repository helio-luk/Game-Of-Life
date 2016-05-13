package mvc.view;


import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import mvc.controller.GameController;
import mvc.model.EstrategiaDeDerivacao;
import mvc.model.ListaEstrategias;



@SuppressWarnings("serial")
public class Menu_GUI extends JPanel implements ActionListener{

	private GameController controller;
	
	BeanFactory factory = new XmlBeanFactory(new FileSystemResource("src/mvc/model/spring/spring.xml"));		
	ListaEstrategias lista =  (ListaEstrategias) factory.getBean("lista");
	
	private String[] estrategia;
	
	private JButton proxGer;
	private JButton animar;
	private JButton parar;
	private boolean stopStatus;
	
	private JComboBox<String> boxEstrategia;
	
	private Timer timer; //evitar o bloqueio do Event Dispatch Thread durante animacao
	
	public Menu_GUI(GameController controller) {
		super();
		this.controller = controller;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		setStringEstrategia();
		setProxGerButton();
		setAnimarButton();
		setPararButton();
		setBoxEstrategia();

	}
	
	public void setStringEstrategia(){
		ArrayList<EstrategiaDeDerivacao> lista = this.lista.getLista();
		int i = 0;
		estrategia = new String[lista.size()];
		for(EstrategiaDeDerivacao estrategiaAtual:lista){
			estrategia[i] = estrategiaAtual.getName();
			i++;
		}
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
