package entities;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Sponsorship {
    private int idSponso;
    private String typeSponso;
    private String descriptionSponso;
    private BigDecimal montant;
    private LocalDateTime date_debut;
    private LocalDateTime date_fin;
    private String statut;
    private List<OffreSponsoring> offreSponsoringList;

    public Sponsorship() {
    }

    public Sponsorship(int idSponso, String typeSponso, String descriptionSponso, BigDecimal montant, LocalDateTime date_debut, LocalDateTime date_fin, String statut) {
        this.idSponso = idSponso;
        this.typeSponso = typeSponso;
        this.descriptionSponso = descriptionSponso;
        this.montant = montant;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.statut = statut;
    }

    public int getIdSponso() {
        return idSponso;
    }

    public void setIdSponso(int idSponso) {
        this.idSponso = idSponso;
    }

    public String getTypeSponso() {
        return typeSponso;
    }

    public void setTypeSponso(String typeSponso) {
        this.typeSponso = typeSponso;
    }

    public String getDescriptionSponso() {
        return descriptionSponso;
    }

    public void setDescriptionSponso(String descriptionSponso) {
        this.descriptionSponso = descriptionSponso;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public LocalDateTime getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(LocalDateTime date_debut) {
        this.date_debut = date_debut;
    }

    public LocalDateTime getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(LocalDateTime date_fin) {
        this.date_fin = date_fin;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public List<OffreSponsoring> getOffreSponsoringList() {
        return offreSponsoringList;
    }

    public void setOffreSponsoringList(List<OffreSponsoring> offreSponsoringList) {
        this.offreSponsoringList = offreSponsoringList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sponsorship)) return false;
        Sponsorship that = (Sponsorship) o;
        return idSponso == that.idSponso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSponso);
    }

    @Override
    public String toString() {
        return "Sponsorship{" +
                "idSponso=" + idSponso +
                ", typeSponso='" + typeSponso + '\'' +
                ", descriptionSponso='" + descriptionSponso + '\'' +
                ", montant=" + montant +
                ", date_debut=" + date_debut +
                ", date_fin=" + date_fin +
                ", statut='" + statut + '\'' +
                ", offreSponsoringList=" + offreSponsoringList +
                '}';
    }
}