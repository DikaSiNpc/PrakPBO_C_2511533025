package TugasPekan1_2511533025;
import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Menggunakan ArrayList untuk menampung banyak objek Rekening
        ArrayList<Rekening2> daftarRekening = new ArrayList<>();
        Rekening2 akunAktif = null; 
        boolean isRunning = true;

        System.out.println("=== SISTEM PERBANKAN MINI (MULTI-AKUN) ===");

        while (isRunning) {
            // Menampilkan informasi akun yang sedang aktif saat ini
            System.out.println("\n-----------------------------------------");
            if (akunAktif != null) {
                System.out.println("Akun Aktif : " + akunAktif.namaPemilik + " (" + akunAktif.nomorRekening + ")");
            } else {
                System.out.println("Akun Aktif : Tidak ada akun yang dipilih");
            }
            System.out.println("-----------------------------------------");
            
            System.out.println("Menu Utama:");
            System.out.println("1. Buka Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cek Informasi Rekening");
            System.out.println("5. Ganti Akun (Pilih Akun)"); // Menu Bonus / Tambahan
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = input.nextInt();
            input.nextLine(); // Membersihkan buffer enter

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan No Rekening: ");
                    String no = input.nextLine();
                    System.out.print("Masukkan Nama Pemilik: ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan Saldo Awal: ");
                    double saldo = input.nextDouble();
                    input.nextLine(); // Membersihkan buffer

                    // Membuat objek baru dan menambahkannya ke ArrayList
                    Rekening2 rekeningBaru = new Rekening2(no, nama, saldo);
                    daftarRekening.add(rekeningBaru);
                    
                    // Otomatis jadikan akun yang baru dibuat sebagai akun aktif
                    akunAktif = rekeningBaru;
                    break;

                case 2:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki atau memilih nomor rekening!");
                    } else {
                        System.out.print("Masukkan nominal setor: ");
                        double setor = input.nextDouble();
                        akunAktif.setorTunai(setor);
                    }
                    break;

                case 3:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memiliki atau memilih nomor rekening!");
                    } else {
                        System.out.print("Masukkan nominal tarik tunai: ");
                        double tarik = input.nextDouble();
                        akunAktif.tarikTunai(tarik);
                    }
                    break;

                case 4:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka atau memilih rekening!");
                    } else {
                        akunAktif.cekInformasi();
                    }
                    break;

                case 5: // Implementasi Ganti Akun
                    if (daftarRekening.isEmpty()) {
                        System.out.println("Error: Belum ada rekening yang terdaftar di sistem.");
                    } else {
                        System.out.println("\n--- DAFTAR REKENING TERSEDIA ---");
                        for (int i = 0; i < daftarRekening.size(); i++) {
                            Rekening2 r = daftarRekening.get(i);
                            System.out.println((i + 1) + ". No: " + r.nomorRekening + " | Nama: " + r.namaPemilik);
                        }
                        System.out.print("Masukkan Nomor Rekening yang ingin diaktifkan: ");
                        String cariNo = input.nextLine();

                        boolean ditemukan = false;
                        for (Rekening2 r : daftarRekening) {
                            if (r.nomorRekening.equals(cariNo)) {
                                akunAktif = r;
                                System.out.println("Berhasil beralih ke rekening atas nama: " + akunAktif.namaPemilik);
                                ditemukan = true;
                                break;
                            }
                        }
                        if (!ditemukan) {
                            System.out.println("Error: Nomor rekening tidak ditemukan!");
                        }
                    }
                    break;

                case 0:
                    isRunning = false;
                    System.out.println("Sistem ditutup. Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        input.close();
    }
}