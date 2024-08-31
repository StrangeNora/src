package department;


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
public class Departments extends SectionPanel<Department> {
	    private static final long serialVersionUID = 1L;

	    public Departments(Role userRole, String sectionName, DefaultListModel<Department> listModel, JPanel quickLinksPanel) {
	    	super(userRole, sectionName, listModel, quickLinksPanel);
	    	this.initGenericListPanel(this::removeDepartmentFromHospital, this::showAddDepartmentDialog, this::showUpdateDepartmentDialog);
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
	        UpdateDepartment updateDepartment = new UpdateDepartment(this,department);
	        JDialog dialog = new JDialog((Frame) null, "Update Department", true);
	        dialog.getContentPane().add(updateDepartment);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }
	    
	    protected void load() {
	        Hospital hospital = Hospital.getInstance();
	        HashMap<Integer, Department> departments = hospital.getDepartments();
	        for (Department department : departments.values()) {
	            listModel.addElement(department);
	        }
	    }
	    
	    protected boolean canAdd() {
	    	return userRole == Role.Admin;
	    }
	    
	    protected boolean canRemove() {
	    	return userRole == Role.Admin;
	    }
	    
	    protected boolean canUpdate() {
	    	return userRole == Role.Admin;
	    }

		@Override
		public void initializeQuickPanelButtons() {
			quickLinksPanel.removeAll();
	    	quickLinksPanel.add(UtilsMethods.getRightPanelTitleLabel(UtilsMethods.QUICK_LINKS_TITLE));
	    	
	    	if(canAdd()) {
				JButton addButton = UtilsMethods.createPanelButton("Add Department");
		    	addButton.addActionListener(e -> {
		    		showAddDepartmentDialog();
		    	});
		    	quickLinksPanel.add(addButton);
	    	}
		}
	}


