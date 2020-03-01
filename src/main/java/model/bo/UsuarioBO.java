package model.bo;

import java.util.ArrayList;

import model.dao.UsuarioDAO;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class UsuarioBO {

	public void cadastrarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.existeRegistroPorCpfDAO(usuarioVO.getCpf())){
			System.out.println("\nUsuário já Cadastrado");
		} else {
			int resultado = usuarioDAO.cadastrarUsuarioDAO(usuarioVO);
			if(resultado == 1){
				System.out.println("\nUsuário cadastrado com Sucesso.");
			} else {
				System.out.println("\nNão foi possível cadastrar o Usuário.");
			}
		}
	}

	
	public ArrayList<TipoUsuarioVO> consultarTipoUsuariosBO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultarTipoUsuariosDAO();
	}

	
	public UsuarioVO recuperarUsuariosBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.recuperarUsuariosDAO(usuarioVO);
	}

	
	public void excluirUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.existeRegistroPorIdUsuarioDAO(usuarioVO.getIdUsuario())){
			int resultado = usuarioDAO.excluirUsuarioDAO(usuarioVO);
			if(resultado == 1){
				System.out.println("\nUsuário excluído com Sucesso.");
			} else {
				System.out.println("\nNão foi possível excluir o Usuário.");
			}
		} else {
			System.out.println("\nUsuário não existe na base da dados.");
		}
	}


	public void atualizarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.existeRegistroPorIdUsuarioDAO(usuarioVO.getIdUsuario())){
			int resultado = usuarioDAO.atualizarUsuarioDAO(usuarioVO);
			if(resultado == 1){
				System.out.println("\nUsuário atualizado com Sucesso.");
			} else {
				System.out.println("\nNão foi possível atualizar o Usuário.");
			}
		} else {
			System.out.println("\nUsuário ainda não foi cadastrado.");
		}
	}


	public ArrayList<UsuarioVO> consultarUsuariosBO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ArrayList<UsuarioVO> usuariosVO = usuarioDAO.consultarTodosUsuariosDAO();
		if(usuariosVO.isEmpty()){
			System.out.println("\nLista de Usuários está vazia.");
		}
		return usuariosVO;
	}


	public UsuarioVO consultarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioVO usuario = usuarioDAO.consultarUsuarioDAO(usuarioVO);
		if(usuario == null){
			System.out.println("\nUsuário não Localizado.");
		}
		return usuario;
	}
	

}
