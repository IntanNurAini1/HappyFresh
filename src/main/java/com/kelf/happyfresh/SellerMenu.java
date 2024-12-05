package com.kelf.happyfresh;

import java.util.Scanner;

public class SellerMenu {
    private Seller seller;
    private Scanner scanner;

    public SellerMenu(Seller seller, Scanner scanner) {
        this.seller = seller;
        this.scanner = scanner;
    }

    public void tampilkanMenuSeller() {
        boolean isSellerActive = true;

        while (isSellerActive) {
            System.out.println("=== Menu Seller ===");
            System.out.println("1. Tambahkan Produk");
            System.out.println("2. Hapus Produk");
            System.out.println("3. Edit Produk");
            System.out.println("4. Tambahkan Stok Produk");
            System.out.println("5. Tampilkan Semua Produk");
            System.out.println("6. Tampilkan Detail Produk");
            System.out.println("7. Logout");
            System.out.print("Pilih opsi: ");
            int sellerChoice = scanner.nextInt();
            scanner.nextLine(); // Membaca newline

            switch (sellerChoice) {
                case 1 -> tambahkanProduk();
                case 2 -> hapusProduk();
                case 3 -> editProduk();
                case 4 -> tambahkanStokProduk();
                case 5 -> tampilkanSemuaProduk();
                case 6 -> tampilkanDetailProduk();
                case 7 -> {
                    System.out.println("Logout berhasil.");
                    isSellerActive = false;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private void tambahkanProduk() {
        System.out.print("Masukkan nama produk: ");
        String namaProduk = scanner.nextLine();
        System.out.print("Masukkan jenis produk: ");
        String jenisProduk = scanner.nextLine();
        System.out.print("Masukkan harga produk: ");
        int hargaProduk = scanner.nextInt();
        System.out.print("Masukkan stok produk: ");
        int stokProduk = scanner.nextInt();
        scanner.nextLine(); // Membaca newline
        System.out.print("Masukkan deskripsi produk: ");
        String deskripsiProduk = scanner.nextLine();

        Product newProduct = new Product(
            seller.getNamaToko(),
            namaProduk,
            jenisProduk,
            hargaProduk,
            stokProduk,
            deskripsiProduk
        );
        seller.tambahkanProduk(newProduct);
        System.out.println("Produk berhasil ditambahkan!");
    }

    private void hapusProduk() {
    System.out.print("Masukkan nama produk yang ingin dihapus: ");
    String namaProdukHapus = scanner.nextLine();
    seller.hapusProduk(namaProdukHapus); // Panggil metode di Seller
}


    private void editProduk() {
        System.out.print("Masukkan nama produk yang ingin diedit: ");
        String namaProdukEdit = scanner.nextLine();
        Product produkEdit = seller.cariProduk(namaProdukEdit);

        if (produkEdit != null) {
            System.out.println("Pilih atribut yang ingin diedit:");
            System.out.println("1. Nama Produk");
            System.out.println("2. Jenis Produk");
            System.out.println("3. Harga Produk");
            System.out.println("4. Deskripsi Produk");
            System.out.print("Pilih opsi: ");
            int editChoice = scanner.nextInt();
            scanner.nextLine(); // Membaca newline

            switch (editChoice) {
                case 1 -> {
                    System.out.print("Masukkan nama produk baru: ");
                    String namaBaru = scanner.nextLine();
                    produkEdit.setNamaProduk(namaBaru);
                    System.out.println("Nama produk berhasil diperbarui.");
                }
                case 2 -> {
                    System.out.print("Masukkan jenis produk baru: ");
                    String jenisBaru = scanner.nextLine();
                    produkEdit.setJenis(jenisBaru);
                    System.out.println("Jenis produk berhasil diperbarui.");
                }
                case 3 -> {
                    System.out.print("Masukkan harga produk baru: ");
                    int hargaBaru = scanner.nextInt();
                    produkEdit.setHarga(hargaBaru);
                    System.out.println("Harga produk berhasil diperbarui.");
                }
                case 4 -> {
                    System.out.print("Masukkan deskripsi produk baru: ");
                    String deskripsiBaru = scanner.nextLine();
                    produkEdit.setDeskripsiProduk(deskripsiBaru);
                    System.out.println("Deskripsi produk berhasil diperbarui.");
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }

    private void tambahkanStokProduk() {
        System.out.print("Masukkan nama produk yang ingin ditambahkan stoknya: ");
        String namaProdukStok = scanner.nextLine();
        Product produkStok = seller.cariProduk(namaProdukStok);

        if (produkStok != null) {
            System.out.print("Masukkan jumlah stok yang ingin ditambahkan: ");
            int jumlahStok = scanner.nextInt();
            produkStok.setStok(produkStok.getStok() + jumlahStok);
            System.out.println("Stok berhasil ditambahkan.");
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }

    private void tampilkanSemuaProduk() {
        if (seller.getProduk().isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia.");
        } else {
            seller.getInfoProduk();
        }
    }

    private void tampilkanDetailProduk() {
        System.out.print("Masukkan nama produk yang ingin ditampilkan detailnya: ");
        String namaProdukDetail = scanner.nextLine();
        Product produkDetail = seller.cariProduk(namaProdukDetail);

        if (produkDetail != null) {
            System.out.println(produkDetail);
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }
}
