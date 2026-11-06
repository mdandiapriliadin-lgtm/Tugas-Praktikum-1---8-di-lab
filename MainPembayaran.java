// Interface kontrak
public interface Pembayaran {
    void prosesPembayaran(double jumlah);
    
    default void cetakBukti() {
        System.out.println("Bukti pembayaran telah dicetak,");
    }
}

// Hapus kata 'public' di sini
class TransferBank implements Pembayaran {
    @Override
    public void prosesPembayaran(double jumlah) {
        System.out.println("Transfer bank Rp " + jumlah + " berhasil.");
    }
}

// Hapus kata 'public' di sini
class EWallet implements Pembayaran {
    @Override
    public void prosesPembayaran(double jumlah) {
        System.out.println("Pembayaran via E-Wallet Rp " + jumlah + " berhasil.");
    }
}

// Main class
public class MainPembayaran {
    public static void main(String[] args) {
        Pembayaran[] metode = {
            new TransferBank(),
            new EWallet()
        };
        for (Pembayaran p : metode) {
            p.prosesPembayaran(150000);
            p.cetakBukti();
            System.out.println("---");
        }
    }
}