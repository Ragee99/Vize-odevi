import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Temel Sınıf
abstract class BaseEntity {
    private int id;
    private String name;

    public BaseEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract void bilgiGoster();
}

// Film Sınıfı
class Film {
    private String ad;
    private int sure;
    private String tur;

    public Film(String ad, int sure, String tur) {
        this.ad = ad;
        this.sure = sure;
        this.tur = tur;
    }

    public String getAd() {
        return ad;
    }

    public int getSure() {
        return sure;
    }

    public String getTur() {
        return tur;
    }

    public void bilgiGoster() {
        System.out.println("Film Adı: " + ad + ", Süresi: " + sure + " dakika, Türü: " + tur);
    }
}

// Koltuk Sınıfı
class Koltuk {
    private int koltukNo;
    private boolean dolu;

    public Koltuk(int koltukNo) {
        this.koltukNo = koltukNo;
        this.dolu = false; // Başlangıçta tüm koltuklar boş.
    }

    public int getKoltukNo() {
        return koltukNo;
    }

    public boolean isDolu() {
        return dolu;
    }

    public void setDolu(boolean dolu) {
        this.dolu = dolu;
    }
}

// Seans Sınıfı
class Seans {
    private String saat;
    private List<Koltuk> koltuklar;

    public Seans(String saat) {
        this.saat = saat;
        this.koltuklar = new ArrayList<>();
        for (int i = 1; i <= 10; i++) { // Her seans için 10 koltuk
            koltuklar.add(new Koltuk(i));
        }
    }

    public String getSaat() {
        return saat;
    }

    public List<Koltuk> getKoltuklar() {
        return koltuklar;
    }

    public void bilgiGoster() {
        System.out.println("Seans: " + saat);
        for (Koltuk koltuk : koltuklar) {
            System.out.println("Koltuk " + koltuk.getKoltukNo() + " - " + (koltuk.isDolu() ? "Dolu" : "Boş"));
        }
    }
}

// Salon Sınıfı
class Salon extends BaseEntity {
    private Film film;
    private List<Seans> seanslar;

    public Salon(int id, String name, Film film) {
        super(id, name);
        this.film = film;
        this.seanslar = new ArrayList<>();
    }

    public Film getFilm() {
        return film;
    }

    public List<Seans> getSeanslar() {
        return seanslar;
    }

    public void seansEkle(Seans seans) {
        seanslar.add(seans);
    }

    @Override
    public void bilgiGoster() {
        System.out.println("Salon ID: " + getId() + ", Adı: " + getName());
        film.bilgiGoster();
        System.out.println("Seanslar:");
        for (int i = 0; i < seanslar.size(); i++) {
            System.out.println((i + 1) + ". " + seanslar.get(i).getSaat());
        }
    }
}

// Sinema Sistemi
class SinemaSistemi {
    private List<Salon> salonlar;

    public SinemaSistemi() {
        this.salonlar = new ArrayList<>();
    }

    public void salonEkle(Salon salon) {
        salonlar.add(salon);
    }

    public List<Salon> getSalonlar() {
        return salonlar;
    }

    public void salonlariListele() {
        for (int i = 0; i < salonlar.size(); i++) {
            System.out.println((i + 1) + ". " + salonlar.get(i).getName() + " - Film: " + salonlar.get(i).getFilm().getAd());
        }
    }

    public void koltukDurumlariniGoster() {
        System.out.println("\n=== Tüm Koltuk Doluluk Bilgileri ===");
        for (Salon salon : salonlar) {
            System.out.println("Salon: " + salon.getName() + " - Film: " + salon.getFilm().getAd());
            for (Seans seans : salon.getSeanslar()) {
                System.out.println("  Seans: " + seans.getSaat());
                for (Koltuk koltuk : seans.getKoltuklar()) {
                    if (koltuk.isDolu()) {
                        System.out.println("    Koltuk No: " + koltuk.getKoltukNo() + " - Dolu");
                    }
                }
            }
        }
    }
}

// Main Sınıfı
public class Main {
    public static void main(String[] args) {
        SinemaSistemi sistem = new SinemaSistemi();
        Scanner scanner = new Scanner(System.in);

        // Filmleri Ekleyelim
        Film film1 = new Film("Inception", 148, "Bilim Kurgu");
        Film film2 = new Film("The Dark Knight", 152, "Aksiyon");
        Film film3 = new Film("Titanic", 195, "Romantik");

        // Salonları ve Seansları Ekleyelim
        Salon salon1 = new Salon(1, "Salon 1", film1);
        salon1.seansEkle(new Seans("10:00"));
        salon1.seansEkle(new Seans("14:00"));
        salon1.seansEkle(new Seans("18:00"));

        Salon salon2 = new Salon(2, "Salon 2", film2);
        salon2.seansEkle(new Seans("12:00"));
        salon2.seansEkle(new Seans("16:00"));
        salon2.seansEkle(new Seans("20:00"));

        Salon salon3 = new Salon(3, "Salon 3", film3);
        salon3.seansEkle(new Seans("11:00"));
        salon3.seansEkle(new Seans("15:00"));
        salon3.seansEkle(new Seans("19:00"));

        sistem.salonEkle(salon1);
        sistem.salonEkle(salon2);
        sistem.salonEkle(salon3);

        while (true) {
            System.out.println("\n1. Salon ve Seansları Listele");
            System.out.println("2. Seans ve Koltuk Seçimi");
            System.out.println("3. Tüm Koltuk Doluluk Durumlarını Göster");
            System.out.println("4. Çıkış");
            System.out.print("\nBir seçim yapın (1-4): ");
            int secim = scanner.nextInt();
            scanner.nextLine(); // Enter'ı temizlemek için

            if (secim == 1) {
                sistem.salonlariListele();
            } else if (secim == 2) {
                sistem.salonlariListele();
                System.out.print("\nBir salon seçin (1-" + sistem.getSalonlar().size() + "): ");
                int salonSecim = scanner.nextInt() - 1;
                scanner.nextLine();

                if (salonSecim >= 0 && salonSecim < sistem.getSalonlar().size()) {
                    Salon seciliSalon = sistem.getSalonlar().get(salonSecim);
                    seciliSalon.bilgiGoster();

                    System.out.print("\nBir seans seçin (1-" + seciliSalon.getSeanslar().size() + "): ");
                    int seansSecim = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (seansSecim >= 0 && seansSecim < seciliSalon.getSeanslar().size()) {
                        Seans seciliSeans = seciliSalon.getSeanslar().get(seansSecim);
                        seciliSeans.bilgiGoster();

                        System.out.print("\nBir koltuk seçin (1-" + seciliSeans.getKoltuklar().size() + "): ");
                        int koltukSecim = scanner.nextInt() - 1;
                        scanner.nextLine();

                        if (koltukSecim >= 0 && koltukSecim < seciliSeans.getKoltuklar().size()) {
                            Koltuk seciliKoltuk = seciliSeans.getKoltuklar().get(koltukSecim);
                            if (!seciliKoltuk.isDolu()) {
                                seciliKoltuk.setDolu(true);
                                System.out.println("Koltuk " + (koltukSecim + 1) + " başarıyla seçildi!");
                            } else {
                                System.out.println("Bu koltuk zaten dolu!");
                            }
                        } else {
                            System.out.println("Geçersiz koltuk seçimi.");
                        }
                    } else {
                        System.out.println("Geçersiz seans seçimi.");
                    }
                } else {
                    System.out.println("Geçersiz salon seçimi.");
                }
            } else if (secim == 3) {
                sistem.koltukDurumlariniGoster();
            } else if (secim == 4) {
                System.out.println("Çıkılıyor...");
                break;
            } else {
                System.out.println("Geçersiz seçim.");
            }
        }
        scanner.close();
    }
}