package panels;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import enums.Role;
import utils.CustomTableModel;
import utils.TableCellRendererEditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.*;

public class GenericListPanel<T> {
    private JPanel panel;
    private DefaultListModel<T> listModel;
    private JTextField searchField;
    private JList<T> list;
    private DefaultTableModel tableModel;
    private Object[][] tableData;
    JTable table;
    private int selectedIdx = -1;

    public GenericListPanel(Object[][] tableData, String[] columns, String sectionName, DefaultListModel<T> listModel, Consumer<T> removeCallback, Runnable addCallback, Consumer<T> updateCallback) {
    	this.tableData = tableData;
        this.listModel = listModel;
        panel = new JPanel(new BorderLayout());
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        JButton addButton = new JButton("Add");

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(addButton);

        // List and scroll pane
        tableModel = new DefaultTableModel(tableData, columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return Object.class; // Both JLabel and JComboBox are derived from Object
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return getValueAt(row, column) instanceof JComboBox;
//            	return true;
            }
        };;
        table = new JTable(tableModel);
        table.setDefaultRenderer(Object.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            	Component comp = value instanceof Component ? (Component) value : new JLabel("");  
                
            	if (table.isRowSelected(row)) {
            		if(comp instanceof JLabel) {
            			((JLabel) comp).setOpaque(true);
            		}
                    comp.setBackground(Color.CYAN); // Set your preferred highlight color
                } else {
                	comp.setBackground(Color.WHITE); // Set the default background color
                }
            	
                return comp;
            }
        });

        // Set the custom editor
        table.setDefaultEditor(Object.class, new DefaultCellEditor(new JComboBox<>()) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                if (value instanceof JComboBox) {
                    return (JComboBox<?>) value; // Cast value to JComboBox
                }
                return (JLabel) value;//super.getTableCellEditorComponent(table, value, isSelected, row, column);
            }
        });
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        JScrollPane listScrollPane = new JScrollPane(table);
        
//        list = new JList<>(listModel);
//        JScrollPane listScrollPane = new JScrollPane(list);

//        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(listScrollPane, BorderLayout.CENTER);

        // Create the popup menu and the "Remove" item
        JPopupMenu popupMenu = new JPopupMenu();
        
        // Update menu item button
        if(updateCallback != null) {
	        JMenuItem updateMenuItem = new JMenuItem("Update");
	        popupMenu.add(updateMenuItem);
	        // Add action listener to "Update" menu item
	        updateMenuItem.addActionListener(e -> {
	            int selectedIndex = table.getSelectedRow();
	            if (selectedIndex != -1) {
	                T selectedItem = listModel.getElementAt(selectedIndex);
	                updateCallback.accept(selectedItem); // Call the update logic
	            }
	        });
        }

        // Remove menu item button
        if(removeCallback != null) {
	        JMenuItem removeMenuItem = new JMenuItem("Remove");
	        popupMenu.add(removeMenuItem);
	        // Add action listener to "Remove" menu item
	        removeMenuItem.addActionListener(e -> {
	        	int selectedIndex = table.getSelectedRow();
	            if (selectedIndex != -1) {
	            	T selectedItem = listModel.getElementAt(selectedIndex);
	            	listModel.remove(selectedIndex);
	                removeCallback.accept(selectedItem); // Call the removal logic
	                JOptionPane.showMessageDialog(null, sectionName + " Removed Successfully");
	            }
	        });
        }

        boolean shouldShowMenu = updateCallback != null || removeCallback != null;
        // Add mouse listener to show pop-up menu on right-click
        
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                showPopup(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                showPopup(evt);
            }

            private void showPopup(java.awt.event.MouseEvent evt) {
                if (shouldShowMenu && evt.isPopupTrigger()) { // This is true for right-clicks
                    int index = table.getSelectedRow();
//                    list.setSelectedIndex(index); // Select the item that was right-clicked
                    popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        });

        // Add action listener for the "Add" button
        addButton.addActionListener(e -> addCallback.run());
        
        if(addCallback == null) {
        	addButton.setVisible(false);
        }

        // Add action listener for the "Search" button
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().trim();
            if (listModel.isEmpty()) {
                JOptionPane.showMessageDialog(null, "The List Is Empty At The Moment", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean found = false;
            for (int i = 0; i < listModel.size(); i++) {
                T item = listModel.get(i);
                if (item.toString().toLowerCase().contains(searchText.toLowerCase())) {
                    list.ensureIndexIsVisible(i);
                    list.setSelectedIndex(i);
                    list.setSelectionBackground(Color.decode("#7F9DBB")); // Use the desired color
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "The " + sectionName + " Does Not Exist", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    public void refreshTableData(Object[][] tableData) {
        tableModel.setRowCount(0);

        for (Object[] row : tableData) {
            tableModel.addRow(row);
        }
        tableModel.fireTableDataChanged();
        selectedIdx = -1;
    }
    
    public T getSelectedObject() {
    	return listModel.getElementAt(table.getSelectedRow());
    }

    public JPanel getPanel() {
        return panel;
    }
}
