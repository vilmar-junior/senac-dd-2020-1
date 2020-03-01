package model.bo;

import java.util.ArrayList;
import model.dao.AvisoCoordenacaoDAO;
import model.vo.AvisoCoordenacaoVO;

public class AvisoCoordenacaoBO {

	public void cadastrarAvisoCoordenacaoBO(AvisoCoordenacaoVO avisoCoordenacaoVO) {
		AvisoCoordenacaoDAO avisoCoordenacaoDAO = new AvisoCoordenacaoDAO();
		if(avisoCoordenacaoDAO.existeRegistroAvisoCoordenacaoDAO(avisoCoordenacaoVO)){
			System.out.println("\nAviso da Coordenação já Cadastrado");
		} else {
			int resultado = avisoCoordenacaoDAO.cadastrarAvisoCoordenacaoDAO(avisoCoordenacaoVO);
			if(resultado == 1){
				System.out.println("\nAviso da Coordenação cadastrado com Sucesso.");
			} else {
				System.out.println("\nNão foi possível cadastrar o Aviso da Coordenação.");
			}
		}		
	}

	public void atualizarAvisoCoordenacaoBO(AvisoCoordenacaoVO avisoCoordenacaoVO) {
		AvisoCoordenacaoDAO avisoCoordenacaoDAO = new AvisoCoordenacaoDAO();
		if(avisoCoordenacaoDAO.existeRegistroPorIdAvisoCoordenacaoDAO(avisoCoordenacaoVO)){
			int resultado = avisoCoordenacaoDAO.atualizarAvisoCoordenacaoDAO(avisoCoordenacaoVO);
			if(resultado == 1){
				System.out.println("\nAviso da Coordenação atualizado com Sucesso.");
			} else {
				System.out.println("\nNão foi possível atualizar o Aviso da Coordenação.");
			}
		} else {
			System.out.println("\nAviso da Coordenação ainda não foi cadastrado.");
		}
	}

	public void excluirAvisoCoordenacaoBO(AvisoCoordenacaoVO avisoCoordenacaoVO) {
		AvisoCoordenacaoDAO avisoCoordenacaoDAO = new AvisoCoordenacaoDAO();
		if(avisoCoordenacaoDAO.existeRegistroPorIdAvisoCoordenacaoDAO(avisoCoordenacaoVO)){
			int resultado = avisoCoordenacaoDAO.excluirAvisoCoordenacaoDAO(avisoCoordenacaoVO);
			if(resultado == 1){
				System.out.println("\nAviso da Coordenação excluído com Sucesso.");
			} else {
				System.out.println("\nNão foi possível excluir o Aviso da Coordenação.");
			}
		} else {
			System.out.println("\nAviso da Coordenação não existe na base da dados.");
		}
	}

	public ArrayList<AvisoCoordenacaoVO> consultarTodosAvisosCoordenacaoBO() {
		AvisoCoordenacaoDAO avisoCoordenacaoDAO = new AvisoCoordenacaoDAO();
		ArrayList<AvisoCoordenacaoVO> avisosCoordenacaoVO = avisoCoordenacaoDAO.consultarTodosAvisoCoordenacaoDAO();
		if(avisosCoordenacaoVO.isEmpty()){
			System.out.println("\nLista de Avisos da Coordenação está vazia.");
		}
		return avisosCoordenacaoVO;
	}

	public AvisoCoordenacaoVO consultarAvisoCoordenacaoBO(AvisoCoordenacaoVO avisoCoordenacaoVO) {
		AvisoCoordenacaoDAO avisoCoordenacaoDAO = new AvisoCoordenacaoDAO();
		AvisoCoordenacaoVO avisoCoordenacao = avisoCoordenacaoDAO.consultarAvisoCoordenacaoDAO(avisoCoordenacaoVO);
		if(avisoCoordenacao == null){
			System.out.println("\nAviso da Coordenação não Localizado.");
		}
		return avisoCoordenacao;
	}

}
