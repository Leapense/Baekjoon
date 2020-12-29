package num2441;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1; i <= n; i++)
        {
        	sb.append("*".repeat(i)).append(" ".repeat(n * 2 - i * 2)).append("*".repeat(i)).append('\n');
        }
        for(int i = n + 1; i <= n * 2 - 1; i++)
        {
        	sb.append("*".repeat(n * 2 - i)).append(" ".repeat(i * 2 - n * 2)).append("*".repeat(n * 2 - i)).append('\n');
        }
        System.out.print(sb);
        
    }
}
