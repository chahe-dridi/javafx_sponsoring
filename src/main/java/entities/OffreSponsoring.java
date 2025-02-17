    package entities;

import java.time.LocalDate;
import java.util.Objects;

public class OffreSponsoring {
    private int id;
    private String nom;
    private String description;
    private String avantages;
    private String duree;
    private String type;
    private LocalDate dateCreation;
    private Sponsorship sponsorship;

    public OffreSponsoring() {
    }

    public OffreSponsoring(int id, String nom, String description, String avantages, String duree, String type, LocalDate dateCreation, Sponsorship sponsorship) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.avantages = avantages;
        this.duree = duree;
        this.type = type;
        this.dateCreation = dateCreation;
        this.sponsorship = sponsorship;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvantages() {
        return avantages;
    }

    public void setAvantages(String avantages) {
        this.avantages = avantages;

    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Sponsorship getSponsorship() {
        return sponsorship;
    }

    public void setSponsorship(Sponsorship sponsorship) {
        this.sponsorship = sponsorship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OffreSponsoring)) return false;
        OffreSponsoring that = (OffreSponsoring) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OffreSponsoring{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", avantages='" + avantages + '\'' +
                ", duree='" + duree + '\'' +
                ", type='" + type + '\'' +
                ", dateCreation=" + dateCreation +
                ", sponsorship=" + sponsorship +
                '}';
    }

    public String getDuree() {
        return duree;
    }
}