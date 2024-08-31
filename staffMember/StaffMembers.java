package staffMember;
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



public class StaffMembers extends SectionPanel<StaffMember> {
	private static final long serialVersionUID = 1L;

	public StaffMembers(Role userRole, String sectionName, DefaultListModel<StaffMember> listModel, JPanel quickLinksPanel) {
    	super(userRole, sectionName, listModel, quickLinksPanel);
    	this.initGenericListPanel(this::removeStaffMemberFromHospital, this::showAddStaffMemberDialog, this::showUpdateStaffMemberDialog);
    }

    protected void load() {
        Hospital hospital = Hospital.getInstance();
        HashMap<Integer, StaffMember> staffMember = hospital.getStaffMembers();
        for (StaffMember stf : staffMember.values()) {
            listModel.addElement(stf);
        }
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
        UpdateStaffMember updateStaffMember = new UpdateStaffMember(this, stf);
        JDialog dialog = new JDialog((Frame) null, "Update StaffMember", true);
        dialog.getContentPane().add(updateStaffMember);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
    public void initializeQuickPanelButtons() {
    	quickLinksPanel.removeAll();
    	quickLinksPanel.add(UtilsMethods.getRightPanelTitleLabel(UtilsMethods.QUICK_LINKS_TITLE));
    	
    	if(canAdd()) {
	    	JButton addButton = UtilsMethods.createPanelButton("Add Staff Member");
	    	addButton.addActionListener(e -> {
	    		showAddStaffMemberDialog();
	    	});
	    	quickLinksPanel.add(addButton);
    	}
    	
    	quickLinksPanel.repaint();
    }
}


