package view;

import java.util.Scanner;

import model.vo.UsuarioVO;

public class MenuAviso {
	
	Scanner teclado = new Scanner(System.in);
	
	private static final int OPCAO_MENU_AVISO_COORDENACAO = 1;
	private static final int OPCAO_MENU_AVISO_ESTAGIO = 2;
	private static final int  OPCAO_MENU_AVISO_CURSOLIVRE = 3;
	private static final int OPCAO_MENU_AVISO_SAIR = 9;
	
	public void apresentarMenuAviso(UsuarioVO usuarioVO) {
		int opcao = apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_AVISO_SAIR) {
			switch (opcao) {
				case OPCAO_MENU_AVISO_COORDENACAO: {
					MenuAvisoCoordenacao menuAvisoCoordenacao = new MenuAvisoCoordenacao();
					menuAvisoCoordenacao.apresentarMenuAvisoCoordenacao(usuarioVO);
					break;
				}
				case OPCAO_MENU_AVISO_ESTAGIO: {
					MenuAvisoEstagio menuAvisoEstagio = new MenuAvisoEstagio();
					menuAvisoEstagio.apresentarMenuAvisoEstagio(usuarioVO);
					break;
				}
				case OPCAO_MENU_AVISO_CURSOLIVRE: {
					MenuAvisoCursoLivre menuAvisoCursoLivre = new MenuAvisoCursoLivre();
					menuAvisoCursoLivre.apresentarMenuAvisoCursoLivre(usuarioVO);
					break;
				}
				default: {
					System.out.println("\nOpção Inválida");
				}
			}
			opcao = apresentarOpcoesMenu();
		}
	}
	
	
	private int apresentarOpcoesMenu() {
		System.out.println("\nSistema de Avisos - Menu Avisos");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_MENU_AVISO_COORDENACAO + " - Avisos da Coordenação");
		System.out.println(OPCAO_MENU_AVISO_ESTAGIO + " - Avisos de Estágio");
		System.out.println(OPCAO_MENU_AVISO_CURSOLIVRE + " - Avisos de Curso Livre");
		System.out.println(OPCAO_MENU_AVISO_SAIR + " - Sair");
		System.out.print("\nDigite a Opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

}
