package num14470;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int d = Integer.parseInt(br.readLine());
        int e = Integer.parseInt(br.readLine());
        
        if(a < 0)
        {
        	System.out.println(Math.abs(a) * c + d + b * e);
        }
        else
        {
        	System.out.println((b - a) * e);
        }
        
        br.close();
    }
}
