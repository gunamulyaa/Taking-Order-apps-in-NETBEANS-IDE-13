package RestoranApp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

import java.util.ArrayList;
import java.util.Scanner;

interface MenuInfo {
    void tampilMenu();
}

abstract class MenuItem {
    protected String nama;
    protected double harga;
    protected String kategori;

    public abstract void tampilMenu();
}

class Makanan extends MenuItem {
    private String jenisMakanan;

    public Makanan(String nama, double harga, String jenisMakanan) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = "Makanan";
        this.jenisMakanan = jenisMakanan;
    }

    @Override
    public void tampilMenu() {
        System.out.println(nama + " - " + jenisMakanan + " - Rp" + harga);
    }
}

class Minuman extends MenuItem {
    private String jenisMinuman;

    public Minuman(String nama, double harga, String jenisMinuman) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = "Minuman";
        this.jenisMinuman = jenisMinuman;
    }

    @Override
    public void tampilMenu() {
        System.out.println(nama + " - " + jenisMinuman + " - Rp" + harga);
    }
}

class Diskon extends MenuItem {
    private double diskon;

    public Diskon(String nama, double harga, double diskon) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = "Diskon";
        this.diskon = diskon;
    }

    @Override
    public void tampilMenu() {
        System.out.println(nama + " - Diskon " + diskon + "% - Rp" + harga);
    }
    
        public double getDiskon() {
        return diskon;
    }
        
    public double hitungHarga() {
        return harga - (harga * diskon / 100);
    }
}

class Menu {
    private ArrayList<MenuItem> daftarMenu = new ArrayList<>();

    public void tambahMenuItem(MenuItem item) {
        daftarMenu.add(item);
    }

    public void tampilkanMenu() {
        System.out.println("===== Menu Restoran =====");
        for (MenuItem item : daftarMenu) {
            item.tampilMenu();
        }
        System.out.println("=========================");
    }

    public ArrayList<MenuItem> getDaftarMenu() {
        return daftarMenu;
    }

}

class Pesanan {
    private ArrayList<MenuItem> pesanan = new ArrayList<>();

    public void tambahPesanan(MenuItem item) {
        pesanan.add(item);
    }

    public double hitungTotal() {
        double total = 0;
        for (MenuItem item : pesanan) {
            if (item instanceof Diskon) {
                total += ((Diskon) item).hitungHarga();
            } else {
                total += item.harga;
            }
        }
        return total;
    }

    public void tampilkanStruk() {
        System.out.println("===== Struk Pesanan =====");
        for (MenuItem item : pesanan) {
            item.tampilMenu();
        }
        System.out.println("Total Biaya: Rp" + hitungTotal());
        System.out.println("=========================");
    }
}

public class RestoranApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menuRestoran = new Menu();
        Pesanan pesananPelanggan = new Pesanan();

        do {
            System.out.println("===== Menu Utama =====");
            System.out.println("1. Tambah Item Menu");
            System.out.println("2. Tampilkan Menu");
            System.out.println("3. Pesan Menu");
            System.out.println("4. Tampilkan Struk Pesanan");
            System.out.println("5. Keluar");
            System.out.print("Pilihan Anda: ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.println("===== Tambah Item Menu =====");
                    System.out.println("1. Tambah Makanan");
                    System.out.println("2. Tambah Minuman");
                    System.out.println("3. Tambah Diskon");
                    System.out.print("Pilihan Anda: ");
                    int jenisMenu = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Harga: Rp");
                    double harga = scanner.nextDouble();

                    MenuItem newItem = null;
                    switch (jenisMenu) {
                        case 1:
                            System.out.print("Jenis Makanan: ");
                            String jenisMakanan = scanner.next();
                            newItem = new Makanan(nama, harga, jenisMakanan);
                            break;
                        case 2:
                            System.out.print("Jenis Minuman: ");
                            String jenisMinuman = scanner.next();
                            newItem = new Minuman(nama, harga, jenisMinuman);
                            break;
                        case 3:
                            System.out.print("Diskon (%): ");
                            double diskon = scanner.nextDouble();
                            newItem = new Diskon(nama, harga, diskon);
                            break;
                        default:
                            System.out.println("Pilihan tidak valid.");
                    }

                    if (newItem != null) {
                        menuRestoran.tambahMenuItem(newItem);
                        System.out.println("Item menu berhasil ditambahkan!");
                    }
                    break;
                case 2:
                    menuRestoran.tampilkanMenu();
                    break;
                case 3:
                    menuRestoran.tampilkanMenu();
                    System.out.print("Pilih item menu yang ingin dipesan (nomor): ");
                    int nomorMenu = scanner.nextInt();
                    if (nomorMenu >= 1 && nomorMenu <= menuRestoran.getDaftarMenu().size()) {
                        MenuItem itemPesan = menuRestoran.getDaftarMenu().get(nomorMenu - 1);
                        pesananPelanggan.tambahPesanan(itemPesan);
                        System.out.println("Pesanan berhasil ditambahkan!");
                    } else {
                        System.out.println("Nomor menu tidak valid.");
                    }
                    break;
                case 4:
                    pesananPelanggan.tampilkanStruk();
                    break;
                case 5:
                    System.out.println("Terima kasih! Program selesai.");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (true);
    }
}
