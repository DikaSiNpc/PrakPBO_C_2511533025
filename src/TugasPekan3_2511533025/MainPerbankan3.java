package TugasPekan3_2511533025;

import java.util.ArrayList;
import java.util.Scanner;

public class MainPerbankan3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Rekening3> daftarRekening = new ArrayList<>();
        Rekening3 akunAktif = null;
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
            System.out.println("6. Tampilkan Riwayat Transaksi (Cetak Mutasi)");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                // ==================== TUGAS POIN 1 ====================
                case 1:
                    System.out.print("Masukkan No. Rekening: ");
                    String no = scanner.nextLine();
                    System.out.print("Masukkan Nama Pemilik: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Saldo Awal: ");
                    double saldoAwal = scanner.nextDouble();
                    scanner.nextLine(); // Clear buffer
                    
                    // Minta user menginputkan PIN (String angka 6 digit) sebelum instansiasi
                    System.out.print("Masukkan PIN (6 digit): ");
                    String pin = scanner.nextLine();
                    
                    Rekening3 rekBaru = new Rekening3(no, nama, saldoAwal, pin);
                    daftarRekening.add(rekBaru);
                    akunAktif = rekBaru; // Otomatis jadikan akun aktif
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

                // ==================== TUGAS POIN 2 (Menu 3) ====================
                case 3:
                    if (akunAktif == null) {
                        System.out.println("Peringatan: Pilih atau buat akun terlebih dahulu!");
                    } else {
                        System.out.print("Masukkan PIN Anda: ");
                        String pinInput = scanner.nextLine();
                        
                        // Memanggil fungsi otentikasi
                        if (akunAktif.otentikasi(pinInput)) {
                            System.out.print("Masukkan nominal tarik tunai: ");
                            double tarik = scanner.nextDouble();
                            akunAktif.tarikTunai(tarik);
                        } else {
                            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
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
                            Rekening3 target = daftarRekening.get(indeks);
                            System.out.print("Masukkan PIN akun target: ");
                            String pinAkses = scanner.nextLine();
                            
                            if (target.otentikasi(pinAkses)) {
                                akunAktif = target;
                                System.out.println("Berhasil beralih ke akun " + akunAktif.getNamaPemilik());
                            } else {
                                System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                            }
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    }
                    break;

                // ==================== TUGAS POIN 2 (Menu 6) ====================
                case 6: 
                    if (akunAktif == null) {
                        System.out.println("Peringatan: Pilih atau buat akun terlebih dahulu!");
                    } else {
                        System.out.print("Masukkan PIN Anda: ");
                        String pinInput = scanner.nextLine();
                        
                        // Memanggil fungsi otentikasi
                        if (akunAktif.otentikasi(pinInput)) {
                            akunAktif.cekInformasi();
                        } else {
                            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
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