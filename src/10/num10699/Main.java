package num10699;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	public static void main(String[] args)
	{
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		System.out.println(format1.format(today.getTime()));
	}
}
