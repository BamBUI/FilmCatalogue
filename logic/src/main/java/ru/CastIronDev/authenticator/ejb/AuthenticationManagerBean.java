package ru.CastIronDev.authenticator.ejb;

import ru.CastIronDev.authenticator.domain.Credentials;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class AuthenticationManagerBean {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public Credentials createCredentials (String email, String password){
        Credentials credential = new Credentials();
        credential.setEmail(email);
        credential.setPassword(password);

        entityManager.persist(credential);
        return credential;
    }

    public List<Credentials> getCredentials(){
        TypedQuery<Credentials> query = entityManager.createQuery("select c from Credentials c", Credentials.class);
        List<Credentials> credentials = query.getResultList();
        return query.getResultList();
    }
}
