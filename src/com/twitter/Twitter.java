package com.twitter;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import com.twitter.api.TwitterAPI;
import com.twitter.poruke.TwitterPoruka;

public class Twitter implements TwitterAPI {
	
	private LinkedList<TwitterPoruka> poruke;
	
	public Twitter(){
		poruke = new LinkedList<TwitterPoruka>();
	}

	public void unesi(String korisnik, String poruka) {
		//Pravi se nova poruka i puni podacima. Vreme se
		//podesava na trenutni datum i vreme.
		TwitterPoruka tp = new TwitterPoruka();
		tp.setKorisnik(korisnik);
		tp.setPoruka(poruka);
		tp.setVreme(new GregorianCalendar());
		
		//Poruka se unosi u listu na kraj
		poruke.addLast(tp);
		
		//Ispis cele liste na ekranu
		for (int i = 0; i <poruke.size(); i++)
			System.out.println(poruke.get(i));
	}
	
	public void vratiPoruke(int maxBroj, String tag) {
		try{
			PrintWriter out = new PrintWriter(new FileWriter("pretraga.txt"));
			
			//Prepravljanje taga da sadrzi # na pocetku i prazno mesto na kraju
			tag = '#' + tag + ' ';
			//Pomocna promenljiva koja predstavlja brojac upisanih poruka
			int brojac = 0;
			
			//Pretrazuju se poruke i traze se one koje sadrze tag.
			//Ako se nadje neka takva, i ako nije prekoracen maxBroj
			//ona se upisuje u fajl. Ako je prekoracen maxBroj,pretraga
			//se prekida.
			for (int i = 0; i < poruke.size(); i++) {
				if (poruke.get(i).getPoruka().indexOf(tag)!=-1)
					if (brojac < maxBroj){
						out.println(poruke.get(i));
						brojac++;
					}
					else break;
			}
			
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public LinkedList<TwitterPoruka> vratiPoruke(String korisnik) {
		LinkedList<TwitterPoruka> novePoruke = new LinkedList<TwitterPoruka>();
		
		//Utvrdjivanje trenutnog meseca i godine
		GregorianCalendar trenutniDatum = new GregorianCalendar();
		int godina = trenutniDatum.get(GregorianCalendar.YEAR);
		int mesec = trenutniDatum.get(GregorianCalendar.MONTH);
		
		//Ako trenutni mesec nije Januar (vrednost 0), trazeni mesec
		//se dobija oduzimanjem jedinice od meseca. Ako jeste januar,
		//onda je trazeni mesec decembar (vrednost 11) a godina se
		//smanjuje za 1.
		if (mesec!=0) mesec--;
		else{
			mesec = 11;
			godina--;
		}
		
		//Pretrazivanje liste poruka i pronalazenje poruka od trazenog
		//korisnika za zeljeni mesec i godinu.
		for (int i = 0; i < poruke.size(); i++)
			if (poruke.get(i).getKorisnik().equals(korisnik) &&
					poruke.get(i).getVreme().get(GregorianCalendar.YEAR)==godina &&
					poruke.get(i).getVreme().get(GregorianCalendar.MONTH)==mesec){
					TwitterPoruka nova = poruke.get(i);
					//Svaka takva poruka koja se nadje, se unosi u listu ali tako da poruke budu
					//poredjane po vremenu poruke. Kad se u novoj listi nadje poruka koja je
					//poslata kasnije od nove, nova se unosi na njeno mesto a sve ostale se 
					//pomeraju udesno.
					for (int j = 0; j < novePoruke.size(); j++)
						if (nova.getVreme().before(novePoruke.get(i).getVreme())){
							novePoruke.add(i, nova);
							break;
						}
						
					//Ako nova poruka ipak nije uneta (najnovijeg je datuma ili
					//je lista prazna, unosi se na kraj liste
					if (!novePoruke.contains(nova))
						novePoruke.addLast(nova);
			}
		
		return novePoruke;
	}


}