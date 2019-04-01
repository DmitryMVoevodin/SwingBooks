package com.netcracker.core;

public class Author {

    private int record;
    private String name;
    private String email;
    private Extra.Gender gender;

    public Author(String name, String email, Extra.Gender gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Extra.Gender getGender() {
        return gender;
    }

    public void setGender(Extra.Gender gender) {
        this.gender = gender;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return ("Author[name=" + name + ", email=" + email + ", gender=" + gender + "]");
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Author)) return false;
        Author another = (Author) obj;
        return ((this.name.equals(another.name)) &&
                (this.email.equals(another.email)) &&
                (this.gender == another.gender));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + ((gender == Extra.Gender.Male) ? 0 : 1);
        return result;
    }

}
