package Tugas2_2511533025;

public class Pelunasan {

    String idTransaksi;
    String jenis;
    double nominal;

    // Constructor
    public Pelunasan(String id, String jenis, double nominal) {

        this.idTransaksi = id;
        this.jenis = jenis;
        this.nominal = nominal;
    }

    // Method untuk mencetak detail transaksi
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