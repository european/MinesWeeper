package mw.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import mw.model.ButtonStatus;
import mw.view.FeldButton;


public class BoardController {
	
	/*class ToggleButtonListner implements MouseListener {

        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {
        	FeldButton but = (FeldButton) e.getSource();
        	
        	but.mouseEnter();
        }
        public void mouseExited(MouseEvent e) {
        	FeldButton but = (FeldButton) e.getSource();
        
        	but.mouseExit();
        }
        public void mousePressed(MouseEvent e) {
        	FeldButton but = (FeldButton) e.getSource();

            if(e.getButton() == MouseEvent.BUTTON3) {
                if(but.getButtonStatus() == ButtonStatus.DEFAULT 
                		|| but.getButtonStatus() == ButtonStatus.FLAG)
                    but.toggleFlagged();
            }
            else if(e.getButton() == MouseEvent.BUTTON1) {
                if(but.isFlagged()) {
                    but.setSelected(true);
                    return;
                }

                showCell(e);
            }
        }
        public void mouseReleased(MouseEvent e) {}
    }*/

}
