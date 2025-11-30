package zadatak.com.twitter.poruke;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.twitter.poruke.TwitterPoruka;

import test.TestUtil;

public class TwitterPorukaTest {
	TwitterPoruka instance;

	@Before
	public void setUp() throws Exception {
		instance = new TwitterPoruka();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}

	@Test
	public void atribut_korisnik() {
		assertTrue("Nije definisan atribut korisnik", TestUtil.doesFieldExist(TwitterPoruka.class, "korisnik"));
	}

	@Test
	public void atribut_korisnik_vidljivost() {
		assertTrue("Atribut korisnik nije privatan",
				TestUtil.hasFieldModifier(TwitterPoruka.class, "korisnik", Modifier.PRIVATE));
	}

	@Test
	public void atribut_poruka() {
		assertTrue("Nije definisan atribut poruka", TestUtil.doesFieldExist(TwitterPoruka.class, "poruka"));
	}

	@Test
	public void atribut_poruka_vidljivost() {
		assertTrue("Atribut poruka nije privatan",
				TestUtil.hasFieldModifier(TwitterPoruka.class, "poruka", Modifier.PRIVATE));
	}

	@Test
	public void atribut_vreme() {
		assertTrue("Nije definisan atribut vreme", TestUtil.doesFieldExist(TwitterPoruka.class, "vreme"));
	}

	@Test
	public void atribut_vreme_vidljivost() {
		assertTrue("Atribut vreme nije privatan",
				TestUtil.hasFieldModifier(TwitterPoruka.class, "vreme", Modifier.PRIVATE));
	}

	@Test
	public void metoda_setPoruka_vidljivost() {
		assertTrue("Metoda setPoruka nije javna", TestUtil.hasMethodModifier(TwitterPoruka.class, "setPoruka",
				new Class<?>[] { String.class }, Modifier.PUBLIC));
	}

	@Test(expected = RuntimeException.class)
	public void metoda_setPoruka_null() {
		String poruka = null;
		instance.setPoruka(poruka);
		assertTrue("Za prosledjeni argument " + poruka + " metoda setPoruka ne baca neproveravani izuzetak", false);
	}

	@Test(expected = RuntimeException.class)
	public void metoda_setPoruka_duziOd140() {
		String poruka = "A message is a discrete unit of communication intended by the source for consumption by some recipient or group of recipients. A message may be delivered by various means, including courier, telegraphy, carrier pigeon and electronic bus. A message can be the content of a broadcast. An interactive exchange of messages forms a conversation.";
		instance.setPoruka(poruka);
		assertTrue("Za prosledjeni string duzi od 140 znakova metoda setPoruka ne baca neproveravani izuzetak", false);
	}

	@Test
	public void metoda_setPoruka_zdravo() {
		String poruka = "zdravo";
		try {
			instance.setPoruka(poruka);
		} catch (RuntimeException e) {
			assertTrue("Za prosledjeni argument " + poruka + " metoda setPoruka baca neproveravani izuzetak", false);
		}
		String actual = (String) TestUtil.getFieldValue(instance, "poruka");
		assertTrue(
				"Za prosledjeni argument " + poruka
						+ ", nakon izvrsetka metode setPoruka vrednost abributa poruka je " + actual,
				poruka.equals(actual));
	}

	@Test
	public void metoda_setKorisnik_vidljivost() {
		assertTrue("Metoda setKorisnik nije javna", TestUtil.hasMethodModifier(TwitterPoruka.class, "setKorisnik",
				new Class<?>[] { String.class }, Modifier.PUBLIC));
	}

	@Test(expected = RuntimeException.class)
	public void metoda_setKorisnik_null() {
		String korisnik = null;
		instance.setKorisnik(korisnik);
		assertTrue("Za prosledjeni argument " + korisnik + " metoda setKorisnik ne baca neproveravani izuzetak", false);
	}

	@Test(expected = RuntimeException.class)
	public void metoda_setKorisnik_prazanString() {
		String korisnik = "";
		instance.setKorisnik(korisnik);
		assertTrue("Za prosledjeni prazan string metoda setKorisnik ne baca neproveravani izuzetak", false);
	}

	@Test
	public void metoda_setKorisnik_Marko() {
		String korisnik = "Marko";
		try {
			instance.setKorisnik(korisnik);
		} catch (RuntimeException e) {
			assertTrue("Za prosledjeni argument " + korisnik + " metoda setKorisnik baca neproveravani izuzetak",
					false);
		}
		String actual = (String) TestUtil.getFieldValue(instance, "korisnik");
		assertTrue(
				"Za prosledjeni argument " + korisnik
						+ ", nakon izvrsetka metode setKorisnik vrednost abributa korisnik je " + actual,
				korisnik.equals(actual));
	}

	@Test
	public void metoda_setVreme_vidljivost() {
		assertTrue("Metoda setVreme nije javna", TestUtil.hasMethodModifier(TwitterPoruka.class, "setVreme",
				new Class<?>[] { GregorianCalendar.class }, Modifier.PUBLIC));
	}

	@Test(expected = RuntimeException.class)
	public void metoda_setVreme_null() {
		GregorianCalendar vreme = null;
		instance.setVreme(vreme);
		assertTrue("Za prosledjeni argument null metoda vreme ne baca neproveravani izuzetak", false);
	}

	@Test(expected = RuntimeException.class)
	public void metoda_setVreme_buduci() {
		GregorianCalendar vreme = new GregorianCalendar();
		vreme.add(Calendar.DATE, 1);
		instance.setVreme(vreme);
		assertTrue("Za prosledjeni argument " + vreme.getTime().toString()
				+ " metoda vreme ne baca neproveravani izuzetak", false);
	}

	@Test
	public void metoda_setVreme_prosli() {
		GregorianCalendar vreme = new GregorianCalendar();
		vreme.add(Calendar.DATE, -1);
		try {
			instance.setVreme(vreme);
		} catch (RuntimeException e) {
			assertTrue("Za prosledjeni argument " + vreme.getTime().toString()
					+ " metoda setVreme baca neproveravani izuzetak", false);
		}

		GregorianCalendar actual = (GregorianCalendar) TestUtil.getFieldValue(instance, "vreme");
		assertTrue(
				"Za prosledjei argument argument " + vreme.getTime().toString()
						+ ", nakon izvrsetka metode setVreme, vrednost atributa vreme je " + vreme.getTime().toString(),
				actual.equals(vreme));

	}

	@Test
	public void metoda_toString() {
		String korisnik = "Marko";
		String poruka = "zdravo";
		GregorianCalendar vreme = new GregorianCalendar();
		vreme.add(Calendar.DATE, -1);
		instance.setKorisnik(korisnik);
		instance.setPoruka(poruka);
		instance.setVreme(vreme);
		korisnik = (String) TestUtil.getFieldValue(instance, "korisnik");
		poruka = (String) TestUtil.getFieldValue(instance, "poruka");
		vreme = (GregorianCalendar) TestUtil.getFieldValue(instance, "vreme");
		String result = instance.toString();
		int indexKorisnik = result.indexOf("KORISNIK");
		assertTrue("String koji vraca metoda toString ne sadrzi rec \"KORISNIK\"", indexKorisnik != -1);
		int indexVreme = result.indexOf("VREME");
		assertTrue("String koji vraca metoda toString ne sadrzi rec \"VREME\"", indexVreme != -1);
		assertTrue("Rec \"VREME\" se ne nalazi na ispravnom mestu u rezultatu koji vraca metoda toString",
				indexVreme > indexKorisnik);
		int indexPoruka = result.indexOf("PORUKA");
		assertTrue("String koji vraca metoda toString ne sadrzi rec \"PORUKA\"", indexPoruka != -1);
		assertTrue("Rec \"PORUKA\" se ne nalazi na ispravnom mestu u rezultatu koji vraca metoda toString",
				indexPoruka > indexVreme);

		int indexKorisnikValue = result.indexOf(korisnik);
		assertTrue("String koji vraca metoda toString ne sadrzi vrednost atributa korisnik", indexKorisnikValue != -1);
		assertTrue(
				"String koji vraca metoda toString nije u doborom formatu, vrednost atributa korisnik nije na ispravnom mestu",
				indexKorisnikValue > indexKorisnik && indexKorisnikValue < indexVreme);

		int indexYearValue = result.indexOf(""+vreme.get(GregorianCalendar.YEAR));
		assertTrue("String koji vraca metoda toString ne sadrzi vrednost godine objavljivanja poruke",
				indexYearValue != -1);

		int indexDateValue = result.indexOf(""+vreme.get(GregorianCalendar.DATE));
		assertTrue("String koji vraca metoda toString ne sadrzi vrednost dana objavljivanja poruke",
				indexDateValue != -1);

		assertTrue(
				"String koji vraca metoda toString nije u doborom formatu, vrednost atributa vreme nije na ispravnom mestu",
				indexDateValue > indexVreme && indexDateValue < indexPoruka && indexYearValue > indexVreme
						&& indexYearValue < indexPoruka);
		
		int indexPorukaValue = result.indexOf(poruka);
		
		assertTrue("String koji vraca metoda toString ne sadrzi vrednost atributa poruka", indexPorukaValue != -1);
		
		assertTrue("String koji vraca metoda toString nije u doborom formatu, vrednost atributa poruka nije na ispravnom mestu",
				indexPorukaValue > indexPoruka);
	}
}
