package com.codecool.lodgingsmanager.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "site_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "null")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private long id;
    private String firstName;
    private String surname;
    private String email;
    private String phoneNumber;
    private String country;
    private String city;
    private String zipCode;
    private String address;
    private String passwordHash;


    @OneToMany(mappedBy = "propertyManager", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Lodgings> propertyManagerLodgings = new HashSet<>();

    @OneToMany(mappedBy = "landlord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Lodgings> landlordLodgings = new HashSet<>();



//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    Lodgings tenantLodgings = new Lodgings();






    public User() {

    }

    public User(
            String firstName,
            String surname,
            String email,
            String phoneNumber,
            String country,
            String city,
            String zipCode,
            String address,
            String passwordHash
    ) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.address = address;
        this.passwordHash = passwordHash;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Set<Lodgings> getPropertyManagerLodgings() {
        return propertyManagerLodgings;
    }

    public void setPropertyManagerLodgings(Set<Lodgings> propertyManagerLodgings) {
        this.propertyManagerLodgings = propertyManagerLodgings;
    }

    public Set<Lodgings> getLandlordLodgings() {
        return landlordLodgings;
    }

    public void setLandlordLodgings(Set<Lodgings> landlordLodgings) {
        this.landlordLodgings = landlordLodgings;
    }

    public String getFullName() {
        return getFirstName() + " " + getSurname();
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", address='" + address + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
