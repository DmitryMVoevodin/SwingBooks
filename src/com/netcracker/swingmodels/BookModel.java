package com.netcracker.swingmodels;

import com.netcracker.core.Book;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BookModel extends AbstractTableModel {

    private List<Book> booksList;
    public static int numberRecords = 1;

    public BookModel(List<Book> booksList){
        this.booksList = booksList;
    }

    public void addBook(Book newBook){
        booksList.add(newBook);
        fireTableDataChanged();
    }

    public void deleteBook(int rowIndex) {
        if(booksList.get(rowIndex).getAuthors() != null) {
            while(booksList.get(rowIndex).getAuthors().size() != 0) {
                booksList.get(rowIndex).getAuthors().remove(0);
            }
        }
        booksList.remove(rowIndex);
        fireTableDataChanged();
    }

    public void deleteAllBook(){
        while(!booksList.isEmpty()) {
            if(booksList.get(0).getAuthors() != null) {
                while(booksList.get(0).getAuthors().size() != 0) {
                    booksList.get(0).getAuthors().remove(0);
                }
            }
            booksList.remove(0);
        }
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return booksList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = booksList.get(rowIndex);
        switch (columnIndex){
            case 0:
                return book.getRecord();
            case 1:
                return book.getISBN();
            case 2:
                return book.getName();
            case 3:
                return book.getPrice();
            case 4:
                return book.getQty();
        }
        return null;
    }

    public Book getBook(int rowIndex) {
        return booksList.get(rowIndex);
    }


    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "#Record";
            case 1:
                return "ISBN";
            case 2:
                return "Title";
            case 3:
                return "Price";
            case 4:
                return "Count";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Integer.class;
            case 1:
                return Integer.class;
            case 2:
                return String.class;
            case 3:
                return Double.class;
            case 4:
                return Integer.class;
        }
        return Object.class;
    }


}
