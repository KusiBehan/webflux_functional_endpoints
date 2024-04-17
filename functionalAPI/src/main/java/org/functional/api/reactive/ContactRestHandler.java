package org.functional.api.reactive;

import org.functional.api.model.Contact;
import org.functional.api.service.ContactService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ContactRestHandler {


    ContactService contactService = new ContactService();

    //GET - find a contact by id
    public Mono<ServerResponse> getContactById(ServerRequest request){
        String id = request.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(contactService.getContactById(id));
    }

    //List all contacts
    public Mono<ServerResponse> getAllContacts(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(contactService.getAllContacts());
    }

    //Find a Contact by email address.
    public Mono<ServerResponse> getContactByEmail(ServerRequest request) {
        String email = request.pathVariable("email");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(contactService.getContactByEmail(email));
    }

    //Save a new Contact
    public Mono<ServerResponse> insertContact(ServerRequest request) {
        Mono<Contact> unsavedContact = request.bodyToMono(Contact.class);
        return  unsavedContact.flatMap(contact -> Mono.just(contactService.insertContact(contact))
                .flatMap(savedContact -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(contact)));
    }

    //Update an existing contact
    //Body of the Request should be a valid Json representation of the Class Contact
    public Mono<ServerResponse> updateContact(ServerRequest request) {
        Mono<Contact> contact = request.bodyToMono(Contact.class);
        String id = request.pathVariable("id");
        Mono<Contact> updatedContact = contact.flatMap(contact1 -> Mono.just(contactService.getContactById(id))
                .flatMap(oldContact -> {
                    oldContact
                            .setId(id) // not interchangeable
                            .setName(contact1.getName())
                            .setEmail(contact1.getEmail())
                            .setPhone(contact1.getPhone());
                    return Mono.just(contactService.updateContact(oldContact)); // update Contact
                }));
        return updatedContact.flatMap(contact1 ->
             ServerResponse.accepted()
                    .bodyValue(contact1));
    }

    public Mono<ServerResponse> deleteContact(ServerRequest request){
        String id = request.pathVariable("id");
        Contact deletedContact = contactService.deleteContactbyId(id);
        return ServerResponse.ok()
                .bodyValue(deletedContact);
    }
}
