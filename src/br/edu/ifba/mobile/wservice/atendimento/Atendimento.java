package br.edu.ifba.mobile.wservice.atendimento;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.ifba.mobile.wservice.atendimento.padrao.AtdCalcados;
import br.edu.ifba.mobile.wservice.atendimento.padrao.AtdVestFem;
import br.edu.ifba.mobile.wservice.atendimento.padrao.AtdVestMasc;
import br.edu.ifba.mobile.wservice.atendimento.padrao.Atendente;

@Path("ws")
public class Atendimento {

	@GET
	@Path("/echo")
	@Produces(MediaType.TEXT_PLAIN)
	public String echo() {
		return "ok";
	}

	@GET
	@Path("/atendentes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Atendente> getAtendentes() {
		
		List<Atendente> atendentes = new ArrayList<Atendente>();
		
		Atendente atd = new AtdCalcados("Ferris");
		atd.setProximo(new AtdVestFem("Sloane"));
		atd.setProximo(new AtdVestMasc("Cameron"));

		while (atd != null){//verificar aqui se precisa colocar um por um, pq o retorno pega os encadeados
			atendentes.add(atd);
			atd = atd.getProximo();
		}
		
		return atendentes;
	}

	@GET//antes era POST mas ai n retornava
	@Path("/solicitacao/{nome}/{cpf}")
	@Produces(MediaType.TEXT_PLAIN)
	public String solicitarAtendimento(
			@PathParam("nome") String nome,
			@PathParam("cpf") int cpf) {
				
		System.out.println("CPF do cliente: " + cpf);
		System.out.println("Nome do cliente: " + nome);
		
		String resultado = "Solicitação de atendimento realizada!";
		return resultado;
	}

	@GET//antes era DELETE mas ai n retornava
	@Path("/cancelamento/{nome}/{cpf}")
	@Produces(MediaType.TEXT_PLAIN)
	public String cancelarAtendimento(
			@PathParam("nome") String nome,
			@PathParam("cpf") int cpf) {
		System.out.println("CPF do cliente cancelado: " + cpf);
		System.out.println("Nome do cliente cancelado: " + nome);

		String resultado = "Atendimento cancelado!";
		return resultado;
	}

}
