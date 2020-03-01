package view;

import java.util.ArrayList;
import java.util.Scanner;
import controller.ControladoraUsuario;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class MenuUsuario {
	
	Scanner teclado = new Scanner(System.in);
	
	private static final int OPCAO_MENU_CADASTRAR_USUARIO = 1;
	private static final int OPCAO_MENU_CONSULTAR_USUARIO = 2;
	private static final int OPCAO_MENU_ATUALIZAR_USUARIO = 3;
	private static final int OPCAO_MENU_EXCLUIR_USUARIO = 4;
	private static final int OPCAO_MENU_USUARIO_SAIR = 9;
	
	private static final int OPCAO_MENU_CONSULTAR_TODOS_USUARIOS = 1;
	private static final int OPCAO_MENU_CONSULTAR_UM_USUARIO = 2;
	private static final int OPCAO_MENU_CONSULTAR_USUARIO_SAIR = 9;
	
	public void apresentarMenuUsuario() {
		int opcao = apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_USUARIO_SAIR) {
			switch (opcao) {
				case OPCAO_MENU_CADASTRAR_USUARIO: {
					this.cadastrarUsuario();
					break;
				}
				case OPCAO_MENU_CONSULTAR_USUARIO: {
					this.consultarUsuario();
					break;
				}
				case OPCAO_MENU_ATUALIZAR_USUARIO: {
					this.atualizarUsuario();
					break;
				}
				case OPCAO_MENU_EXCLUIR_USUARIO: {
					this.excluirUsuario();
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
		System.out.println("\nSistema de Avisos \n-------- Menu Cadastro de Usuários --------");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_MENU_CADASTRAR_USUARIO + " - Cadastrar Usuário");
		System.out.println(OPCAO_MENU_CONSULTAR_USUARIO + " - Consultar Usuário");
		System.out.println(OPCAO_MENU_ATUALIZAR_USUARIO + " - Atualizar Usuário");
		System.out.println(OPCAO_MENU_EXCLUIR_USUARIO + " - Excluir Usuário");
		System.out.println(OPCAO_MENU_USUARIO_SAIR + " - Voltar");
		System.out.print("\nDigite a Opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private void cadastrarUsuario() {
		UsuarioVO usuarioVO = new UsuarioVO();
		System.out.print("\nDigite o nome do Usuário: ");
		usuarioVO.setNome(teclado.nextLine());
		System.out.print("Digite o cpf do Usuário: ");
		usuarioVO.setCpf(teclado.nextLine());
		System.out.print("Digite o e-mail do Usuário: ");
		usuarioVO.setEmail(teclado.nextLine());
		int opcao = this.apresentarOpcoesTipoUsuario();
		usuarioVO.setIdTipoUsuario(opcao);
		System.out.print("Digite o login do Usuário: ");
		usuarioVO.setLogin(teclado.nextLine());
		System.out.print("Digite a senha do Usuário: ");
		usuarioVO.setSenha(teclado.nextLine());
		
		ControladoraUsuario controladoraUsuario = new ControladoraUsuario();
		controladoraUsuario.cadastrarUsuarioController(usuarioVO);
	}

	private int apresentarOpcoesTipoUsuario() {
		ControladoraUsuario controladoraUsuario = new ControladoraUsuario();
		ArrayList<TipoUsuarioVO> tipoUsuariosVO = controladoraUsuario.consultarTipoUsuarios();
		System.out.println("\n-------- Tipos de Usuários --------");
		System.out.println("Opções:");
		for(int i = 0; i < tipoUsuariosVO.size(); i++) {
			System.out.println(tipoUsuariosVO.get(i).getIdTipoUsuario() + " - " + tipoUsuariosVO.get(i).getDescricao());
		}
		System.out.print("\nDigite a Opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

	private void excluirUsuario() {
		UsuarioVO usuarioVO = new UsuarioVO();
		System.out.print("\nInforme o código do Usuário: ");
		usuarioVO.setIdUsuario(Integer.parseInt(teclado.nextLine()));

		ControladoraUsuario controladoraUsuario = new ControladoraUsuario();
		controladoraUsuario.excluirUsuarioController(usuarioVO);
	}

	private void atualizarUsuario() {
		UsuarioVO usuarioVO = new UsuarioVO();
		System.out.print("\nInforme o código do Usuario: ");
		usuarioVO.setIdUsuario(Integer.parseInt(teclado.nextLine()));
		System.out.print("\nDigite o nome do Usuário: ");
		usuarioVO.setNome(teclado.nextLine());
		System.out.print("Digite o cpf do Usuário: ");
		usuarioVO.setCpf(teclado.nextLine());
		System.out.print("Digite o e-mail do Usuário: ");
		usuarioVO.setEmail(teclado.nextLine());
		int opcao = this.apresentarOpcoesTipoUsuario();
		usuarioVO.setIdTipoUsuario(opcao);
		System.out.print("Digite a senha do Usuário: ");
		usuarioVO.setSenha(teclado.nextLine());

		ControladoraUsuario controladoraUsuario = new ControladoraUsuario();
		controladoraUsuario.atualizarUsuarioController(usuarioVO);
	}

	private void consultarUsuario() {
		int opcao = this.apresentarOpcoesConsulta();
		ControladoraUsuario controladoraUsuario = new ControladoraUsuario();
		while (opcao != OPCAO_MENU_CONSULTAR_USUARIO_SAIR) {
			switch (opcao) {
				case OPCAO_MENU_CONSULTAR_TODOS_USUARIOS: {
					opcao = OPCAO_MENU_CONSULTAR_USUARIO_SAIR;
					ArrayList<UsuarioVO> listaUsuariosVO = controladoraUsuario.consultarTodosUsuariosController();
					System.out.print("\n--------- RESULTADO DA CONSULTA ---------");
					System.out.printf("\n%3s   %-40s   %-15s   %-15s  \n", "ID", "NOME", "CPF", "E-MAIL");
					for (int i = 0; i < listaUsuariosVO.size(); i++) {
						listaUsuariosVO.get(i).imprimir();
					}
					break;
				}
				case OPCAO_MENU_CONSULTAR_UM_USUARIO: {
					opcao = OPCAO_MENU_CONSULTAR_USUARIO_SAIR;
					UsuarioVO usuarioVO = new UsuarioVO();
					System.out.print("\nInforme o código do Usuário: ");
					usuarioVO.setIdUsuario(Integer.parseInt(teclado.nextLine()));
	
					UsuarioVO usuario = controladoraUsuario.consultarUsuarioController(usuarioVO);
					System.out.print("\n--------- RESULTADO DA CONSULTA ---------");
					System.out.printf("\n%3s   %-40s   %-15s   %-15s  \n", "ID", "NOME", "CPF", "E-MAIL");
					usuario.imprimir();
					break;
				}
				default: {
					System.out.println("\nOpção Inválida");
					opcao = this.apresentarOpcoesConsulta();
				}
			}
		}
	}
	
	private int apresentarOpcoesConsulta() {
		System.out.println("\nInforme o tipo de consulta a ser realizada");
		System.out.println(OPCAO_MENU_CONSULTAR_TODOS_USUARIOS + " - Consultar todos os Usuários");
		System.out.println(OPCAO_MENU_CONSULTAR_UM_USUARIO + " - Consultar um Usuário Específico");
		System.out.println(OPCAO_MENU_CONSULTAR_USUARIO_SAIR + " - Voltar");
		System.out.print("\nDigite a Opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	public void criarNovoUsuario() {
		this.cadastrarUsuario();
	}

	public UsuarioVO recuperarUsuario() {
		UsuarioVO usuarioVO = new UsuarioVO();
		System.out.print("Digite o login do Usuário: ");
		usuarioVO.setLogin(teclado.nextLine());
		System.out.print("Digite o senha do Usuário: ");
		usuarioVO.setSenha(teclado.nextLine());
		
		ControladoraUsuario controladoraUsuario = new ControladoraUsuario();
		return controladoraUsuario.recuperarUsuarioController(usuarioVO);
	}

}
