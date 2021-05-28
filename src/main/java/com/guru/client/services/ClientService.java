package com.guru.client.services;

import com.guru.client.exceptions.ClientServerException;
import com.guru.client.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> filterClient(String firstName, String idNumber, String mobileNumber) throws ClientServerException;

    Client save(Client client) throws ClientServerException;
}
