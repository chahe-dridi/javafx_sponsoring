package services;
import java.util.List;

public interface IOfferSponsoring<T> {
    void ajouter(T obj);
    void modifier(T obj);
    void supprimer(int id);
    List<T> getAll();
    T getOneById(int id);
    List<T> search(String keyword);
}