import java.io.*;
import java.util.*;

public class Main {
    private static class GroupData {
        int count;
        TreeSet<String> unique;
        GroupData() {
            this.count = 0;
            this.unique = new TreeSet<>();
        }
    }
    
    private static class Group {
        int count;
        String minWord;
        TreeSet<String> unique;
        
        Group(GroupData gd) {
            this.count = gd.count;
            this.unique = gd.unique;
            this.minWord = gd.unique.first();
        }
    }
    
    private static String signature(String w) {
        int[] cnt = new int[26];
        for (int i = 0; i < w.length(); i++) {
            cnt[w.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder(26 * 3);
        for (int i = 0; i < 26; i++) {
            sb.append('#').append(cnt[i]);
        }
        return sb.toString();
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, GroupData> map = new HashMap<>();
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String key = signature(line);
            GroupData gd = map.get(key);
            if (gd == null) {
                gd = new GroupData();
                map.put(key, gd);
            }
            
            gd.count++;
            gd.unique.add(line);
        }
        List<Group> groups = new ArrayList<>(map.size());
        for (GroupData gd : map.values()) {
            groups.add(new Group(gd));
        }
        
        groups.sort((a, b) -> {
            if (a.count != b.count) return Integer.compare(b.count, a.count);
            return a.minWord.compareTo(b.minWord);
        });
        
        int limit = Math.min(5, groups.size());
        StringBuilder out = new StringBuilder();
        
        for (int i = 0; i < limit; i++) {
            Group g = groups.get(i);
            out.append("Group of size ").append(g.count).append(": ");
            for (String w : g.unique) {
                out.append(w).append(' ');
            }
            
            out.append(".\n");
        }
        
        System.out.print(out.toString());
    }
}