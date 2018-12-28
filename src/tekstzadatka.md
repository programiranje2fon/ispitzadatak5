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