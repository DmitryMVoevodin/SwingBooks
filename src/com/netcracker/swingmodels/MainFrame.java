package com.netcracker.swingmodels;

import com.netcracker.core.Author;
import com.netcracker.core.Book;
import com.netcracker.core.Extra;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.netcracker.core.Extra.Gender.Male;

public class MainFrame extends JFrame {

    private List<Book> booksList = new ArrayList<>();

    public MainFrame() {
        super("My library application");
        setIconImage((new ImageIcon("resources/img/MainFrameIcon.png")).getImage());
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Main");
        mainMenu.setIcon(new ImageIcon("resources/img/MainMenuIcon.png"));
        JMenu referenceMenu = new JMenu("Reference");
        referenceMenu.setIcon(new ImageIcon("resources/img/referenceMenuIcon.png"));
        JMenuItem referenceAboutMenuItem = new JMenuItem("About");
        referenceAboutMenuItem.setIcon(new ImageIcon("resources/img/referenceAboutMenuItemIcon.png"));
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.setIcon(new ImageIcon("resources/img/newMenuItemIcon.png"));
        JMenuItem importMenuItem = new JMenuItem("Import");
        importMenuItem.setIcon(new ImageIcon("resources/img/importMenuItemIcon.png"));
        JMenuItem exportMenuItem = new JMenuItem("Export");
        exportMenuItem.setEnabled(false);
        exportMenuItem.setIcon(new ImageIcon("resources/img/exportMenuItemIcon.png"));
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setIcon(new ImageIcon("resources/img/exitMenuItemIcon.png"));
        mainMenu.add(newMenuItem);
        mainMenu.addSeparator();
        mainMenu.add(importMenuItem);
        mainMenu.add(exportMenuItem);
        mainMenu.addSeparator();
        mainMenu.add(exitMenuItem);
        referenceMenu.add(referenceAboutMenuItem);
        menuBar.add(mainMenu);
        menuBar.add(referenceMenu);
        setJMenuBar(menuBar);

        JPanel changesPanel = new JPanel();
        changesPanel.setLayout(new GridBagLayout());
        changesPanel.setBackground(Color.LIGHT_GRAY);
        JButton addButton = new JButton("Add");
        JButton changeButton = new JButton("Change");
        JButton deleteButton = new JButton("Delete");
        JButton deleteAllButton = new JButton("Delete All");
        addButton.setIcon(new ImageIcon("resources/img/addButtonIcon.png"));
        changeButton.setIcon(new ImageIcon("resources/img/changeButtonIcon.png"));
        deleteButton.setIcon(new ImageIcon("resources/img/deleteButtonIcon.png"));
        deleteAllButton.setIcon(new ImageIcon("resources/img/deleteAllButtonIcon.png"));
        changesPanel.add(addButton, new GridBagConstraints(
                0,0,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(1, 3, 1, 3), 0 ,0));
        changesPanel.add(changeButton, new GridBagConstraints(
                1,0,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(1, 3, 1, 3), 0 ,0));
        changesPanel.add(deleteButton, new GridBagConstraints(
                2,0,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(1, 3, 1, 3), 0 ,0));
        changesPanel.add(deleteAllButton, new GridBagConstraints(
                3,0,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(1, 3, 1, 3), 0 ,0));
        addButton.setEnabled(false);
        changeButton.setEnabled(false);
        deleteButton.setEnabled(false);
        deleteAllButton.setEnabled(false);
        add(changesPanel, BorderLayout.NORTH);

        JPanel mainPanel1 = new JPanel();
        mainPanel1.setLayout(new GridBagLayout());
        mainPanel1.setBackground(Color.GRAY);
        JPanel mainPanel2 = new JPanel();
        mainPanel2.setLayout(new GridBagLayout());
        mainPanel2.setBackground(Color.GRAY);
        setLayout(new GridBagLayout());
        add(mainPanel1, new GridBagConstraints(
                0,3,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0 ,0));
        add(mainPanel2, new GridBagConstraints(
                0,4,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0 ,0));


        //Creating the table of books
        JLabel bookLabel = new JLabel("The Table of Books");
        bookLabel.setForeground(Color.WHITE);
        mainPanel1.add(bookLabel);
        BookModel bookModel = new BookModel(booksList);
        JTable tableForBooks = new JTable(bookModel);
        JScrollPane jScrollPaneForBooks = new JScrollPane(tableForBooks);
        mainPanel1.add(jScrollPaneForBooks, new GridBagConstraints(
                0,1,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0 ,0));
        //Creating the table of authors
        JLabel authorLabel = new JLabel("The Table of Authors");
        authorLabel.setForeground(Color.WHITE);
        mainPanel2.add(authorLabel);

        AuthorModel authorModel = new AuthorModel(null);
        JTable tableForAuthors = new JTable(authorModel);
        JScrollPane jScrollPaneForAuthors = new JScrollPane(tableForAuthors);
        mainPanel2.add(jScrollPaneForAuthors, new GridBagConstraints(
                0,1,1,1,1,1,
                GridBagConstraints.CENTER,GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0 ,0));

        //Reacting on the table rows
        ListSelectionModel bookSelectionModel = tableForBooks.getSelectionModel();
        bookSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bookSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                changeButton.setEnabled(true);
                deleteButton.setEnabled(true);
                int[] selectedRows = tableForBooks.getSelectedRows();
                int selectedIndex = (selectedRows.length == 0) ? (-1) : selectedRows[0];
                authorModel.changeAuthorsList((selectedIndex != -1) ? booksList.get(selectedIndex) : null);
            }
        });
        ListSelectionModel authorSelectionModel = tableForAuthors.getSelectionModel();
        authorSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Alignment in the cells of the tables
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableForBooks.setDefaultRenderer(Object.class, centerRenderer);
        tableForBooks.setDefaultRenderer(Integer.class, centerRenderer);
        tableForBooks.setDefaultRenderer(Double.class, centerRenderer);
        tableForAuthors.setDefaultRenderer(Object.class, centerRenderer);
        tableForAuthors.setDefaultRenderer(Integer.class, centerRenderer);
        tableForAuthors.setDefaultRenderer(Double.class, centerRenderer);

        newMenuItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                addButton.setEnabled(true);
                changeButton.setEnabled(false);
                deleteButton.setEnabled(false);
                deleteAllButton.setEnabled(true);
                newMenuItem.setEnabled(false);
                importMenuItem.setEnabled(false);
                exportMenuItem.setEnabled(true);
            }
        });

        importMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser filesToChoose = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Library-file (*.library)", "library");
                filesToChoose.setFileFilter(filter);
                int ret = filesToChoose.showDialog(null, "Import data");

                if(ret == JFileChooser.APPROVE_OPTION) {

                    String pathToFile = (filesToChoose.getSelectedFile()).getAbsolutePath();

                    boolean IOb1 = false;
                    BufferedReader reader = null;
                    try{
                        reader = new BufferedReader(new FileReader(pathToFile));
                    } catch (IOException IOe1) {
                        IOb1 = true;
                    }
                    if(!IOb1) {
                        try{
                            /*String line;
                            List<String> lines = new ArrayList<String>();
                            while ((line = reader.readLine()) != null) {
                                lines.add(line);
                            }
                            for(String s : lines) {
                                System.out.println(s);
                            }*/
                            BookModel.numberRecords = Integer.parseInt(reader.readLine());
                            AuthorModel.numberRecords = Integer.parseInt(reader.readLine());
                            int l = Integer.parseInt(reader.readLine());
                            if(booksList == null) {
                                booksList = new ArrayList<>();
                            }
                            for (int ii = 0; ii < l; ++ii) {
                                ArrayList<Author> authorArrayList = new ArrayList<Author>();
                                Book bb = new Book(0,"", authorArrayList,0,0);
                                bb.setRecord(Integer.parseInt(reader.readLine()));
                                bb.setISBN(Integer.parseInt(reader.readLine()));
                                bb.setName(reader.readLine());
                                bb.setPrice(Double.parseDouble(reader.readLine()));
                                bb.setQty(Integer.parseInt(reader.readLine()));
                                int ll = Integer.parseInt(reader.readLine());
                                for(int jj = 0; jj < ll; ++jj) {
                                    int rec = Integer.parseInt(reader.readLine());
                                    String strName = reader.readLine();
                                    String strEmail = reader.readLine();
                                    Extra.Gender gender = (Integer.parseInt(reader.readLine()) == 0) ? Extra.Gender.Male : Extra.Gender.Female;
                                    Author author = new Author(strName, strEmail, gender);
                                    author.setRecord(rec);
                                    authorArrayList.add(author);
                                }
                                booksList.add(bb);
                            }

                        } catch(IOException IOe2) {}
                        finally {
                            try{
                                reader.close();
                            } catch (IOException IOe3){};
                        }
                    }

                    addButton.setEnabled(true);
                    changeButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    deleteAllButton.setEnabled(true);
                    newMenuItem.setEnabled(false);
                    importMenuItem.setEnabled(false);
                    exportMenuItem.setEnabled(true);
                    bookModel.fireTableDataChanged();
                }
            }
        });

        exportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser filesToChoose = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Library-file (*.library)", "library");
                filesToChoose.setFileFilter(filter);
                int ret = filesToChoose.showDialog(null, "Export data");

                if(ret == JFileChooser.APPROVE_OPTION) {

                    File file = filesToChoose.getSelectedFile();
                    String pathToFileTmp = file.getAbsolutePath();
                    String fileNameTmp = file.getName();
                    String pathToFile = pathToFileTmp.replaceAll(fileNameTmp, "");
                    String fileNameTmp2 = fileNameTmp.replaceAll(".library", "");
                    String fileName = new String(fileNameTmp2 + ".library");

                    try(FileOutputStream fos = new FileOutputStream(pathToFile + fileName)) {
                        byte[] buffer;
                        buffer = (Integer.toString(BookModel.numberRecords) + "\n").getBytes();
                        fos.write(buffer, 0, buffer.length);
                        buffer = (Integer.toString(AuthorModel.numberRecords) + "\n").getBytes();
                        fos.write(buffer, 0, buffer.length);
                        int l = (booksList == null) ? 0 : (booksList.size());
                        buffer = (Integer.toString(l) + "\n").getBytes();
                        fos.write(buffer, 0, buffer.length);
                        for(int ii = 0; ii < l; ++ii){
                            Book bb = booksList.get(ii);
                            buffer = (Integer.toString(bb.getRecord()) + "\n").getBytes();
                            fos.write(buffer, 0, buffer.length);
                            buffer = (Integer.toString(bb.getISBN()) + "\n").getBytes();
                            fos.write(buffer, 0, buffer.length);
                            buffer = (bb.getName() + "\n").getBytes();
                            fos.write(buffer, 0, buffer.length);
                            buffer = (Double.toString(bb.getPrice()) + "\n").getBytes();
                            fos.write(buffer, 0, buffer.length);
                            buffer = (Integer.toString(bb.getQty()) + "\n").getBytes();
                            fos.write(buffer, 0, buffer.length);
                            int ll = (bb.getAuthors() == null) ? 0 : (bb.getAuthors().size());
                            buffer = (Integer.toString(ll) + "\n").getBytes();
                            fos.write(buffer, 0, buffer.length);
                            for(int jj = 0; jj < ll; ++jj) {
                                Author aa = bb.getAuthors().get(jj);
                                buffer = (Integer.toString(aa.getRecord()) + "\n").getBytes();
                                fos.write(buffer, 0, buffer.length);
                                buffer = (aa.getName() + "\n").getBytes();
                                fos.write(buffer, 0, buffer.length);
                                buffer = (aa.getEmail() + "\n").getBytes();
                                fos.write(buffer, 0, buffer.length);
                                if(aa.getGender() == Extra.Gender.Male) {
                                    buffer = ("0\n").getBytes();
                                } else {
                                    buffer = ("1\n").getBytes();
                                }
                                fos.write(buffer, 0, buffer.length);
                            }
                        }

                    } catch(IOException IOe){}

                }
            }
        });

        //Reacting on the reference menu item
        referenceAboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reference();
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddFrame(booksList, bookModel, BookModel.numberRecords);
                changeButton.setEnabled(false);
                deleteButton.setEnabled(false);
            }
        });

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = tableForBooks.getSelectedRows();
                int selectedIndex = (selectedRows.length == 0) ? (-1) : selectedRows[0];
                new ChangeFrame((selectedIndex == -1) ? null : newListAsCopy(booksList.get(selectedIndex)), booksList, Math.max(0, selectedIndex), bookModel);
                changeButton.setEnabled(false);
                deleteButton.setEnabled(false);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = tableForBooks.getSelectedRows();
                int selectedIndex = (selectedRows.length == 0) ? (-1) : selectedRows[0];
                if(selectedIndex != (-1)) {
                    bookModel.deleteBook(selectedIndex);
                }
                changeButton.setEnabled(false);
                deleteButton.setEnabled(false);
            }
        });

        deleteAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookModel.deleteAllBook();
                changeButton.setEnabled(false);
                deleteButton.setEnabled(false);
            }
        });


        setVisible(true);
        //pack();
    }

    private Book newListAsCopy(Book book) {
        if (book == null) return null;
        List<Author> authorsNew = null;
        List<Author> authorsOld = book.getAuthors();
        if(authorsOld != null) {
            authorsNew = new ArrayList<>();
            for(Author authOld : authorsOld) {
                Author authNew = new Author(
                        new String(authOld.getName()),
                        new String(authOld.getEmail()),
                        ((authOld.getGender() == Extra.Gender.Male) ? Extra.Gender.Male : Extra.Gender.Female));
                authNew.setRecord(authOld.getRecord());
                authorsNew.add(authNew);
            }
        }
        Book bookNew = new Book(book.getISBN(), new String(book.getName()), authorsNew, book.getPrice(), book.getQty());
        bookNew.setRecord(book.getRecord());
        return bookNew;
    }

}