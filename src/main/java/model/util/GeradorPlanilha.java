package model.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.vo.exercicio1.Cliente;

public class GeradorPlanilha {

	public void gerarPlanilhaClientes(List<Cliente> clientes, String destinoArquivoNoDisco) {
		String[] nomesColunas = { "Nome completo", "Data de nascimento", "CPF", "Qtde. Telefones" };

		// 1) Criar a planilha e uma aba
		XSSFWorkbook planilha = new XSSFWorkbook();
		XSSFSheet abaPlanilha = planilha.createSheet("Clientes");

		// 2) Criar o cabeçalho (na primeira linha)
		Row linhaCabecalho = abaPlanilha.createRow(0);

		for (int i = 0; i < nomesColunas.length; i++) {
			Cell celula = linhaCabecalho.createCell(i);
			celula.setCellValue(nomesColunas[i]);
		}
		
		// 3) Criar as linhas com os clientes da lista
		int linhaAtual = 1;

		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		for (Cliente clienteAtual : clientes) {
			Row novaLinha = abaPlanilha.createRow(linhaAtual++);

			// Preencher a linha, célula a célula com os valores do objeto clienteAtual
			novaLinha.createCell(0).setCellValue(clienteAtual.getNomeCompleto());
			novaLinha.createCell(1).setCellValue(clienteAtual.getDataNascimento().format(formatador));
			novaLinha.createCell(2).setCellValue(clienteAtual.getCpf());
			novaLinha.createCell(3).setCellValue(clienteAtual.getTelefones().size());
		}

		// 4) Ajustar a largura das colunas
		for (int i = 0; i < nomesColunas.length; i++) {
			abaPlanilha.autoSizeColumn(i);
		}

		// 5) Salvar a planilha no disco
		FileOutputStream arquivoSaida = null;

		try {
			arquivoSaida = new FileOutputStream(destinoArquivoNoDisco + ".xlsx");
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado");
		} finally {
			if (arquivoSaida != null) {
				try {
					arquivoSaida.close();
					planilha.close();
				} catch (IOException e) {
					System.out.println("Erro ao fechar arquivos");
				}
			}
		}
	}
}
