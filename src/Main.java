package src;

import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Path.of("code.elg"));

            Lexer lexer = new Lexer();
            Parser parser = new Parser();

            List<List<Token>> allTokenLines = new ArrayList<>();
            for (String line : lines) {
                List<Token> tokens = lexer.tokenizeLine(line);
                allTokenLines.add(tokens);
            }

            parser.analyzeCode(allTokenLines);

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
