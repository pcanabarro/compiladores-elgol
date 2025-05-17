import java.util.regex.*;
import java.util.*;

public class LexicAnalyzer {

    private static final Set<String> PALAVRAS_RESERVADAS = new HashSet<>(Arrays.asList(
            "elgio", "inteiro", "zero", "comp", "enquanto", "se", "entao", "senao",
            "inicio", "fim", "maior", "menor", "igual", "diferente"
    ));

    private static final Pattern PADRAO_TOKEN = Pattern.compile(
            "(#.*)|" +                                     // Comentário
            "(_[A-Z][a-zA-Z]{2,})|" +                      // Nome de função
            "([A-Z][a-zA-Z]{2,})|" +                       // Identificador
            "([1-9][0-9]*)|" +                             // Número inteiro (sem 0 à esquerda)
            "(zero)|" +                                    // Representação especial de zero
            "(comp)|" +                                    // Operador comp
            "(elgio|inteiro|enquanto|se|entao|senao|inicio|fim|maior|menor|igual|diferente)|" + // Palavras-chave
            "(=|\\+|\\-|x|/)|" +                           // Operadores
            "(\\(|\\))|" +                                 // Parênteses
            "(\\.)"                                        // Fim de comando
    );

    public void analisarLinha(String linha) {
        // Remove comentários
        linha = linha.split("#", 2)[0].trim();

        if (linha.isEmpty()) return;

        Matcher matcher = PADRAO_TOKEN.matcher(linha);

        System.out.println("Analisando linha: \"" + linha + "\"");
        int pos = 0;
        while (matcher.find()) {
            String token = matcher.group();

            // Skip espaços entre tokens
            if (matcher.start() > pos) {
                String erro = linha.substring(pos, matcher.start()).trim();
                if (!erro.isEmpty()) {
                    System.out.println("TOKEN INVÁLIDO: \"" + erro + "\"");
                }
            }

            if (token.startsWith("#")) {
                break; // ignora o resto da linha
            } else if (PALAVRAS_RESERVADAS.contains(token)) {
                System.out.println("PALAVRA-RESERVADA: " + token);
            } else if (token.equals("zero")) {
                System.out.println("OPERADOR: zero");
            } else if (token.equals("comp")) {
                System.out.println("OPERADOR: comp");
            } else if (token.matches("_[A-Z][a-zA-Z]{2,}")) {
                System.out.println("ID_FUNCAO: " + token);
            } else if (token.matches("[A-Z][a-zA-Z]{2,}")) {
                System.out.println("IDENTIFICADOR: " + token);
            } else if (token.matches("[1-9][0-9]*")) {
                System.out.println("NUMERO: " + token);
            } else if (token.matches("[=\\+\\-x/]")) {
                System.out.println("OPERADOR: " + token);
            } else if (token.matches("[()]")) {
                System.out.println("PARENTESE: " + token);
            } else if (token.equals(".")) {
                System.out.println("FIM_COMANDO: .");
            }

            pos = matcher.end();
        }

        // Checa por tokens inválidos no final da linha
        if (pos < linha.length()) {
            String erro = linha.substring(pos).trim();
            if (!erro.isEmpty()) {
                System.out.println("TOKEN INVÁLIDO: \"" + erro + "\"");
            }
        }
    }
}
