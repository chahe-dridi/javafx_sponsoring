package services;

import entities.Sponsorship;
import com.esprit.microservice.sponsor.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SponsorshipService implements ISponsorshipService {

    private Connection conn;

    public SponsorshipService() {
        conn = DatabaseConnection.getInstance().getCnx();
    }

    @Override
    public void addSponsorship(Sponsorship sponsorship) throws SQLException {
        String sql = "INSERT INTO sponsorship (typeSponso, descriptionSponso, montant, date_debut, date_fin, statut) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, sponsorship.getTypeSponso());
            statement.setString(2, sponsorship.getDescriptionSponso());
            statement.setBigDecimal(3, sponsorship.getMontant());
            statement.setTimestamp(4, Timestamp.valueOf(sponsorship.getDate_debut()));
            statement.setTimestamp(5, Timestamp.valueOf(sponsorship.getDate_fin()));
            statement.setString(6, sponsorship.getStatut());

            statement.executeUpdate();
        }
    }

    @Override
    public void updateSponsorship(Sponsorship sponsorship) throws SQLException {
        String sql = "UPDATE sponsorship SET typeSponso=?, descriptionSponso=?, montant=?, date_debut=?, date_fin=?, statut=? WHERE idSponso=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, sponsorship.getTypeSponso());
            statement.setString(2, sponsorship.getDescriptionSponso());
            statement.setBigDecimal(3, sponsorship.getMontant());
            statement.setTimestamp(4, Timestamp.valueOf(sponsorship.getDate_debut()));
            statement.setTimestamp(5, Timestamp.valueOf(sponsorship.getDate_fin()));
            statement.setString(6, sponsorship.getStatut());
            statement.setInt(7, sponsorship.getIdSponso());

            statement.executeUpdate();
        }
    }

    @Override
    public void deleteSponsorship(Sponsorship sponsorship) throws SQLException {
        String sql = "DELETE FROM sponsorship WHERE idSponso=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, sponsorship.getIdSponso());
            statement.executeUpdate();
        }
    }

    @Override
    public List<Sponsorship> getAllSponsorships() throws SQLException {
        List<Sponsorship> sponsorships = new ArrayList<>();
        String sql = "SELECT * FROM sponsorship";
        try (PreparedStatement statement = conn.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Sponsorship sponsorship = new Sponsorship(
                        resultSet.getInt("idSponso"),
                        resultSet.getString("typeSponso"),
                        resultSet.getString("descriptionSponso"),
                        resultSet.getBigDecimal("montant"),
                        resultSet.getTimestamp("date_debut").toLocalDateTime(),
                        resultSet.getTimestamp("date_fin").toLocalDateTime(),
                        resultSet.getString("statut")
                );
                sponsorships.add(sponsorship);
            }
        }
        return sponsorships;
    }

    @Override
    public Sponsorship getSponsorshipById(int id) throws SQLException {
        Sponsorship sponsorship = null;
        String sql = "SELECT * FROM sponsorship WHERE idSponso = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    sponsorship = new Sponsorship(
                            resultSet.getInt("idSponso"),
                            resultSet.getString("typeSponso"),
                            resultSet.getString("descriptionSponso"),
                            resultSet.getBigDecimal("montant"),
                            resultSet.getTimestamp("date_debut").toLocalDateTime(),
                            resultSet.getTimestamp("date_fin").toLocalDateTime(),
                            resultSet.getString("statut")
                    );
                }
            }
        }
        return sponsorship;
    }

    @Override
    public List<Sponsorship> searchSponsorshipByType(String type) throws SQLException {
        List<Sponsorship> sponsorships = new ArrayList<>();
        String sql = "SELECT * FROM sponsorship WHERE typeSponso LIKE ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, "%" + type + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Sponsorship sponsorship = new Sponsorship(
                            resultSet.getInt("idSponso"),
                            resultSet.getString("typeSponso"),
                            resultSet.getString("descriptionSponso"),
                            resultSet.getBigDecimal("montant"),
                            resultSet.getTimestamp("date_debut").toLocalDateTime(),
                            resultSet.getTimestamp("date_fin").toLocalDateTime(),
                            resultSet.getString("statut")
                    );
                    sponsorships.add(sponsorship);
                }
            }
        }
        return sponsorships;
    }
}