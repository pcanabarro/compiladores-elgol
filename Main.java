import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> codigoFonte = new ArrayList<>();

        System.out.println("Digite o código da linguagem Elgol linha por linha.");
        System.out.println("Deixe uma linha em branco para encerrar a entrada.");

        while (true) {
            System.out.print("> ");
            String linha = scanner.nextLine();

            if (linha.equalsIgnoreCase("")) {
                break;
            }

            codigoFonte.add(linha);
        }

        System.out.println("\nIniciando análise léxica...\n");

        LexicAnalyzer analisador = new LexicAnalyzer();

        for (String linha : codigoFonte) {
            analisador.analisarLinha(linha);
        }

        System.out.println("\nAnálise finalizada.");
        scanner.close();
    }
}
