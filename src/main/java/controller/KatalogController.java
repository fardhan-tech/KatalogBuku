package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import data.BukuRepository;
import model.Buku;

public class KatalogController {

    @FXML
    private TextField judulField;
    @FXML
    private TextField penulisField;
    @FXML
    private TextField hargaField;
    @FXML
    private ComboBox<String> sampulBox;
    @FXML
    private Label pesanLabel;
    @FXML
    private Label jumlahLabel;
    @FXML
    private TilePane katalogPane;

    private final BukuRepository repository = new BukuRepository();

    @FXML
    private void initialize() {
        sampulBox.getItems().add("biru.png");
        sampulBox.getItems().add("hijau.png");
        sampulBox.getItems().add("merah.png");
        sampulBox.setValue("biru.png");
        try {
            showData();
        } catch (SQLException e) {
            pesanLabel.setText("Gagal membaca database. Lihat Output.");
            e.printStackTrace();
        }
    }

    private void showData() throws SQLException {
        ArrayList<Buku> daftar = repository.getAll();
        katalogPane.getChildren().clear();
        for (Buku buku : daftar) {
            katalogPane.getChildren().add(createBookCard(buku));
        }
        jumlahLabel.setText("Jumlah buku: " + daftar.size());
    }

    private VBox createBookCard(Buku buku) {
        ImageView sampulView = new ImageView();
        sampulView.setFitWidth(170);
        sampulView.setFitHeight(110);
        sampulView.setPreserveRatio(true);

        StackPane bingkai = new StackPane();
        bingkai.setPrefSize(180, 120);
        bingkai.setMinSize(180, 120);
        bingkai.setMaxSize(180, 120);

        URL lokasi = getClass().getResource("/gambar/" + buku.getSampul());
        if (lokasi != null) {
            Image gambar = new Image(lokasi.toExternalForm(), 170, 110, true, true);
            if (!gambar.isError()) {
                sampulView.setImage(gambar);
                bingkai.getChildren().add(sampulView);
            } else {
                bingkai.getChildren().add(new Label("Gambar tidak terbaca"));
            }
        } else {
            bingkai.getChildren().add(new Label("Gambar tidak tersedia"));
        }

        Label judulLabel = new Label(buku.getJudul());
        judulLabel.setWrapText(true);
        judulLabel.getStyleClass().add("judul-buku");
        Label penulisLabel = new Label("Penulis: " + buku.getPenulis());
        penulisLabel.setWrapText(true);
        penulisLabel.getStyleClass().add("muted");
        Label hargaLabel = new Label("Rp" + buku.getHarga());
        Label idLabel = new Label("ID: " + buku.getId());
        idLabel.getStyleClass().add("muted");

        VBox kartu = new VBox();
        kartu.setSpacing(8);
        kartu.setMinWidth(220);
        kartu.setPrefWidth(220);
        kartu.setMaxWidth(220);
        kartu.getStyleClass().add("kartu-buku");
        kartu.getChildren().add(bingkai);
        kartu.getChildren().add(judulLabel);
        kartu.getChildren().add(penulisLabel);
        kartu.getChildren().add(hargaLabel);
        kartu.getChildren().add(idLabel);
        return kartu;
    }

    @FXML
    private void saveBook() {
        Buku buku;
        try {
            String judul = judulField.getText();
            String penulis = penulisField.getText();
            long harga = Long.parseLong(hargaField.getText().trim());
            String sampul = sampulBox.getValue();
            buku = new Buku(judul, penulis, harga, sampul);
        } catch (NumberFormatException e) {
            pesanLabel.setText("Harga harus angka bulat, contoh 45000.");
            return;
        } catch (IllegalArgumentException e) {
            pesanLabel.setText(e.getMessage());
            return;
        }

        try {
            repository.add(buku);
        } catch (SQLException e) {
            pesanLabel.setText("Gagal menyimpan. Lihat Output.");
            e.printStackTrace();
            return;
        }

        judulField.clear();
        penulisField.clear();
        hargaField.clear();
        try {
            showData();
            pesanLabel.setText("Buku berhasil disimpan.");
        } catch (SQLException e) {
            pesanLabel.setText("Data tersimpan, tetapi katalog gagal dimuat ulang.");
            e.printStackTrace();
        }
    }
}
