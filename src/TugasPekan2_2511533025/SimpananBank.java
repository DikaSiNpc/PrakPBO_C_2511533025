package TugasPekan2_2511533025;

import java.util.ArrayList;

public class SimpananBank {

    String nomorRekening;
    String namaPemilik;
    double saldo;

    // Satu rekening memiliki banyak transaksi
    ArrayList<Pelunasan> riwayatTransaksi;

    public SimpananBank(String nomor, String nama, double saldoAwal) {

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

    // =========================
    // METHOD SETOR TUNAI
    // =========================
    public void setorTunai(double nominal) {

        if (nominal > 0) {

            saldo += nominal;

            String idTrx =
                    "TRX-S-" + System.currentTimeMillis();

            Pelunasan trxBaru =
                    new Pelunasan(idTrx, "Kredit", nominal);

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

    // =========================
    // METHOD TARIK TUNAI
    // =========================
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

            saldo -= nominal;

            String idTrx =
                    "TRX-T-" + System.currentTimeMillis();

            Pelunasan trxBaru =
                    new Pelunasan(idTrx, "Debit", nominal);

            riwayatTransaksi.add(trxBaru);

            System.out.println(
                    "Tarik tunai Rp"
                    + nominal
                    + " berhasil. Saldo saat ini: Rp"
                    + saldo
            );
        }
    }

    // =========================
    // METHOD CETAK MUTASI
    // =========================
    public void cetakMutasi() {

        System.out.println("\n===== MUTASI REKENING =====");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("---------------------------");

        if (riwayatTransaksi.isEmpty()) {

            System.out.println(
                    "Belum ada transaksi pada rekening ini"
            );

        } else {

            for (Pelunasan trx : riwayatTransaksi) {

                trx.cetakDetail();
            }
        }

        System.out.println("===========================");
    }

    // =========================
    // METHOD CEK INFORMASI
    // =========================
    public void cekInformasi() {

        System.out.println("--- INFO REKENING ---");
        System.out.println(
                "No. Rekening : " + nomorRekening
        );
        System.out.println(
                "Nama Pemilik : " + namaPemilik
        );
        System.out.println(
                "Saldo Akhir  : Rp" + saldo
        );
        System.out.println("---------------------");
    }
}