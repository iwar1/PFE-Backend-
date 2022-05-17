package com.example.transcriboot.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.io.Serializable;

@Entity
@Table(name="messages")
public class Message  implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name",length=30, nullable= false)
    private String firstName;

    @Column(name="last_name",length=30, nullable= false)
    private String lastName;

    @Column(name="company_name",length=30, nullable= false)
    private String companyName;

    @Column(name="phone_number")
    private Long phoneNumber;

    @Column(name="email",length=30, nullable= false , unique= true)
    private String email;

    @Column(name="msg",length=1000, nullable= false)
    private String text;

    //constructor


    public Message() {}

    public Message(String firstName, String lastName, String companyName, Long phoneNumber, String email, String text) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.text = text;
    }

    //getters & setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    //toString

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
