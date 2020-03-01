package controller;

import java.util.ArrayList;

import model.bo.AvisoCoordenacaoBO;
import model.vo.AvisoCoordenacaoVO;

public class ControladoraAvisoCoordenacao {

	public void cadastrarAvisoCoordenacaoController(AvisoCoordenacaoVO avisoCoordenacaoVO) {
		AvisoCoordenacaoBO avisoCoordenacaoBO = new AvisoCoordenacaoBO();
		avisoCoordenacaoBO.cadastrarAvisoCoordenacaoBO(avisoCoordenacaoVO);
	}

	public void atualizarAvisoCoordenacaoController(AvisoCoordenacaoVO avisoCoordenacaoVO) {
		AvisoCoordenacaoBO avisoCoordenacaoBO = new AvisoCoordenacaoBO();
		avisoCoordenacaoBO.atualizarAvisoCoordenacaoBO(avisoCoordenacaoVO);
	}

	public void excluirAvisoCoordenacaoController(AvisoCoordenacaoVO avisoCoordenacaoVO) {
		AvisoCoordenacaoBO avisoCoordenacaoBO = new AvisoCoordenacaoBO();
		avisoCoordenacaoBO.excluirAvisoCoordenacaoBO(avisoCoordenacaoVO);
	}

	public ArrayList<AvisoCoordenacaoVO> consultarTodosAvisosCoordenacaoController() {
		AvisoCoordenacaoBO avisoCoordenacaoBO = new AvisoCoordenacaoBO();
		return avisoCoordenacaoBO.consultarTodosAvisosCoordenacaoBO();
	}

	public AvisoCoordenacaoVO consultarAvisoCoordenacaoController(AvisoCoordenacaoVO avisoCoordenacaoVO) {
		AvisoCoordenacaoBO avisoCoordenacaoBO = new AvisoCoordenacaoBO();
		return avisoCoordenacaoBO.consultarAvisoCoordenacaoBO(avisoCoordenacaoVO);
	}
	

}
