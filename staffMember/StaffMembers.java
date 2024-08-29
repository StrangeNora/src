package staffMember;
import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JPanel;

import Patient.UpdatePatient;
import control.Hospital;
import enums.Role;
import model.*;
import panels.GenericListPanel;



public class StaffMembers extends JPanel {


		private static final long serialVersionUID = 1L;
		private GenericListPanel<StaffMember> genericListPanel;
	    private DefaultListModel<StaffMember> listModel;
	    private Role userRole;

	    public StaffMembers(Role userRole, String sectionName, DefaultListModel<StaffMember> listModel) {
	    	this.userRole = userRole;
	        this.listModel = listModel;
	        genericListPanel = new GenericListPanel<>(
	        		sectionName,
	        		listModel, 
	        		canRemove() ? this::removeStaffMemberFromHospital : null,
	        		canAdd() ? this::showAddStaffMemberDialog : null,
	        		canUpdate() ? this::showUpdateStaffMemberDialog : null
	        	);
	        loadStaffMembersFromHospital();
	    }

	    public JPanel getPanel() {
	        return genericListPanel.getPanel();
	    }

	    private void loadStaffMembersFromHospital() {
	        Hospital hospital = Hospital.getInstance();
	        HashMap<Integer, StaffMember> staffMember = hospital.getStaffMembers();
	        for (StaffMember stf : staffMember.values()) {
	            listModel.addElement(stf);
	        }
	    }

	    public void refreshList() {
	        listModel.clear();
	        loadStaffMembersFromHospital();
	    }

	    private void removeStaffMemberFromHospital(StaffMember stf) {
	        Hospital.getInstance().removeStaffMember(stf);
	    }

	    private void showAddStaffMemberDialog() {
	       AddStaffMember addStaffMember = new AddStaffMember(this);
	        JDialog dialog = new JDialog((Frame) null, "Add StaffMember", true);
	        dialog.getContentPane().add(addStaffMember);
	        dialog.pack();
	        dialog.setLocationRelativeTo(null);
	        dialog.setVisible(true);
	    }
	    private void showUpdateStaffMemberDialog(StaffMember stf) {
	        UpdateStaffMember updateStaffMember = new UpdateStaffMember(this);
	        JDialog dialog = new JDialog((Frame) null, "Update StaffMember", true);
	        dialog.getContentPane().add(updateStaffMember);
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

