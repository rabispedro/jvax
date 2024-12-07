package com.jvax.views;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import com.jvax.controllers.DoacaoController;
import com.jvax.enums.Situacao;
import com.jvax.models.DoacaoModel;
import com.jvax.models.DoadorModel;
import com.jvax.views.interfaces.IView;

public class DoacaoView implements IView {
	private final DoacaoController doacaoController;

	public DoacaoView(DoacaoController doacaoController) {
		this.doacaoController = doacaoController;
	}

	@Override
	public void menu() {
		try(var scanner = new Scanner(System.in); var scanner2 = new Scanner(System.in)) {
			Integer option;

			do {
				System.out.println("\t\tMENU DA DOAÇÃO");
				System.out.println("1) Cadastrar nova doação");
				System.out.println("2) Pesquisar uma doação");
				System.out.println("3) Voltar");
				System.out.print("Digite sua opção: ");
				option = scanner.nextInt();

				switch (option) {
					case 1 -> handleCadastro();
					case 2 -> handlePesquisa();
					case 3 -> System.out.println("Voltando...");
					default -> System.out.println("Opção inválida");
				}
			} while(option != 3);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Sistema de Doador com falha!");
			System.out.println(e.getMessage());
		}
	}

private void handleCadastro() {
		try(var scanner = new Scanner(System.in)) {
			System.out.print("Digite uma data: ");
			LocalDate data = LocalDate.parse(scanner.next());
			
			System.out.print("Digite uma hora: ");
			LocalTime hora = LocalTime.parse(scanner.next());

			System.out.print("Digite um volume: ");
			double volume = scanner.nextDouble();

			System.out.print("Digite a situação ( ");
			for(var value : Situacao.values()) {
				System.out.print(value.getDescricao() + " ");
			}
			System.out.print("): ");
			Situacao situacao = Situacao.toEnum(scanner.next());

			System.out.print("Digite o código do doador: ");
			Long codigoDoador = scanner.nextLong();

			var doador = new DoadorModel();
			doador.setCodigo(codigoDoador);
			var doacao = new DoacaoModel(data, hora, volume, situacao, doador);
		
			var novaDoacao = this.doacaoController.cadastro(doacao);
			System.out.println("Nova Doação: " + novaDoacao);

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handleCadastro com falha");
			System.out.println(e.getMessage());
		}
	}

	private void handlePesquisa() {
		try(var scanner = new Scanner(System.in)) {
			Integer option;

			do {
				System.out.println("\tPESQUISA DE DOAÇÃO");
				System.out.println("1) Pesquisa por código do doador");
				System.out.println("2) Pesquisa por parte do nome do doador");
				System.out.println("3) Pesquisa por cpf do doador");
				System.out.println("4) Pesquisa por código da doação");
				System.out.println("5) Pesquisa pela data da doação");
				System.out.println("6) Voltar");
				System.out.print("Digite sua opção: ");
				option = scanner.nextInt();

				switch (option) {
					case 1 -> handlePesquisaPorCodigoDoador();
					case 2 -> handlePesquisaPorNomeDoador();
					case 3 -> handlePesquisaPorCpfDoador();
					case 4 -> handlePesquisaPorCodigo();
					case 5 -> handlePesquisaPorData();
					case 6 -> System.out.println("Voltando...");
					default -> System.out.println("Opção inválida");
				}
			} while(option < 1 || option > 6 );

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handlePesquisa com falha");
			System.out.println(e.getMessage());
		}
	}

	private void handlePesquisaPorCodigoDoador() {
		try(var scanner = new Scanner(System.in)) {
			System.out.print("Digite o código do doador: ");
			Long codigo = scanner.nextLong();

			var doacoes = this.doacaoController.pesquisaPorCodigoDoador(codigo);
			System.out.println("Doação(ões): " + doacoes);

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handlePesquisaPorCodigoDoador com falha");
			System.out.println(e.getMessage());
		}
	}

	private void handlePesquisaPorNomeDoador() {
		try(var scanner = new Scanner(System.in)) {
			System.out.print("Digite parte do nome do doador: ");
			String nome = scanner.nextLine();

			var doacoes = this.doacaoController.pesquisaPorNomeDoador(nome);
			System.out.println("Doação(ões): " + doacoes);

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handlePesquisaPorNomeDoador com falha");
			System.out.println(e.getMessage());
		}
	}

	private void handlePesquisaPorCpfDoador() {
		try(var scanner = new Scanner(System.in)) {
			System.out.print("Digite o cpf do doador: ");
			String cpf = scanner.nextLine();

			var doacoes = this.doacaoController.pesquisaPorCpfDoador(cpf);
			System.out.println("Doação(ões): " + doacoes);

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handlePesquisaPorCpfDoador com falha");
			System.out.println(e.getMessage());
		}
	}

	private void handlePesquisaPorCodigo() {
		try(var scanner = new Scanner(System.in)) {
			System.out.print("Digite o código: ");
			Long codigo = scanner.nextLong();

			var doacao = this.doacaoController.pesquisaPorCodigo(codigo);
			System.out.println("Doação: " + doacao);

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handlePesquisaPorCodigo com falha");
			System.out.println(e.getMessage());
		}
	}

	
	private void handlePesquisaPorData() {
		try(var scanner = new Scanner(System.in)) {
			Integer option;

			do {
				System.out.println("\tPESQUISA DE DOAÇÃO POR DATA");
				System.out.println("1) Pesquisa por data inicial");
				System.out.println("2) Pesquisa por data inicial e data final");
				System.out.println("3) Pesquisa por data final");
				System.out.println("4) Voltar");
				System.out.print("Digite sua opção: ");
				option = scanner.nextInt();

				switch (option) {
					case 1 -> handlePesquisaPorDataInicial();
					case 2 -> handlePesquisaPorDataInicialEFinal();
					case 3 -> handlePesquisaPorDataFinal();
					case 4 -> System.out.println("Voltando...");
					default -> System.out.println("Opção inválida");
				}
			} while(option < 1 || option > 4 );

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handlePesquisaPorData com falha");
			System.out.println(e.getMessage());
		}
	}

	private void handlePesquisaPorDataInicial() {
		try(var scanner = new Scanner(System.in)) {
			System.out.print("Digite a data inicial: ");
			LocalDate inicio = LocalDate.parse(scanner.next());

			var doacoes = this.doacaoController.pesquisaPorDataInicial(inicio);
			System.out.println("Doação(ões): " + doacoes);

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handlePesquisaPorDataInicial com falha");
			System.out.println(e.getMessage());
		}
	}

	private void handlePesquisaPorDataInicialEFinal() {
		try(var scanner = new Scanner(System.in)) {
			System.out.print("Digite a data inicial: ");
			LocalDate inicio = LocalDate.parse(scanner.next());

			System.out.print("Digite a data final: ");
			LocalDate fim = LocalDate.parse(scanner.next());

			var doacoes = this.doacaoController.pesquisaPorDataInicialEFinal(inicio, fim);
			System.out.println("Doação(ões): " + doacoes);

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handlePesquisaPorDataInicialEFinal com falha");
			System.out.println(e.getMessage());
		}
	}

	private void handlePesquisaPorDataFinal() {
		try(var scanner = new Scanner(System.in)) {
			System.out.print("Digite a data final: ");
			LocalDate fim = LocalDate.parse(scanner.next());

			var doacoes = this.doacaoController.pesquisaPorDataFinal(fim);
			System.out.println("Doação(ões): " + doacoes);

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handlePesquisaPorDataFinal com falha");
			System.out.println(e.getMessage());
		}
	}
}
