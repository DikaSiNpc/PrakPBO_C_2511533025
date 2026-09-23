package Tugas2_2511533025;

import java.util.ArrayList;
import java.util.Scanner;
public class PlayPerbankan {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<TabunganBank> daftarRekening = new ArrayList<>();
        TabunganBank akunAktif = null;
        int pilihan;
        do {
            System.out.println("\n=== SISTEM PERBANKAN MINI (MULTI-AKUN) ===");
            System.out.println("------------------------------------------");

            if (akunAktif == null) {
                System.out.println("Akun Aktif : Tidak ada akun yang dipilih");
            } else {
                System.out.println("Akun Aktif : " + akunAktif.namaPemilik
                        + " (" + akunAktif.nomorRekening + ")");
            }

            System.out.println("------------------------------------------");
            System.out.println("Menu Utama:");
            System.out.println("1. Buka Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cek Informasi Rekening");
            System.out.println("5. Ganti Akun (Pilih Akun)");
            System.out.println("6. Tampilkan Riwayat Transaksi");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            pilihan = scanner.nextInt();
            scanner.nextLine();
            switch (pilihan) {

                case 1:
                    System.out.print("Masukkan No. Rekening: ");
                    String no = scanner.nextLine();

                    System.out.print("Masukkan Nama Pemilik: ");
                    String nama = scanner.nextLine();

                    System.out.print("Masukkan Saldo Awal: ");
                    double saldoAwal = scanner.nextDouble();
                    TabunganBank rekBaru =
                            new TabunganBank(no, nama, saldoAwal);

                    daftarRekening.add(rekBaru);
                    akunAktif = rekBaru;
                    break;

                case 2:
                    if (akunAktif == null) {
                        System.out.println(
                                "Peringatan: Pilih atau buat akun terlebih dahulu!");
                    } else {
                        System.out.print("Masukkan nominal setor tunai: ");
                        double setor = scanner.nextDouble();
                        akunAktif.setorTunai(setor);
                    }
                    break;

                case 3:
                    if (akunAktif == null) {
                        System.out.println(
                                "Peringatan: Pilih atau buat akun terlebih dahulu!");
                    } else {
                        System.out.print("Masukkan nominal tarik tunai: ");
                        double tarik = scanner.nextDouble();
                        akunAktif.tarikTunai(tarik);
                    }
                    break;

                case 4:
                    if (akunAktif == null) {
                        System.out.println(
                                "Peringatan: Pilih atau buat akun terlebih dahulu!");
                    } else {
                        akunAktif.cekInformasi();
                    }
                    break;

                case 5:
                    if (daftarRekening.isEmpty()) {
                        System.out.println(
                                "Belum ada rekening yang terdaftar.");

                    } else {
                        System.out.println("--- DAFTAR AKUN ---");
                        for (int i = 0; i < daftarRekening.size(); i++) {
                            System.out.println(
                                    (i + 1) + ". "
                                    + daftarRekening.get(i).namaPemilik
                                    + " ("
                                    + daftarRekening.get(i).nomorRekening
                                    + ")"
                            );
                        }
                        System.out.print("Pilih nomor akun: ");
                        int indeks = scanner.nextInt() - 1;
                        if (indeks >= 0
                                && indeks < daftarRekening.size()) {
                            akunAktif = daftarRekening.get(indeks);
                            System.out.println("Berhasil beralih ke akun "
                                    + akunAktif.namaPemilik);

                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    }
                    break;

                case 6:
                    if (akunAktif == null) {
                        System.out.println(
                                "Peringatan: Pilih atau buat akun terlebih dahulu!");
                    } else {
                        if (akunAktif.riwayatTransaksi.isEmpty()) {
                            System.out.println(
                                    "Belum ada riwayat transaksi pada akun ini.");

                        } else {
                            System.out.println("--- RIWAYAT TRANSAKSI ---");
                            for (Pembayaran trx :
                                    akunAktif.riwayatTransaksi) {

                                System.out.println(trx);
                            }
                            System.out.println("-------------------------");
                        }
                    }
                    break;

                case 0:
                    System.out.println(
                            "Terima kasih telah menggunakan sistem perbankan.");
                    break;

                default:
                    System.out.println(
                            "Pilihan tidak valid. Silakan coba lagi.");
            }

        } while (pilihan != 0);

        scanner.close();
    }
}