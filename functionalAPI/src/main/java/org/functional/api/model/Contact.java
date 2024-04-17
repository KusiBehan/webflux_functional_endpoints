package org.functional.api.model;

public class Contact {
    private String id;
    private String name;
    private String email;
    private String phone;

    public Contact(){
        // For deserialization purposes
    }

    public Contact(String id ,String name, String email, String phone){
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }



    public String getId() {
        return id;
    }

    public Contact setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Contact setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Contact setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
