package com.rokas;

import java.util.ArrayList;

public class Contact {
    private String contactsName;
    private int phoneNumber;

    private Contact(String contactsName, int phoneNumber){
        this.contactsName = contactsName;
        this.phoneNumber = phoneNumber;
    }

    public static Contact createContact(String name, int number){ // method to create a new object STATIC required due to access from other claass just by using Contact.method()
        return new Contact(name,number); // STATIC  is a convenience method.
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}