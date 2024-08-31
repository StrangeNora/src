package Patient;

import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import control.Hospital;
import enums.Role;
import model.*;
import panels.GenericListPanel;
import utils.UtilsMethods;

public class Patients extends SectionPanel<Patient> {
    private static final long serialVersionUID = 1L;
    
    public Patients(Role userRole, String sectionName, DefaultListModel<Patient> listModel, JPanel quickLinksPanel) {
    	super(userRole, sectionName, listModel, quickLinksPanel);
    	this.initGenericListPanel(this::removePatientFromHospital, this::showAddPatientDialog, this::showUpdatePatientDialog);
    }

    @Override
    protected void load() {
        Hospital hospital = Hospital.getInstance();
        HashMap<Integer, Patient> patients = hospital.getPatients();
        for (Patient patient : patients.values()) {
            listModel.addElement(patient);
        }
    }
    
    private void removePatientFromHospital(Patient patient) {
        Hospital.getInstance().removePatient(patient);
    }

    private void showAddPatientDialog() {
        AddPatient addPatient = new AddPatient(this);
        JDialog dialog = new JDialog((Frame) null, "Add Patient", true);
        dialog.getContentPane().add(addPatient);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void showUpdatePatientDialog(Patient patient) {
        UpdatePatient updatePatient = new UpdatePatient(this,patient);
        JDialog dialog = new JDialog((Frame) null, "Update Patient", true);
        dialog.getContentPane().add(updatePatient);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    @Override
    public void initializeQuickPanelButtons() {
    	quickLinksPanel.removeAll();
    	quickLinksPanel.add(UtilsMethods.getRightPanelTitleLabel(UtilsMethods.QUICK_LINKS_TITLE));
    	
    	if(canAdd()) {
	    	JButton addButton = UtilsMethods.createPanelButton("Add Patient");
	    	addButton.addActionListener(e -> {
	    		showAddPatientDialog();
	    	});
	    	quickLinksPanel.add(addButton);
    	}
    	
    	quickLinksPanel.repaint();
	}

	@Override
	protected Object[][] getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getColumns() {
		// TODO Auto-generated method stub
		return null;
	}
}
