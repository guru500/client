package com.guru.client.repository;

import com.guru.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer>, ClientRepoCustom {
    Client findByFirstNameEquals(String firstName);

    Client findByMobileNumberEquals(String mobileNumber);

    Client findByIdNumberEquals(String idNumber);
}
