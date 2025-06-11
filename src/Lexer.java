package src;

import java.util.*;
import java.util.regex.*;

public class Lexer {
    private static final Pattern TOKEN_PATTERN = Pattern.compile(
            "\\b(inteiro|elgio|enquanto|se|inicio|fim|entao|senao)\\b|" + // RES
                    "_[a-zA-Z]\\w*|" +                                           // FUNC
                    "[a-zA-Z]\\w*|" +                                            // ID
                    "\\d+|" +                                                    // NUM
                    "[=+\\-*/()\\.,]|" +                                         // OP, PUNCT, PAR
                    "\\b(maior|menor|igual)\\b"                                  // REL
    );

    public List<Token> tokenizeLine(String line) {
        List<Token> tokens = new ArrayList<>();
        Matcher matcher = TOKEN_PATTERN.matcher(line);
        int currentIndex = 0;

        while (matcher.find()) {
            // Verifica se há trecho não reconhecido antes do próximo token
            if (matcher.start() > currentIndex) {
                String unknown = line.substring(currentIndex, matcher.start()).trim();
                if (!unknown.isEmpty()) {
                    throw new RuntimeException("Token desconhecido: '" + unknown + "' na posição " + currentIndex);
                }
            }
            String lexeme = matcher.group();

            if (lexeme.matches("inteiro|elgio|enquanto|se|inicio|fim|entao|senao"))
                tokens.add(new Token("RES", lexeme));
            else if (lexeme.matches("_[a-zA-Z]\\w*"))
                tokens.add(new Token("FUNC", lexeme));
            else if (lexeme.matches("[a-zA-Z]\\w*"))
                tokens.add(new Token("ID", lexeme));
            else if (lexeme.matches("\\d+"))
                tokens.add(new Token("NUM", lexeme));
            else if (lexeme.matches("[=+\\-*/]"))
                tokens.add(new Token("OP", lexeme));
            else if (lexeme.matches("\\.|,"))
                tokens.add(new Token("PUNCT", lexeme));
            else if (lexeme.matches("[()]") )
                tokens.add(new Token("PAR", lexeme));
            else if (lexeme.matches("maior|menor|igual"))
                tokens.add(new Token("REL", lexeme));
            currentIndex = matcher.end();
        }
        // Verifica se restou algum trecho não reconhecido ao final da linha
        if (currentIndex < line.length()) {
            String unknown = line.substring(currentIndex).trim();
            if (!unknown.isEmpty()) {
                throw new RuntimeException("Token desconhecido: '" + unknown + "' na posição " + currentIndex);
            }
        }
        return tokens;
    }
}
