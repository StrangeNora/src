package view;
import panels.*;
import panels.ProfilePage;
import staffMember.*;
import treatment.Treatments;
import utils.UtilsMethods;
import visit.Visits;
import control.*;
import department.Departments;
import enums.Role;
import enums.Specialization;
import exceptions.*;
import javax.swing.*;
import Patient.*;
import exceptions.ObjectAlreadyExistsException;
import medicalProblem.MedicalProblems;
import medication.Medications;
import model.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class UserPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel centerPanel;
	private CardLayout cardLayout;
	private JPanel contentPanel;
	private JToolBar toolBar;
	private JPanel rightPanel;
	private Role userRole;
	private StaffMember staffUser;
	// DefaultListModels for each page
	private DefaultListModel<StaffMember> staffMembersListModel = new DefaultListModel<>();
	private DefaultListModel<String> SystemQueriesListModel = new DefaultListModel<>();
	private DefaultListModel<Medication> medicationsListModel = new DefaultListModel<>();
	private DefaultListModel<MedicalProblem> medicalProblemsListModel = new DefaultListModel<>();
	private DefaultListModel<Department> departmentsListModel = new DefaultListModel<>();
	private DefaultListModel<Treatment> treatmentsListModel = new DefaultListModel<>();
	private DefaultListModel<Visit> visitsListModel = new DefaultListModel<>();
	private DefaultListModel<Patient> patientListModel = new DefaultListModel<>();

	public UserPage(Role role, StaffMember staffUser) {
		this.userRole = role;
		this.staffUser=staffUser;
		
		// Right panel for buttons and set its background color
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setBackground(Color.decode("#096bbe"));

		// Initialize sidebar
		JPanel quickLinksPanel = initializeSidebar();
		
		HashMap<String, SectionPanel> sections = new HashMap<String, SectionPanel>();
		sections.put("Staff Members", new StaffMembers(userRole, "Staff Members", staffMembersListModel, quickLinksPanel));
		sections.put("Patient", new Patients(userRole, "Patient", patientListModel, quickLinksPanel));
		sections.put("Medications", new Medications(userRole, "Medications", medicationsListModel, quickLinksPanel));
		sections.put("Medical Problems", new MedicalProblems(userRole, "Medical Problems", medicalProblemsListModel, quickLinksPanel));
		sections.put("Departments", new Departments(userRole, "Departments", departmentsListModel, quickLinksPanel));
		sections.put("Treatments", new Treatments(userRole, "Treatments", treatmentsListModel, quickLinksPanel));
		sections.put("Visits", new Visits(userRole, "Visits", visitsListModel, quickLinksPanel));

		JPanel SystemQueriesPanel = new SystemQueries("SystemQueries", SystemQueriesListModel, quickLinksPanel).getPanel();

		createToolBar(sections);
		
		setTitle("Hospital Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		setMinimumSize(new Dimension(800, 600));

		// Add tool bar to the frame
		getContentPane().add(toolBar, BorderLayout.NORTH);

		// Center panel and set its background color
		centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(Color.decode("#096bbe"));
		getContentPane().add(centerPanel, BorderLayout.CENTER);

		// Initialize content panel with CardLayout
		cardLayout = new CardLayout();
		contentPanel = new JPanel(cardLayout);
		centerPanel.add(contentPanel, BorderLayout.CENTER);

		// Add content panels
		contentPanel.add(createHomePanel(), "Home");
		for(String name : sections.keySet()) {
			contentPanel.add(sections.get(name).getPanel(), name);			
		}
		//contentPanel.add(SystemQueriesPanel,"SystemQueries");
		
		getContentPane().add(rightPanel, BorderLayout.EAST);

		// Make the frame visible
		setSize(1000, 800);
		setVisible(true);

		// Adjust button font size on component resize
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				adjustButtonFontSize(rightPanel);
			}
		});
		
		repaint();
	}
	
	private void createToolBar(HashMap<String, SectionPanel> sections) {
		// Create the tool bar
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBackground(Color.decode("#096bbe"));
		JButton homeButton = createToolBarButton("Home");
		toolBar.add(homeButton);

		// Create buttons for each section
		homeButton.addActionListener(e -> cardLayout.show(contentPanel, "Home"));
		
		for(String name : sections.keySet()) {
			JButton sectionButton = createToolBarButton(name);
			toolBar.add(sectionButton);
			SectionPanel sectionPanel = sections.get(name);
			sectionButton.addActionListener(e -> {
				cardLayout.show(contentPanel, name);
				sectionPanel.initializeQuickPanelButtons();
			});
		}
		JButton SystemQueriesButton = createToolBarButton("SystemQueries");
		
		toolBar.add(SystemQueriesButton);
		SystemQueriesButton.addActionListener(e -> cardLayout.show(contentPanel, "SystemQueries"));
		
		// hide other staff members buttons if its not an admin
		if(userRole != Role.Admin) {
			
		}
	}


	private JPanel initializeSidebar() {
		rightPanel.removeAll();

		JPanel quickLinksPanel = createSidebarPanel("Quick Links");
		JPanel accountDetailsPanel = createSidebarPanel("Account Details");
		
		// edit account button
		JButton accountDetailsButton = UtilsMethods.createPanelButton("Edit Personal Details");
		accountDetailsButton.addActionListener(e -> {
			
		});

		accountDetailsPanel.add(accountDetailsButton);
		accountDetailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		JButton signOutButton = UtilsMethods.createPanelButton("Sign out");
		signOutButton.addActionListener(e -> {
			new LoginPage().setVisible(true);
            dispose();
		});
		accountDetailsPanel.add(signOutButton);
		accountDetailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		rightPanel.add(quickLinksPanel);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		rightPanel.add(accountDetailsPanel);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		rightPanel.revalidate();
		rightPanel.repaint();
		
		return quickLinksPanel;
	}

	private JPanel createSidebarPanel(String title) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.decode("#096bbe"));

		JLabel titleLabel = UtilsMethods.getRightPanelTitleLabel(title);
		panel.add(titleLabel);

		return panel;
	}

	private JButton createToolBarButton(String title) {
		JButton button = new JButton(title);
		button.setBackground(Color.decode("#7fb5de"));
		return button;
	}

	private JPanel createHomePanel() {
	    if (staffUser instanceof Doctor) {
	        ProfilePage doctorUser = new ProfilePage((Doctor) staffUser);
	        return doctorUser;
	    }
	    if (staffUser instanceof Nurse) {
	        ProfilePage nurseUser = new ProfilePage((Nurse) staffUser);
	        return nurseUser;
	    } else {
	        JPanel panel = new JPanel(new BorderLayout());
	        JLabel homeLabel = new JLabel("<html>Welcome Admin!<br><br>Manage all aspects of the hospital here.</html>", JLabel.CENTER);
	        homeLabel.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 18));
	        homeLabel.setForeground(Color.BLACK);

	        panel.add(homeLabel, BorderLayout.CENTER);
	        return panel;
	    }
	}


	private void adjustButtonFontSize(JComponent component) {
		int newSize = getHeight() / 50;
		Font newFont = new Font(component.getFont().getName(), Font.PLAIN, newSize);

		for (Component comp : component.getComponents()) {
			if (comp instanceof JButton) {
				comp.setFont(newFont);
			}
		}

		revalidate();
		repaint();
	}

	
}
