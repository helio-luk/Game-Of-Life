package mvc.view;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Cell extends JButton{
	public int linha, coluna;
	
	public Cell(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
}
