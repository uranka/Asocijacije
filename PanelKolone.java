package com.jelena.asocijacije;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PanelKolone extends JPanel implements ActionListener{
		
	private ButtonPolje[] polja = new ButtonPolje[4]; 
	private ButtonResenjeKolone resenjeKolone;
	
	private String nazivKolone;//moze verovatno preko resenjeKolone ali ovako mi lakse
	
	private int brojNeotkrivenihPolja;
	private int poeniKolone;
	
	public static int brojNeotkrivenihPoljaUSvimKolonama; 
	public static boolean zabranjenoResavanjeKolona; //da ga vidi i panel asocijacije
	
	public PanelKolone(String[] sl, String[] sn) {
		brojNeotkrivenihPolja = 4;
		poeniKolone = 0;
		
		setLayout(new GridLayout(5,0,10,10));
		nazivKolone = sl[4];		
		for (int i=0; i<4; i++){			
			polja[i] = new ButtonPolje(sl[i], sn[i]);			
			add(polja[i]);
			polja[i].addActionListener(this);
		}
		resenjeKolone = new ButtonResenjeKolone(sl[4], sn[4]);	
		add(resenjeKolone);
		resenjeKolone.addActionListener(this);		
	}
	
	/********************************************************************************************/
	// vraca broj novotkrivenih polja (polja koja su morala da se otrkiju zbog resavanja kolone)
	public int otrkijSvaPolja() { //brojim koliko polja sam morala da otkrijem pa cu za toliko da umanjujem PanelKolone.brojNeotkrivenihPoljaUSvimKolonama
		int br = 0; //brojac novootkrivenih polja
		for (int i=0; i<4; i++) {
			if (!polja[i].getText().equals(polja[i].getNalicje()) ) { //neotkriveno polje, valjda nalicje nece nikad biti A1, B3 i sl.
				polja[i].setText(polja[i].getNalicje()); //otkrivam ga
				polja[i].setEnabled(false); 
				br ++;				
			}
		}
		resenjeKolone.setText(resenjeKolone.getNalicje()); //otkrivam resenje kolone
		resenjeKolone.setEnabled(false);
		return br;		
	}
	/***********************************************************************************************/
	public void unesiAsocijaciju() {
		String response = JOptionPane.showInputDialog("Unesite vasu asocijaciju:");
		if (response == null) {
			System.out.println("You have cancelled the input dialog.");
		}
		else {
			System.out.println("You entered: " + response);
			//Use the string.equals(String other) function to compare strings, not the == operator.
			//The function checks the actual contents of the string, the == operator checks whether the references to the objects are equal. 
			if (response.equals(resenjeKolone.getNalicje())) { //kolona je resena					
				JOptionPane.showMessageDialog(null, "Tacan odgovor!", "Poruka", JOptionPane.INFORMATION_MESSAGE);
				poeniKolone = izracunajPoeneKolone();
				resenjeKolone.setText(resenjeKolone.getNalicje());	
								
				int b = otrkijSvaPolja();		
				PanelKolone.brojNeotkrivenihPoljaUSvimKolonama -= b;
				PanelAsocijacije.zabranjenoResavanjeKonacneAsocijacije = false;//uspesno resena kolona pa moze i  konacna as. da se resava
			}
			else { //kolona nije resena
				JOptionPane.showMessageDialog(null, "Odgovor nije tacan!", "Poruka", JOptionPane.INFORMATION_MESSAGE); 
				PanelAsocijacije.zabranjenoResavanjeKonacneAsocijacije = true;
			}					
		}		
	}
	/***********************************************************************************************/
	
	public void actionPerformed(ActionEvent e){
		
		if (e.getActionCommand() == nazivKolone) { //resenje kolone
			if (PanelKolone.brojNeotkrivenihPoljaUSvimKolonama == 0){ //nema vise polja da se otvara pa moze kolona vise puta zaredom				
				unesiAsocijaciju();				
			}
			else {						
				if (PanelKolone.zabranjenoResavanjeKolona == false) {
					unesiAsocijaciju();									
					PanelKolone.zabranjenoResavanjeKolona = true;
				}
				else JOptionPane.showMessageDialog(null, "Morate otvoriti polje!", "Poruka", JOptionPane.INFORMATION_MESSAGE);
			}
		}	
		
		else { //obicno polje; otvori ga, updejtuj broj neotkrivenih
			ButtonPolje btnClicked = (ButtonPolje) e.getSource(); //exc. se javlja kad nie vise nazivkolene nego e nalicje tj resenje prijazano pa neko klikce, onemogucila sam kliktanje na resenu kolonu u otrkijSvaPolja
			btnClicked.setText(btnClicked.getNalicje());			
			brojNeotkrivenihPolja--;
			//da li je bole da se obrcaam statickom polju uz ime klase i ovde?
			PanelKolone.brojNeotkrivenihPoljaUSvimKolonama --;//   brojNeotkrivenihPoljaUSvimKolonama--;//ovo mi odlazi u minus ako klikcem po polju iako je otkriveno vec jednom			
			btnClicked.setEnabled(false);//pa sam zabranila da ima uticaja kliktanje; zasivljeno je sto je mozda bez veze
			PanelKolone.zabranjenoResavanjeKolona  = false;
			PanelAsocijacije.zabranjenoResavanjeKonacneAsocijacije = false;					
		}
	}
	/***********************************************************************************************/	
	public int izracunajPoeneKolone() {
		return 5+5*brojNeotkrivenihPolja;
	}
	
	public int getPoeniKolone() {
		return poeniKolone;
	}
	public int getBrojNeotkrivenihPolja() {
		return brojNeotkrivenihPolja;
	}

}
