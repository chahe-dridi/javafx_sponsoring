package com.esprit.microservice.sponsor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection cnx;

    // Constructeur privé pour empêcher l'instanciation
    private DatabaseConnection() {
        try {
            // Mise à jour des détails de la base de données
            String url = "jdbc:mysql://localhost:3306/sponsoring";
            String user = "root";
            String password = "";

            // Initialisation de la connexion
            cnx = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }
    }

    // Singleton pour garantir une seule instance de DatabaseConnection
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Getter pour l'objet Connection
    public Connection getCnx() {
        return cnx;
    }

    // Méthode pour fermer la connexion (optionnel)
    public void close() {
        try {
            if (cnx != null && !cnx.isClosed()) {
                cnx.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}