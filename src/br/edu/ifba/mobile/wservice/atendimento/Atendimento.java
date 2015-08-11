package br.edu.ifba.mobile.wservice.atendimento;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifba.mobile.wservice.atendimento.duvida.Duvida;
import br.edu.ifba.mobile.wservice.atendimento.padrao.AtdCalcados;
import br.edu.ifba.mobile.wservice.atendimento.padrao.AtdVestFem;
import br.edu.ifba.mobile.wservice.atendimento.padrao.AtdVestMasc;
import br.edu.ifba.mobile.wservice.atendimento.padrao.Atendente;
import br.edu.ifba.mobile.wservice.atendimento.padrao.TipoDuvida;

@Path("ws")
public class Atendimento {

	@GET
	@Path("/echo")
	@Produces(MediaType.TEXT_PLAIN)
	public String echo() {
		return "ok";
	}

	@GET
	@Path("/tiposDuvida")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getTiposDeDuvida() {
		
		Duvida tipos = new Duvida();
		//List<String> tipos = new ArrayList<String>();
		
	//	tipos.add("Calçados");
	//	tipos.add("Vestuário Feminino");
	//	tipos.add("Vestuário Masculino");
		
		return tipos.getTiposDeDuvida();
		
	}

	@GET//antes era POST mas ai n retornava
	@Path("/solicitacao/{tipo}/{cpf}")
	@Produces(MediaType.TEXT_PLAIN)
	public String solicitarAtendimento(
			@PathParam("tipo") String tipo,
			@PathParam("cpf") int cpf) {
		
		TipoDuvida tipoDuvida = TipoDuvida.SEMDUVIDA;
		if ("Calçados".equals(tipo)){
			tipoDuvida = TipoDuvida.CALCADOS;
		} else if ("Vestuário Feminino".equals(tipo)){
			tipoDuvida = TipoDuvida.VESTFEM;
		} else if ("Vestuário Masculino".equals(tipo)){
			tipoDuvida = TipoDuvida.VESTMASC;
		}
		
		String resultado = getAtendente(tipoDuvida);
		
		System.out.println("CPF do cliente: " + cpf);
		System.out.println("Tipo de atendimento: " + tipo);
		
		return resultado;
	}
	
	private String getAtendente(TipoDuvida tipoDuvida){
		Atendente atd = new AtdCalcados("Ferris");
		atd.setProximo(new AtdVestFem("Sloane"));
		atd.setProximo(new AtdVestMasc("Cameron"));
		
		return atd.resolverDuvida(tipoDuvida);
	}

	@GET//antes era DELETE mas ai n retornava
	@Path("/cancelamento/{tipo}/{cpf}")
	@Produces(MediaType.TEXT_PLAIN)
	public String cancelarAtendimento(
			@PathParam("tipo") String tipo,
			@PathParam("cpf") int cpf) {
		System.out.println("CPF do cliente cancelado: " + cpf);
		System.out.println("Tpo do atendimento cancelado: " + tipo);

		String resultado = "Atendimento cancelado!";
		return resultado;
	}

}
