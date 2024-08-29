package view;
import panels.*;
import staffMember.*;
import treatment.Treatments;
import visit.Visits;
import control.*;
import department.Departments;
import enums.Role;
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

public class UserPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel centerPanel;
	private CardLayout cardLayout;
	private JPanel contentPanel;
	private JToolBar toolBar;
	private JPanel rightPanel;
	private int buttonWidth = 260;
	private Role userRole;

	// DefaultListModels for each page
	private DefaultListModel<StaffMember> staffMembersListModel = new DefaultListModel<>();
	private DefaultListModel<String> SystemQueriesListModel = new DefaultListModel<>();
	private DefaultListModel<Medication> medicationsListModel = new DefaultListModel<>();
	private DefaultListModel<MedicalProblem> medicalProblemsListModel = new DefaultListModel<>();
	private DefaultListModel<Department> departmentsListModel = new DefaultListModel<>();
	private DefaultListModel<Treatment> treatmentsListModel = new DefaultListModel<>();
	private DefaultListModel<Visit> visitsListModel = new DefaultListModel<>();
	private DefaultListModel<Patient> patientListModel = new DefaultListModel<>();

	public UserPage(Role role) {
		this.userRole = role;
		JPanel staffMemberPanel = new StaffMembers(userRole, "StaffMembers", staffMembersListModel).getPanel();

		JPanel patientsPanel = new Patients(userRole, "Patient", patientListModel).getPanel();
		JPanel medicationsPanel = new Medications(userRole, "Medications", medicationsListModel).getPanel();
		JPanel medicalProblemsPanel = new MedicalProblems(userRole, "Medical Problems", medicalProblemsListModel).getPanel();
		JPanel departmentsPanel = new Departments(userRole, "Departments", departmentsListModel).getPanel();
		JPanel treatmentsPanel = new Treatments(userRole, "Treatments", treatmentsListModel).getPanel();
		JPanel visitsPanel = new Visits(userRole, "Visits", visitsListModel).getPanel();
		JPanel SystemQueriesPanel = new SystemQueries("SystemQueries", SystemQueriesListModel).getPanel();
		createToolBar();
		
		setTitle("Hospital Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		setMinimumSize(new Dimension(800, 600));

		// Add tool bar to the frame
		getContentPane().add(toolBar, BorderLayout.NORTH);

		// Center panel and set its background color
		centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(Color.decode("#A9BED2"));
		getContentPane().add(centerPanel, BorderLayout.CENTER);

		// Initialize content panel with CardLayout
		cardLayout = new CardLayout();
		contentPanel = new JPanel(cardLayout);
		centerPanel.add(contentPanel, BorderLayout.CENTER);

		// Add content panels
		contentPanel.add(createHomePanel(), "Home");
		contentPanel.add(staffMemberPanel,"StaffMembers");
	
		contentPanel.add(patientsPanel,"Patients");
		contentPanel.add(medicationsPanel,"Medications");
		contentPanel.add(medicalProblemsPanel,"Medical Problems");
		contentPanel.add(departmentsPanel,"Departments");
		contentPanel.add(treatmentsPanel,"Treatments");
		contentPanel.add(visitsPanel,"Visits");
		contentPanel.add(SystemQueriesPanel,"SystemQueries");
		// Right panel for buttons and set its background color
		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setBackground(Color.decode("#547DA5"));
		getContentPane().add(rightPanel, BorderLayout.EAST);

		// Initialize sidebar
		initializeSidebar();

		// Make the frame visible
		setSize(1000, 800);
		setVisible(true);

		// Adjust button font size on component resize
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				adjustButtonFontSize(rightPanel);
			}
		});
	}
	
	private void createToolBar() {
		// Create the tool bar
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBackground(Color.decode("#2a5d8f"));

		// Create buttons for each section
		JButton homeButton = createToolBarButton("Home");
		JButton staffMemberButton = createToolBarButton("Staff Members");
		JButton SystemQueriesButton = createToolBarButton("SystemQueries");
		JButton patientsButton = createToolBarButton("Patients");
		JButton medicationsButton = createToolBarButton("Medications");
		JButton medicalProblemsButton = createToolBarButton("Medical Problems");
		JButton departmentsButton = createToolBarButton("Departments");
		JButton treatmentsButton = createToolBarButton("Treatments");
		JButton visitsButton = createToolBarButton("Visits");

		// Add buttons to the toolbar
		toolBar.add(homeButton);
		toolBar.add(staffMemberButton);
		toolBar.add(SystemQueriesButton);
		toolBar.add(patientsButton);
		toolBar.add(medicationsButton);
		toolBar.add(medicalProblemsButton);
		toolBar.add(departmentsButton);
		toolBar.add(treatmentsButton);
		toolBar.add(visitsButton);
		
		// Add action listeners to toolbar buttons
		homeButton.addActionListener(e -> cardLayout.show(contentPanel, "Home"));
		staffMemberButton.addActionListener(e -> cardLayout.show(contentPanel, "StaffMembers"));
		
		patientsButton.addActionListener(e -> cardLayout.show(contentPanel, "Patients"));
		medicationsButton.addActionListener(e -> cardLayout.show(contentPanel, "Medications"));
		medicalProblemsButton.addActionListener(e -> cardLayout.show(contentPanel, "Medical Problems"));
		departmentsButton.addActionListener(e -> cardLayout.show(contentPanel, "Departments"));
		treatmentsButton.addActionListener(e -> cardLayout.show(contentPanel, "Treatments"));
		visitsButton.addActionListener(e -> cardLayout.show(contentPanel, "Visits"));
		SystemQueriesButton.addActionListener(e -> cardLayout.show(contentPanel, "SystemQueries"));
		// hide other staff members buttons if its not an admin
		if(userRole != Role.Admin) {
			staffMemberButton.setVisible(false);
			
		}
	}


	private void initializeSidebar() {
		rightPanel.removeAll();

		// Create buttons again with the fixed width
		JPanel quickLinksPanel = createSidebarSection("Quick Links", new String[]{
				"Add Patient", 
				"Add Doctor", 
				"Add Nurse", 
				"Add Medication", 
				"Add Medical Problem", 
				"Add Department", 
				"Add Treatment", 
				"Add Visit"
		});

		JPanel accountDetailsPanel = createSidebarSection("Account Details", new String[]{
				"Edit Personal Details"
		});

		rightPanel.add(quickLinksPanel);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		rightPanel.add(accountDetailsPanel);
		rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		rightPanel.revalidate();
		rightPanel.repaint();
	}

	private JPanel createSidebarSection(String title, String[] buttonNames) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.decode("#547DA5"));

		JLabel titleLabel = new JLabel(title);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(titleLabel);

		// Set the fixed button size for all buttons
		Dimension buttonSize = new Dimension(buttonWidth, 40);

		for (String buttonName : buttonNames) {
			JButton button = createUniformButton(buttonName);
			button.setPreferredSize(buttonSize);
			button.setMaximumSize(buttonSize);
			button.setBackground(Color.decode("#D4DEE8"));
			panel.add(button);
			panel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between buttons
		}

		return panel;
	}

	private JButton createUniformButton(String buttonName) {
		JButton button = new JButton(buttonName);
		button.setBackground(Color.decode("#D4DEE8"));
		button.setFont(new Font("Arial", Font.PLAIN, 14)); // Set a fixed font size

		// Set a fixed width and height for the button
		button.setPreferredSize(new Dimension(buttonWidth, 40));
		button.setMaximumSize(new Dimension(buttonWidth, 40));

		return button;
	}

	private JButton createToolBarButton(String title) {
		JButton button = new JButton(title);
		button.setBackground(Color.decode("#D4DEE8"));
		return button;
	}

	private JPanel createHomePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel homeLabel = new JLabel("<html>Welcome Admin!<br><br>Manage all aspects of the hospital here.</html>", JLabel.CENTER);
		homeLabel.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 18));
		homeLabel.setForeground(Color.BLACK);

		panel.add(homeLabel, BorderLayout.CENTER);

		return panel;
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
private void loadDataFromHospital() {
	

	
	
}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new UserPage(Role.Admin));
	}
}
