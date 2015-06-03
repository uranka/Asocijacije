package com.jelena.asocijacije;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class ButtonKonacnoResenje extends JButton{
	
	private String nalicje;
	private String lice;
	
	public ButtonKonacnoResenje(){
		
	}
	public ButtonKonacnoResenje(String konacnoResenje){
		super("???");
		this.lice="???";
		this.nalicje = konacnoResenje;
		//Color myColor = new Color (193, 255, 193);
		setBackground(Color.RED);
		setFont(new Font("Arial", Font.BOLD, 16));
		setPreferredSize(new Dimension(350, 50));		
	}
	public String getNalicje() {
		return nalicje;
	}

	public String toString(){
		return lice + ", " + nalicje +'\n';
	}
}
