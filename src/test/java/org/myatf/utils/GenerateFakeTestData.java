package org.myatf.utils;

import com.github.javafaker.Faker;

public class GenerateFakeTestData {

    private String email;
    private String firstName;
    private String lastName;
    private String password;

    Faker fkobj = new Faker();

    public String getEmail(String email) {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        fkobj.internet().emailAddress();
    }

    public String getFirstName(String firstName) {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        fkobj.address().firstName();
    }

    public String getLastName(String lastName) {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        fkobj.address().lastName();
    }

    public String getPassword(String password) {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        fkobj.internet().password();
    }

}