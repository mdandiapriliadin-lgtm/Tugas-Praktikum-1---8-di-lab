import java.util.ArrayList;

// 1. Abstract Class AsetDigital
abstract class AsetDigital {
    protected String id;
    protected String namaFile;
    protected double ukuranMB;

    public AsetDigital(String id, String namaFile, double ukuranMB) {
        this.id = id;
        this.namaFile = namaFile;
        this.ukuranMB = ukuranMB;
    }

    public abstract void tampilkanPreview();

    public void getInfo() {
        System.out.println("ID        : " + id);
        System.out.println("Nama File : " + namaFile);
        System.out.println("Ukuran    : " + ukuranMB + " MB");
    }
}

// 2. Subclass Gambar
class Gambar extends AsetDigital {
    private String resolusi;

    public Gambar(String id, String namaFile, double ukuranMB, String resolusi) {
        super(id, namaFile, ukuranMB);
        this.resolusi = resolusi;
    }

    @Override
    public void tampilkanPreview() {
        getInfo();
        System.out.println("Resolusi  : " + resolusi);
        System.out.println("Preview   : Menampilkan gambar dengan resolusi " + resolusi);
    }
}

// Subclass Video
class Video extends AsetDigital {
    private int durasiDetik;

    public Video(String id, String namaFile, double ukuranMB, int durasiDetik) {
        super(id, namaFile, ukuranMB);
        this.durasiDetik = durasiDetik;
    }

    @Override
    public void tampilkanPreview() {
        getInfo();
        System.out.println("Durasi    : " + durasiDetik + " detik");
        System.out.println("Preview   : Memutar cuplikan video...");
    }
}

// Subclass Dokumen
class Dokumen extends AsetDigital {
    private int jumlahHalaman;

    public Dokumen(String id, String namaFile, double ukuranMB, int jumlahHalaman) {
        super(id, namaFile, ukuranMB);
        this.jumlahHalaman = jumlahHalaman;
    }

    @Override
    public void tampilkanPreview() {
        getInfo();
        System.out.println("Halaman   : " + jumlahHalaman + " halaman");
        System.out.println("Preview   : Menampilkan pratinjau halaman pertama dokumen.");
    }
}

// 3. Interface DapatDipromosikan
interface DapatDipromosikan {
    void promosikan();

    default void infoPromosi() {
        System.out.println("[INFO PROMOSI] Aset ini berkategori unggulan dan siap diiklankan.");
    }
}

// 4. Subclass VideoPromosi
class VideoPromosi extends Video implements DapatDipromosikan {
    private String targetAudience;

    public VideoPromosi(String id, String namaFile, double ukuranMB, int durasiDetik, String targetAudience) {
        super(id, namaFile, ukuranMB, durasiDetik);
        this.targetAudience = targetAudience;
    }

    @Override
    public void promosikan() {
        System.out.println("Promosi Video '" + namaFile + "' diluncurkan untuk target: " + targetAudience);
    }
}

// 5. Main Class (Wajib Public & Nama File Harus MainAset.java)
public class MainAset {
    public static void main(String[] args) {
        // Poin 5.1: ArrayList<AsetDigital>
        ArrayList<AsetDigital> daftarAset = new ArrayList<>();
        daftarAset.add(new Gambar("IMG01", "Banner_Diskon.jpg", 4.5, "1920x1080"));
        daftarAset.add(new Video("VID01", "Company_Profile.mp4", 150.0, 180));
        daftarAset.add(new Dokumen("DOC01", "Laporan_Keuangan.pdf", 12.3, 45));
        daftarAset.add(new VideoPromosi("VPR01", "Iklan_Produk_Baru.mp4", 85.0, 30, "Remaja & Dewasa"));

        System.out.println("=== 1. PREVIEW ASET (POLIMORFISME ABSTRACT CLASS) ===");
        // Poin 5.2 & 5.3: Iterasi & Cek instanceof
        for (AsetDigital aset : daftarAset) {
            aset.tampilkanPreview();
            
            if (aset instanceof DapatDipromosikan) {
                System.out.println("-> Catatan: Objek ini mengimplementasikan DapatDipromosikan!");
                DapatDipromosikan promoAset = (DapatDipromosikan) aset;
                promoAset.infoPromosi();
                promoAset.promosikan();
            }
            System.out.println("--------------------------------------------------");
        }

        System.out.println("\n=== 2. DAFTAR PROMOSI (POLIMORFISME INTERFACE) ===");
        // Poin 5.4: ArrayList<DapatDipromosikan> terpisah
        ArrayList<DapatDipromosikan> daftarPromosi = new ArrayList<>();
        daftarPromosi.add(new VideoPromosi("VPR02", "Promo_Teaser_Game.mp4", 45.0, 15, "Gamer"));

        for (DapatDipromosikan promo : daftarPromosi) {
            promo.infoPromosi();
            promo.promosikan();
            System.out.println("--------------------------------------------------");
        }
    }
}