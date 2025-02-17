package services;
import entities.Sponsorship;

import java.sql.SQLException;
import java.util.List;

public interface ISponsorshipService {
    void addSponsorship(Sponsorship sponsorship) throws SQLException;
    void updateSponsorship(Sponsorship sponsorship) throws SQLException;
    void deleteSponsorship(Sponsorship sponsorship) throws SQLException;
    List<Sponsorship> getAllSponsorships() throws SQLException;
    Sponsorship getSponsorshipById(int id) throws SQLException;
    List<Sponsorship> searchSponsorshipByType(String type) throws SQLException;
}