package Tugas2_2511533025;
import java.util.ArrayList;
public class TabunganBank {
    String nomorRekening;
    String namaPemilik;
    double saldo;
    // Satu rekening memiliki banyak transaksi
    ArrayList<Pembayaran> riwayatTransaksi;

    public TabunganBank(String nomor, String nama, double saldoAwal) {
        this.nomorRekening = nomor;
        this.namaPemilik = nama;
        this.saldo = saldoAwal;

        // Inisialisasi riwayat transaksi
        this.riwayatTransaksi = new ArrayList<>();
        System.out.println(
                "Rekening atas nama "
                + namaPemilik
                + " berhasil dibuat."
        );
    }
    public void setorTunai(double nominal) {

        if (nominal > 0) {
            saldo += nominal;
            // Membuat ID transaksi setor tunai
            String idTrx = "TRX-S-" + System.currentTimeMillis();

            // Membuat objek transaksi
            Pembayaran trxBaru =
                    new Pembayaran(idTrx, "Kredit", nominal);

            // Menambahkan transaksi ke riwayat
            riwayatTransaksi.add(trxBaru);

            System.out.println(
                    "Setor tunai Rp"
                    + nominal
                    + " berhasil. Saldo saat ini: Rp"
                    + saldo
            );

        } else {

            System.out.println(
                    "Gagal: Nominal setor harus lebih dari 0!"
            );
        }
    }
    public void tarikTunai(double nominal) {

        if (nominal < 10000) {
            System.out.println(
                    "Transaksi Gagal : Minimal nominal penarikan 10.000"
            );

        } else if (nominal > saldo) {
            System.out.println(
                    "Transaksi Gagal: Saldo tidak mencukupi. "
                    + "Saldo Anda: Rp"
                    + saldo
            );

        } else {

            // Saldo dikurangi setelah penarikan berhasil
            saldo -= nominal;

            // ==============================
            // TUGAS 1: REKAM TRANSAKSI TARIK
            // ==============================

            // ID transaksi menggunakan prefix TRX-T-
            String idTrx =
                    "TRX-T-" + System.currentTimeMillis();

            // Jenis transaksi dicatat sebagai Debit
            Pembayaran trxBaru =
                    new Pembayaran(idTrx, "Debit", nominal);

            // Simpan transaksi ke riwayat
            riwayatTransaksi.add(trxBaru);
            System.out.println(
                    "Tarik tunai Rp"
                    + nominal
                    + " berhasil. Saldo saat ini: Rp"
                    + saldo
            );
        }
    }
    public void cekInformasi() {
        System.out.println("--- INFO REKENING ---");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir  : Rp" + saldo);
        System.out.println("---------------------");
    }
}