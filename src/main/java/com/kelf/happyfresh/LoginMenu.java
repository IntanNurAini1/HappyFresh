package com.kelf.happyfresh;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class LoginMenu {
    private final Map<String, String> credentials; // Menyimpan ID dan password
    private final ArrayList<User> users;          // Menyimpan daftar user
    private final Scanner scanner;

    public LoginMenu(Map<String, String> credentials, ArrayList<User> users, Scanner scanner) {
        this.credentials = credentials;
        this.users = users;
        this.scanner = scanner;
    }

    public User login() {
        System.out.println("=== Login ===");
        System.out.print("Masukkan ID: ");
        String loginId = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String loginPassword = scanner.nextLine();

        // Periksa apakah ID terdaftar
        if (credentials.containsKey(loginId)) {
            // Periksa apakah password cocok
            if (credentials.get(loginId).equals(loginPassword)) {
                // Cari user berdasarkan ID
                for (User user : users) {
                    if (user.getId().equals(loginId)) {
                        System.out.println("Login berhasil! Selamat datang, " + user.getNama() + ".");
                        return user; // Mengembalikan user yang berhasil login
                    }
                }
            } else {
                System.out.println("Password salah. Silakan coba lagi.");
            }
        } else {
            System.out.println("ID tidak ditemukan. Silakan register terlebih dahulu.");
        }

        return null; // Mengembalikan null jika login gagal
    }
}
