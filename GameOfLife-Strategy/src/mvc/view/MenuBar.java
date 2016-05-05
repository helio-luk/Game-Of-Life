package mvc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import mvc.controller.GameController;

public class MenuBar  implements ActionListener {
	public JMenuBar menu;
	public JMenu opcoes;
	public JMenuItem estatistica, matar;
	public GameController controller;
	
	public MenuBar(GameController controller){
		
		this.controller = controller;
		
		menu = new JMenuBar();
		opcoes = new JMenu ("Opções");
		
		menu.add(opcoes);
		
		estatistica = new JMenuItem("Estatística");
		matar = new JMenuItem("Matar todas células");
		
		opcoes.add(estatistica);
		opcoes.add(matar);
		
		estatistica.addActionListener(this);
		matar.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {  
		if(e.getSource()==estatistica)  
			mostraEstatistica();  
		if(e.getSource()==matar)  
			mataTodas();
	}
	
	public void mataTodas(){
		controller.killAllCells();
	}
	
	public void mostraEstatistica() {
		JOptionPane.showMessageDialog(null, controller.getStatistics());
	}
}