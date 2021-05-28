package com.guru.client.controller;

import com.guru.client.exceptions.ClientServerException;
import com.guru.client.exceptions.GenericResponse;
import com.guru.client.model.Client;
import com.guru.client.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Manage client details.")
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Filters client details.", notes = "Filter client details based on firstName, idNumber & " +
            "phoneNumber.",
            response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful filtering data."),
            @ApiResponse(code = 404, message = "No records found for the search criteria."),
            @ApiResponse(code = 500, message = "Something went wrong, Internal server error")
    })
    @PostMapping(value = "/filter")
    public ResponseEntity<GenericResponse<Object>> filterClient(
            @ApiParam(name = "firstName", value = "First name of client.", required = false)
            @RequestParam(value = "firstName", required = false) String firstName,
            @ApiParam(name = "idNumber", value = "ID number of client.", required = false)
            @RequestParam(value = "idNumber", required = false) String idNumber,
            @ApiParam(name = "phoneNumber", value = "Phone number of client.", required = false)
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber) throws ClientServerException {

        return new ResponseEntity<GenericResponse<Object>>(new GenericResponse<>(true, HttpStatus.OK.name(),
                clientService.filterClient(firstName, idNumber, phoneNumber)), HttpStatus.OK);
    }

    @ApiOperation(value = "Save client details.", notes = "Save client details and validate details.",
            response = Client.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created client."),
            @ApiResponse(code = 400, message = "Bad request, parameters passed are not correct."),
            @ApiResponse(code = 409, message = "Conflict, parameters passed are already associated with other client."),
            @ApiResponse(code = 500, message = "Something went wrong, Internal server error")
    })
    @PostMapping(value = "/save-client")
    public ResponseEntity<GenericResponse<Object>> saveClient(@Valid @RequestBody Client client) throws ClientServerException {

        return new ResponseEntity<GenericResponse<Object>>(new GenericResponse<>(true, HttpStatus.CREATED.name(),
                clientService.save(client)), HttpStatus.CREATED);

    }
}
