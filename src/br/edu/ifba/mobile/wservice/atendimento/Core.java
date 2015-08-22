package br.edu.ifba.mobile.wservice.atendimento;

import java.util.List;
import java.util.Random;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
public class Core {
	
	static int id = 0;

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
		return tipos.getTiposDeDuvida();
		
	}

	@POST//@POST manda para o webservice. para ter retorno usa @GET
	@Path("/solicitacao/{tipo}/{cpf}/")
	@Produces(MediaType.APPLICATION_JSON)
	public Atendimento solicitarAtendimento(
			@PathParam("tipo") String tipo,
			@PathParam("cpf") String cpf) {
		
		if (tipo.equals("1")){
			tipo = "Calçados";
		} else if (tipo.equals("2")){
			tipo = "Vestuário Feminino";
		} else if (tipo.equals("3")){
			tipo = "Vestuário Masculino";
		}
		
		TipoDuvida tipoDuvida = TipoDuvida.SEMDUVIDA;
		if ("Calçados".equals(tipo)){
			tipoDuvida = TipoDuvida.CALCADOS;
		} else if ("Vestuário Feminino".equals(tipo)){
			tipoDuvida = TipoDuvida.VESTFEM;
		} else if ("Vestuário Masculino".equals(tipo)){
			tipoDuvida = TipoDuvida.VESTMASC;
		}
		
		String atendente = getAtendente(tipoDuvida);
		String horario = geraHorario();
		
		Atendimento atendimento = new Atendimento(cpf, tipo, atendente, id, horario);
				
		System.out.println("CPF do cliente: " + cpf);
		System.out.println("Tipo de atendimento: " + tipo);
		System.out.println("Nome do atendimento: " + atendente);
		System.out.println("ID de atendimento: " + id);
		System.out.println("Agendado para: " + horario + " horas.");
		System.out.println();
		
		id++;
		
		return atendimento;
	}
	
	private String getAtendente(TipoDuvida tipoDuvida){
		Atendente atd = new AtdCalcados("Ferris");
		atd.setProximo(new AtdVestFem("Sloane"));
		atd.setProximo(new AtdVestMasc("Cameron"));
		
		return atd.resolverDuvida(tipoDuvida);
	}

	@DELETE////@DELETE deleta do webservice. para ter retorno usa @GET
	@Path("/cancelamento/{tipo}/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String cancelarAtendimento(
			@PathParam("tipo") String tipo,
			@PathParam("id") int id) {
		
		if (tipo.equals("1")){
			tipo = "Calçados";
		} else if (tipo.equals("2")){
			tipo = "Vestuário Feminino";
		} else if (tipo.equals("3")){
			tipo = "Vestuário Masculino";
		}
		
		System.out.println("ID do atendimento cancelado: " + id);
		System.out.println("Tpo do atendimento cancelado: " + tipo);

		String resultado = "Atendimento cancelado!";
		return resultado;
	}
	
	public String geraHorario(){
		
		Random gerador = new Random();
		int hora = gerador.nextInt(10)+8;
		
		return hora+":00";
	}

}
