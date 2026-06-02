package org.veloplus.modelPackage;

public class Client {

    private Integer clientId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private ClientKind clientKind;
    private Category category;

    public Client() {
    }

    public Integer getClientId() {
        return clientId;
    }
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public ClientKind getClientKind() {
        return clientKind;
    }
    public void setClientKind(ClientKind clientKind) {
        this.clientKind = clientKind;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        String first = firstName == null ? "" : firstName;
        String last = lastName == null ? "" : lastName;

        return (first + " " + last).trim();
    }
}