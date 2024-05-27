package org.uniquindio.edu.co.escuela.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.uniquindio.edu.co.escuela.DTO.LoginDTO;
import org.uniquindio.edu.co.escuela.services.interfaces.AutenticacionService;

@AllArgsConstructor
@Service
public class AutenticacionServiceImpl implements AutenticacionService {


    private final EntityManager entityManager;

    @Transactional
    @Override
    public Character login(LoginDTO user) {

        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("login");

        storedProcedure.registerStoredProcedureParameter("id", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("rol", String.class, ParameterMode.IN);
        storedProcedure.registerStoredProcedureParameter("res", Character.class, ParameterMode.OUT);

        storedProcedure.setParameter("id", user.id());
        storedProcedure.setParameter("rol", user.rol());

        storedProcedure.execute();

        return (Character) storedProcedure.getOutputParameterValue("res");
    }





}
