package ru.CastIronDev.domain;
import javax.persistence.*;
import java.util.List;


@Entity
public class Film {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)

    private long id;
    private String name;
    private int price;
    private int quantity;

    @OneToMany(fetch=FetchType.EAGER ,mappedBy = "film")
    private List<FilmInOrder> filmInOrderList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public List<FilmInOrder> getFilmInOrderList() {
        return filmInOrderList;
    }


}
