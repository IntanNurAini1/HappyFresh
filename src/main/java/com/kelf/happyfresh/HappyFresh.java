package com.kelf.happyfresh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HappyFresh {

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        HashMap<String, String> credentials = new HashMap<>(); // Map untuk menyimpan ID dan Password
        Scanner scanner = new Scanner(System.in);
        RegisterMenu registerMenu = new RegisterMenu(users, credentials); // Instance RegisterMenu

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("=== Selamat Datang di HappyFresh ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membaca newline

            switch (pilihan) {
                case 1 -> registerMenu.tampilkanMenuRegister(scanner); // Panggil menu register
                case 2 -> {
                            LoginMenu loginMenu = new LoginMenu(credentials, users, scanner); // Membuat instance LoginMenu
                            User currentUser = loginMenu.login(); // Memanggil metode login untuk mendapatkan user

                            if (currentUser != null) {
                                boolean isLoggedIn = true;

                                while (isLoggedIn) {
                                    System.out.println("=== Menu ===");
                                    System.out.println("1. Manajemen Profil");
                                    System.out.println("2. Logout");
                                    System.out.print("Pilih opsi: ");
                                    int menuPilihan = scanner.nextInt();
                                    scanner.nextLine(); // Membaca newline

                                    switch (menuPilihan) {
                                        case 1 -> {
                                            if (currentUser instanceof Seller) {
                                                System.out.println("Anda login sebagai Seller.");
                                                SellerMenu sellerMenu = new SellerMenu((Seller) currentUser, scanner);
                                                sellerMenu.tampilkanMenuSeller();
                                            } else if (currentUser instanceof Buyer) {
                                                System.out.println("Anda login sebagai Buyer.");
                                                Buyer buyer = (Buyer) currentUser;
                                                BuyerMenu buyerMenu = new BuyerMenu(buyer, users);
                                                buyerMenu.tampilkanMenu();
                                            } else if (currentUser instanceof Driver) {
                                                System.out.println("Anda login sebagai Driver.");
                                                Driver driver = (Driver) currentUser;
                                                DriverMenu driverMenu = new DriverMenu(driver, scanner);
                                                driverMenu.tampilkanMenuDriver();
                                            }
                                        }
                                        case 2 -> {
                                            System.out.println("Anda telah logout.");
                                            isLoggedIn = false;
                                        }
                                        default -> System.out.println("Pilihan tidak valid.");
                                    }
                                }
                            }
                        }

                case 3 -> {
                    System.out.println("Terima kasih telah menggunakan HappyFresh!");
                    isRunning = false;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }

        scanner.close();
    }
}
