package com.netcracker.swingmodels;

import com.netcracker.core.Author;
import com.netcracker.core.Book;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class InputChangeFrame extends JDialog {

    JLabel recordLabel;
    JLabel isbnLabel;
    JLabel titleLabel;
    JLabel priceLabel;
    JLabel countLabel;
    JTextField recordTextField;
    JTextField isbnTextField;
    JTextField titleTextField;
    JTextField priceTextField;
    JTextField countTextField;
    JLabel recordValidationLabel;
    JLabel isbnValidationLabel;
    JLabel titleValidationLabel;
    JLabel priceValidationLabel;
    JLabel countValidationLabel;
    JButton addButton;
    JButton deleteButton;
    Book book;
    AuthorModel authorModel;
    JTable tableForAuthors;
    JScrollPane jScrollPaneForAuthors;
    JButton acceptButton;
    JButton cancelButton;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    private int numRec = 0;

    public InputChangeFrame(Book book, String title, String icon){
        numRec = AuthorModel.numberRecords;
        setTitle(title);
        setIconImage((new ImageIcon(icon)).getImage());
        setSize(600, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setModal(true);
        recordLabel = new JLabel("#Record:");
        isbnLabel = new JLabel("ISBN:");
        titleLabel = new JLabel("Title:");
        priceLabel = new JLabel("Price:");
        countLabel = new JLabel("Count:");
        recordTextField = new JTextField(20); recordTextField.setEditable(false);
        isbnTextField = new JTextField(20);
        titleTextField = new JTextField(20);
        priceTextField = new JTextField(20);
        countTextField = new JTextField(20);
        recordValidationLabel = new JLabel("This field is not available for changing!");
        isbnValidationLabel = new JLabel();
        titleValidationLabel = new JLabel();
        priceValidationLabel = new JLabel();
        countValidationLabel = new JLabel();
        recordValidationLabel.setForeground(Color.GRAY);
        isbnValidationLabel.setForeground(Color.RED);
        titleValidationLabel.setForeground(Color.RED);
        priceValidationLabel.setForeground(Color.RED);
        countValidationLabel.setForeground(Color.RED);
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        addButton = new JButton("Add the author");
        deleteButton = new JButton("Delete the author");
        addButton.setIcon(new ImageIcon("resources/img/addButtonIcon.png"));
        deleteButton.setIcon(new ImageIcon("resources/img/deleteButtonIcon.png"));
        deleteButton.setEnabled(false);
        this.book = book;
        authorModel = new AuthorModel(book);
        tableForAuthors = new JTable(authorModel);
        jScrollPaneForAuthors = new JScrollPane(tableForAuthors);

        //Design for table cells
        ListSelectionModel authorSelectionModel = tableForAuthors.getSelectionModel();
        authorSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableForAuthors.setDefaultRenderer(Object.class, centerRenderer);
        tableForAuthors.setDefaultRenderer(Integer.class, centerRenderer);
        //Reacting on the table rows
        authorSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] selectedRows = tableForAuthors.getSelectedRows();
                int selectedIndex = (selectedRows.length == 0) ? (-1) : selectedRows[0];
                deleteButton.setEnabled((selectedIndex != (-1)) ? true : false);
            }
        });

        panel2 = new JPanel();
        acceptButton = new JButton("Accept");
        cancelButton = new JButton("Cancel");
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        //#Record
        panel1.add(recordLabel, new GridBagConstraints(
                0,0,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(recordTextField, new GridBagConstraints(
                1,0,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(recordValidationLabel, new GridBagConstraints(
                4,0,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        //ISBN
        panel1.add(isbnLabel, new GridBagConstraints(
                0,1,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(isbnTextField, new GridBagConstraints(
                1,1,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(isbnValidationLabel, new GridBagConstraints(
                4,1,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        //Title
        panel1.add(titleLabel, new GridBagConstraints(
                0,2,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(titleTextField, new GridBagConstraints(
                1,2,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(titleValidationLabel, new GridBagConstraints(
                4,2,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        //Price
        panel1.add(priceLabel, new GridBagConstraints(
                0,3,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(priceTextField, new GridBagConstraints(
                1,3,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(priceValidationLabel, new GridBagConstraints(
                4,3,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        //Count
        panel1.add(countLabel, new GridBagConstraints(
                0,4,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(countTextField, new GridBagConstraints(
                1,4,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(countValidationLabel, new GridBagConstraints(
                4,4,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        //The table of the authors
        panel2.add(addButton);
        panel2.add(deleteButton);
        panel2.add(jScrollPaneForAuthors);
        //Accept & Cancel buttons
        panel3.add(acceptButton);
        panel3.add(cancelButton);

        add(panel1, BorderLayout.NORTH);
        add(panel2);
        add(panel3, BorderLayout.SOUTH);


        //Cancel-button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AuthorModel.numberRecords = numRec;
                dispose();
            }
        });

        //Accept-button
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acceptMethod();
            }
        });

        //Add-the-author-button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(book.getAuthors() == null){
                    book.setAuthors(new ArrayList<>());
                }
                new AuthorsInputFrame(book.getAuthors(), authorModel);
            }
        });

        //Delete-the-author-button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = tableForAuthors.getSelectedRows();
                int selectedIndex = (selectedRows.length == 0) ? (-1) : selectedRows[0];
                System.out.println(selectedIndex);
                if(selectedIndex != (-1) && selectedRows != null && selectedRows.length != 0) {
                    authorModel.deleteAuthor(selectedIndex);
                    book.getAuthors().remove(selectedIndex);
                    deleteButton.setEnabled(false);
                }
            }
        });

    }


    protected boolean isValidFields() {
        boolean b = true;
        if(!((priceTextField.getText()).matches("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)"))) {
            b = false;
            priceValidationLabel.setText("Enter the valid number (double)!");
            if((priceTextField.getText()).matches("")) {
                priceValidationLabel.setText("The field can't be empty!");
            }
        } else {
            if(Double.parseDouble(priceTextField.getText()) < 0) {
                b = false;
                priceValidationLabel.setText("The field must be >= 0!");
            } else {
                priceValidationLabel.setText("");
            }
        }
        if(!((countTextField.getText()).matches("[+-]?[0-9]{1,}"))) {
            b = false;
            countValidationLabel.setText("Enter the valid number (int)!");
            if((countTextField.getText()).matches("")) {
                countValidationLabel.setText("The field can't be empty!");
            }
        } else {
            if(Integer.parseInt(countTextField.getText()) < 0) {
                b = false;
                countValidationLabel.setText("The field must be >= 0!");
            } else {
                countValidationLabel.setText("");
            }
        }
        if(!((isbnTextField.getText()).matches("[+-]?[0-9]{9}"))) {
            b = false;
            isbnValidationLabel.setText("Enter int with 9 figures!");
            if((isbnTextField.getText()).matches("")) {
                isbnValidationLabel.setText("The field can't be empty!");
            }
        } else {
            if(Integer.parseInt(isbnTextField.getText()) < 0) {
                b = false;
                isbnValidationLabel.setText("The field must be >= 0!");
            } else {
                isbnValidationLabel.setText("");
            }
        }
        if((titleTextField.getText() == null) || (titleTextField.getText().length() == 0)) {
            b = false;
            titleValidationLabel.setText("The field can't be empty!");
        } else {
            titleValidationLabel.setText("");
        }
        return b;
    }

    protected abstract void acceptMethod();

}