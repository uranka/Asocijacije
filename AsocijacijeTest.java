package com.jelena.asocijacije;

import java.awt.Container;

import javax.swing.JFrame;

public class AsocijacijeTest extends JFrame{
	
	public AsocijacijeTest() {
		super("Asocijacije");
		PanelAsocijacije pa = new PanelAsocijacije();
		Container contentPane = getContentPane();
		contentPane.add(pa);
		setSize(500,300);
		setVisible(true);
	}


		
	public static void main(String[] args) {
		AsocijacijeTest at = new AsocijacijeTest();
		at.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
