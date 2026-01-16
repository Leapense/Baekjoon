import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    private static long readLong() throws IOException {
        BufferedInputStream in = new BufferedInputStream(System.in);
        long n = 0;
        int c;
        
        while ((c = in.read()) != -1 && c <= ' ') {}
        while (c > ' ') {
            n = n * 10 + (c - '0');
            c = in.read();
        }
        
        return n;
    }
    
    public static void main(String[] args) throws Exception {
        long n = readLong();
        long steps = 0;
        
        while (n != 1) {
            if ((n & 1) == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
            
            steps++;
        }
        
        System.out.print(steps);
    }
}