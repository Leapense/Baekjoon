package num20232;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int year = Integer.parseInt(br.readLine());
		if(year == 1995 || year == 1998 || year == 1999 || (year >= 2001 && year <= 2005)
				|| (year >= 2009 && year <= 2012) || (year >= 2014 && year <= 2017) || year == 2019)
		{
			System.out.println("ITMO");
		}
		else if(year == 1996 || year == 1997 || year == 2000 || year == 2007 || year == 2008 || year == 2013 || year == 2018)
		{
			System.out.println("SPbSU");
		}
		else if(year == 2006)
		{
			System.out.println("PetrSU, ITMO");
		}
		br.close();
	}
}
