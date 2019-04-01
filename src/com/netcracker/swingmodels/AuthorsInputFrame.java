package com.netcracker.swingmodels;

import com.netcracker.core.Author;
import com.netcracker.core.Extra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AuthorsInputFrame extends JDialog {

    private Extra.Gender[] itemsForComboBox = { Extra.Gender.Male, Extra.Gender.Female };
    private List<Author> authors;
    private AuthorModel authorModel;

    public AuthorsInputFrame(List<Author> authors, AuthorModel authorModel) {

        this.authors = (authors != null) ? authors : (new ArrayList<>());
        this.authorModel = authorModel;
        setTitle("Add the author");
        setIconImage((new ImageIcon("resources/img/addButtonIcon.png")).getImage());
        setSize(700, 210);
        setResizable(false);
        setLocationRelativeTo(null);
        setModal(true);
        JLabel recordLabel = new JLabel("#Record:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel genderLabel = new JLabel("Gender:");
        JTextField recordTextField = new JTextField(20); recordTextField.setEditable(false);
        JTextField nameTextField = new JTextField(20);
        JTextField emailTextField = new JTextField(20);
        JComboBox<Extra.Gender> genderComboBox = new JComboBox<Extra.Gender>(itemsForComboBox);
        JLabel recordValidationLabel = new JLabel("This field is not available for changing!");
        JLabel nameValidationLabel = new JLabel();
        JLabel emailValidationLabel = new JLabel();
        recordValidationLabel.setForeground(Color.GRAY);
        nameValidationLabel.setForeground(Color.RED);
        emailValidationLabel.setForeground(Color.RED);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        JButton acceptButton = new JButton("Accept");
        JButton cancelButton = new JButton("Cancel");
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
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
        panel1.add(nameLabel, new GridBagConstraints(
                0,1,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(nameTextField, new GridBagConstraints(
                1,1,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(nameValidationLabel, new GridBagConstraints(
                4,1,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        //Title
        panel1.add(emailLabel, new GridBagConstraints(
                0,2,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(emailTextField, new GridBagConstraints(
                1,2,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(emailValidationLabel, new GridBagConstraints(
                4,2,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        //Price
        panel1.add(genderLabel, new GridBagConstraints(
                0,3,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));
        panel1.add(genderComboBox, new GridBagConstraints(
                1,3,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0 ,0));

        //Accept & Cancel buttons
        panel2.add(acceptButton);
        panel2.add(cancelButton);

        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.SOUTH);

        recordTextField.setText(Integer.toString(AuthorModel.numberRecords));

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean b = true;
                if((nameTextField.getText() == null) || (nameTextField.getText().length() == 0)) {
                    b = false;
                    nameValidationLabel.setText("The field can't be empty!");
                } else {
                    nameValidationLabel.setText("");
                }
                if((emailTextField.getText() == null) || (emailTextField.getText().length() == 0)) {
                    b = false;
                    emailValidationLabel.setText("The field can't be empty!");
                } else {
                    if(!((emailTextField.getText()).matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+"))) {
                        b = false;
                        emailValidationLabel.setText("It's like abc@mail.ru or yt@gmai.com!");
                    } else {
                        emailValidationLabel.setText("");
                    }
                }
                if(b) {
                    Author auth = new Author(nameTextField.getText(), emailTextField.getText(), genderComboBox.getItemAt(genderComboBox.getSelectedIndex()));
                    auth.setRecord(AuthorModel.numberRecords++);
                    authors.add(auth);
                    authorModel.addAuthor(auth);
                    dispose();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

}