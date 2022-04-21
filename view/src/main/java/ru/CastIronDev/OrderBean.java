package ru.CastIronDev;


import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import ru.CastIronDev.authenticator.domain.Credentials;
import ru.CastIronDev.authenticator.ejb.AuthenticationManagerBean;
import ru.CastIronDev.domain.Order;
import ru.CastIronDev.domain.Film;
import ru.CastIronDev.authenticator.domain.ShopUser;
import ru.CastIronDev.ejb.OrdersManagerBean;
import ru.CastIronDev.ejb.FilmsManagerBean;

@Named
@SessionScoped
public class OrderBean implements Serializable {

    private Order order;
    private Credentials credentials;
    private String name;
    private int price;
    private int quantity;

    @EJB
    OrdersManagerBean ordersManagerBean;
    @EJB
    FilmsManagerBean filmsManagerBean;
    @EJB
    AuthenticationManagerBean authenticationManagerBean;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void createOrder(){
        if (order == null){
            order = ordersManagerBean.createOrder();
        }
    }

    public void createFilm(){
        filmsManagerBean.createFilm(name, price, quantity);
    }

    public List<Film> getFilms(){
        return filmsManagerBean.getFilms();
    }

    public void addFilm(Film film){
        if (order == null){
            return;
        }
        ordersManagerBean.addToOrder(film.getId(), order.getId(), film.getPrice(), 1);
    }

    public List<Film> getFilmsInOrder(){
        if (order == null){
            return Collections.emptyList();
        }
        return ordersManagerBean.getFilmsInOrder(order.getId());
    }


}
