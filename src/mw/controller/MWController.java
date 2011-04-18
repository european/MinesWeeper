package mw.controller;

import mw.model.MWModel;
import mw.view.MWViewer;

public class MWController {
	
	MWModel m_model;
		
	public MWController() {
		MWViewer viewer = new MWViewer(this);
		m_model = new MWModel();
		m_model.addObserver(viewer);
    }
	
	// Benutzerdefinierte Einstellungen
	
	
}
