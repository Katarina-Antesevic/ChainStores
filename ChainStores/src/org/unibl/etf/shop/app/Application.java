package org.unibl.etf.shop.app;

import java.awt.EventQueue;

import javax.swing.UIManager;

import org.unibl.etf.shop.gui.GlavniProzor;

//import org.unibl.etf.shop.gui.GlavniProzor;


public class Application {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniProzor frame = new GlavniProzor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
