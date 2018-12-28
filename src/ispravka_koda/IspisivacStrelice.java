package ispravka_koda;


public class IspisivacStrelice {

	public static void ispisiStrelicu() {
		int j = 1;
		while (j <= 28) {
			if (j % 5 == 3 || j == 7 || j == 9 || j == 11 || j == 15)
				System.out.print('0');
			else
				System.out.print(' ');
			if(j % 5 == 0)
				System.out.println();
			j++;
		}
	}
}