package ru.CastIronDev.domain;


import ru.CastIronDev.authenticator.domain.Credentials;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "booking")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    @OneToMany(mappedBy = "order")
    private List<FilmInOrder> filmInOrderList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<FilmInOrder> getFilmInOrderList() {
        return filmInOrderList;
    }

    public void setFilmInOrderList(List<FilmInOrder> filmInOrderList) {
        this.filmInOrderList = filmInOrderList;
    }

}
