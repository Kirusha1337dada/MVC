package com.example.mvc.repository.impl;

import com.example.mvc.models.Client;
import com.example.mvc.models.ContractCredit;
import com.example.mvc.models.DecisionCredit;
import com.example.mvc.repository.ContractCreditRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class ContractCreditImpl implements ContractCreditRepository
{
    private final EntityManagerFactory emf;

    public ContractCreditImpl(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public Optional<ContractCredit> findById(Long id)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.getTransaction().commit();
        return Optional.ofNullable(em.find(ContractCredit.class, id));
    }

    @Override
    public List<ContractCredit> findByContractStatus(String status)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();

            String request = "FROM ContractCredit WHERE status=:status";
            var result = em.createQuery(request, ContractCredit.class).setParameter("status", status).getResultList();

            em.getTransaction().commit();
            return result;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void SaveOrUpdate(ContractCredit contractCredit)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();

            em.persist(contractCredit);

            em.getTransaction().commit();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void createContractApproved(DecisionCredit decisionCredit, Client client)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            Random random = new Random();
            String SignOrNotSign = random.nextBoolean() ? "Unsigned" : "UnSigned";

            if ("Approved".equals(decisionCredit.getCreditStatus()))
            {
                em.merge(decisionCredit);
                ContractCredit contractCredit = ContractCredit.builder()
                        .client(client)
                        .decisionCredit(decisionCredit)
                        .dateSign(LocalDateTime.now())
                        .status(SignOrNotSign) //status("Signed")
                        .build();
                em.getTransaction().begin();
                em.persist(contractCredit);

                decisionCredit.setContractCredit(contractCredit);
                //em.flush();
                em.merge(decisionCredit);
                em.getTransaction().commit();
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
