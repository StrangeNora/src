package Patient;

import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JPanel;

import control.Hospital;
import model.*;
import panels.GenericListPanel;
public class Patients extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GenericListPanel<Patient> genericListPanel;
    private DefaultListModel<Patient> listModel;

  ;

    public Patients(String sectionName, DefaultListModel<Patient> listModel) {
        this.listModel = listModel;
        genericListPanel = new GenericListPanel<>(sectionName, listModel, this::removePatientFromHospital, this::showAddPatientDialog);
        loadPatientsFromHospital();
    }

    public JPanel getPanel() {
        return genericListPanel.getPanel();
    }

    private void loadPatientsFromHospital() {
        Hospital hospital = Hospital.getInstance();
        HashMap<Integer, Patient> patients = hospital.getPatients();
        for (Patient patient : patients.values()) {
            listModel.addElement(patient);
        }
    }

    public void refreshList() {
        listModel.clear();
        loadPatientsFromHospital();
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
}
