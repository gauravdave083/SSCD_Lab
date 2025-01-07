import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssemblyTokenizer {

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

        // Print the tokens
        System.out.println("\nTokens:");
        tokens.forEach(token -> 
            System.out.printf("LABEL: %-10s INSTR: %-10s OP1: %-10s OP2: %-10s%n", 
                              token[0], token[1], token[2], token[3])
        );
    }

    // Tokenize a line into LABEL, INSTR, OP1, OP2
    private static String[] tokenizeLine(String line) {
        String[] parts = line.split("\\s+");
        return new String[]{
            parts.length > 3 ? parts[0] : "",          // LABEL (if present)
            parts.length > 3 ? parts[1] : parts[0],   // INSTR
            parts.length > 1 ? parts[parts.length - 2] : "", // OP1
            parts.length > 2 ? parts[parts.length - 1] : ""  // OP2
        };
    }
}
