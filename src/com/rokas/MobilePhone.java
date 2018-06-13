// Having too many method calls in one line aka method chain can be really hard to find errors when they happen
// Again the same just create variable then check null and set it


package com.rokas;

import java.util.ArrayList;
import java.util.Scanner;

public class MobilePhone {
    private static Scanner s = new Scanner(System.in);
    private static ArrayList<Contact> contacts;
    private String myNumber;

    public MobilePhone(String myNumber){
        this.myNumber = myNumber;
        this.contacts = new ArrayList<Contact>();
    }

    private void addContact(String name, int phoneNumber){

        if (getIndex(name)<0) {
            Contact contact = Contact.createContact(name, phoneNumber);
            contacts.add(contact); //  creating new Contact object to insert to Contacts ArrayList
        }
    }

    private void changeName (String oldName, String newName){
        int indexOldName = getIndex(oldName);
        int indexNewName = getIndex(newName);
        if(indexOldName<0){
            System.out.println("Could not find a contact with " + oldName + " name");
        }else if (indexNewName >= 0){
            System.out.println("Contact name " + newName + " already exists. Update was not successful.");
        } else {
            changeName(indexOldName,newName);
        }

    }

    private void changeName(int nameIndex, String newName){
        Contact contact = contacts.get(nameIndex);
        if (contact == null){
            System.out.println("Error. Null object");
        } else {
            contact.setContactsName(newName);
        }

    }

    private int getIndex(String contactName){

        for(int i = 0; i < contacts.size(); i++) {

            Contact contact = contacts.get(i);

            if (contact == null){
                System.out.println("Error. Null.");
            } else {

                String currentName = contact.getContactsName();

                if (currentName.equals(contactName)) { // does not work with ==. Thats why used .equals
                    return i;
                }
            }
        }

        return -1;
    }




    private void printContacts(){

        for(int i=0; i<contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact == null) {
                System.out.println("Error. Null");
            } else {
                System.out.println("Name: " + contact.getContactsName() + ". Phone number: " + contact.getPhoneNumber());
            }
        }

    }

    private void removeContact(String contactName){
        int indexToRemove = getIndex(contactName);
        if (indexToRemove >= 0) {
            Contact contact = contacts.get(indexToRemove);
            if (contact == null){
                System.out.println("Error. Null.");
            } else {
                contacts.remove(indexToRemove);
            }
        } else {
            System.out.println("Could not find a contact with " + contactName + " name");
        }

   }

   private void findContact(String contactName){
        int index = getIndex(contactName);
        if (index >= 0) {
            Contact contact = contacts.get(index);
            if (contact == null){
                System.out.println("Error. Null.");
            } else {
                System.out.println(contact.getContactsName() + " " + contact.getPhoneNumber());
            }
        } else {
            System.out.println("Could not find a contact with " + contactName + " name");
        }
    }


    private ArrayList<String> getContactsNames(){

        ArrayList<String> contactsNames = new ArrayList<String>();

        for(int i = 0; i<contacts.size(); i++){
            contactsNames.add(contacts.get(i).getContactsName());
        }

        return contactsNames;
    }

    private void printMenu(){
        System.out.println("Choose of one options. Press:");
        System.out.println("1 for add contact");
        System.out.println("2 for remove contact");
        System.out.println("3 for change contact name");
        System.out.println("4 for show all contacts");
        System.out.println("5 for find a contact");
        System.out.println("6 for close");
    }

    public void start(){

        String oldName;
        String newName;
        boolean isOn = true;

        while (isOn) {
            printMenu();

            switch (s.nextInt()) {

                case 1:
                    System.out.println("Enter contact name to be added");
                    oldName = s.next(); // have to use next, beause it is waiting for enter.
                    if (getIndex(oldName)<0){
                        System.out.println("Enter his phone number: ");
                        int phoneNumber = s.nextInt();
                        this.addContact(oldName, phoneNumber);
                    } else {
                        System.out.println(oldName + " could not add, because name already exists.");
                    }
                    break;
                case 2:
                    System.out.println("Enter contact name to remove:");
                    String name = s.next();
                    this.removeContact(name);
                    break;
                case 3:
                    System.out.println("Enter name to be changed: ");
                    oldName = s.next();
                    if (getIndex(oldName)>=0){
                        System.out.println("Enter a new name: ");
                        newName = s.next();
                        this.changeName(oldName, newName);
                    }  else {
                        System.out.println(oldName + " does not exists.");
                    }
                    break;
                case 4:
                    this.printContacts();
                    System.out.println("\n");
                    break;
                case 5:
                    System.out.println("Enter name to find: ");
                    oldName = s.next();
                    findContact(oldName);
                    break;
                case 6:
                    System.out.println("Goodbye");
                    isOn = false;
                    break;
            }
        }
    }
}


