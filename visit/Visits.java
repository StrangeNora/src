package visit;

import javax.swing.JPanel;


import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JPanel;

import control.Hospital;
import model.*;
import panels.GenericListPanel;
import visit.*;



public class Visits extends JPanel {


		private static final long serialVersionUID = 1L;
		private GenericListPanel<Visit> genericListPanel;
	    private DefaultListModel<Visit> listModel;

	  ;

	    public Visits(String sectionName, DefaultListModel<Visit> listModel) {
	        this.listModel = listModel;
	        genericListPanel = new GenericListPanel<>(sectionName, listModel, this:: removeVisittFromHospital, this::showAddVisitDialog,
	        		this::showUpdateVisitDialog);
	        loadVisitsFromHospital();
	    }

	    public JPanel getPanel() {
	        return genericListPanel.getPanel();
	    }

	    private void loadVisitsFromHospital() {
	        Hospital hospital = Hospital.getInstance();
	        HashMap<Integer, Visit> visit = hospital.getVisits();
	        for (Visit v : visit.values()) {
	            listModel.addElement(v);
	        }
	    }

	    public void refreshList() {
	        listModel.clear();
	        loadVisitsFromHospital();
	    }

	    private void removeVisittFromHospital(Visit v) {
	        Hospital.getInstance().removeVisit(v);
	    }

	    private void showAddVisitDialog() {
	       AddVisit addVisit = new AddVisit(this);
	        JDialog dialog = new JDialog((Frame) null, "Add Visit", true);
	        dialog.getContentPane().add(addVisit);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }
	    private void showUpdateVisitDialog(Visit stf) {
	        UpdateVisit updateVisit = new UpdateVisit(this);
	        JDialog dialog = new JDialog((Frame) null, "Update Visit", true);
	        dialog.getContentPane().add(updateVisit);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }
	}


