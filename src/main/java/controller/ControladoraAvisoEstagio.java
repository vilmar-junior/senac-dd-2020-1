package controller;

import java.util.ArrayList;
import model.bo.AvisoEstagioBO;
import model.vo.AvisoEstagioVO;

public class ControladoraAvisoEstagio {

	public void cadastrarAvisoEstagioController(AvisoEstagioVO avisoEstagioVO) {
		AvisoEstagioBO avisoEstagioBO = new AvisoEstagioBO();
		avisoEstagioBO.cadastrarAvisoEstagioBO(avisoEstagioVO);
	}

	public void excluirAvisoEstagioController(AvisoEstagioVO avisoEstagioVO) {
		AvisoEstagioBO avisoEstagioBO = new AvisoEstagioBO();
		avisoEstagioBO.excluirAvisoEstagioBO(avisoEstagioVO);
	}

	public void atualizarAvisoEstagioController(AvisoEstagioVO avisoEstagioVO) {
		AvisoEstagioBO avisoEstagioBO = new AvisoEstagioBO();
		avisoEstagioBO.atualizarAvisoEstagioBO(avisoEstagioVO);
	}

	public ArrayList<AvisoEstagioVO> consultarTodosAvisosEstagioController() {
		AvisoEstagioBO avisoEstagioBO = new AvisoEstagioBO();
		return avisoEstagioBO.consultarAvisoEstagioBO();
	}

	public AvisoEstagioVO consultarAvisoEstagioController(AvisoEstagioVO avisoEstagioVO) {
		AvisoEstagioBO avisoEstagioBO = new AvisoEstagioBO();
		return avisoEstagioBO.consultarAvisoEstagioBO(avisoEstagioVO);
	}

}
