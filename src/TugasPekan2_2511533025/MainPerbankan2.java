package TugasPekan2_2511533025;

import java.util.ArrayList;
import java.util.Scanner;

public class MainPerbankan2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Rekening2> daftarRekening = new ArrayList<>();
        Rekening2 akunAktif = null;
        boolean isRunning = true;

        System.out.println("=== SISTEM PERBANKAN MINI ===");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Buka Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cek Informasi Rekening");
            System.out.println("5. Ganti Akun Aktif");
            System.out.println("6. Cetak Mutasi (Riwayat)"); // Gambar 3 (Tugas 2)[cite: 4]
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = input.nextInt();
            input.nextLine(); 

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan No Rekening: ");
                    String no = input.nextLine();
                    System.out.print("Masukkan Nama Pemilik: ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan Saldo Awal: ");
                    double saldo = input.nextDouble();

                    Rekening2 rekeningBaru = new Rekening2(no, nama, saldo);
                    daftarRekening.add(rekeningBaru);
                    akunAktif = rekeningBaru;
                    break;

                case 2:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memilih rekening!");
                    } else {
                        System.out.print("Masukkan nominal setor: ");
                        double setor = input.nextDouble();
                        akunAktif.setorTunai(setor);
                    }
                    break;

                case 3:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, Anda belum memilih rekening!");
                    } else {
                        System.out.print("Masukkan nominal tarik: ");
                        double tarik = input.nextDouble();
                        akunAktif.tarikTunai(tarik);
                    }
                    break;

                case 4:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum memilih rekening!");
                    } else {
                        akunAktif.cekInformasi();
                    }
                    break;

                case 5:
                    if (daftarRekening.isEmpty()) {
                        System.out.println("Error: Belum ada rekening yang terdaftar!");
                    } else {
                        System.out.print("Masukkan No Rekening yang ingin diaktifkan: ");
                        String cariNo = input.nextLine();
                        boolean ditemukan = false;

                        for (Rekening2 rek : daftarRekening) {
                            if (rek.nomorRekening.equalsIgnoreCase(cariNo)) {
                                akunAktif = rek;
                                ditemukan = true;
                                System.out.println("Akun aktif berhasil diubah ke atas nama: " + akunAktif.namaPemilik);
                                break;
                            }
                        }

                        if (!ditemukan) {
                            System.out.println("Error: Nomor rekening tidak ditemukan!");
                        }
                    }
                    break;

                case 6:
                    // Gambar 3 (Tugas 2): Memanggil method cetakMutasi()[cite: 4]
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum memilih rekening!");
                    } else {
                        akunAktif.cetakMutasi(); //[cite: 4]
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