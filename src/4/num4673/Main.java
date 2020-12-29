package num4673;

public class Main {
	public static void main(String[] args)
	{
		boolean[] isNotSelfNumber = new boolean[10001];
		int sum = 0;
		for(int i = 1; i <= 10000; i++) {
			int dn = getDn(i);
			if(dn <= 10000) {
				isNotSelfNumber[dn] = true;
			}
		}
		for(int i = 1; i < isNotSelfNumber.length; i++) {
			if(!isNotSelfNumber[i]) {
				System.out.println(i);
			}
		}
	}
	
	private static int getDn(int n) {
		int dn = n;
		while(n > 0) {
			dn += n % 10;
			n /= 10;
		}
		return dn;
	}
}
