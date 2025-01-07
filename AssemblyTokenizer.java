import java.util.ArrayList;
import java.util.List;

public class AssemblyTokenizer {

    public static void main(String[] args) {
        // Hardcoded assembly lines
        String[] assemblyCode = {
            "START 40",
            "MOVER AREG A",
            "ADD AREG A",
            "END"
        };

        List<String[]> tokens = new ArrayList<>();

        // Tokenize each line
        for (String line : assemblyCode) {
            tokens.add(tokenizeLine(line));
        }

        // Print the tokens
        System.out.println("Tokens:");
        for (String[] token : tokens) {
            System.out.printf("LABEL: %-10s INSTR: %-10s OP1: %-10s OP2: %-10s%n",
                    token[0], token[1], token[2], token[3]);
        }
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
