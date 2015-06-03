package com.jelena.asocijacije;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelAsocijacije extends JPanel implements ActionListener{
	
	String[] slA = {"A1","A2","A3", "A4", "A"};
	String[] snA = {"beli", "pismo", "mir", "prsten", "golub"};
	String[] slB = {"B1","B2","B3", "B4", "B"};
	String[] snB= {"Djokovic", "stjuardesa", "kapetan", "Boing", "avion"};
	String[] slC = {"C1","C2","C3", "C4", "C"};
	String[] snC = {"vatra", "rep", "pecina", "igracka", "zmaj"};
	String[] slD = {"D1","D2","D3", "D4", "D"};
	String[] snD = {"pilece", "laptop", "zgrada", "mahati", "krilo"};
	
	PanelKolone panelA;
	PanelKolone panelB;
	PanelKolone panelC;
	PanelKolone panelD;
	ButtonKonacnoResenje konacnoResenje;	
	public static boolean zabranjenoResavanjeKonacneAsocijacije;//public da ga vidi i panel kolone, da li moze private + get i set metode
	
	public PanelAsocijacije() {
		PanelKolone.brojNeotkrivenihPoljaUSvimKolonama = 16;//postavila sam stat polje i pre nego sto sam napravila instancu klase PanelKolone, valjda to moze
		PanelKolone.zabranjenoResavanjeKolona  = true;
		PanelAsocijacije.zabranjenoResavanjeKonacneAsocijacije = true;
		
		panelA = new PanelKolone(slA, snA);
		add(panelA);
		panelB = new PanelKolone(slB, snB);
		add(panelB);
		panelC = new PanelKolone(slC, snC);
		add(panelC);
		panelD = new PanelKolone(slD, snD);
		add(panelD);
		konacnoResenje = new ButtonKonacnoResenje("letenje");
		add(konacnoResenje);
		konacnoResenje.addActionListener(this);
	}
	//IZBACI CANCEL DUGME KOD UNOSA ASOCIJACIJE, DA NE MORAM I ODUSTAJANJE DA HENDLUJEM. SAMO OK
	//JER SADA AKO ODUSTANE NE MOZE PONOVO
	//stavila public polja, da li je moralo?
	public void unesiAsocijaciju(){
		String response = JOptionPane.showInputDialog("Unesite vasu konacnu asocijaciju:");
		if (response == null) {
			System.out.println("You have cancelled the input dialog konacne asocijacije.");
		}
		else {
			System.out.println("You entered for final as.: " + response);//kontrola			
			if (response.equals(konacnoResenje.getNalicje())) {		//ako je pogodio konacno resenje: prikazi ga, izracunaj konacne poene									
				konacnoResenje.setText(konacnoResenje.getNalicje());
				JOptionPane.showMessageDialog(null, "Tacan odgovor!", "Poruka", JOptionPane.INFORMATION_MESSAGE);
				
				panelA.otrkijSvaPolja();
				panelB.otrkijSvaPolja();
				panelC.otrkijSvaPolja();
				panelD.otrkijSvaPolja();
				
				int p = izracunajKonacnePoene();		
				JOptionPane.showMessageDialog(null, "Osvojili ste "+ p + " poena, broj neotkrivenih polja u svim kolonama je "+ PanelKolone.brojNeotkrivenihPoljaUSvimKolonama, "Poruka", JOptionPane.INFORMATION_MESSAGE);									
			}//ako nije pogodio konacno resenje
			else JOptionPane.showMessageDialog(null, "Odgovor nije tacan!", "Poruka", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	
	public void actionPerformed (ActionEvent e){
		
		//nema vise polja da se otvara pa moze konacno resenje vise puta zaredom
		if (PanelKolone.brojNeotkrivenihPoljaUSvimKolonama == 0){				
			PanelKolone.zabranjenoResavanjeKolona  = false;
			PanelAsocijacije.zabranjenoResavanjeKonacneAsocijacije = false;
		}
		//ako su sva polja otvorena klik na resavanje konacne asocijacije  uvek prvo postavlja flegove na false
		//tako da se uvek moze uneti asocojacija		
		if (PanelAsocijacije.zabranjenoResavanjeKonacneAsocijacije == false) {				
			unesiAsocijaciju();			
			PanelAsocijacije.zabranjenoResavanjeKonacneAsocijacije = true;
			PanelKolone.zabranjenoResavanjeKolona = true;
		}	
		else JOptionPane.showMessageDialog(null, "Morate otvoriti polje!", "Poruka", JOptionPane.INFORMATION_MESSAGE);
	}

	public int izracunajKonacnePoene(){
		int s;		
		s= panelA.getPoeniKolone() + panelB.getPoeniKolone() + panelC.getPoeniKolone() + panelD.getPoeniKolone() + 10 + 
				PanelKolone.brojNeotkrivenihPoljaUSvimKolonama;
		return s;
		
	}
}
