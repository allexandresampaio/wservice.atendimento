package br.edu.ifba.mobile.wservice.atendimento.padrao;

public class AtdVestMasc extends Atendente {

	public AtdVestMasc(String nome) {
		super(nome);
}

	@Override
	public String resolverDuvida(TipoDuvida duvida) {
		if (duvida == TipoDuvida.VESTMASC){
			return this.getNome();
		}
		else return "N�o h� atendenes dispon�vel para esta d�vida.";
	}

}
