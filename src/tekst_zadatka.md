#Zadatak 5
Napraviti javnu klasu **TwitterPoruka** u paketu **com.twitter.poruke** koja ima:
* Privatni atribut **korisnik** koji predstavlja naziv korisnika koji je objavio poruku.
* Privatni atribut **poruka** koji predstavlja tekst poruke.
* Privatni atribut **vreme** koji predstavlja datum i vreme objavljivanja poruke (klasa GregorianCalendar)
* Odgovarajuće javne get i set metode za ove atribute. Nedozvoljene vrednosti za atribut korisnik su
NULL i prazan String, a poruka ne sme biti NULL i može imati najviše 140 znakova. Vreme ne sme da
bude neki datum u pudućnosti i ne sme da bude NULL. U slučaju unosa ovih nedozvoljenih vrednosti
potrebno je baciti izuzetak sa odgovarajućom porukom.
* Redefinisanu metodu toString klase Object koja koja vraća jedan String u kome se nalaze svi podaci
poruke u formatu **“KORISNIK_####_VREME_####_PORUKA_####”**.

Napraviti javni interfejs **TwitterAPI** u paketu **com.twitter.api** koji ima:
* Javnu metodu vratiPoruke koja kao ulaz prima naziv korisnika i vraća listu objekata TwitterPoruka.
* Javnu metodu vratiPoruke koja kao ulazni parametar prima ceo broj koji predstavlja maksimalan broj
poruka i String koji predstavlja tag, a ne vraća ništa.
* Javnu metodu unesi koja kao ulaz dobija naziv korisnika i tekst poruke, a ne vraća ništa.

Napraviti javnu klasu **Twitter** u paketu **com.twitter** koja implem. interfejs **TwitterAPI** i ima:
* Privatni atribut **poruke** koji predstavlja listu objekata klase TwitterPoruka.
* Javni konstruktor koji inicijalizuje listu poruka.
* __Implementiranu metodu unesi__ koja novu poruku unosi na kraj liste. Pre unosa podesiti vreme
objavljivanja poruke na trenutno vreme. Kad se izvrši unos, potrebno je na ekranu ispisati sve
poruke iz liste.
* Implementiranu metodu **vratiPoruke** koja kao ulazni parametar prima ceo broj koji predstavlja
maksimalan broj poruka i String koji predstavlja tag. Metoda u tekstualni fajl “pretraga.txt” upisuje
tekst svih poruka koje sadrže dati tag. __Tag se nalazi u tekstu poruke i počinje znakom # a završava se praznim mestom (tag u sebi ne sme da sadrži prazna mesta)__. Primer: ako je unet tag
“programiranje” onda se u tekstu poruke traži “#programiranje_”. Maksimalan broj poruka koje treba
upisati u fajl je dat kao ulazni parametar - ako ih ima više od toga, prekobrojne poruke se ne upisuju.
* __Implementiranu metodu vratiPoruke__ koja kao ulazni parametar prima naziv korisnika i vraća listu
objekata klase TwitterPoruka u kojoj se nalaze samo poruke koje je objavio taj korisnik i to u toku
prethodnog meseca (u odnosu na trenutni datum). Poruke u listi treba da budu poređane tako da na
početku budu najstarije poruke pa tek onda novije. __Obratiti pažnju na situaciju kada je trenutno januar mesec__.

#Zadatak 5 - Ispravka koda

U produžetku teksta je dat kod klase sa metodom koja bi trebalo da, na ekranu, ispisuje strelicu od znakova
0. Ideja je da strelica bude visine šest redova, širine 5 znakova i okrenuta “na gore”, tako da bi konačan ispis
na ekranu trebalo da bude:


	  0
	  
	 000
	 
	0 0 0
	
	  0
	  
	  0
	  
	  0
	  
Dati kod se kompajlira, ali ne radi to šta treba. Napraviti javnu klasu **IspisivacStrelice** u paketu
**ispravka_koda**, prekucati u nju kod koji je dat i, __uz minimalne izmene__ ga ispraviti tako da funkcioniše
kako treba. Napraviti test klasu i, koristeći njenu main metodu, pozvati metodu ispisiStrelicu() i proveriti
njen rad.

	package ispravka_koda;

	public class IspisivacStrelice {
	
		public static void ispisiStrelicu() {
		
			int j = 1;
			
			while (j<=28) {
				
				if(j%5==5 || j==7 || j==9 || j==11 || j==15)
					
					System.out.print('0');
				
				if(j%5==0)
					
					System.out.println();
				
				j++;
			}
		}
	}