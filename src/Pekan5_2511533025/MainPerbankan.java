package Pekan5_2511533025;

import java.util.ArrayList;
import java.util.Scanner;

public class MainPerbankan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Rekening> daftarRekening = new ArrayList<>();
        Rekening akunAktif = null;
        int pilihan;

        do {
            System.out.println("\n=== SISTEM PERBANKAN MINI (MULTI-AKUN) ===");
            System.out.println("------------------------------------------");
            if (akunAktif == null) {
                System.out.println("Akun Aktif : Tidak ada akun yang dipilih");
            } else {
                System.out.println("Akun Aktif : " + akunAktif.getNamaPemilik() + " (" + akunAktif.getNomorRekening() + ")");
            }
            System.out.println("------------------------------------------");
            System.out.println("Menu Utama:");
            System.out.println("1. Buka Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cek Informasi Rekening");
            System.out.println("5. Ganti Akun (Pilih Akun)");
            System.out.println("6. Tampilkan Riwayat Transaksi");
            System.out.println("7. Simulasi Akhir Bulan (Khusus Tabungan)");
            // --- MODIFIKASI 1: Penambahan Tampilan Menu 8 ---
            System.out.println("8. Transfer ke Rekening Lain");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan No. Rekening: ");
                    String no = scanner.nextLine();
                    System.out.print("Masukkan Nama Pemilik: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Saldo Awal: ");
                    double saldoAwal = scanner.nextDouble();
                    scanner.nextLine(); // Clear buffer
                    System.out.print("Buat PIN (6 digit): ");
                    String pin = scanner.nextLine();

                    System.out.println("Pilih Produk: 1. Tabungan Umum | 2. Giro Bisnis");
                    System.out.print("Pilihan Anda: ");
                    int jenisProduk = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer

                    Rekening rekBaru = null;

                    if (jenisProduk == 1) {
                        System.out.print("Masukkan Suku Bunga (%): ");
                        double sukuBunga = scanner.nextDouble();
                        scanner.nextLine();
                        rekBaru = new RekeningTabungan(no, nama, saldoAwal, pin, sukuBunga);
                    } else if (jenisProduk == 2) {
                        System.out.print("Masukkan Batas Overdraft (Limit Pinjaman): ");
                        double batasOverdraft = scanner.nextDouble();
                        scanner.nextLine();
                        rekBaru = new RekeningGiro(no, nama, saldoAwal, pin, batasOverdraft);
                    } else {
                        System.out.println("Pilihan produk tidak valid! Pembuatan rekening dibatalkan.");
                        break;
                    }

                    daftarRekening.add(rekBaru);
                    akunAktif = rekBaru;
                    break;

                case 2:
                    if (akunAktif == null) {
                        System.out.println("Peringatan: Pilih atau buat akun terlebih dahulu!");
                    } else {
                        System.out.print("Masukkan nominal setor tunai: ");
                        double setor = scanner.nextDouble();
                        akunAktif.setorTunai(setor);
                    }
                    break;

                case 3:
                    if (akunAktif == null) {
                        System.out.println("Peringatan: Pilih atau buat akun terlebih dahulu!");
                    } else {
                        System.out.print("Masukkan PIN Anda: ");
                        String inputPin = scanner.nextLine();
                        if (akunAktif.otentikasi(inputPin)) {
                            System.out.print("Masukkan nominal tarik tunai: ");
                            double tarik = scanner.nextDouble();
                            akunAktif.tarikTunai(tarik);
                        } else {
                            System.out.println("Transaksi Gagal: PIN salah!");
                        }
                    }
                    break;

                case 4:
                    if (akunAktif == null) {
                        System.out.println("Peringatan: Pilih atau buat akun terlebih dahulu!");
                    } else {
                        akunAktif.cekInformasi();
                    }
                    break;

                case 5:
                    if (daftarRekening.isEmpty()) {
                        System.out.println("Belum ada rekening yang terdaftar.");
                    } else {
                        System.out.println("--- DAFTAR AKUN ---");
                        for (int i = 0; i < daftarRekening.size(); i++) {
                            System.out.println((i + 1) + ". " + daftarRekening.get(i).getNamaPemilik() + " (" + daftarRekening.get(i).getNomorRekening() + ")");
                        }
                        System.out.print("Pilih nomor akun: ");
                        int indeks = scanner.nextInt() - 1;
                        scanner.nextLine(); // Clear buffer
                        
                        if (indeks >= 0 && indeks < daftarRekening.size()) {
                            Rekening target = daftarRekening.get(indeks);
                            System.out.print("Masukkan PIN akun target: ");
                            String pinAkses = scanner.nextLine();
                            
                            if (target.otentikasi(pinAkses)) {
                                akunAktif = target;
                                System.out.println("Berhasil beralih ke akun " + akunAktif.getNamaPemilik());
                            } else {
                                System.out.println("Gagal beralih: PIN salah!");
                            }
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    }
                    break;

                case 6: 
                    if (akunAktif == null) {
                        System.out.println("Peringatan: Pilih atau buat akun terlebih dahulu!");
                    } else {
                        System.out.print("Masukkan PIN Anda: ");
                        String inputPin = scanner.nextLine();
                        if (akunAktif.otentikasi(inputPin)) {
                            if (akunAktif.getRiwayatTransaksi().isEmpty()) {
                                System.out.println("Belum ada riwayat transaksi pada akun ini.");
                            } else {
                                System.out.println("--- RIWAYAT TRANSAKSI ---");
                                for (Transaksi trx : akunAktif.getRiwayatTransaksi()) {
                                    System.out.println(trx);
                                }
                                System.out.println("----------------------------------");
                            }
                        } else {
                            System.out.println("Akses Ditolak: PIN salah!");
                        }
                    }
                    break;

                case 7:
                    if (akunAktif == null) {
                        System.out.println("Peringatan: Pilih atau buat akun terlebih dahulu!");
                    } else {
                        if (akunAktif instanceof RekeningTabungan) {
                            RekeningTabungan tabungan = (RekeningTabungan) akunAktif;
                            tabungan.tambahBungaAkhirBulan();
                        } else {
                            System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan.");
                        }
                    }
                    break;

                // =========================================================================
                // MODIFIKASI 2: Penambahan Case 8 (Fitur Transfer Antar Rekening)
                // =========================================================================
                case 8:
                    if (akunAktif == null) {
                        System.out.println("Peringatan: Pilih atau buat akun terlebih dahulu!");
                    } else {
                        System.out.print("Masukkan Nomor Rekening Tujuan: ");
                        String noTujuan = scanner.nextLine();
                        if (noTujuan.equalsIgnoreCase(akunAktif.getNomorRekening())) {
                            System.out.println("Gagal: Tidak dapat melakukan transfer ke rekening sendiri!");
                            break;
                        }
                        Rekening akunTujuan = null;
                        for (Rekening rek : daftarRekening) {
                            if (rek.getNomorRekening().equalsIgnoreCase(noTujuan)) {
                                akunTujuan = rek;
                                break;
                            }
                        }
                        if (akunTujuan != null) {
                            System.out.print("Masukkan Nominal Transfer: Rp");
                            double nominalTrf = scanner.nextDouble();
                            scanner.nextLine(); // Clear buffer
                            
                            System.out.print("Masukkan PIN Anda: ");
                            String pinTrf = scanner.nextLine();
                            akunAktif.transfer(akunTujuan, nominalTrf, pinTrf);
                        } else {
                            System.out.println("Gagal: Nomor rekening tujuan tidak ditemukan!");
                        }
                    }
                    break;

                case 0:
                    System.out.println("Terima kasih telah menggunakan sistem perbankan.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 0);

        scanner.close();
    }
}