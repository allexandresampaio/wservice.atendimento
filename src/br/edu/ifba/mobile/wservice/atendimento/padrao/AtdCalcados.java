package br.edu.ifba.mobile.wservice.atendimento.padrao;

public class AtdCalcados extends Atendente {


	public AtdCalcados(String nome) {
		super(nome);
}

	public String resolverDuvida(TipoDuvida duvida) {
		if (duvida == TipoDuvida.CALCADOS){
			return this.getNome();
		}
		else return this.proximo.resolverDuvida(duvida);
	}

}
