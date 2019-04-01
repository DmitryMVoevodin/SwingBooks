package com.netcracker.swingmodels;

import com.netcracker.core.Author;
import com.netcracker.core.Book;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddFrame extends InputChangeFrame {

    private Book book;
    private List<Book> booksList;
    private BookModel bookModel;

    AddFrame(List<Book> booksList, BookModel bookModel, int numberRecords) {
        super(new Book(0,"", new ArrayList<Author>(),0,0), "Add data", "resources/img/addButtonIcon.png");
        this.book = super.book;
        book.setRecord(numberRecords);
        recordTextField.setText(Integer.toString(book.getRecord()));
        this.booksList = booksList;
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
            booksList.add(booksList.size(), book);
            bookModel.fireTableDataChanged();
            BookModel.numberRecords++;
            dispose();
        }
    }
}
