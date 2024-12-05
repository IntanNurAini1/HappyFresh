package com.kelf.happyfresh;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Buyer extends User {
    private String alamat;
    private int saldo;
    private ArrayList<Product> keranjang;
    private ArrayList<Product> favorit;

    public Buyer(String id, String nama, String noHp, String alamat, int saldo) {
        super(id, nama, noHp);
        this.alamat = alamat;
        this.saldo = saldo;
        this.keranjang = new ArrayList<>();
        this.favorit = new ArrayList<>();
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    // Method untuk membeli produk
    public void beliProduk(Product produk, int jumlah, int jarakKm, Driver driver) {
        if (produk.getStok() < jumlah) {
            System.out.println("Stok produk '" + produk.getNamaProduk() + "' tidak mencukupi!");
            return;
        }

        int totalHarga = produk.getHarga() * jumlah;
        int ongkir = 0;

        // Menghitung ongkir berdasarkan jarak
        if (jarakKm < 5) {
            ongkir = 10000;
        } else if (jarakKm <= 10) {
            ongkir = 20000;
        } else {
            System.out.println("Maaf, jarak pengiriman melebihi 10 km.");
            return;
        }

        int totalBiaya = totalHarga + ongkir;

        // Validasi saldo
        if (saldo < totalBiaya) {
            System.out.println("Saldo tidak mencukupi untuk membeli produk ini.");
            return;
        }

        // Mengurangi stok produk dan saldo pembeli
        produk.setStok(produk.getStok() - jumlah);
        saldo -= totalBiaya;

        // Menambahkan saldo ke driver
        driver.menerimaOrderan(ongkir);

        System.out.println("Berhasil membeli " + jumlah + " " + produk.getNamaProduk() + ".");
        System.out.println("Ongkir sebesar Rp" + ongkir + " telah dibayarkan.");
    }

    // Method untuk menambahkan ke keranjang
    public void tambahkanKeKeranjang(Product produk) {
        keranjang.add(produk);
        System.out.println("Produk '" + produk.getNamaProduk() + "' berhasil ditambahkan ke keranjang.");
    }

    public void tampilkanKeranjang() {
        System.out.println("Keranjang Anda:");
        if (keranjang.isEmpty()) {
            System.out.println("- Tidak ada produk dalam keranjang.");
        } else {
            for (Product produk : keranjang) {
                produk.infoProduct();
            }
        }
    }

    // Method untuk menambahkan ke favorit
    public void tambahkanKeFavorit(Product produk) {
        favorit.add(produk);
        System.out.println("Produk '" + produk.getNamaProduk() + "' berhasil ditambahkan ke favorit.");
    }

    public void tampilkanFavorit() {
        System.out.println("Daftar Produk Favorit Anda:");
        if (favorit.isEmpty()) {
            System.out.println("- Tidak ada produk dalam daftar favorit.");
        } else {
            for (Product produk : favorit) {
                produk.infoProduct();
            }
        }
    }
    
    // Method untuk menambahkan saldo
    public void tambahSaldo(int jumlah) {
        if (jumlah <= 0) {
            System.out.println("Jumlah saldo yang ditambahkan harus lebih dari 0.");
            return;
        }
        saldo += jumlah;
        System.out.println("Saldo berhasil ditambahkan sebesar Rp" + jumlah + ". Saldo Anda sekarang adalah Rp" + saldo + ".");
    }


    public void getInfo() {
        Locale myIndonesianLocale = new Locale("in", "ID");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(myIndonesianLocale);
        formatter.setMaximumFractionDigits(0);
        String formattedPrice = formatter.format(saldo);
        System.out.println("============================================================");
        System.out.println("Nama pembeli: " + super.getNama());
        System.out.println("Id: " + super.getId());
        System.out.println("Saldo: " + formattedPrice);
        System.out.println("============================================================");
    }
}
