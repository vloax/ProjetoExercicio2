package com.exercicio.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.exercicio.dao.CachorroDAO;
import com.exercicio.model.Cachorro;

public class Principal {

    public static int showMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1 - Listar");
        System.out.println("2 - Inserir");
        System.out.println("3 - Excluir");
        System.out.println("4 - Atualizar");
        System.out.println("5 - Sair");
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }

    public static void main(String[] args) {
        CachorroDAO dao = new CachorroDAO();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        do {
            try {
                showMenu();
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida!");
                continue;
            }

            switch (opcao) {
                case 1:
                    List<Cachorro> lista = dao.listar();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum cachorro cadastrado.");
                    } else {
                        for (Cachorro c : lista) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Raça: ");
                    String raca = scanner.nextLine();
                    System.out.print("Data de Nascimento (DD/MM/YYYY): ");
                    String dataStr = scanner.nextLine();
                    try {
                        LocalDate dataNascimento = LocalDate.parse(dataStr, formatter);
                        Cachorro cachorro = new Cachorro(nome, raca, dataNascimento);
                        dao.inserir(cachorro);
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de data inválido! Use DD/MM/YYYY.");
                    }
                    break;

                case 3:
                    System.out.print("Informe o ID do cachorro a ser excluído: ");
                    try {
                        int idExcluir = Integer.parseInt(scanner.nextLine());
                        dao.excluir(idExcluir);
                    } catch (NumberFormatException e) {
                        System.out.println("ID inválido!");
                    }
                    break;

                case 4:
                    System.out.print("Informe o ID do cachorro a ser atualizado: ");
                    try {
                        int idAtualizar = Integer.parseInt(scanner.nextLine());
                        System.out.print("Novo Nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Nova Raça: ");
                        String novaRaca = scanner.nextLine();
                        System.out.print("Nova Data de Nascimento (YYYY-MM-DD): ");
                        String novaDataStr = scanner.nextLine();
                        LocalDate novaData = LocalDate.parse(novaDataStr);
                        Cachorro cachorroAtualizado = new Cachorro(idAtualizar, novoNome, novaRaca, novaData);
                        dao.atualizar(cachorroAtualizado);
                    } catch (NumberFormatException | DateTimeParseException e) {
                        System.out.println("Entrada inválida!");
                    }
                    break;

                case 5:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção não reconhecida!");
            }

            System.out.println();
        } while (opcao != 5);

        scanner.close();
    }
}
