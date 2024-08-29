package medication;


import javax.swing.JPanel;

import Patient.UpdatePatient;

import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JPanel;

import control.Hospital;
import model.*;
import panels.GenericListPanel;



public class Medications extends JPanel {


		private static final long serialVersionUID = 1L;
		private GenericListPanel<Medication> genericListPanel;
	    private DefaultListModel<Medication> listModel;

	  ;

	    public Medications(String sectionName, DefaultListModel<Medication> listModel) {
	        this.listModel = listModel;
	        genericListPanel = new GenericListPanel<>(sectionName, listModel, this:: removeMedicationtFromHospital, this::showAddMedicationDialog
	        		,this::showUpdateMedicationDialog);
	        loadMedicationsFromHospital();
	    }

	    public JPanel getPanel() {
	        return genericListPanel.getPanel();
	    }

	    private void loadMedicationsFromHospital() {
	        Hospital hospital = Hospital.getInstance();
	        HashMap<Integer, Medication> Medication = hospital.getMedications();
	        for (Medication v : Medication.values()) {
	            listModel.addElement(v);
	        }
	    }

	    public void refreshList() {
	        listModel.clear();
	        loadMedicationsFromHospital();
	    }

	    private void removeMedicationtFromHospital(Medication v) {
	        Hospital.getInstance().removeMedication(v);
	    }

	    private void showAddMedicationDialog() {
	       AddMedication addMedication = new AddMedication(this);
	        JDialog dialog = new JDialog((Frame) null, "Add Medication", true);
	        dialog.getContentPane().add(addMedication);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }
	    private void showUpdateMedicationDialog(Medication m) {
	        UpdateMedication updateMedication = new UpdateMedication(this);
	        JDialog dialog = new JDialog((Frame) null, "Update Medication", true);
	        dialog.getContentPane().add(updateMedication);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }
	}


