import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssemblyTokenizer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the assembly code (type 'END' to finish):");

        List<String[]> tokens = new ArrayList<>();

        // Read the input line by line
        while (true) {
            String line = scanner.nextLine().trim();

            // Stop reading if 'END' is encountered
            if (line.equalsIgnoreCase("END")) {
                tokens.add(new String[]{"", "END", "", ""});
                break;
            }

            // Tokenize the input line
            String[] parts = tokenizeLine(line);

            // Add the tokens to the list
            tokens.add(parts);
        }

        scanner.close();

        // Print the tokens
        System.out.println("\nTokens:");
        for (String[] token : tokens) {
            System.out.printf("LABEL: %-10s INSTR: %-10s OP1: %-10s OP2: %-10s%n", 
                token[0], token[1], token[2], token[3]);
        }
    }

    // Tokenize a single line into LABEL, INSTR, OP1, OP2
    private static String[] tokenizeLine(String line) {
        String label = "", instr = "", op1 = "", op2 = "";

        // Split the line into parts
        String[] parts = line.split("\\s+");

        // Determine the tokens based on the number of parts
        if (parts.length == 4) { // Full format: LABEL INSTR OP1 OP2
            label = parts[0];
            instr = parts[1];
            op1 = parts[2];
            op2 = parts[3];
        } else if (parts.length == 3) { // Format: INSTR OP1 OP2
            instr = parts[0];
            op1 = parts[1];
            op2 = parts[2];
        } else if (parts.length == 2) { // Format: INSTR OP1
            instr = parts[0];
            op1 = parts[1];
        } else if (parts.length == 1) { // Format: INSTR
            instr = parts[0];
        }

        return new String[]{label, instr, op1, op2};
    }
}



