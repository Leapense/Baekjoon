package num16170;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	public static void main(String[] args) {
		Date current = new Date();
		SimpleDateFormat dat = new SimpleDateFormat("yyyy");
		SimpleDateFormat mon = new SimpleDateFormat("MM");
		SimpleDateFormat day = new SimpleDateFormat("dd");
		System.out.println(dat.format(current.getTime()));
		System.out.println(mon.format(current.getTime()));
		System.out.println(day.format(current.getTime()));
	}
}
