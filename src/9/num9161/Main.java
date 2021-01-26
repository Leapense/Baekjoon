package num9161;

public class Main {
	public static void main(String[] args)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 10; i < 100; i++) {
			for(int j = 1; j < 10; j++) {
				for(int k = 10; k < 100; k++)
				{
					int x = i * 10 + j, y = j * 100 + k;
					
					if((int)(x / y) == (int)(i / k) && (x != y || i != k)) {
						sb.append(x + " / " + y + " = " + i + " / " + k).append('\n');
					}
				}
			}
		}
		
		System.out.print(sb);
	}
}
/*
 * 계속 출력 초과라고 뜸.
 */
