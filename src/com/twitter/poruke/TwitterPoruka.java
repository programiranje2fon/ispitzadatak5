package com.twitter.poruke;

import java.util.GregorianCalendar;

public class TwitterPoruka {

	private String korisnik;
	private String poruka;
	private GregorianCalendar vreme;

	public String getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(String korisnik) {
		if (korisnik == null || korisnik.equals(""))
			throw new RuntimeException("Ime korisnika mora biti uneto");

		this.korisnik = korisnik;
	}

	public String getPoruka() {
		return poruka;
	}

	public void setPoruka(String poruka) {
		if (poruka == null || poruka.length() > 140)
			throw new RuntimeException("Poruka mora biti uneta i mora imati najvise 140 znakova");

		this.poruka = poruka;
	}

	public GregorianCalendar getVreme() {
		return vreme;
	}

	public void setVreme(GregorianCalendar vreme) {
		if (vreme == null || vreme.after(new GregorianCalendar()))
			throw new RuntimeException("Vreme se mora uneti i mora se odnositi na prosli trenutak");

		this.vreme = vreme;
	}

	public String toString() {
		return "KORISNIK:" + korisnik + " VREME:" + vreme.getTime() + " PORUKA:" + poruka;
	}

}