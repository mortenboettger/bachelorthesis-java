package io.mboettger.bachelorthesis.persistence.memory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity extends EntityModel {
    @Column(
            name = "first_name",
            length = 255,
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            length = 255,
            nullable = false
    )
    private String lastName;

    @Column(
            name = "street",
            length = 255,
            nullable = false
    )
    private String street;

    @Column(
            name = "house_number",
            length = 10,
            nullable = false
    )
    private String houseNumber;

    @Column(
            name = "house_number_addition",
            length = 10,
            nullable = true
    )
    private String houseNumberAddition;

    @Column(
            name = "post_code",
            length = 20,
            nullable = false
    )
    private String postCode;

    @Column(
            name = "city",
            length = 255,
            nullable = false
    )
    private String city;

    @Column(
            name = "district",
            length = 255,
            nullable = true
    )
    private String district;

    @Column(
            name = "phone_number",
            length = 255,
            nullable = true
    )
    private String phoneNumber;

    @Column(
            name = "email_address",
            length = 255,
            nullable = true
    )
    private String emailAddress;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumberAddition() {
        return houseNumberAddition;
    }

    public void setHouseNumberAddition(String houseNumberAddition) {
        this.houseNumberAddition = houseNumberAddition;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
