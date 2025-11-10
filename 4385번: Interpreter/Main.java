import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> tokens = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            for (String p : parts) {
                if (!p.isEmpty()) tokens.add(p);
            }
        }

        int[] ram = new int[1000];
        Arrays.fill(ram, 0);
        for (int i = 0; i < tokens.size() && i < 1000; i++) {
            ram[i] = Integer.parseInt(tokens.get(i));
        }

        int[] r = new int[10];
        int pc = 0;
        int count = 0;
        while (true) {
            int instr = ram[pc];
            pc++;
            count++;

            int op = instr / 100;
            int b = (instr / 10) % 10;
            int c = instr % 10;

            switch (op) {
                case 1:
                    System.out.println(count);
                    return;
                case 2:
                    r[b] = c;
                    break;
                case 3:
                    r[b] = (r[b] + c) % 1000;
                    break;
                case 4:
                    r[b] = (r[b] * c) % 1000;
                    break;
                case 5:
                    r[b] = r[c];
                    break;
                case 6:
                    r[b] = (r[b] + r[c]) % 1000;
                    break;
                case 7:
                    r[b] = (r[b] * r[c]) % 1000;
                    break;
                case 8:
                    r[b] = ram[r[c]];
                    break;
                case 9:
                    ram[r[c]] = r[b];
                    break;
                case 0:
                    if (r[c] != 0) pc = r[b];
                    break;
                default:
                    break;
            }
        }
    }
}