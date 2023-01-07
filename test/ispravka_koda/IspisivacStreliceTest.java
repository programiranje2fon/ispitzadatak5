package ispravka_koda;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ispravka_koda.IspisivacStrelice;

public class IspisivacStreliceTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}
	
	private String pomocna() {
		String s = new String();
		int j = 1;
		while (j <= 28) {
			if (j % 5 == 3 || j == 7 || j == 9 || j == 11 || j == 15)
				s = s.concat("0");
			else
				s = s.concat(" ");
			if(j % 5 == 0)
				s = s.concat("\r\n");
			j++;
		}
		return s;
	}
	
	// ne stavljati trim
	// jer ako se stavi ne vodi racuna o
	// tome da li je prvi red uvucen
	@Test
	public void metoda_ispisiStrelicu2() {
		IspisivacStrelice.ispisiStrelicu();
		String ispis = outContent.toString();
		String ocekivano = pomocna();
		assertTrue("Nakon izvrsetka metode ispisiStrelicu ispisano je \n" + ispis + "\nUmesto\n" + ocekivano + "\n",
				ocekivano.equals(ispis));
	}
}
