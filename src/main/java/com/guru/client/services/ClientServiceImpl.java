package com.guru.client.services;

import com.guru.client.exceptions.ClientServerException;
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
        return clientRepo.filterClient(firstName, idNumber, mobileNumber);
    }

    @Override
    public Client save(Client client) throws ClientServerException{
        Map<String, String> errors = new HashMap<>();
        if (!Objects.isNull(clientRepo.findByMobileNumberEquals(client.getMobileNumber()))) {
            errors.put("mobileNumber", "Mobile number already associated with other client");
        }
        if (!Objects.isNull(clientRepo.findByIdNumberEquals(client.getIdNumber()))) {
            errors.put("idNumber", "ID number already associated with other client.");
        }
        if (!errors.isEmpty()) {
            throw new UniqueConstraintException(errors, HttpStatus.BAD_REQUEST);

        }
        return clientRepo.save(client);
    }
}
