package com.example.mvc.repository.impl;

import com.example.mvc.models.Client;
import com.example.mvc.models.DecisionCredit;
import com.example.mvc.repository.DecisionCreditRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class DecisionCreditImpl implements DecisionCreditRepository
{
    private final EntityManagerFactory emf;

    @Autowired
    public DecisionCreditImpl(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public void SaveOrUpdate(DecisionCredit decisionCredit)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();

            em.persist(decisionCredit);

            em.getTransaction().commit();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public Optional<DecisionCredit> findById(Long id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();

            DecisionCredit decisionCredit = em.find(DecisionCredit.class, id);

            em.getTransaction().commit();
            return Optional.ofNullable(decisionCredit);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<DecisionCredit> findByCreditStatus(String status)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            String request = "FROM DecisionCredit WHERE creditStatus = :status";

            List<DecisionCredit> result = em.createQuery(request,DecisionCredit.class).setParameter("status", status).getResultList();

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
    public List<DecisionCredit> findAll()
    {
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();

       String request = "SELECT c FROM DecisionCredit c";
       em.getTransaction().commit();
       return em.createQuery(request, DecisionCredit.class).getResultList();
    }

    @Override
    public void deleteById(Long id)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        DecisionCredit decisionCredit = em.find(DecisionCredit.class, id);
        if(decisionCredit != null)
        {
            em.remove(decisionCredit);
        }

        em.getTransaction().commit();
    }

    @Override
    public DecisionCredit makeDecisionCredit(Client client)
    {
        Random random = new Random();
        BigDecimal amountRequest = client.getAmount();
        String creditStatus = random.nextBoolean() ? "Approved" : "Rejected";
        int days=(random.nextInt(12)+1)*30;

        if(amountRequest.compareTo(new BigDecimal(150000)) >= 0)
        {
            creditStatus="Approved";
        }
        else if(amountRequest.compareTo(new BigDecimal(50000))<=0)
        {
            creditStatus = "Rejected";
        }

        BigDecimal approvedAmount = BigDecimal.ZERO;
        if(creditStatus.equals("Approved"))
        {
            double coef = 0.5+(1.5*random.nextDouble());
            approvedAmount = amountRequest.multiply(BigDecimal.valueOf(coef));
        }

        DecisionCredit decisionCredit = DecisionCredit.builder()
                .client(client).creditStatus(creditStatus).days(days).approvedAmount(approvedAmount).build();

        SaveOrUpdate(decisionCredit);
        return decisionCredit;
    }
}
