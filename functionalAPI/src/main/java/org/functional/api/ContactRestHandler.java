package org.functional.api;

import com.mongodb.internal.connection.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
public class ContactRestHandler {


    private Mono<ServerResponse> response404
            = ServerResponse.notFound().build();

    private Mono<ServerResponse> response406
            = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();

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

    public Mono<ServerResponse> insertContact(ServerRequest request) {
        Mono<Contact> unsavedContact = request.bodyToMono(Contact.class);
    return  unsavedContact.flatMap(contact -> Mono.just(contactService.insertContact(contact))
                .flatMap(savedContact -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(contact)));
    }


    //Save a new Contact

    //Update an existing contact

    //Delete a Contact
}
