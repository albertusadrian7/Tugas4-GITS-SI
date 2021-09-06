/*
Nama: Albertus Adrian Susanto

Soal:
Natan adalah seorang pemilik toko alat musik gitar dan keyboard. Anda sebagai teman Natan ingin membantunya dengan membuat sebuah program kasir sederhana. Program ini dapat menampilkan daftar gitar keyboard yang tersedia berdasarkan input user. Selanjutnya, program ini akan meminta user untuk memilih alat musik yang ingin dibeli beserta dengan jumlahnya. Output yang dihasilkan dari program ini berupa nota yang berisi rincian alat musik, jumlah, serta total biaya yang harus dibayar oleh pembeli.

Input:
- Pilihan untuk kategori yang ingin ditampilkan user [1. Gitar || 2. Keyboard]
- Pilihan alat musik
- Jumlah item

Output:
Nota Transaksi dengan rincian, yaitu
- Nama item
- Jumlah
- Total tagihan
 */

import java.util.*

fun main(args: Array<String>) {
    // Membuat list alat musik gitar dengan memanfaatkan method add
    var listGitar = arrayListOf<String>()
    listGitar.add("Gitar Cort AD810 OP - Rp 1115000")
    listGitar.add("Gitar Akustik Yamaha F310 - Rp 1135000")
    listGitar.add("Gitar Tanglewood TWR2 P - Rp 1800000")

    // Membuat list alat musik keyboard
    var listKeyboard = arrayListOf<String>(
        "Keyboard Yamaha PSR SX600 SX 600 - Rp 8990000",
        "Keyboard Yamaha PSR E373 - Rp 3520000",
        "Keyboard Yamaha PSR E 273 - Rp 2243000"
    )
    println("=== Selamat Datang di Toko Musik ===")
    val reader = Scanner(System.`in`)
    println("1. Gitar")
    println("2. Keyboard")

    // Input untuk melihat daftar musik berdasarkan kategori gitar atau keyboard
    print("Mau cari Gitar atau Keyboard? [1/2]: ")
    var pilihan: Int = reader.nextInt()

    // Membuat lambda untuk mengecek apakah input kategori sudah tepat
    val lambdaCekKategori = {pilihan: Int ->
        when(pilihan) {
            1,2 -> true
            else -> false
        }
    }

    // Jika lambdaCekKategori bernilai true
    if (lambdaCekKategori(pilihan)) {
        var kategori: String = ""
        var no = 0
        if (pilihan == 1){
            println("\nDaftar Alat Musik Gitar:")
            kategori = "gitar"
            // Mengiterasi isi listGitar dengan for
            for (i in listGitar){
                no++
                println("$no. $i")
            }
        } else {
            println("\nDaftar Alat Musik Keyboard:")
            kategori = "keyboard"
            // Mengiterasi isi listKeyboard dengan while
            while (no < listKeyboard.size) {
                println("${no+1}. ${listKeyboard[no]}")
                no++
            }
        }

        // Membuat lambda yang fungsinya seperti nota. Outputnya berupa string yang berisi:
        // 1. Alat musik yang ingin dibeli
        // 2. Total biaya (harga alat musik * jumlah)
        val lambdaNota: (String, Int) -> String = { alatMusik: String, jumlah: Int ->
            var splitter = alatMusik.split(" - Rp ")
            var namaAlat = splitter[0]
            var totalHarga = splitter[1].toInt()*jumlah
            var nota = "\n=== Nota Transaksi ===\nItem: $namaAlat \nJumlah: $jumlah \nTotal yang harus Anda bayar Rp $totalHarga"
            nota
        }

        // Input alat musik yang ingin dibeli berdasarkan nomor
        print("\nPilih alat musik yang ingin Anda beli berdasarkan nomor di atas: ")
        var noAlatMusik: Int = reader.nextInt()

        // Membuat lambda untuk mengecek apakah input nomor alat musik sudah tepat dan tersedia
        val lambdaCekInput = fun ( noAlatMusik: Int ): Boolean {
            if (noAlatMusik > 0 && noAlatMusik <= listKeyboard.size){
                return true
            }
            return false
        }
        // Jika lambdaCekInput bernilai true
        if (lambdaCekInput(noAlatMusik)) {
            // Input jumlah alat musik yang ingin dibeli
            print("Masukkan jumlah yang ingin Anda beli: ")
            var jumlah: Int = reader.nextInt()
            if (kategori.equals("keyboard")){
                // Menampilkan nota transaksi keyboard dengan memanggil lambdaNota
                println(lambdaNota(listKeyboard[noAlatMusik-1],jumlah))
            } else {
                // Menampilkan nota transaksi gitar dengan memanggil lambdaNota
                println(lambdaNota(listGitar[noAlatMusik-1],jumlah))
            }
        } else {
            println("\nOops, telah terjadi kesalahan. Input Anda salah!")
        }
    } else {
        println("\nOops, telah terjadi kesalahan. Input Anda salah!")
    }
}