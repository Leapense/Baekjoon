import java.io.*;
import java.util.*;

public class Main {
    static class Row {
        final String[] fields;
        final int originalIndex;
        Row(String[] fields, int idx) {
            this.fields = fields;
            this.originalIndex = idx;
        }
    }

    static class Key {
        final int field;
        final boolean asc;
        Key(int field, boolean asc) {
            this.field = field;
            this.asc = asc;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        boolean firstDataset = true;
        StringBuilder out = new StringBuilder();

        while (true) {
            line = br.readLine();
            if (line == null) break;
            if (line.trim().equals("#")) break;
            String title = line;
            List<Row> baseRows = new ArrayList<>();
            int rowIdx = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("#")) break;
                String[] parts = line.split(",", -1);
                baseRows.add(new Row(parts, rowIdx++));
            }
            List<List<Key>> sorterSpecs = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String t = line.trim();
                if (t.equals("0#")) break;
                if (t.isEmpty()) continue;

                String[] tokens = t.split(",");
                List<Key> keys = new ArrayList<>(tokens.length);
                for (String tok : tokens) {
                    String s = tok.trim();
                    if (s.isEmpty()) continue;
                    char dir = s.charAt(s.length() - 1);
                    String numStr = s.substring(0, s.length() - 1);
                    int fieldIdx = Integer.parseInt(numStr) - 1;
                    boolean asc = (dir == 'A' || dir == 'a');
                    keys.add(new Key(fieldIdx, asc));
                }
                sorterSpecs.add(keys);
            }

            if (!firstDataset) out.append("\n");
            firstDataset = false;
            out.append(title).append("\n");
            for (int g = 0; g < sorterSpecs.size(); g++) {
                if (g > 0) out.append("\n");
                List<Row> rows = new ArrayList<>(baseRows);
                List<Key> keys = sorterSpecs.get(g);
                rows.sort((r1, r2) -> compareRows(r1, r2, keys));

                for (Row r : rows) {
                    out.append("  ").append(String.join(",", r.fields)).append("\n");
                }
            }
        }

        System.out.print(out.toString());
    }

    private static int compareRows(Row r1, Row r2, List<Key> keys) {
        for (Key k : keys) {
            String a = r1.fields[k.field];
            String b = r2.fields[k.field];
            int cmp = a.compareTo(b);
            if (!k.asc) cmp = -cmp;
            if (cmp != 0) return cmp;
        }

        return Integer.compare(r1.originalIndex, r2.originalIndex);
    }
}