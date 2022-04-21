package ru.CastIronDev.domain;
import ru.CastIronDev.authenticator.domain.Credentials;
import ru.CastIronDev.authenticator.domain.ShopUser;

import javax.persistence.*;

@Entity
public class FilmInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private int price;
    private int quantity;

    @ManyToOne
    private Film film;
    @ManyToOne
    private Order order;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
