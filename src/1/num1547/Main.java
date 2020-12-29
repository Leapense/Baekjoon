package num1547;
import java.io.*;
import java.util.StringTokenizer;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        int m, x, y;
        boolean exists = false;
        int[] p = new int[]{0, 1, 0, 0};
        int temp = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(br.readLine());
        while(m-- > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            
            temp = p[x];
            p[x] = p[y];
            p[y] = temp;
        }
        for(int i = 1; i <= 3; i++)
        {
            if(p[i] == 1)
            {
                exists = true;
                System.out.println(i);
                break;
            }
        }
        if(!exists)
        {
            System.out.println(-1);
        }
    }
}