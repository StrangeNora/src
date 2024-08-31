package staffMember;
import java.awt.Frame;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.Hospital;
import department.AddDoctorToDepartment;
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
    		
    		JButton addButton1 = UtilsMethods.createPanelButton("Doctors By Specialization");
    		
            addButton1.addActionListener(e -> {
                DocsBySpec docBySpec = new DocsBySpec(this);
                JDialog dialog = new JDialog((Frame) null, "Doctors By Specialization", true);
                dialog.getContentPane().add(docBySpec);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            });
            quickLinksPanel.add(addButton1);

	    	JButton addButton = UtilsMethods.createPanelButton("Add Staff Member");
	    	addButton.addActionListener(e -> {
	    		showAddStaffMemberDialog();
	    	});
	    	quickLinksPanel.add(addButton);
	    	JButton addButton22 = UtilsMethods.createPanelButton("Information About The Staff");
	    	
	    	JButton addDepartment = UtilsMethods.createPanelButton("Add Department to Staff Member");
	    	addDepartment.addActionListener(e -> {
	    		StaffMember selectedMember = getSelectedObject();
	    		if(selectedMember == null) {
	    			JOptionPane.showMessageDialog(null, "Please select one to add to");
	    			return;
	    		}
	    		AddDepartmentToStaffMember addDepartmentToStaffMember = new AddDepartmentToStaffMember(this, selectedMember);
		        JDialog dialog = new JDialog((Frame) null, "Add Department to Staff Member", true);
		        dialog.getContentPane().add(addDepartmentToStaffMember);
		        dialog.pack();
		        dialog.setLocationRelativeTo(null);
		        dialog.setVisible(true);
	    	});
	    	quickLinksPanel.add(addDepartment);
    	}
    	
    	quickLinksPanel.repaint();
    }
}


