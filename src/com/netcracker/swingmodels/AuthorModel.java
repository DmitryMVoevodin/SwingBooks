package com.netcracker.swingmodels;

import com.netcracker.core.Author;
import com.netcracker.core.Book;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AuthorModel extends AbstractTableModel {

    private List<Author> authorsList = new ArrayList<>();
    public static int numberRecords = 1;

    /*public AuthorModel() {
        //
    }*/

    public AuthorModel(Book book) {
        if(book != null) {
            if(book.getAuthors() != null) {
                for(Author auth : book.getAuthors()) {
                    authorsList.add(auth);
                }
            }
        }
    }

    public void changeAuthorsList(Book book) {
        this.authorsList = (book != null) ? book.getAuthors() : null;
        fireTableDataChanged();
    }

    public void addAuthor(Author newAuthor){
        authorsList.add(newAuthor);
        fireTableDataChanged();
    }

    public void deleteAuthor(int rowIndex) {
        authorsList.remove(rowIndex);
        fireTableDataChanged();
    }

    public void deleteAllAuthor(){
        while(!authorsList.isEmpty()) {
            authorsList.remove(0);
        }
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        if(authorsList == null) return 0;
        return authorsList.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Author author = authorsList.get(rowIndex);
        switch (columnIndex){
            case 0:
                return author.getRecord();
            case 1:
                return author.getName();
            case 2:
                return author.getEmail();
            case 3:
                return author.getGender();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "#Record";
            case 1:
                return "Name";
            case 2:
                return "Email";
            case 3:
                return "Gender";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Character.class;
        }
        return Object.class;
    }


}
