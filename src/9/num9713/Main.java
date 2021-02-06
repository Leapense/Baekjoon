package num9713;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++)
        {
            int sum = 0;
            int n = Integer.parseInt(br.readLine());
            for(int a = 1; a <= n; a++)
            {
                if(a % 2 != 0)
                    sum += a;
            }
            System.out.println(sum);
        }
    }
}