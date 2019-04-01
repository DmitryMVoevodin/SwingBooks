package com.netcracker.swingmodels;

import com.netcracker.core.Book;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChangeFrame extends InputChangeFrame {

    private Book book;
    private List<Book> booksList;
    private BookModel bookModel;
    private int index;

    ChangeFrame(Book book, List<Book> booksList, int index, BookModel bookModel) {
        super(book, "Change data", "resources/img/changeButtonIcon.png");
        recordTextField.setText(String.valueOf(book.getRecord()));
        isbnTextField.setText(String.valueOf(book.getISBN()));
        titleTextField.setText(book.getName());
        priceTextField.setText(String.valueOf(book.getPrice()));
        countTextField.setText(String.valueOf(book.getQty()));
        this.book = book;
        this.booksList = booksList;
        this.index = index;
        this.bookModel = bookModel;
        this.setVisible(true);
    }

    @Override
    public void acceptMethod() {
        if(isValidFields()) {
            if(book.getAuthors() == null || book.getAuthors().size() == 0) {
                JOptionPane.showMessageDialog(new JFrame(), "You must add at least one author!", "Warning",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            book.setISBN(Integer.parseInt(isbnTextField.getText()));
            book.setName(titleTextField.getText());
            book.setPrice(Double.parseDouble(priceTextField.getText()));
            book.setQty(Integer.parseInt(countTextField.getText()));
            if(booksList.size() == 0) {
                booksList.add(book);
            } else {
                booksList.remove(index);
                booksList.add(index, book);
            }
            bookModel.fireTableDataChanged();
            dispose();
        }
    }

}