package mw.main;

import javax.swing.SwingUtilities;

import mw.controller.MWController;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//MWModel model = new MWModel();
		//MWViewer view = new MWViewer(model);
		//MWController controller = new MWController();
		
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
