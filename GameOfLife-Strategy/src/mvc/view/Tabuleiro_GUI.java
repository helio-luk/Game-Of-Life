package mvc.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//import controller

public class Tabuleiro_GUI extends JPanel{
	private ImageIcon liveCell = new ImageIcon("live.png");
	private ImageIcon deadCell = new ImageIcon("dead.png");
	
	private int altura;
	private int largura;
	
	private GameController controller;
	private Cell[][] tabuleiro;
	
	public Tabuleiro_GUI(GameController controller) {
		super();
		altura = controller.getAlturaTabuleiro();
		largura = controller.getLarguraTabuleiro();
		this.controller = controller;
		
		criarTabuleiro();
		update();
		
		GridLayout manager = new GridLayout(altura, largura);
		setLayout(manager);
		
		for ( int i = 0; i < altura; i++ ) {
			for ( int j = 0; j < largura; j++ ) {
				add(tabuleiro[i][j]);
			}
		}
		
		setVisible(true);
	}
	
	private void criarTabuleiro() {
		tabuleiro = new Cell[altura][largura];
		
		for ( int i = 0; i < altura; i++ ) {
			for ( int j = 0; j < largura; j++ ) {
				tabuleiro[i][j] = new Cell(i, j);
				tabuleiro[i][j].setBackground(Color.white);
				tabuleiro[i][j].setBorderPainted(false);
				tabuleiro[i][j].addActionListener(new CellListener(tabuleiro[i][j]));
			}
		}
	}
	
	private class CellListener implements ActionListener {

		private Cell button;
		
		public CellListener(Cell button) {
			this.button = button;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if ( controller.isCellAlive(button.linha, button.coluna) ) {
				button.setIcon(deadCell);
				controller.makeCellDead(button.linha, button.coluna);
			} else {
				button.setIcon(liveCell);
				controller.makeCellAlive(button.linha, button.coluna);
			}
		}
		
	}

	public void update() {
		for ( int i = 0; i < altura; i++ ) {
			for ( int j = 0; j < largura; j++ ) {
				if ( controller.isCellAlive(i, j) )
					tabuleiro[i][j].setIcon(liveCell);
				else
					tabuleiro[i][j].setIcon(deadCell);
				tabuleiro[i][j].setVisible(true);	
			}
		}
	}

}