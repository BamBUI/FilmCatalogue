package ru.CastIronDev.ejb;

import ru.CastIronDev.authenticator.domain.Credentials;
import ru.CastIronDev.authenticator.domain.ShopUser;
import ru.CastIronDev.authenticator.ejb.AuthenticationManager;
import ru.CastIronDev.authenticator.ejb.AuthenticationManager.*;
import ru.CastIronDev.authenticator.ejb.AuthenticationManagerBean;
import ru.CastIronDev.domain.Film;
import ru.CastIronDev.domain.FilmInOrder;
import ru.CastIronDev.domain.Order;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
@LocalBean
public class OrdersManagerBean extends AuthenticationManager {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public Order createOrder() {
        Order order = new Order();
        entityManager.persist(order);
        return order;
    }

    public boolean addToOrder(long filmId, long orderId , int price, int quantity)
    {

        Film film = entityManager.find(Film.class, filmId);
        if (film==null){
            return false;
        }
        Order order = entityManager.find(Order.class, orderId);
            if(order==null){
                return false;
            }
        FilmInOrder filmInOrder = new FilmInOrder();
        filmInOrder.setOrder(order);
        filmInOrder.setFilm(film);
        filmInOrder.setPrice(price);
        filmInOrder.setQuantity(quantity);
        entityManager.persist(filmInOrder);

        return true;
    }

    public List<Film> getFilmsInOrder(long orderId){
        Order order = entityManager.find(Order.class, orderId);
        if(order==null){
            return Collections.emptyList();
        }
        List<FilmInOrder> filmsInOrders = order.getFilmInOrderList();
        List<Film> result = new ArrayList<>();
        for (FilmInOrder filmInOrder: filmsInOrders){
            result.add(filmInOrder.getFilm());
        }
        return result;
    }
}
