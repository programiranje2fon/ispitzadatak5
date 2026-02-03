package com.twitter.poruke;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TwitterPoruka {

	private String korisnik;
	private String poruka;
	private LocalDateTime vreme;

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

	public LocalDateTime getVreme() {
		return vreme;
	}

	public void setVreme(LocalDateTime vreme) {
		if (vreme == null || vreme.isAfter(LocalDateTime.now()))
			throw new RuntimeException("Vreme se mora uneti i mora se odnositi na prosli trenutak");

		this.vreme = vreme;
	}

	public String toString() {
		return "KORISNIK:" + korisnik + " VREME:" + vreme + " PORUKA:" + poruka;
	}

}