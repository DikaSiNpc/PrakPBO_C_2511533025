package Pekan5_2511533025;

import java.util.ArrayList;

public class Rekening {
    // Atribut private
    private String nomorRekening;
    private String namaPemilik;
    private String pin; // Data sensitif!

    // Protected agar Subclass bisa mengaksesnya
    protected double saldo;
    protected ArrayList<Transaksi> riwayatTransaksi;

    // Constructor
    public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {
        this.nomorRekening = nomor;
        this.namaPemilik = nama;
        this.saldo = saldoAwal;

        // Validasi PIN di dalam Constructor
        if (pinAwal != null && pinAwal.length() == 6) {
            this.pin = pinAwal;
        } else {
            System.out.println("Peringatan: PIN harus 6 digit! Menggunakan PIN default 123456");
            this.pin = "123456";
        }
        // Inisialisasi ArrayList
        this.riwayatTransaksi = new ArrayList<>();

        System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat.");
    }
    // Getter
    public String getNomorRekening() { return nomorRekening; }
    public String getNamaPemilik() { return namaPemilik; }
    
    public ArrayList<Transaksi> getRiwayatTransaksi() {
        return this.riwayatTransaksi;
    }

    // Otentikasi Internal
    public boolean otentikasi(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public void setorTunai(double nominal) {
        if (nominal > 0) {
            saldo += nominal;
            
            String idTrx = "TRX-S-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
            riwayatTransaksi.add(trxBaru);            
            System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
        } else {
            System.out.println("Gagal: Nominal setor harus lebih dari 0!");
        }
    }

    public boolean tarikTunai(double nominal) {
        if (nominal <= 0) {
            System.out.println("Gagal: Nominal tarik harus lebih dari 0!");
            return false;
        } else if (nominal > saldo) {
            System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp" + saldo);
            return false;
        } else {
            saldo -= nominal;
            
            String idTrx = "TRX-T-" + System.currentTimeMillis();
            riwayatTransaksi.add(new Transaksi(idTrx, "Debit", nominal));
            
            System.out.println("Tarik tunai Rp" + nominal + " berhasil. Sisa saldo: Rp" + saldo);
            return true;
        }
    }

    // =========================================================================
    // BAGIAN YANG DITAMBAHKAN (Latihan/Tugas: Fitur Transfer Antar Rekening)
    // =========================================================================
    public void transfer(Rekening tujuan, double nominal, String pinInput) {
        if (!otentikasi(pinInput)) {
            System.out.println("Transfer Gagal: PIN yang Anda masukkan salah!");
            return;
        }
        boolean suksesTarik = this.tarikTunai(nominal);
        if (suksesTarik) {
            tujuan.setorTunai(nominal);
            System.out.println("Transfer sebesar Rp" + nominal + " ke rekening " + tujuan.getNomorRekening() + " (" + tujuan.getNamaPemilik() + ") berhasil!");
        } else {
            System.out.println("Transfer Gagal: Proses penarikan dari saldo asal tidak berhasil.");
        }
    }
    // =========================================================================

    public void cekInformasi() {
        System.out.println("----- INFO REKENING -----");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir  : Rp" + saldo);
        System.out.println("-------------------------");
    }
}