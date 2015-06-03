package com.jelena.asocijacije;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class ButtonPolje extends JButton{ 
	private String nalicje;
	private String lice;
	
	public ButtonPolje(){
		
	}
	public ButtonPolje(String lice, String nalicje) {
		super(lice);
		this.lice=lice;
		this.nalicje = nalicje;
		Color myColor = new Color (193, 255, 193);
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
