package view;

import java.util.Scanner;

import model.vo.UsuarioVO;

public class Menu {
	
	Scanner teclado = new Scanner(System.in);
	
	private static final int OPCAO_MENU_AVISOS = 1;
	private static final int OPCAO_MENU_RELATORIO = 2;
	private static final int OPCAO_MENU_USUARIO = 3;
	private static final int OPCAO_MENU_SAIR = 9;
	
	public void apresentarMenu(UsuarioVO usuarioVO) {
		int opcao = this.apresentarOpcoesMenu(usuarioVO.getIdTipoUsuario());
		while (opcao != OPCAO_MENU_SAIR){
			switch(opcao){
				case OPCAO_MENU_AVISOS: {
					MenuAviso menuAviso = new MenuAviso();
					menuAviso.apresentarMenuAviso(usuarioVO);
					break;
				}
				case OPCAO_MENU_USUARIO: {
					MenuUsuario menuUsuario = new MenuUsuario();
					menuUsuario.apresentarMenuUsuario();
					break;
				}
				case OPCAO_MENU_RELATORIO: {
					MenuRelatorio menuRelatorio = new MenuRelatorio();
					menuRelatorio.apresentarMenuRelatorio();
					break;
				}
				default: {
					System.out.println("\nOpção Inválida");
				}
			}
			opcao = this.apresentarOpcoesMenu(usuarioVO.getIdTipoUsuario());
		}
	}
	
	private int apresentarOpcoesMenu(int tipoUsuario) {
		System.out.println("\nSistema de Avisos - Menu Principal");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_MENU_AVISOS + " - Avisos");
		if(tipoUsuario != 4) {
			System.out.println(OPCAO_MENU_RELATORIO + " - Relatório");
		}
		if(tipoUsuario == 1 || tipoUsuario == 2) {
			System.out.println(OPCAO_MENU_USUARIO + " - Usuário");
		}
		System.out.println(OPCAO_MENU_SAIR + " - Sair");
		System.out.print("\nDigite a Opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

}
