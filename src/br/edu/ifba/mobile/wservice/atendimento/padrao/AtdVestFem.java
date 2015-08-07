package br.edu.ifba.mobile.wservice.atendimento.padrao;

public class AtdVestFem extends Atendente {

	public AtdVestFem(String nome) {
		super(nome);
	}

	@Override
	public String resolverDuvida(TipoDuvida duvida) {
		if (duvida == TipoDuvida.VESTFEM){
			return this.getNome();
		}
		else return this.proximo.resolverDuvida(duvida);
	}

}
