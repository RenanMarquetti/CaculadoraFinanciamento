package calculateJuros;

import java.awt.ComponentOrientation;
import java.beans.Visibility;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class WindowCalculateJuros extends JFrame {
	
	ControllerCalculateJuros controller;
	JTextField textCapital,textTaxaJuro,textNumParcelas,textMesesCarencia;
	JTable tabelaParcelasPrice,tabelaParcelasSac;
	JButton btnCalcular;
	JScrollPane barraRolagemParcelasPrice,barraRolagemParcelasSac;
	String colunas[] = {"N°","capital","Juro","Total"};
	
	WindowCalculateJuros() {
		
		setTitle("Calcular Financiamento");
		setSize(856,480);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		textCapital = createTextFild("Capital:", 20);
		textTaxaJuro = createTextFild("Taxa Juros:", 50);
		textNumParcelas = createTextFild("Parcelas: ", 80);	
		textMesesCarencia = createTextFild("Carencia (Mêses): ", 110);

			
		btnCalcular = new JButton("Calcular Parcelas");
		btnCalcular.setSize(200,50);
		btnCalcular.setLocation(550, 50);
		btnCalcular.addActionListener((ae) -> {
			calcular();
		});
		add(btnCalcular);
		
		tabelaParcelasPrice = new JTable();
		
		barraRolagemParcelasPrice = creatBarraRolagem("Price",tabelaParcelasPrice,30);
		
		tabelaParcelasSac = new JTable();
		
		barraRolagemParcelasSac = creatBarraRolagem("Sac",tabelaParcelasSac,458);
		
	}
	
	JScrollPane creatBarraRolagem(String titulo,JTable tabela,int marginStart) {
		
		JLabel tituloTabela = new JLabel(titulo);
		tituloTabela.setSize(368,30);
		tituloTabela.setHorizontalAlignment(SwingConstants.CENTER);
		tituloTabela.setLocation(marginStart, 140);
		add(tituloTabela);
		
		JScrollPane barraRolagem = new JScrollPane(tabela);
		barraRolagem.setSize(368,250);
		barraRolagem.setLocation(marginStart, 170);
		add(barraRolagem);
		
		return barraRolagem;
	}
	
	JTextField createTextFild(String frase,int alturaLinha) {
		
		int width = 150;
		int height = 25;
		
		JLabel label = new JLabel(frase);
		label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		label.setSize(width,height);
		label.setLocation(50, alturaLinha);
		add(label);
		
		JTextField textFild = new JTextField(10);
		textFild.setSize(width,height);
		textFild.setLocation(210, alturaLinha);
		add(textFild);
		
		return textFild;
	}
	
	void calcular() {
		
		System.out.println("Recalculando");
		
		double capital = Double.parseDouble(textCapital.getText());
		int numParcelas = Integer.parseInt(textNumParcelas.getText());
		double taxaDeJuro = Double.parseDouble(textTaxaJuro.getText())/100.0;
		int mesesCarencia = Integer.parseInt(textMesesCarencia.getText());
		
		controller = new ControllerCalculateJuros(capital,numParcelas,taxaDeJuro,mesesCarencia);
		
		insertModel(tabelaParcelasPrice,controller.getDadosPrice());
		insertModel(tabelaParcelasSac,controller.getDadosSac());
		
		/*tabelaParcelasPrice.setModel(new DefaultTableModel(controller.getDadosPrice(),colunas));	
		tabelaParcelasPrice.getColumnModel().getColumn(0).setPreferredWidth(8);
		tabelaParcelasPrice.getColumnModel().getColumn(1).setCellRenderer(new formatadorMoeda());
		tabelaParcelasPrice.getColumnModel().getColumn(2).setCellRenderer(new formatadorMoeda());
		tabelaParcelasPrice.getColumnModel().getColumn(3).setCellRenderer(new formatadorMoeda());

		
		tabelaParcelasSac.setModel(new DefaultTableModel(controller.getDadosSac(),colunas));
		tabelaParcelasSac.getColumnModel().getColumn(0).setPreferredWidth(8);
		tabelaParcelasSac.getColumnModel().getColumn(1).setCellRenderer(new formatadorMoeda());
		tabelaParcelasSac.getColumnModel().getColumn(2).setCellRenderer(new formatadorMoeda());
		tabelaParcelasSac.getColumnModel().getColumn(3).setCellRenderer(new formatadorMoeda());*/
		
	}
	
	void insertModel(JTable tabelaParcelas,Object[][] dados) {
		
		tabelaParcelas.setModel(new DefaultTableModel(dados,colunas));	
		tabelaParcelas.getColumnModel().getColumn(0).setPreferredWidth(8);
		tabelaParcelas.getColumnModel().getColumn(1).setCellRenderer(new formatadorMoeda());
		tabelaParcelas.getColumnModel().getColumn(2).setCellRenderer(new formatadorMoeda());
		tabelaParcelas.getColumnModel().getColumn(3).setCellRenderer(new formatadorMoeda());
		
	}
}
