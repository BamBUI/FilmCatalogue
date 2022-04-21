package ru.CastIronDev.authenticator.ejb;

import org.apache.commons.lang3.StringUtils;
import ru.CastIronDev.authenticator.domain.Credentials;
import ru.CastIronDev.authenticator.domain.ShopUser;
import ru.CastIronDev.domain.Order;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateless
@LocalBean
public class AuthenticationManager {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;


    public ShopUser.Role login(String email, String password){
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
            return null;
        }
        Credentials credentials = entityManager.find(Credentials.class,email);
        if(credentials == null){
            return null;
        }
        if (!password.equals(credentials.getPassword())){
            return null;
        }
        ShopUser shopUser = credentials.getShopUser();

        if (shopUser == null){
            return null;
        }
        return shopUser.getRole();
    }


}
