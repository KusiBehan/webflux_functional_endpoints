package org.functional.api.service;

import org.functional.api.model.Contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ContactService {

    private final List<Contact> contactList = (

            new ArrayList<>( Arrays.asList(
                    new Contact("1","Behan", "fake@gmail.com", "1243"),
                    new Contact("2","Behan", "fake@gmail.com", "1243"),
                    new Contact("3","Behan", "fake@gmail.com", "1243")
            ))
            );

    public List<Contact> getAllContacts(){
        return contactList;
    }

    public Contact getContactById(String id){
         Optional<Contact> contactOptional = contactList.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst();
         return contactOptional.orElse(null);
    }

    public Contact getContactByEmail(String email){
        Optional<Contact> contactOptional = contactList.stream()
                .filter(contact -> contact.getEmail().equals(email))
                .findFirst();
        return contactOptional.orElse(null);
    }


    //todo find a way to keep the data persistant and not violating the functional programming paradigma
    public Contact insertContact(Contact contactToSave){
        contactList.add(contactToSave);
        return contactToSave;
    }

    public Contact updateContact(Contact updatedContact){
        int index = contactList.indexOf(getContactById(updatedContact.getId()));
        contactList.set(index, updatedContact);
        return updatedContact;
    }


    public Contact deleteContactbyId(String id){
        Contact contactToRemove = getContactById(id);
        contactList.remove(contactToRemove);
        return contactToRemove;
    }
}