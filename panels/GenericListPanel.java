package panels;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;

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
    private CustomTableModel tableModel;
    private Object[][] tableData;

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
        tableModel = new CustomTableModel(tableData, columns);
        JTable table = new JTable(tableModel);
        table.setDefaultRenderer(Object.class, new TableCellRendererEditor());
//        table.setDefaultRenderer(JComboBox.class, new TableCellRendererEditor());
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
	            int selectedIndex = list.getSelectedIndex();
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
	            int selectedIndex = list.getSelectedIndex();
	            if (selectedIndex != -1) {
	                T selectedItem = listModel.getElementAt(selectedIndex);
	                removeCallback.accept(selectedItem); // Call the removal logic
	                listModel.remove(selectedIndex);
	                JOptionPane.showMessageDialog(null, sectionName + " Removed Successfully");
	            }
	        });
        }

        boolean shouldShowMenu = updateCallback != null || removeCallback != null;
        // Add mouse listener to show pop-up menu on right-click
        
//        list.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mousePressed(java.awt.event.MouseEvent evt) {
//                showPopup(evt);
//            }
//
//            public void mouseReleased(java.awt.event.MouseEvent evt) {
//                showPopup(evt);
//            }
//
//            private void showPopup(java.awt.event.MouseEvent evt) {
//                if (shouldShowMenu && evt.isPopupTrigger()) { // This is true for right-clicks
//                    int index = list.locationToIndex(evt.getPoint());
//                    list.setSelectedIndex(index); // Select the item that was right-clicked
//                    popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
//                }
//            }
//        });

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
    	tableModel.setData(tableData);
    	
    }
    
    public T getSelectedObject() {
    	return list.getSelectedValue();
    }

    public JPanel getPanel() {
        return panel;
    }
}
