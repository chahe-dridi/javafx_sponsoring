package services;

import entities.OffreSponsoring;
import com.esprit.microservice.sponsor.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferSponsoringService implements IOfferSponsoring<OffreSponsoring> {

    private Connection conn;

    public OfferSponsoringService() {
        conn = DatabaseConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(OffreSponsoring offreSponsoring) {
        String sql = "INSERT INTO offre_sponsoring (nom, description, avantages, duree, type, dateCreation, sponsorship_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, offreSponsoring.getNom());
            statement.setString(2, offreSponsoring.getDescription());
            statement.setString(3, offreSponsoring.getAvantages());
            statement.setString(4, offreSponsoring.getDuree());
            statement.setString(5, offreSponsoring.getType());
            statement.setDate(6, java.sql.Date.valueOf(offreSponsoring.getDateCreation()));
            statement.setInt(7, offreSponsoring.getSponsorship().getIdSponso());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifier(OffreSponsoring offreSponsoring) {
        String sql = "UPDATE offre_sponsoring SET nom=?, description=?, avantages=?, duree=?, type=?, dateCreation=?, sponsorship_id=? WHERE id=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, offreSponsoring.getNom());
            statement.setString(2, offreSponsoring.getDescription());
            statement.setString(3, offreSponsoring.getAvantages());
            statement.setString(4, offreSponsoring.getDuree());
            statement.setString(5, offreSponsoring.getType());
            statement.setDate(6, java.sql.Date.valueOf(offreSponsoring.getDateCreation()));
            statement.setInt(7, offreSponsoring.getSponsorship().getIdSponso());
            statement.setInt(8, offreSponsoring.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM offre_sponsoring WHERE id=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OffreSponsoring> getAll() {
        List<OffreSponsoring> offreSponsoringList = new ArrayList<>();
        String sql = "SELECT * FROM offre_sponsoring";
        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                OffreSponsoring offreSponsoring = new OffreSponsoring(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("description"),
                        resultSet.getString("avantages"),
                        resultSet.getString("duree"),
                        resultSet.getString("type"),
                        resultSet.getDate("dateCreation").toLocalDate(),
                        new SponsorshipService().getSponsorshipById(resultSet.getInt("sponsorship_id"))
                );
                offreSponsoringList.add(offreSponsoring);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offreSponsoringList;
    }

    @Override
    public OffreSponsoring getOneById(int id) {
        OffreSponsoring offreSponsoring = null;
        String sql = "SELECT * FROM offre_sponsoring WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    offreSponsoring = new OffreSponsoring(
                            resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getString("description"),
                            resultSet.getString("avantages"),
                            resultSet.getString("duree"),
                            resultSet.getString("type"),
                            resultSet.getDate("dateCreation").toLocalDate(),
                            new SponsorshipService().getSponsorshipById(resultSet.getInt("sponsorship_id"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offreSponsoring;
    }

    @Override
    public List<OffreSponsoring> search(String keyword) {
        List<OffreSponsoring> offreSponsoringList = new ArrayList<>();
        String sql = "SELECT * FROM offre_sponsoring WHERE nom LIKE ? OR description LIKE ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    OffreSponsoring offreSponsoring = new OffreSponsoring(
                            resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getString("description"),
                            resultSet.getString("avantages"),
                            resultSet.getString("duree"),
                            resultSet.getString("type"),
                            resultSet.getDate("dateCreation").toLocalDate(),
                            new SponsorshipService().getSponsorshipById(resultSet.getInt("sponsorship_id"))
                    );
                    offreSponsoringList.add(offreSponsoring);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offreSponsoringList;
    }
}