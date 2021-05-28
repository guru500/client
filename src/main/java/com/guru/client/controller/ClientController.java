package com.guru.client.controller;

import com.guru.client.exceptions.ClientServerException;
import com.guru.client.exceptions.GenericResponse;
import com.guru.client.model.Client;
import com.guru.client.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/filter")
    public ResponseEntity<GenericResponse<Object>> listAllUserByAllocation(
            @RequestParam(value = "firstName", required = false) String firstName, @RequestParam(value = "idNumber",
            required = false) String idNumber, @RequestParam(
            value = "phoneNumber", required = false) String phoneNumber) throws ClientServerException {

        return new ResponseEntity<GenericResponse<Object>>(new GenericResponse<>(true, HttpStatus.OK.name(),
                clientService.filterClient(firstName, idNumber, phoneNumber)), HttpStatus.OK);
    }

    @PostMapping(value = "/save-client")
    public ResponseEntity<GenericResponse<Object>> saveClient(@Valid @RequestBody Client client) throws ClientServerException {

        return new ResponseEntity<GenericResponse<Object>>(new GenericResponse<>(true, HttpStatus.OK.name(),
                clientService.save(client)), HttpStatus.CREATED);

    }
}
