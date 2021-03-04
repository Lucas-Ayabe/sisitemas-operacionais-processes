package view;

import controller.RedesController;
import java.util.Scanner;
import services.ConsoleReader;

public class Main {

    public static void main(String[] args) {
        var reader = new ConsoleReader(new Scanner(System.in));
        var controller = new RedesController();
        var operationalSystem = System.getProperty("os.name");
        var option = 0;
        var optionMessage =
            "Menu\n0 - Sair;\n1 - hosts ethernet;\n2 - ping\nDigite aqui ==> ";

        do {
            option = reader.askToInt(optionMessage);
            System.out.println("\n========\n");
            switch (option) {
                case 0:
                    System.out.println("Encerrando aplicação...");
                    break;
                case 1:
                    controller.ip(operationalSystem);
                    break;
                case 2:
                    controller.ping(operationalSystem);
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente :(");
                    break;
            }
            System.out.println("\n========\n1");
        } while (option != 0);
    }
}
