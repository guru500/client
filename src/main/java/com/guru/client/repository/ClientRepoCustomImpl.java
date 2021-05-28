package com.guru.client.repository;

import com.guru.client.exceptions.ClientServerException;
import com.guru.client.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ClientRepoCustomImpl implements ClientRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Client> filterClient(String firstName, String idNumber, String mobileNumber) throws ClientServerException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);

        Root<Client> clientRoot = query.from(Client.class);
        List<Predicate> predicates = new ArrayList<>();

        if (firstName != null) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(clientRoot.get("firstName")), firstName.toLowerCase()));
        }
        if (idNumber != null) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(clientRoot.get("idNumber")), idNumber.toLowerCase()));
        }
        if (mobileNumber != null) {
            predicates.add(criteriaBuilder.equal(clientRoot.get("mobileNumber"), mobileNumber));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
