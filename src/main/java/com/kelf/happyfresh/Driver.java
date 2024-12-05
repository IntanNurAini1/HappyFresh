package com.kelf.happyfresh;

public class Driver extends User {
    private int saldo;
    private boolean hasOrder;

    public Driver(String id, String nama, String noHp, int saldo) {
        super(id, nama, noHp);
        this.saldo = saldo;
        this.hasOrder = false; // Default tidak ada order
    }

    // Getter dan Setter
    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public boolean hasOrder() {
        return hasOrder;
    }

    public void setOrder(boolean order) {
        this.hasOrder = order;
    }

    // Method clearOrder untuk reset status order
    public void clearOrder() {
        this.hasOrder = false;
        System.out.println("Order telah dihapus. Anda siap menerima orderan baru.");
    }

    // Method untuk menerima orderan
    public void menerimaOrderan(int ongkir) {
        if (hasOrder) {
            System.out.println("Anda menerima orderan dengan ongkir sebesar Rp" + ongkir + ". Apakah Anda ingin menerima orderan ini? (y/n)");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String pilihan = scanner.nextLine();

            if (pilihan.equalsIgnoreCase("y")) {
                terimaOrderan(ongkir, true);
            } else if (pilihan.equalsIgnoreCase("n")) {
                terimaOrderan(ongkir, false);
            } else {
                System.out.println("Pilihan tidak valid. Orderan dianggap ditolak.");
                terimaOrderan(ongkir, false);
            }
        } else {
            System.out.println("Tidak ada orderan untuk diterima saat ini.");
        }
    }

    private void terimaOrderan(int ongkir, boolean menerima) {
        if (menerima) {
            saldo += ongkir;
            System.out.println("Orderan diterima. Saldo Anda bertambah sebesar Rp" + ongkir + ". Saldo sekarang: Rp" + saldo);
        } else {
            System.out.println("Orderan ditolak. Tidak ada perubahan saldo.");
        }
    }

    public void orderanSelesai(String namaProduk) {
        if (!hasOrder) {
            System.out.println("Orderan '" + namaProduk + "' telah selesai. Barang telah sampai ke pembeli.");
        } else {
            System.out.println("Selesaikan orderan terlebih dahulu sebelum menandai selesai.");
        }
    }
}
