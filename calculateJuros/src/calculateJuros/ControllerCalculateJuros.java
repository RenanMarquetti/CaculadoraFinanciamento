package calculateJuros;

public class ControllerCalculateJuros {
	
	double capitalInicial,taxaJuro;
	int parcelas,diasCarencia;
	
	ControllerCalculateJuros(double cap,int parc,double jur,int carenc){
		capitalInicial = cap* Math.pow(1.0+jur,carenc);
		System.out.println(Math.pow(1.0+jur,carenc));
		parcelas = parc;
		taxaJuro = jur;
		diasCarencia = carenc;
	}
	
	Object[][] getDadosPrice() {
		
		Object[][] dados = new Object[parcelas][];
		
		double capital = capitalInicial;
		
		double juroComposto = Math.pow(1.0+taxaJuro,(double) parcelas);
		
		double valorParcela = capital * ((juroComposto*taxaJuro)/(juroComposto-1.0));
		
		double valorJuroParcela,valorCapitalParcela;
		
		for(int c = 0; c<dados.length; c++) {
			
			valorJuroParcela = capital * taxaJuro;
			valorCapitalParcela = valorParcela - valorJuroParcela;
			
			dados[c] = new Object[]{c+1,valorCapitalParcela,valorJuroParcela,valorParcela};
			
			capital -= valorCapitalParcela;
		}
		
		return dados;
	}
	
	Object[][] getDadosSac() {
		
		Object[][] dados = new Object[parcelas][];
		
		double capital = capitalInicial;
		
		double amortizacao = capital / parcelas;
		
		for(int c = 0; c<dados.length; c++) {
			
			double valorJuro = capital*taxaJuro;
			capital -= amortizacao;
			
			
			dados[c] = new Object[]{c+1,amortizacao,valorJuro,amortizacao+valorJuro};
		}
		
		return dados;
	}
}
