package com.jelena.asocijacije;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class ButtonResenjeKolone extends JButton{
	
	private String nalicje;
	private String lice;
	
	public ButtonResenjeKolone(){
		
	}
	
	public ButtonResenjeKolone(String lice, String nalicje) {
		super(lice);
		this.lice=lice;
		this.nalicje = nalicje;
		Color myColor = new Color (191, 239, 255);
		setBackground(myColor);
		setFont(new Font("Arial", Font.BOLD, 12));
		setPreferredSize(new Dimension(100, 30));
	}
	
	public String getNalicje() {
		return nalicje;
	}

	public String toString(){
		return lice + ", " + nalicje +'\n';
	}
}
