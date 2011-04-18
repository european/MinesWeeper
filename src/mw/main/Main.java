package mw.main;

import javax.swing.SwingUtilities;

import mw.controller.MWController;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// jetzt ist es auf meinem Rechner 15:44
		
		 Runnable gui = new Runnable() {
			 
	            @Override
	            public void run() {
	                new MWController();
	            }
	        };
	        //GUI must start on EventDispatchThread:
	        SwingUtilities.invokeLater(gui);

	}

}
