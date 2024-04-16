package org.functional.api;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ContactService {

    private final List<Contact> contactList = (
            Arrays.asList(
                    new Contact("1","Behan", "fake@gmail.com", "1243"),
                    new Contact("2","Behan", "fake@gmail.com", "1243"),
                    new Contact("3","Behan", "fake@gmail.com", "1243")
            )
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
}
