package com.jvax.views;

import java.util.Scanner;

import com.jvax.controllers.DoadorController;
import com.jvax.enums.RH;
import com.jvax.enums.Situacao;
import com.jvax.enums.TipoSanguineo;
import com.jvax.models.DoadorModel;
import com.jvax.views.interfaces.IView;

public class DoadorView implements IView {
	private final DoadorController doadorController;

	public DoadorView(DoadorController doadorController) {
		this.doadorController = doadorController;
	}

	@Override
	public void menu() {
		try(var scanner = new Scanner(System.in)) {
			Integer option;

			do {
				System.out.println("\t\tMENU DO DOADOR");
				System.out.println("1) Cadastrar novo doador");
				System.out.println("2) Pesquisar um doador");
				System.out.println("3) Alterar um doador");
				System.out.println("4) Remover um doador");
				System.out.println("5) Voltar");
				System.out.print("Digite sua opção: ");
				option = scanner.nextInt();

				switch (option) {
					case 1 -> handleCadastro();
					case 2 -> handlePesquisa();
					case 3 -> handleAlteracao();
					case 4 -> handleRemocao();
					case 5 -> System.out.println("Voltando...");
					default -> System.out.println("Opção inválida");
				}
			} while(option != 5);
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println("Sistema de Doador com falha");
			System.out.println(e.getMessage());
		}
	}

	private void handleCadastro() {
		try(var scanner = new Scanner(System.in)) {
			System.out.print("Digite um nome: ");
			String nome = scanner.nextLine();
			
			System.out.print("Digite o cpf: ");
			String cpf = scanner.next();

			System.out.print("Digite o contato: ");
			String contato = scanner.next();

			System.out.print("Digite a situação (");
			for(var value : Situacao.values()) {
				System.out.print(value.getDescricao() + " ");
			}
			System.out.print("): ");
			Situacao situacao = Situacao.toEnum(scanner.next());

			System.out.print("Digite o tipo sanguíneo (");
			for(var value : TipoSanguineo.values()) {
				System.out.print(value.getDescricao() + " ");
			}
			System.out.print("): ");
			TipoSanguineo tipoSanguineo = TipoSanguineo.toEnum(scanner.next());

			System.out.print("Digite o rh (");
			for(var value : RH.values()) {
				System.out.print(value.getDescricao() + " ");
			}
			System.out.print("): ");
			RH rh = RH.toEnum(scanner.next());

			var doador = new DoadorModel(nome, cpf, contato, situacao, tipoSanguineo, rh);
			var novoDoador = this.doadorController.cadastro(doador);
			System.out.println("Novo Doador: " + novoDoador);

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
				System.out.println("\tPESQUISA DE DOADOR");
				System.out.println("1) Pesquisa por código");
				System.out.println("2) Pesquisa por parte do nome");
				System.out.println("3) Pesquisa por CPF");
				System.out.println("4) Voltar");
				System.out.print("Digite sua opção: ");
				option = scanner.nextInt();

				switch (option) {
					case 1 -> handlePesquisaPorCodigo();
					case 2 -> handlePesquisaPorNome();
					case 3 -> handlePesquisaPorCpf();
					case 4 -> System.out.println("Voltando...");
					default -> System.out.println("Opção inválida");
				}
			} while(option < 1 || option > 4 );

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handlePesquisa com falha");
			System.out.println(e.getMessage());
		}
	}

	private void handlePesquisaPorCodigo() {
		try(var scanner = new Scanner(System.in)) {
			System.out.print("Digite o código: ");
			Long codigo = scanner.nextLong();

			var doador = this.doadorController.pesquisaPorCodigo(codigo);
			System.out.println("Doador: " + doador);

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handlePesquisaPorCodigo com falha");
			System.out.println(e.getMessage());
		}
	}

	private void handlePesquisaPorNome() {
		try(var scanner = new Scanner(System.in)) {
			System.out.print("Digite parte do nome: ");
			String nome = scanner.nextLine();

			var doadores = this.doadorController.pesquisaPorNome(nome);
			System.out.println("Doador(es): " + doadores);

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handlePesquisaPorNome com falha");
			System.out.println(e.getMessage());
		}
	}

	private void handlePesquisaPorCpf() {
		try(var scanner = new Scanner(System.in)) {
			System.out.print("Digite o cpf: ");
			String cpf = scanner.nextLine();

			var doador = this.doadorController.pesquisaPorCpf(cpf);
			System.out.println("Doador: " + doador);

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handlePesquisaPorCpf com falha");
			System.out.println(e.getMessage());
		}
	}

	private void handleAlteracao() {
		try(var scanner = new Scanner(System.in)) {
			System.out.print("Digite o código do doador: ");
			Long codigo = scanner.nextLong();

			System.out.print("Digite um nome: ");
			String nome = scanner.nextLine();
			
			System.out.print("Digite o cpf: ");
			String cpf = scanner.next();

			System.out.print("Digite o contato: ");
			String contato = scanner.next();

			System.out.print("Digite a situação (");
			for(var value : Situacao.values()) {
				System.out.print(value.getDescricao() + " ");
			}
			System.out.print("): ");
			Situacao situacao = Situacao.toEnum(scanner.next());

			System.out.print("Digite o tipo sanguíneo (");
			for(var value : TipoSanguineo.values()) {
				System.out.print(value.getDescricao() + " ");
			}
			System.out.print("): ");
			TipoSanguineo tipoSanguineo = TipoSanguineo.toEnum(scanner.next());

			System.out.print("Digite o rh (");
			for(var value : RH.values()) {
				System.out.print(value.getDescricao() + " ");
			}
			System.out.print("): ");
			RH rh = RH.toEnum(scanner.next());

			var doador = new DoadorModel(nome, cpf, contato, situacao, tipoSanguineo, rh);
			
			var doadorAlterado = this.doadorController.alteracao(codigo, doador);
			System.out.println("Doador alterado: " + doadorAlterado);
			
		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handleAlteracao com falha");
			System.out.println(e.getMessage());
		}
	}
	
	private void handleRemocao() {
		try(var scanner = new Scanner(System.in)) {
			System.out.print("Digite o código do doador: ");
			Long codigo = scanner.nextLong();

			var deletado = this.doadorController.remocao(codigo);
			if (deletado) {
				System.out.println("Doador deletado");
			} else {
				System.out.println("O sistema não pode deletar o doador");
			}

		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("handlePesquisaPorCodigo com falha");
			System.out.println(e.getMessage());
		}
	}
}
