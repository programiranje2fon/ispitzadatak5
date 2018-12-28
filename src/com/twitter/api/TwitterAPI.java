package com.twitter.api;
import com.twitter.poruke.*;
import java.util.*;


public interface TwitterAPI {
	public LinkedList<TwitterPoruka> vratiPoruke(String korisnik);
	public void vratiPoruke(int maxBroj, String tag);
	public void unesi(String korisnik, String poruka);
}