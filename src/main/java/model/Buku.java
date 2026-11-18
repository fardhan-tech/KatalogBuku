package model;

public class Buku {

    private long id;
    private String judul;
    private String penulis;
    private long harga;
    private String sampul;

    public Buku(long id, String judul, String penulis, long harga, String sampul) {
        if (judul == null || judul.isBlank()) {
            throw new IllegalArgumentException("Judul buku wajib diisi.");
        }
        if (penulis == null || penulis.isBlank()) {
            throw new IllegalArgumentException("Nama penulis wajib diisi.");
        }
        if (harga <= 0) {
            throw new IllegalArgumentException("Harga harus lebih dari nol.");
        }
        if (sampul == null || sampul.isBlank()) {
            throw new IllegalArgumentException("Pilih sampul buku.");
        }
        this.id = id;
        this.judul = judul.trim();
        this.penulis = penulis.trim();
        this.harga = harga;
        this.sampul = sampul;
    }

    // Dipakai saat membuat buku baru dari form (id 0 = belum punya ID database).
    public Buku(String judul, String penulis, long harga, String sampul) {
        this(0, judul, penulis, harga, sampul);
    }

    public long getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public long getHarga() {
        return harga;
    }

    public String getSampul() {
        return sampul;
    }
}
