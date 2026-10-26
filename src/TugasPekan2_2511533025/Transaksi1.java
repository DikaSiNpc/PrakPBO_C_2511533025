package TugasPekan2_2511533025;

public class Transaksi1 {
    String idTransaksi;
    String jenis;
    double nominal;
    // Constructor
    public Transaksi1(String id, String jenis, double nominal) {
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