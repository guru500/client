package com.guru.client.repository;

import com.guru.client.exceptions.ClientServerException;
import com.guru.client.model.Client;

import java.util.List;

public interface ClientRepoCustom {

    List<Client> filterClient(String firstName, String idNumber, String mobileNumber) throws ClientServerException;
}
