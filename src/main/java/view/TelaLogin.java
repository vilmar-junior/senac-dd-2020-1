package view;

import java.util.Scanner;

import model.vo.UsuarioVO;

public class TelaLogin {
	
	Scanner teclado = new Scanner(System.in);
	
	private static final int OPCAO_LOGIN = 1;
	private static final int OPCAO_CADASTRAR_USUARIO = 2;
	private static final int OPCAO_MENU_SAIR = 9;

	public void apresentarTelaLogin() {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_SAIR){
			switch(opcao){
				case OPCAO_LOGIN: {
					this.realizarLogin();
					break;
				}
				case OPCAO_CADASTRAR_USUARIO: {
					this.criarNovoUsuario();
					break;
				}
				default: {
					System.out.println("\nOpção Inválida");
				}
			}
			opcao = this.apresentarOpcoesMenu();
		}
		
	}
	
	private void criarNovoUsuario() {
		MenuUsuario menuUsuario = new MenuUsuario();
		menuUsuario.criarNovoUsuario();
	}

	private void realizarLogin() {
		MenuUsuario menuUsuario = new MenuUsuario();
		UsuarioVO usuarioVO = menuUsuario.recuperarUsuario();
		if(usuarioVO.getIdUsuario() != 0) {
			Menu menu = new Menu();
			menu.apresentarMenu(usuarioVO);
		} else {
			System.out.println("Login ou Senha incorretos.\n");
		}
	}

	private int apresentarOpcoesMenu() {
		System.out.println("Sistema de Avisos - Tela de Login");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_LOGIN + " - Realizar Login");
		System.out.println(OPCAO_CADASTRAR_USUARIO + " - Novo Usuário");
		System.out.println(OPCAO_MENU_SAIR + " - Sair");
		System.out.print("\nDigite a Opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

}
