package zadatak.com.twitter;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.twitter.Twitter;
import com.twitter.poruke.TwitterPoruka;

import test.TestUtil;

public class TwitterTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	Twitter instance;

	@Before
	public void setUp() throws Exception {
		instance = new Twitter();
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	public void atribut_poruke() {
		assertTrue("Nije definisan atribut poruke", TestUtil.doesFieldExist(Twitter.class, "poruke"));
	}

	@Test
	public void atribut_poruke_vidljivost() {
		assertTrue("Atribut poruke nije privatan",
				TestUtil.hasFieldModifier(Twitter.class, "poruke", Modifier.PRIVATE));
	}

	@Test
	public void konstruktor_inicijalizacija() {
		assertTrue("Nakon poziva konstruktora atribut poruke nije inicijalizovan",
				TestUtil.getFieldValue(instance, "poruke") != null);
	}

	private boolean iste(List<TwitterPoruka> a, List<TwitterPoruka> b) {
		if (a.size() != b.size())
			return false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < a.size(); i++) {
			TwitterPoruka prva = a.get(i), druga = b.get(i);
			if (!prva.getKorisnik().equals(druga.getKorisnik()) || !prva.getPoruka().equals(druga.getPoruka())
					|| !sdf.format(prva.getVreme().getTime()).equals(sdf.format(druga.getVreme().getTime())))
				return false;
		}
		return true;
	}

	public void inicijalizuj() {
		instance.unesi("Marko", "#p2 prva");
		instance.unesi("Zdravko", "#p2 druga");
		instance.unesi("Marko", "#p3 treca");
	}

	@Test(timeout = 2000)
	public void metoda_unesi() {
		inicijalizuj();
		List<TwitterPoruka> stvarna = (List<TwitterPoruka>) TestUtil.getFieldValue(instance, "poruke");
		List<TwitterPoruka> kopija = new LinkedList<>();
		for (int i = 0; i < stvarna.size(); i++) {
			kopija.add(stvarna.get(i));
		}

		String korisnik = "Marko";
		String poruka = "zdravo";
		GregorianCalendar vreme = new GregorianCalendar();
		TwitterPoruka twitterPoruka = new TwitterPoruka();
		twitterPoruka.setKorisnik(korisnik);
		twitterPoruka.setPoruka(poruka);
		twitterPoruka.setVreme(vreme);
		instance.unesi(korisnik, poruka);
		kopija.add(twitterPoruka);

		assertTrue("Nakon izvrsetka metode unesi sadrzaj liste poruka nije ispravan", iste(stvarna, kopija));
		String ispis = outContent.toString().trim();

		for (TwitterPoruka t : stvarna) {
			if (ispis.indexOf(t.toString()) == -1) {
				assertTrue("Metoda unesi ne ispisuje sve poruke na ekranu", false);
			}
		}
	}

	private LinkedList<TwitterPoruka> pomocnaTag(int maxBroj, String tag) {
		LinkedList<TwitterPoruka> poruke = (LinkedList<TwitterPoruka>) TestUtil.getFieldValue(instance, "poruke");
		LinkedList<TwitterPoruka> rezultat = new LinkedList<>();
		try {
			PrintWriter out = new PrintWriter(new FileWriter("pretraga.txt"));

			tag = '#' + tag + ' ';
			int brojac = 0;

			for (int i = 0; i < poruke.size(); i++)
				if (poruke.get(i).getPoruka().indexOf(tag) != -1)
					if (brojac < maxBroj) {
						rezultat.add(poruke.get(i));
						brojac++;
					} else
						break;

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rezultat;
	}

	private void vratiPorukeTag(int maxBroj) {
		String tag = "p2";
		inicijalizuj();
		LinkedList<TwitterPoruka> rezultat = pomocnaTag(maxBroj, tag);
		instance.vratiPoruke(maxBroj, tag);

		String fajl = new String();
		int brojLinija = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("pretraga.txt"))) {
			for (String line; (line = br.readLine()) != null;) {
				fajl = fajl.concat(line);
				if (!line.isEmpty())
					brojLinija++;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			assertTrue("Nije pronadjen fajl pretraga.txt", false);
		}

		assertTrue("Fajl sadrzi vise poruka nego sto je potrebno", brojLinija == rezultat.size());

		for (TwitterPoruka t : rezultat) {
			assertTrue("Fajl ne sadrzi sve potrebne poruke", fajl.indexOf(t.toString()) != -1);
		}
	}

	@Test
	public void metoda_vratiPorukeTag_maxBrojManjiOdBrojaPoruka() {
		vratiPorukeTag(1);
	}

	@Test
	public void metoda_vratiPorukeTag_maxBrojJednakBrojaPoruka() {
		vratiPorukeTag(2);
	}

	@Test
	public void metoda_vratiPorukeTag_maxBrojVeciOdBrojaPoruka() {
		vratiPorukeTag(3);
	}

	private LinkedList<TwitterPoruka> pomocna(String korisnik) {
		LinkedList<TwitterPoruka> novePoruke = new LinkedList<TwitterPoruka>();
		LinkedList<TwitterPoruka> poruke = (LinkedList<TwitterPoruka>) TestUtil.getFieldValue(instance, "poruke");
		GregorianCalendar trenutniDatum = new GregorianCalendar();
		int godina = trenutniDatum.get(GregorianCalendar.YEAR);
		int mesec = trenutniDatum.get(GregorianCalendar.MONTH);

		if (mesec != 0)
			mesec--;
		else {
			mesec = 11;
			godina--;
		}

		for (int i = 0; i < poruke.size(); i++)
			if (poruke.get(i).getKorisnik().equals(korisnik)
					&& poruke.get(i).getVreme().get(GregorianCalendar.YEAR) == godina
					&& poruke.get(i).getVreme().get(GregorianCalendar.MONTH) == mesec) {
				TwitterPoruka nova = poruke.get(i);

				for (int j = 0; j < novePoruke.size(); j++)
					if (nova.getVreme().before(novePoruke.get(i).getVreme())) {
						novePoruke.add(i, nova);
						break;
					}

				if (!novePoruke.contains(nova))
					novePoruke.addLast(nova);
			}

		return novePoruke;
	}

	@Test(timeout = 2000)
	public void metoda_vratiPorukeKorisnik() {
		inicijalizuj();
		List<TwitterPoruka> ocekivana = pomocna("Marko");
		List<TwitterPoruka> stvarna = instance.vratiPoruke("Marko");

		for (TwitterPoruka t : ocekivana) {
			assertTrue("Vracena lista ne sadrzi sve potrebne poruke", stvarna.contains(ocekivana));
		}

		for (TwitterPoruka t : stvarna) {
			assertTrue("Vracena lista sadrzi poruke koje ne bi trebala", ocekivana.contains(stvarna));
		}
	}
}
