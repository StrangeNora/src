package department;


import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JPanel;

import control.Hospital;
import model.*;
import panels.GenericListPanel;
public class Departments extends JPanel {


	

	    private static final long serialVersionUID = 1L;
	    private GenericListPanel<Department> genericListPanel;
	    private DefaultListModel<Department> listModel;

	    public Departments(String sectionName, DefaultListModel<Department> listModel) {
	        this.listModel = listModel;
	        genericListPanel = new GenericListPanel<>(sectionName, listModel, this::removeDepartmentFromHospital, this::showAddDepartmentDialog, this::showUpdateDepartmentDialog);
	        loadDepartmentsFromHospital();
	    }

	    public JPanel getPanel() {
	        return genericListPanel.getPanel();
	    }

	    private void loadDepartmentsFromHospital() {
	        Hospital hospital = Hospital.getInstance();
	        HashMap<Integer, Department> departments = hospital.getDepartments();
	        for (Department department : departments.values()) {
	            listModel.addElement(department);
	        }
	    }

	    public void refreshList() {
	        listModel.clear();
	        loadDepartmentsFromHospital();
	    }

	    private void removeDepartmentFromHospital(Department department) {
	        Hospital.getInstance().removeDepartment(department);
	    }

	    private void showAddDepartmentDialog() {
	        AddDepartment addDepartment = new AddDepartment(this);
	        JDialog dialog = new JDialog((Frame) null, "Add Department", true);
	        dialog.getContentPane().add(addDepartment);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }

	    private void showUpdateDepartmentDialog(Department department) {
	        UpdateDepartment updateDepartment = new UpdateDepartment(this);
	        JDialog dialog = new JDialog((Frame) null, "Update Department", true);
	        dialog.getContentPane().add(updateDepartment);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }
	}


