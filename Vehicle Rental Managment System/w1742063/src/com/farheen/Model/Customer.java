package com.farheen.Model;

import java.util.Objects;

public class Customer {

    // declaration of variables
    private String name;
    private String contactNo;
    private String email;
    private String address;

    //Constructor
    public Customer(String name, String contactNo, String email, String address) {
        this.name = name;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    //Overriding equals and hashcode method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return name.equals(customer.name) &&
                contactNo.equals(customer.contactNo) &&
                email.equals(customer.email) &&
                address.equals(customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, contactNo, email, address);
    }

    //Overriding the toString method
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
