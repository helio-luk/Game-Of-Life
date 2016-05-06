package mvc.model;

import java.util.ArrayList;
import mvc.model.EstrategiaDeDerivacao;


public class ListaEstrategias {
	
	private ArrayList<EstrategiaDeDerivacao>  listaEstrategias = new ArrayList<EstrategiaDeDerivacao>(0);

	public ArrayList<EstrategiaDeDerivacao> getLista() {
		return listaEstrategias;
	}

	public void setLista(ArrayList<EstrategiaDeDerivacao> listaEstrategias) {
		this.listaEstrategias = listaEstrategias;
	}

	
}
