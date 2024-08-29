package department;


import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JPanel;

import control.Hospital;
import enums.Role;
import model.*;
import panels.GenericListPanel;
public class Departments extends JPanel {


	

	    private static final long serialVersionUID = 1L;
	    private GenericListPanel<Department> genericListPanel;
	    private DefaultListModel<Department> listModel;
	    private Role userRole;

	    public Departments(Role userRole, String sectionName, DefaultListModel<Department> listModel) {	    	
	    	this.userRole = userRole;
	        this.listModel = listModel;
	        genericListPanel = new GenericListPanel<>(
	        		sectionName,
	        		listModel, 
	        		canRemove() ? this::removeDepartmentFromHospital : null,
	        		canAdd() ? this::showAddDepartmentDialog : null,
	        		canUpdate() ? this::showUpdateDepartmentDialog : null
	        	);
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
	    
	    private boolean canAdd() {
	    	return userRole == Role.Admin;
	    }
	    
	    private boolean canRemove() {
	    	return userRole == Role.Admin;
	    }
	    
	    private boolean canUpdate() {
	    	return userRole == Role.Admin;
	    }
	}


