import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AssemblyTokenizer {

    // Hardcoded optab
    static final Map<String, String[]> optab = new HashMap<>() {{
        put("ADD", new String[]{"01", "IS"});
        put("SUB", new String[]{"02", "IS"});
        put("MUL", new String[]{"03", "IS"});
        put("DIV", new String[]{"04", "IS"});
        put("BC", new String[]{"05", "IS"});
        put("READ", new String[]{"06", "IS"});
        put("PRINT", new String[]{"07", "IS"});
        put("MOVER", new String[]{"08", "IS"});
        put("MOVEM", new String[]{"09", "IS"});
        put("DC", new String[]{"01", "DS"});
        put("DS", new String[]{"02", "DS"});
        put("START", new String[]{"01", "AD"});
        put("END", new String[]{"02", "AD"});
    }};

    // Hardcoded regtable
    static final Map<String, String> regtable = new HashMap<>() {{
        put("AREG", "01");
        put("BREG", "02");
        put("CREG", "03");
        put("DREG", "04");
    }};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the assembly code (type 'END' to finish):");

        List<String[]> tokens = new ArrayList<>();
        String line;

        // Read input and tokenize
        while (!(line = scanner.nextLine().trim()).equalsIgnoreCase("END")) {
            tokens.add(tokenizeLine(line));
        }

        scanner.close();
        tokens.add(new String[]{"", "END", "", ""}); // Add END token

        // Print the tokens in tabular format
        System.out.println("\nTokens:");
        System.out.printf("%-10s %-10s %-10s %-10s%n", "LABEL", "INSTR", "OP1", "OP2");
        tokens.forEach(token -> 
            System.out.printf("%-10s %-10s %-10s %-10s%n", 
                              token[0], token[1], token[2], token[3])
        );
    }

    // Tokenize a line into LABEL, INSTR, OP1, OP2
    private static String[] tokenizeLine(String line) {
        Scanner lineScanner = new Scanner(line);

        // Initialize variables
        String label = null;
        String instruction = null;
        String operand1 = null;
        String operand2 = null;

        int tokenIndex = 0;

        while (lineScanner.hasNext()) {
            String token = lineScanner.next();
            tokenIndex++;

            // Classify tokens based on their position and tables
            if (tokenIndex == 1 && !optab.containsKey(token)) {
                label = token; // First token is a label if not an instruction
            } else if (optab.containsKey(token)) {
                instruction = token; // Recognized instruction
            } else if (regtable.containsKey(token)) {
                if (operand1 == null) {
                    operand1 = token; // First register
                } else {
                    operand2 = token; // Second register
                }
            } else {
                if (operand1 == null) {
                    operand1 = token; // Assume as operand1 if it's not a register
                } else {
                    operand2 = token; // Assume as operand2
                }
            }
        }

        lineScanner.close();
        return new String[]{ 
            label != null ? label : "", 
            instruction != null ? instruction : "", 
            operand1 != null ? operand1 : "", 
            operand2 != null ? operand2 : "" 
        };
    }
}
