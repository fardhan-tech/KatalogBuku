package db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    // Database disimpan di <root proyek>/data/buku.db, bukan di target/resources.
    private static final Path FILE_DATABASE = Path.of(
            System.getProperty("user.dir"), "data", "buku.db"
    ).toAbsolutePath().normalize();

    public static Connection bukaKoneksi() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + FILE_DATABASE);
    }

    public static void prepareDatabase() throws IOException, SQLException {
        Files.createDirectories(FILE_DATABASE.getParent());
        System.out.println("DATABASE AKTIF: " + FILE_DATABASE);
        String sql = "CREATE TABLE IF NOT EXISTS buku ("
                + "id INTEGER PRIMARY KEY, "
                + "judul TEXT NOT NULL CHECK(length(trim(judul)) > 0), "
                + "penulis TEXT NOT NULL CHECK(length(trim(penulis)) > 0), "
                + "harga INTEGER NOT NULL CHECK(harga > 0), "
                + "sampul TEXT NOT NULL CHECK(length(trim(sampul)) > 0))";

        try (Connection koneksi = bukaKoneksi();
             Statement perintah = koneksi.createStatement()) {
            perintah.executeUpdate(sql);
        }
    }
}
