package org.myatf.utils;

import com.github.javafaker.Faker;

public class GenerateFakeTestData {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    Faker fakerObject = new Faker();

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void generateRandomEmail(){
        setEmail(fakerObject.internet().emailAddress());
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void generateRandomFirstName(){
        setFirstName(fakerObject.address().firstName());
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void generateRandomLastName(){
        setLastName(fakerObject.address().lastName());
    }
    public String getPassword() {
       return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void generateRandomPassword(){
        setPassword(fakerObject.internet().password(10,16,true,true));
    }
}