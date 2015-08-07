package br.edu.ifba.mobile.wservice.atendimento.duvida;

import java.util.ArrayList;
import java.util.List;

//import javax.faces.bean.ManagedBean; //pra q isso?
//import javax.faces.bean.SessionScoped; //pra q isso?

import br.edu.ifba.mobile.wservice.atendimento.padrao.Atendente;
import br.edu.ifba.mobile.wservice.atendimento.padrao.AtdCalcados;
import br.edu.ifba.mobile.wservice.atendimento.padrao.AtdVestFem;
import br.edu.ifba.mobile.wservice.atendimento.padrao.AtdVestMasc;
import br.edu.ifba.mobile.wservice.atendimento.padrao.TipoDuvida;


	public class Duvida {
	
	private String resultado = "";
	
	public List<String> getTiposDeDuvida(){
		List<String> tipos = new ArrayList<String>();
		
		tipos.add("Calçados");
		tipos.add("Vestuário Feminino");
		tipos.add("Vestuário Masculino");
		
		return tipos;
	}
	
	public void setTipoEscolhido(String tipo){
		TipoDuvida tipoDuvida = TipoDuvida.SEMDUVIDA;
		if ("Calçados".equals(tipo)){
			tipoDuvida = TipoDuvida.CALCADOS;
		} else if ("Vestuário Feminino".equals(tipo)){
			tipoDuvida = TipoDuvida.VESTFEM;
		} else if ("Vestuário Masculino".equals(tipo)){
			tipoDuvida = TipoDuvida.VESTMASC;
		}
		
		resultado = getAtendente(tipoDuvida);
	}

	private String getAtendente(TipoDuvida tipoDuvida){
		Atendente atd = new AtdCalcados("Ferris");
		atd.setProximo(new AtdVestFem("Sloane"));
		atd.setProximo(new AtdVestMasc("Cameron"));
		
		return atd.resolverDuvida(tipoDuvida);
	}
	
	public String getResultado(){
		return resultado;
	}
}
