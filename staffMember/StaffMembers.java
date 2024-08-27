package staffMember;
import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JPanel;

import control.Hospital;
import model.*;
import panels.GenericListPanel;



public class StaffMembers extends JPanel {


		private static final long serialVersionUID = 1L;
		private GenericListPanel<StaffMember> genericListPanel;
	    private DefaultListModel<StaffMember> listModel;

	  ;

	    public StaffMembers(String sectionName, DefaultListModel<StaffMember> listModel) {
	        this.listModel = listModel;
	        genericListPanel = new GenericListPanel<>(sectionName, listModel, this:: removeStaffMembertFromHospital, this::showAddStaffMemberDialog);
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

	    private void removeStaffMembertFromHospital(StaffMember stf) {
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
	}

