import java.io.*;
import java.util.*;

public class Main {
    static int[][] L = new int[3][4];
    static int[][] R = new int[3][4];
    static char[] op = new char[3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> toks = new ArrayList<>();
        String s;
        while ((s = br.readLine()) != null) {
            s = s.trim();
            if (s.isEmpty()) continue;
            String[] ps = s.split("\\s+");
            Collections.addAll(toks, ps);
        }
        if (toks.size() < 27) {

        }
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) L[i][j] = Integer.parseInt(toks.get(idx++));
            op[i] = toks.get(idx++).charAt(0);
            for (int j = 0; j < 4; j++) R[i][j] = Integer.parseInt(toks.get(idx++));
        }

        int count = 0, ansCoin = -1; char ansSign = '?';
        for (int coin = 1; coin <= 12; coin++) {
            for (char sign : new char[]{'+', '-'}) {
                if (consistent(coin, sign)) {
                    count++;
                    ansCoin = coin;
                    ansSign = sign;
                }
            }
        }

        if (count == 0) {
            System.out.println("impossible");
        } else if (count == 1) {
            System.out.println(ansCoin + String.valueOf(ansSign));
        } else {
            System.out.println("indefinite");
        }
    }

    static boolean consistent(int coin, char sign) {
        int bias = (sign == '+') ? 1 : -1;
        for (int i = 0; i < 3; i++) {
            int d = 0;
            if (contains(L[i], coin)) d += bias;
            if (contains(R[i], coin)) d -= bias;
            char o = op[i];
            if (o == '=' && d != 0) return false;
            if (o == '<' && !(d < 0)) return false;
            if (o == '>' && !(d > 0)) return false;
        }
        return true;
    }

    static boolean contains(int[] arr, int x) {
        for (int v : arr) if (v == x) return true;
        return false;
    }
}