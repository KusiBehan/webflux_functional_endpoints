package org.functional.api;

import java.util.Arrays;
import java.util.List;

public class ContactService {
    public List<Contact> getAllContacts(){
        return Arrays.asList(
                new Contact("Behan", "fake@gmail.com", "1243"),
                new Contact("Behan", "fake@gmail.com", "1243"),
                new Contact("Behan", "fake@gmail.com", "1243")
        );
    }
}
