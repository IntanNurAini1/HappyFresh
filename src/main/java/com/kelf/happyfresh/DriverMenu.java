package com.kelf.happyfresh;

import java.util.Scanner;

public class DriverMenu {
    private Driver driver;
    private Scanner scanner;

    public DriverMenu(Driver driver, Scanner scanner) {
        this.driver = driver;
        this.scanner = scanner;
    }

    public void tampilkanMenuDriver() {
        boolean isDriverActive = true;

        while (isDriverActive) {
            System.out.println("=== Menu Driver ===");
            System.out.println("1. Lihat Saldo");
            System.out.println("2. Terima Orderan");
            System.out.println("3. Tandai Orderan Selesai");
            System.out.println("4. Logout");
            System.out.print("Pilih opsi: ");
            int driverChoice = scanner.nextInt();
            scanner.nextLine(); // Membaca newline

            switch (driverChoice) {
                case 1 -> System.out.println("Saldo Anda saat ini: Rp" + driver.getSaldo());
                case 2 -> {
                    if (driver.hasOrder()) {
                        System.out.print("Masukkan ongkir orderan: ");
                        int ongkir = scanner.nextInt();
                        scanner.nextLine(); // Membaca newline
                        driver.menerimaOrderan(ongkir);
                        driver.clearOrder(); // Reset status order
                    } else {
                        System.out.println("Tidak ada orderan untuk diterima.");
                    }
                }
                case 3 -> {
                    System.out.print("Masukkan nama produk orderan yang selesai: ");
                    String namaProduk = scanner.nextLine();
                    driver.orderanSelesai(namaProduk);
                }
                case 4 -> {
                    System.out.println("Logout berhasil.");
                    isDriverActive = false;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
