package ru.CastIronDev.autheticator;

import org.apache.commons.lang3.StringUtils;
import ru.CastIronDev.authenticator.domain.Credentials;
import ru.CastIronDev.authenticator.domain.ShopUser;
import ru.CastIronDev.authenticator.ejb.AuthenticationManager;
import ru.CastIronDev.authenticator.ejb.AuthenticationManagerBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class AuthenticatorBean implements Serializable {
    private ShopUser.Role role;

    private String name;
    private String email;
    private String login;
    private String password;
    private String requestedPage;
    private Credentials credentials;

    @EJB
    private AuthenticationManager authenticationManager;

    @EJB
    private AuthenticationManagerBean authenticationManagerBean;


    public ShopUser.Role getRole() {
        return role;
    }

    public void setRole(ShopUser.Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRequestedPage() {
        return requestedPage;
    }

    public void setRequestedPage(String requestedPage) {
        this.requestedPage = requestedPage;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationManagerBean getAuthenticationManagerBean() {
        return authenticationManagerBean;
    }

    public void setAuthenticationManagerBean(AuthenticationManagerBean authenticationManagerBean) {
        this.authenticationManagerBean = authenticationManagerBean;
    }

    public void createCredentials(){authenticationManagerBean.createCredentials(email, password);}

    public List<Credentials> getCredentials(){
        return authenticationManagerBean.getCredentials();
    }

    public void doLogin() throws IOException {
        if(StringUtils.isEmpty(login)|| StringUtils.isEmpty(password)){
            role = null;
            return;
        }
        role = authenticationManager.login(login,password);
        if (role != null) {
            try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(requestedPage);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        }
    }

}
