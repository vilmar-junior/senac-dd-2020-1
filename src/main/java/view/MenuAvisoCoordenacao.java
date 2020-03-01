package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import controller.ControladoraAvisoCoordenacao;
import model.vo.AvisoCoordenacaoVO;
import model.vo.UsuarioVO;

public class MenuAvisoCoordenacao {
	
	Scanner teclado = new Scanner(System.in);
	DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private static final int OPCAO_MENU_CONSULTAR_AVISO_COORDENACAO = 1;
	private static final int OPCAO_MENU_CADASTRAR_AVISO_COORDENACAO = 2;
	private static final int OPCAO_MENU_ATUALIZAR_AVISO_COORDENACAO = 3;
	private static final int OPCAO_MENU_EXCLUIR_AVISO_COORDENACAO = 4;
	private static final int OPCAO_MENU_AVISO_COORDENACAO_SAIR = 9;
	
	private static final int OPCAO_MENU_CONSULTAR_TODOS_AVISOS_COORDENACAO = 1;
	private static final int OPCAO_MENU_CONSULTAR_UM_AVISO_COORDENACAO = 2;
	private static final int OPCAO_MENU_CONSULTAR_AVISO_COORDENACAO_SAIR = 9;

	public void apresentarMenuAvisoCoordenacao(UsuarioVO usuarioVO) {
		int opcao = apresentarOpcoesMenu(usuarioVO.getIdTipoUsuario());
		while (opcao != OPCAO_MENU_AVISO_COORDENACAO_SAIR) {
			switch (opcao) {
				case OPCAO_MENU_CADASTRAR_AVISO_COORDENACAO: {
					this.cadastrarAvisoCoordenacao(usuarioVO);
					break;
				}
				case OPCAO_MENU_CONSULTAR_AVISO_COORDENACAO: {
					this.consultarAvisoCoordenacao();
					break;
				}
				case OPCAO_MENU_ATUALIZAR_AVISO_COORDENACAO: {
					this.atualizarAvisoCoordenacao(usuarioVO);
					break;
				}
				case OPCAO_MENU_EXCLUIR_AVISO_COORDENACAO: {
					this.excluirAvisoCoordenacao();
					break;
				}
				default: {
					System.out.println("\nOpção Inválida");
				}
			}
			opcao = apresentarOpcoesMenu(usuarioVO.getIdTipoUsuario());
		}
	}
	
	private int apresentarOpcoesMenu(int tipoUsuario) {
		System.out.println("\nSistema de Avisos \n-------- Menu Avisos da Coordenação --------");
		System.out.println("\nOpções:");
		System.out.println(OPCAO_MENU_CONSULTAR_AVISO_COORDENACAO + " - Consultar Avisos da Coordenação");
		System.out.println(OPCAO_MENU_CADASTRAR_AVISO_COORDENACAO + " - Cadastrar Avisos da Coordenação");
		System.out.println(OPCAO_MENU_ATUALIZAR_AVISO_COORDENACAO + " - Atualizar Avisos da Coordenação");
		System.out.println(OPCAO_MENU_EXCLUIR_AVISO_COORDENACAO + " - Excluir Avisos da Coordenação");
		System.out.println(OPCAO_MENU_AVISO_COORDENACAO_SAIR + " - Voltar");
		System.out.print("\nDigite a Opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private void cadastrarAvisoCoordenacao(UsuarioVO usuarioVO) {
		AvisoCoordenacaoVO avisoCoordenacaoVO = new AvisoCoordenacaoVO();
		avisoCoordenacaoVO.setIdUsuario(usuarioVO.getIdUsuario());
		System.out.print("\nDigite o Aviso da Coordenação: ");
		avisoCoordenacaoVO.setDescricao(teclado.nextLine());
		System.out.print("Digite a data de cadastro do Aviso da Coordenação: ");
		avisoCoordenacaoVO.setDataCadastro(LocalDate.parse(teclado.nextLine(), dataFormatter));
		System.out.print("Digite a data de expiração do Aviso da Coordenação: ");
		avisoCoordenacaoVO.setDataExpiracao(LocalDate.parse(teclado.nextLine(), dataFormatter));
		
		ControladoraAvisoCoordenacao controladoraAvisoCoordenacao = new ControladoraAvisoCoordenacao();
		controladoraAvisoCoordenacao.cadastrarAvisoCoordenacaoController(avisoCoordenacaoVO);
	}
	
	private void excluirAvisoCoordenacao() {
		AvisoCoordenacaoVO avisoCoordenacaoVO = new AvisoCoordenacaoVO();
		System.out.print("\nInforme o código do Aviso: ");
		avisoCoordenacaoVO.setIdAviso(Integer.parseInt(teclado.nextLine()));
		System.out.print("\nInforme o código do Aviso da Coordenacao: ");
		avisoCoordenacaoVO.setIdAvisoCoordenacao(Integer.parseInt(teclado.nextLine()));

		ControladoraAvisoCoordenacao controladoraAvisoCoordenacao = new ControladoraAvisoCoordenacao();
		controladoraAvisoCoordenacao.excluirAvisoCoordenacaoController(avisoCoordenacaoVO);
	}

	private void atualizarAvisoCoordenacao(UsuarioVO usuarioVO) {
		AvisoCoordenacaoVO avisoCoordenacaoVO = new AvisoCoordenacaoVO();
		avisoCoordenacaoVO.setIdUsuario(usuarioVO.getIdUsuario());
		System.out.print("\nInforme o código do Aviso: ");
		avisoCoordenacaoVO.setIdAviso(Integer.parseInt(teclado.nextLine()));
		System.out.print("\nInforme o código do Aviso da Coordenacao: ");
		avisoCoordenacaoVO.setIdAvisoCoordenacao(Integer.parseInt(teclado.nextLine()));
		System.out.print("\nDigite o Aviso da Coordenação: ");
		avisoCoordenacaoVO.setDescricao(teclado.nextLine());
		System.out.print("\nDigite a data de cadastro do Aviso da Coordenação: ");
		avisoCoordenacaoVO.setDataCadastro(LocalDate.parse(teclado.nextLine(), dataFormatter));
		System.out.print("\nDigite a data de expiração do Aviso da Coordenação: ");
		avisoCoordenacaoVO.setDataExpiracao(LocalDate.parse(teclado.nextLine(), dataFormatter));

		ControladoraAvisoCoordenacao controladoraAvisoCoordenacao = new ControladoraAvisoCoordenacao();
		controladoraAvisoCoordenacao.atualizarAvisoCoordenacaoController(avisoCoordenacaoVO);
	}

	private void consultarAvisoCoordenacao() {
		int opcao = this.apresentarOpcoesConsulta();
		ControladoraAvisoCoordenacao controladoraAvisoCoordenacao = new ControladoraAvisoCoordenacao();
		while (opcao != OPCAO_MENU_CONSULTAR_AVISO_COORDENACAO_SAIR) {
			switch (opcao) {
				case OPCAO_MENU_CONSULTAR_TODOS_AVISOS_COORDENACAO: {
					opcao = OPCAO_MENU_CONSULTAR_AVISO_COORDENACAO_SAIR;
					ArrayList<AvisoCoordenacaoVO> listaAvisosCoordenacaoVO = controladoraAvisoCoordenacao.consultarTodosAvisosCoordenacaoController();
					System.out.print("\n--------- RESULTADO DA CONSULTA ---------");
					System.out.printf("\n%8s   %15s   %-50s   %-15s   %-15s  \n", "ID AVISO", "ID AVISO COORD.", "DESCRIÇÃO", "DATA CADASTRO", "DATA EXPIRAÇÃO");
					for (int i = 0; i < listaAvisosCoordenacaoVO.size(); i++) {
						listaAvisosCoordenacaoVO.get(i).imprimir();
					}
					break;
				}
				case OPCAO_MENU_CONSULTAR_UM_AVISO_COORDENACAO: {
					opcao = OPCAO_MENU_CONSULTAR_AVISO_COORDENACAO_SAIR;
					AvisoCoordenacaoVO avisoCoordenacaoVO = new AvisoCoordenacaoVO();
					System.out.print("\nInforme o código do Aviso: ");
					avisoCoordenacaoVO.setIdAviso(Integer.parseInt(teclado.nextLine()));
					System.out.print("\nInforme o código do Aviso da Coordenação: ");
					avisoCoordenacaoVO.setIdAvisoCoordenacao(Integer.parseInt(teclado.nextLine()));
	
					AvisoCoordenacaoVO avisoCoordenacao = controladoraAvisoCoordenacao.consultarAvisoCoordenacaoController(avisoCoordenacaoVO);
					System.out.print("\n--------- RESULTADO DA CONSULTA ---------");
					System.out.printf("\n%8s   %15s   %-50s   %-15s   %-15s  \n", "ID AVISO", "ID AVISO COORD.", "DESCRIÇÃO", "DATA CADASTRO", "DATA EXPIRAÇÃO");
					avisoCoordenacao.imprimir();
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
		System.out.println(OPCAO_MENU_CONSULTAR_TODOS_AVISOS_COORDENACAO + " - Consultar todos os Avisos da Coordenação");
		System.out.println(OPCAO_MENU_CONSULTAR_UM_AVISO_COORDENACAO + " - Consultar um Aviso da Coordenação Específico");
		System.out.println(OPCAO_MENU_CONSULTAR_AVISO_COORDENACAO_SAIR + " - Voltar");
		System.out.print("\nDigite a Opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

}
