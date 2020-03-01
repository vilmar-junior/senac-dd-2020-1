package controller;

import java.util.ArrayList;

import model.bo.AvisoCursoLivreBO;
import model.vo.AvisoCursoLivreVO;

public class ControladoraAvisoCursoLivre {

	public void cadastrarAvisoCursoLivreController(AvisoCursoLivreVO avisoCursoLivreVO) {
		AvisoCursoLivreBO avisoCursoLivreBO = new AvisoCursoLivreBO();
		avisoCursoLivreBO.cadastrarAvisoCursoLivreBO(avisoCursoLivreVO);
	}

	public void excluirAvisoCursoLivreController(AvisoCursoLivreVO avisoCursoLivreVO) {
		AvisoCursoLivreBO avisoCursoLivreBO = new AvisoCursoLivreBO();
		avisoCursoLivreBO.excluirAvisoCursoLivreBO(avisoCursoLivreVO);
	}

	public void atualizarAvisoCursoLivreController(AvisoCursoLivreVO avisoCursoLivreVO) {
		AvisoCursoLivreBO avisoCursoLivreBO = new AvisoCursoLivreBO();
		avisoCursoLivreBO.atualizarAvisoCursoLivreBO(avisoCursoLivreVO);
	}

	public ArrayList<AvisoCursoLivreVO> consultarTodosAvisosEstagioController() {
		AvisoCursoLivreBO avisoCursoLivreBO = new AvisoCursoLivreBO();
		return avisoCursoLivreBO.consultarTodosAvisoCursoLivreBO();
	}

	public AvisoCursoLivreVO consultarAvisoEstagioController(AvisoCursoLivreVO avisoCursoLivreVO) {
		AvisoCursoLivreBO avisoCursoLivreBO = new AvisoCursoLivreBO();
		return avisoCursoLivreBO.consultarAvisoCursoLivreBO(avisoCursoLivreVO);
	}

}
