import java.io.*;
import java.util.*;

public class Main {
    private static char mapSingle(char c) {
        switch(c) {
            case 'a': return 'a';
            case 'b': return 'b';
            case 'k': return 'c';
            case 'd': return 'd';
            case 'e': return 'e';
            case 'g': return 'f';
            case 'h': return 'g';
            case 'i': return 'h';
            case 'l': return 'i';
            case 'm': return 'j';
            case 'n': return 'k';
            case 'o': return 'm';
            case 'p': return 'n';
            case 'r': return 'o';
            case 's': return 'p';
            case 't': return 'q';
            case 'u': return 'r';
            case 'w': return 's';
            case 'y': return 't';
            default: return c;
        }
    }

    private static String encode(String s) {
        StringBuilder key = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);
            if (c == 'n' && i + 1 < s.length() && s.charAt(i + 1) == 'g') {
                key.append('l');
                i += 2;
            } else {
                key.append(mapSingle(c));
                i++;
            }
        }
        return key.toString();
    }

    private static class Entry {
        String orig;
        String key;
        Entry(String o, String k) { this.orig = o; this.key = k; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        List<Entry> list = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            String w = br.readLine().trim();
            list.add(new Entry(w, encode(w)));
        }
        list.sort(Comparator.comparing(e -> e.key));
        StringBuilder out = new StringBuilder();
        for (Entry e : list) {
            out.append(e.orig).append('\n');
        }

        System.out.print(out.toString());
    }
}