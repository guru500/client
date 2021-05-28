package com.guru.client.model;

import com.guru.client.utils.ValidId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "client_details")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @NotBlank(message = "Can not be blank.")
    private String firstName;

    @NotBlank(message = "Can not be blank.")
    private String lastName;

    @NotBlank(message = "Can not be blank.")
    private String mobileNumber;

    @NotBlank(message = "Can not be blank.")
    @ValidId(message = "Provided ID is not a valid ID.")
    private String idNumber;

    private String physicalAddress;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }
}
