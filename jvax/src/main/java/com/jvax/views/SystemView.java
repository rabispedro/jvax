package com.jvax.views;

import java.util.Scanner;

import com.jvax.views.interfaces.IView;

public class SystemView implements IView{
	private final DoadorView doadorView;
	private final DoacaoView doacaoView;

	public SystemView(
		DoadorView doadorView,
		DoacaoView doacaoView) {

		this.doadorView = doadorView;
		this.doacaoView = doacaoView;
	}
	
	@Override
	public void menu() {
		try(var scanner = new Scanner(System.in); var scanner2 = new Scanner(System.in)) {
			Integer option;

			do {
				System.out.println("\t\tMENU GERAL");
				System.out.println("1) Doador");
				System.out.println("2) Doação");
				System.out.println("3) Sair");
				System.out.print("Digite sua opção: ");
				option = scanner.nextInt();

				switch (option) {
					case 1 -> this.doadorView.menu();
					case 2 -> this.doacaoView.menu();
					case 3 -> System.out.println("Obrigado por usar o Sistema de Controle de Doação de Sangue");
					default -> System.out.println("Opção inválida");
				}
			} while(option != 3);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Sistema com falha!");
			System.out.println(e.getMessage());
		}
	}
}
