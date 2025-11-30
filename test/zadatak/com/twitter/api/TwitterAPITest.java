package zadatak.com.twitter.api;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.Test;

import com.twitter.api.TwitterAPI;

import test.TestUtil;

public class TwitterAPITest {
	@Test
	public void metoda_vratiPoruke1_vidljivost() {
		assertTrue("Metoda vratiPoruke koja prima korisnika nije javna", TestUtil.hasMethodModifier(TwitterAPI.class,
				"vratiPoruke", new Class<?>[] { String.class }, Modifier.PUBLIC));
	}

	@Test
	public void metoda_vratiPoruke2_vidljivost() {
		assertTrue("Metoda vratiPoruke koja prima maksimalan broj poruka i tag nije javna", TestUtil.hasMethodModifier(
				TwitterAPI.class, "vratiPoruke", new Class<?>[] { int.class, String.class }, Modifier.PUBLIC));
	}

	@Test
	public void metoda_unesi_vidljivost() {
		assertTrue("Metoda unesi nije javna", TestUtil.hasMethodModifier(TwitterAPI.class, "unesi",
				new Class<?>[] { String.class, String.class }, Modifier.PUBLIC));
	}
}
