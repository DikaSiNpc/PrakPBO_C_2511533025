package Tugas2_2511533025;

public class Pembayaran {
    String idTransaksi;
    String jenis;
    double nominal;
    // Constructor
    public Pembayaran(String id, String jenis, double nominal) {
        this.idTransaksi = id;
        this.jenis = jenis;
        this.nominal = nominal;
    }
    public void cetakDetail() {
        System.out.println(
                "ID: " + idTransaksi
                + " | Jenis: " + jenis
                + " | Nominal: Rp" + nominal
        );
    }
    @Override
    public String toString() {
        return "ID: " + idTransaksi
                + " | Jenis: " + jenis
                + " | Nominal: Rp" + nominal;
    }
}