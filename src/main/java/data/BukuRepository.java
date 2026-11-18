package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.Database;
import model.Buku;

public class BukuRepository {

    public void add(Buku buku) throws SQLException {
        String sql = "INSERT INTO buku (judul, penulis, harga, sampul) VALUES (?, ?, ?, ?)";
        try (Connection koneksi = Database.bukaKoneksi();
             PreparedStatement perintah = koneksi.prepareStatement(sql)) {
            perintah.setString(1, buku.getJudul());
            perintah.setString(2, buku.getPenulis());
            perintah.setLong(3, buku.getHarga());
            perintah.setString(4, buku.getSampul());
            perintah.executeUpdate();
        }
    }

    public ArrayList<Buku> getAll() throws SQLException {
        ArrayList<Buku> daftar = new ArrayList<Buku>();
        String sql = "SELECT id, judul, penulis, harga, sampul FROM buku ORDER BY id";
        try (Connection koneksi = Database.bukaKoneksi();
             PreparedStatement perintah = koneksi.prepareStatement(sql);
             ResultSet hasil = perintah.executeQuery()) {
            while (hasil.next()) {
                long id = hasil.getLong("id");
                String judul = hasil.getString("judul");
                String penulis = hasil.getString("penulis");
                long harga = hasil.getLong("harga");
                String sampul = hasil.getString("sampul");
                daftar.add(new Buku(id, judul, penulis, harga, sampul));
            }
        }
        return daftar;
    }
}
