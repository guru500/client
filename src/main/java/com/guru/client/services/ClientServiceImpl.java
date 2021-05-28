package com.guru.client.services;

import com.guru.client.exceptions.ClientServerException;
import com.guru.client.exceptions.NotFoundException;
import com.guru.client.exceptions.UniqueConstraintException;
import com.guru.client.model.Client;
import com.guru.client.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepo clientRepo;

    @Override
    public List<Client> filterClient(String firstName, String idNumber, String mobileNumber) throws ClientServerException {
        List<Client> clientList = clientRepo.filterClient(firstName, idNumber, mobileNumber);
        if (clientList.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("Error", "No client found for the given search criteria.");
            throw new NotFoundException(error, HttpStatus.NOT_FOUND);
        }
        return clientList;
    }

    @Override
    public Client save(Client client) throws ClientServerException {
        Map<String, String> errors = new HashMap<>();
        if (!Objects.isNull(clientRepo.findByMobileNumberEquals(client.getMobileNumber()))) {
            errors.put("mobileNumber", "Mobile number already associated with other client");
        }
        if (!Objects.isNull(clientRepo.findByIdNumberEquals(client.getIdNumber()))) {
            errors.put("idNumber", "ID number already associated with other client.");
        }
        if (!errors.isEmpty()) {
            throw new UniqueConstraintException(errors, HttpStatus.CONFLICT);

        }
        return clientRepo.save(client);
    }
}
