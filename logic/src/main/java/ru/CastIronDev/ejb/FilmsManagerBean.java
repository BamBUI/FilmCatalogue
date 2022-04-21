package ru.CastIronDev.ejb;

import ru.CastIronDev.domain.Film;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class FilmsManagerBean {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public Film createFilm(String name, int price, int quantity){
        Film film = new Film();
        film.setName(name);
        film.setPrice(price);
        film.setQuantity(quantity);
        entityManager.persist(film);

        return film;
    }

    public List<Film> getFilms(){
        TypedQuery<Film> query = entityManager.createQuery("select c from Film c", Film.class);
        List<Film> films = query.getResultList();
        return query.getResultList();
    }
}
