package com.netcracker.core;

import java.util.List;

public class Book {

    private int record;
    private int ISBN;
    private String name;
    private List<Author> authors;
    private double price;
    private int qty = 0;

    public Book(int ISBN, String name, List<Author> authors, double price) {
        this.ISBN = ISBN;
        this.name = name;
        this.authors = authors;
        this.price = price;
    }

    public Book(int ISBN, String name, List<Author> authors, double price, int qty) {
        this(ISBN, name, authors, price);
        this.qty = qty;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    private String authorsInformation() {
        String s = new String("");
        if(authors == null) {
            s += "No authors";
        } else {
            for(Author str : authors) {
                s += str.toString() + ", ";
            }
            s = s.substring(0, (s.length() - 2));
        }
        return s;
    }

    @Override
    public String toString() {
        return ("Book[name=" + name + ", authors={" + authorsInformation() + "}, price=" + price + ", qty=" + qty + "]");
    }

    public String getAuthorNames() {
        String s = new String("");
        if(authors == null) {
            s += "No authors";
        } else {
            for(Author str : authors) {
                s += str.getName() + ", ";
            }
            s = s.substring(0, (s.length() - 2));
        }
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Book)) return false;
        Book another = (Book) obj;
        boolean b = true;
        if(this.authors.size() != another.authors.size()) {
            b = false;
        } else {
            for(int i = 0; i < authors.size(); ++i) {
                if(!this.authors.get(i).equals(another.authors.get(i))) {
                    b = false;
                    break;
                }
            }
        }
        return ((this.name.equals(another.name)) && b &&
                (Extra.isEqual(this.price, another.price)) &&
                (this.qty == another.qty));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + ISBN;
        result = 31 * result + name.hashCode();
        for (Author auth : authors) {
            result = 31 * result + (auth == null ? 0 : auth.hashCode());
        }
        long r = Double.doubleToLongBits(price);
        result = 31 * result + ((int)(r ^ (r >>> 32)));
        result = 31 * result + qty;
        return result;
    }


}