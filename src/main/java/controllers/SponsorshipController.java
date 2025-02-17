package controllers;

import entities.Sponsorship;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.SponsorshipService;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class SponsorshipController implements Initializable {

    @FXML
    private TableView<Sponsorship> sponsorshipTableView;

    @FXML
    private TableColumn<Sponsorship, Integer> sponsorshipIdColumn;

    @FXML
    private TableColumn<Sponsorship, String> sponsorshipTypeColumn;

    @FXML
    private TableColumn<Sponsorship, String> sponsorshipDescriptionColumn;

    @FXML
    private TableColumn<Sponsorship, BigDecimal> sponsorshipAmountColumn;

    @FXML
    private TableColumn<Sponsorship, LocalDateTime> sponsorshipStartDateColumn;

    @FXML
    private TableColumn<Sponsorship, LocalDateTime> sponsorshipEndDateColumn;

    @FXML
    private TableColumn<Sponsorship, String> sponsorshipStatusColumn;

    @FXML
    private TextField newSponsorshipTypeField;

    @FXML
    private TextField newSponsorshipDescriptionField;

    @FXML
    private TextField newSponsorshipAmountField;

    @FXML
    private DatePicker newSponsorshipStartDateField;

    @FXML
    private DatePicker newSponsorshipEndDateField;

    @FXML
    private TextField newSponsorshipStatusField;

    private SponsorshipService sponsorshipService = new SponsorshipService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureTableView();
        try {
            refreshTableView();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sponsorshipTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateFields(newSelection);
            }
        });
    }

    private void configureTableView() {
         sponsorshipTypeColumn.setCellValueFactory(new PropertyValueFactory<>("typeSponso"));
        sponsorshipDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionSponso"));
        sponsorshipAmountColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
        sponsorshipStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        sponsorshipEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        sponsorshipStatusColumn.setCellValueFactory(new PropertyValueFactory<>("statut"));
    }

    @FXML
    void handleNewSponsorshipButton() throws SQLException {
        String type = newSponsorshipTypeField.getText().trim();
        String description = newSponsorshipDescriptionField.getText().trim();
        BigDecimal amount = new BigDecimal(newSponsorshipAmountField.getText().trim());
        LocalDate startDate = newSponsorshipStartDateField.getValue();
        LocalDate endDate = newSponsorshipEndDateField.getValue();
        String status = newSponsorshipStatusField.getText().trim();

        Sponsorship newSponsorship = new Sponsorship();
        newSponsorship.setTypeSponso(type);
        newSponsorship.setDescriptionSponso(description);
        newSponsorship.setMontant(amount);
        newSponsorship.setDate_debut(LocalDateTime.of(startDate, LocalTime.MIDNIGHT));
        newSponsorship.setDate_fin(LocalDateTime.of(endDate, LocalTime.MIDNIGHT));
        newSponsorship.setStatut(status);

        sponsorshipService.addSponsorship(newSponsorship);
        refreshTableView();
        clearFields();
    }

    @FXML
    void handleModifySponsorshipButton() throws SQLException {
        Sponsorship selectedSponsorship = sponsorshipTableView.getSelectionModel().getSelectedItem();
        if (selectedSponsorship != null) {
            String type = newSponsorshipTypeField.getText().trim();
            String description = newSponsorshipDescriptionField.getText().trim();
            BigDecimal amount = new BigDecimal(newSponsorshipAmountField.getText().trim());
            LocalDate startDate = newSponsorshipStartDateField.getValue();
            LocalDate endDate = newSponsorshipEndDateField.getValue();
            String status = newSponsorshipStatusField.getText().trim();

            selectedSponsorship.setTypeSponso(type);
            selectedSponsorship.setDescriptionSponso(description);
            selectedSponsorship.setMontant(amount);
            selectedSponsorship.setDate_debut(LocalDateTime.of(startDate, LocalTime.MIDNIGHT));
            selectedSponsorship.setDate_fin(LocalDateTime.of(endDate, LocalTime.MIDNIGHT));
            selectedSponsorship.setStatut(status);

            sponsorshipService.updateSponsorship(selectedSponsorship);
            refreshTableView();
            clearFields();
        }
    }

    @FXML
    void handleDeleteSponsorshipButton() throws SQLException {
        Sponsorship selectedSponsorship = sponsorshipTableView.getSelectionModel().getSelectedItem();
        if (selectedSponsorship != null) {
            sponsorshipService.deleteSponsorship(selectedSponsorship);
            refreshTableView();
        }
    }

    private void refreshTableView() throws SQLException {
        List<Sponsorship> sponsorships = sponsorshipService.getAllSponsorships();
        ObservableList<Sponsorship> sponsorshipObservableList = FXCollections.observableArrayList(sponsorships);
        sponsorshipTableView.setItems(sponsorshipObservableList);
    }

    private void populateFields(Sponsorship sponsorship) {
        newSponsorshipTypeField.setText(sponsorship.getTypeSponso());
        newSponsorshipDescriptionField.setText(sponsorship.getDescriptionSponso());
        newSponsorshipAmountField.setText(sponsorship.getMontant().toString());
        newSponsorshipStartDateField.setValue(sponsorship.getDate_debut().toLocalDate());
        newSponsorshipEndDateField.setValue(sponsorship.getDate_fin().toLocalDate());
        newSponsorshipStatusField.setText(sponsorship.getStatut());
    }

    private void clearFields() {
        newSponsorshipTypeField.clear();
        newSponsorshipDescriptionField.clear();
        newSponsorshipAmountField.clear();
        newSponsorshipStartDateField.setValue(null);
        newSponsorshipEndDateField.setValue(null);
        newSponsorshipStatusField.clear();
    }
}