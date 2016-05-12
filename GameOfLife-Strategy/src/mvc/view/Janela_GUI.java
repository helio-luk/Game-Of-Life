package mvc.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mvc.view.MenuBar;
import mvc.view.Tabuleiro_GUI;
import mvc.view.Menu_GUI;
import mvc.controller.GameController;
import mvc.view.Estat_GUI;



public class Janela_GUI extends JFrame{
	
	private Tabuleiro_GUI tabuleiro;
	private Menu_GUI menu;
	private Estat_GUI estats;
	private Container pane = getContentPane();
	private MenuBar menubar;
	
	public Janela_GUI(GameController controller) {
		super("Game of Life");
		
		createWindow();
		
		GridBagConstraints c = setWindowLayout();
		setMenuBar(controller);
		setTabuleiroGUI(controller, c);
		setMenuGUI(controller, c);
		setEstatsGUI(controller, c);
		
		setVisible(true);
	}

	public GridBagConstraints setWindowLayout() {
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		return c;
	}

	private static void inicLookAndFeel() {
		 
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();

		try {
			 UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException e) {
			 System.err.println("ClassNotFoundException");
		} catch (UnsupportedLookAndFeelException e) {
			 System.err.println("UnsupportedLookAndFeelException");
		} catch (Exception e) {
			 System.err.println("Exception");
			 e.printStackTrace();
		}
		
		JFrame.setDefaultLookAndFeelDecorated(true);

	}
	
	public void createWindow() {
		inicLookAndFeel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		if ( dim.width > 600 )
			dim.width -= 100;
		if ( dim.height > 600 )
			dim.height -= 100;
		setSize(dim);
		setLocationRelativeTo(null);
	}

	public void setMenuBar(GameController controller){
		this.menubar = new MenuBar(controller);
		setJMenuBar(menubar.menu);
	}
	
	public void setEstatsGUI(GameController controller, GridBagConstraints c) {
		estats = new Estat_GUI(controller);
		c.weighty = 0.2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.SOUTH;
		c.weightx = 0.5;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(estats, c);
	}

	public void setMenuGUI(GameController controller, GridBagConstraints c) {
		menu = new Menu_GUI(controller);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHEAST;
		c.weightx = 0;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(menu, c);
	}

	public void setTabuleiroGUI(GameController controller, GridBagConstraints c) {
		tabuleiro = new Tabuleiro_GUI(controller);
		c.weighty = 0.8;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(tabuleiro, c);
	}
	
	public void updateTabuleiro() {
		tabuleiro.update();
	}
	
	public Tabuleiro_GUI getTabuleiro() {
		return tabuleiro;
	}
}
