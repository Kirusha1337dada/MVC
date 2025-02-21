package com.example.mvc.repository.impl;

import com.example.mvc.models.Client;
import com.example.mvc.repository.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements ClientRepository
{
    private final EntityManagerFactory emf;

    @Autowired
    public ClientRepositoryImpl(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public void SaveOrUpdate(Client client)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();

            if (client.getId() == null)
            {
                em.persist(client);
            } else
            {
                em.merge(client);
            }

            em.getTransaction().commit();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Optional<Client> findById(Long id)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();

            em.getTransaction().commit();
            return Optional.ofNullable(em.find(Client.class, id));
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Client> findAll()
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();

            em.getTransaction().commit();
            return em.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Client> findBySearch(String search)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();

            String []arr = search.split("\\s+");

            StringBuilder request = new StringBuilder("FROM Client WHERE");
            for(int i = 0;i < arr.length; i++)
            {
                if(i>0)
                {
                    request.append(" AND ");
                }
                request.append("(firstName LIKE :search"+i+" OR lastName LIKE :search"+i+" OR middleName LIKE :search"+i+" OR passportInfo LIKE :search"+i+" OR numberPhone LIKE:search"+i+")");
            }

            var query = em.createQuery(request.toString(),Client.class);
            for(int i = 0; i< arr.length; i++)
            {
                query.setParameter("search"+i,"%"+arr[i]+"%");
            }

            em.getTransaction().commit();
            return query.getResultList();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteById(Long id)
    {
        try(EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();

            Client client = em.find(Client.class, id);
            if (client != null)
            {
                em.remove(client);
            }

            em.getTransaction().commit();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
