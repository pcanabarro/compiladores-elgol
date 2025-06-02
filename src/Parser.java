package src;

import java.util.List;

public class Parser {

    public void analyzeCode(List<List<Token>> codeLines) {
        int lineNumber = 1;
        for (List<Token> line : codeLines) {
            System.out.println("Analyzing line " + lineNumber + ": " + line);
            if (!analyzeLine(line)) {
                System.out.println("Syntax error on line " + lineNumber + ": " + line);
            }
            lineNumber++;
        }
    }

    public boolean analyzeLine(List<Token> tokens) {
        if (tokens.isEmpty()) return true;

        Token first = tokens.get(0);

        return switch (first.getType()) {
            case "ID" -> analyzeAssignment(tokens);
            case "RES" -> analyzeReservedCommand(tokens);
            default -> false;
        };
    }

    private boolean analyzeAssignment(List<Token> tokens) {
        if (tokens.size() < 4) return false;
        if (!tokens.get(1).getLexeme().equals("=")) return false;
        if (!tokens.get(tokens.size() - 1).getLexeme().equals(".")) return false;
        return true;
    }

    private boolean analyzeReservedCommand(List<Token> tokens) {
        String command = tokens.get(0).getLexeme();

        return switch (command) {
            case "inteiro" -> analyzeDeclarationOrFunction(tokens);
            case "elgio" -> analyzeFunctionReturn(tokens);
            case "enquanto" -> analyzeWhile(tokens);
            case "se" -> analyzeIf(tokens);
            case "inicio", "fim", "entao", "senao" -> true;
            default -> false;
        };
    }

    private boolean analyzeDeclarationOrFunction(List<Token> tokens) {
        if (tokens.size() < 3) return false;

        Token second = tokens.get(1);
        if (second.getType().equals("FUNC")) {
            return analyzeFunctionDefinition(tokens);
        } else {
            return analyzeVariableDeclaration(tokens);
        }
    }

    private boolean analyzeVariableDeclaration(List<Token> tokens) {
        if (!tokens.get(tokens.size() - 1).getLexeme().equals(".")) return false;

        for (int i = 1; i < tokens.size() - 1; i += 2) {
            if (!tokens.get(i).getType().equals("ID")) return false;
            if (i + 1 < tokens.size() - 1 && !tokens.get(i + 1).getLexeme().equals(",")) return false;
        }

        return true;
    }

    private boolean analyzeFunctionDefinition(List<Token> tokens) {
        if (!tokens.get(2).getLexeme().equals("(")) return false;

        int i = 3;
        while (i < tokens.size() - 2) {
            if (!tokens.get(i).getLexeme().equals("inteiro")) return false;
            if (!tokens.get(i + 1).getType().equals("ID")) return false;
            i += 2;
            if (tokens.get(i).getLexeme().equals(",")) {
                i++;
            } else {
                break;
            }
        }

        return tokens.get(i).getLexeme().equals(")") &&
                tokens.get(i + 1).getLexeme().equals(".");
    }

    private boolean analyzeFunctionReturn(List<Token> tokens) {
        for (Token t : tokens) {
            if (t.getType().equals("FUNC")) return false;
        }

        return tokens.get(1).getLexeme().equals("=") &&
                tokens.get(tokens.size() - 1).getLexeme().equals(".");
    }

    private boolean analyzeWhile(List<Token> tokens) {
        if (tokens.size() != 5) return false;
        if (!tokens.get(2).getType().equals("REL")) return false;
        if (!tokens.get(4).getLexeme().equals(".")) return false;

        if (tokens.get(1).getType().equals("FUNC") || tokens.get(3).getType().equals("FUNC"))
            return false;

        return true;
    }

    private boolean analyzeIf(List<Token> tokens) {
        return analyzeWhile(tokens);
    }
}
