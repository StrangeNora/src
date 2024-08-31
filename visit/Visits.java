package visit;

import javax.swing.JPanel;


import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.Hospital;
import enums.Role;
import model.*;
import panels.GenericListPanel;
import treatment.AddMedicalProblemToTreatment;
import utils.UtilsMethods;
import visit.*;



public class Visits extends SectionPanel<Visit> {


		private static final long serialVersionUID = 1L;
		private GenericListPanel<Visit> genericListPanel;
	    private DefaultListModel<Visit> listModel;
	    private Role userRole;

	    public Visits(Role userRole, String sectionName, DefaultListModel<Visit> listModel, JPanel quickLinksPanel) {
	    	super(userRole, sectionName, listModel, quickLinksPanel);
	    	this.initGenericListPanel(this::removeVisitFromHospital, this::showAddVisitDialog, this::showUpdateVisitDialog);
	    }

	    private void removeVisitFromHospital(Visit v) {
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
	    private void showUpdateVisitDialog(Visit v) {
	        UpdateVisit updateVisit = new UpdateVisit(this,v);
	        JDialog dialog = new JDialog((Frame) null, "Update Visit", true);
	        dialog.getContentPane().add(updateVisit);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }

	    protected void load() {
	        Hospital hospital = Hospital.getInstance();
	        HashMap<Integer, Visit> visit = hospital.getVisits();
	        for (Visit v : visit.values()) {
	            listModel.addElement(v);
	        }
	    }
	    
	    protected boolean canAdd() {
	    	return userRole != Role.Doctor;
	    }
	    
	    protected boolean canRemove() {
	    	return userRole != Role.Doctor;
	    }
	    
	    protected boolean canUpdate() {
	    	return userRole != Role.Doctor;
	    }

		@Override
		public void initializeQuickPanelButtons() {
			quickLinksPanel.removeAll();
	    	quickLinksPanel.add(UtilsMethods.getRightPanelTitleLabel(UtilsMethods.QUICK_LINKS_TITLE));
	    	
	    	if(canAdd()) {
				JButton addButton = UtilsMethods.createPanelButton("Add Visit");
		    	addButton.addActionListener(e -> {
		    		showAddVisitDialog();
		    	});
		    	quickLinksPanel.add(addButton);
		    	
		    	JButton addMedicalProblem = UtilsMethods.createPanelButton("Add Medical Problem To Visit");
		    	addMedicalProblem.addActionListener(e -> {
		    		Visit selectedMember = getSelectedObject();
		    		if(selectedMember == null) {
		    			JOptionPane.showMessageDialog(null, "Please select one to add to");
		    			return;
		    		}
		    		AddMedicalProblemToVisit addMedicalProblemToTreatment = new AddMedicalProblemToVisit(this, selectedMember);
			        JDialog dialog = new JDialog((Frame) null, "Add Medical Problem To Visit", true);
			        dialog.getContentPane().add(addMedicalProblemToTreatment);
			        dialog.pack();
			        dialog.setLocationRelativeTo(null);
			        dialog.setVisible(true);
		    	});
		    	quickLinksPanel.add(addMedicalProblem);
	    	}
	    	
	    	quickLinksPanel.repaint();
		}
	}


