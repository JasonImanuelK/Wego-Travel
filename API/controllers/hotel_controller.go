package controllers

import (
	"fmt"
	"hash/fnv"
	"log"
	"net/http"
	"time"

	"encoding/json"

	"github.com/Wego-Travel/API/model"
	"github.com/gorilla/mux"
)

func LihatListHotel(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	errForm := r.ParseForm()
	if errForm != nil {
		SendErrorResponse(w, 400)
		return
	}

	vars := mux.Vars(r)

	kota := vars["kota"]
	tanggal_inap := vars["tanggal_inap"]
	akhir_inap := vars["akhir_inap"]
	jumlah_penginap := vars["jumlah_penginap"]

	// kota := r.FormValue("kota")
	// tanggal_inap := r.FormValue("tanggal_inap")
	// akhir_inap := r.FormValue("akhir_inap")
	// jumlah_penginap := r.FormValue("jumlah_penginap")

	log.Print(kota)
	log.Print(tanggal_inap)
	log.Print(akhir_inap)
	log.Print(jumlah_penginap)

	query := "SELECT e.id_hotel, e.nama_hotel, e.alamat_hotel, e.deskripsi, e.rating, e.promo FROM (SELECT d.*, IF(d.tipe_kamar = 'Single', 1, IF(d.tipe_kamar = 'Double', 2, IF(d.tipe_kamar = 'Deluxe', 4, 4))) AS 'capacity' FROM (SELECT a.*, b.nomor_kamar, b.tipe_kamar, b.status_kamar, c.tanggal_inap, DATE_ADD(c.tanggal_inap, INTERVAL c.lama_inap DAY) AS 'akhir_inap' FROM kamar_hotel b LEFT JOIN hotel a ON a.id_hotel=b.id_hotel LEFT JOIN tiket_hotel c ON b.id_tiket_hotel=c.id_tiket_hotel) d WHERE SUBSTR(d.alamat_hotel, LOCATE(', ', d.alamat_hotel)+2)=? AND ((? > d.akhir_inap OR ? < d.tanggal_inap ) OR d.tanggal_inap IS NULL)) e HAVING COUNT(e.capacity) >= ?;"
	rows, err := db.Query(query, kota, tanggal_inap, akhir_inap, jumlah_penginap)
	if err != nil {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 500)
		return
	}

	var hotel model.Hotel
	var list_hotel []model.Hotel

	for rows.Next() {
		if err := rows.Scan(&hotel.Id_hotel, &hotel.Nama_hotel, &hotel.Alamat_hotel, &hotel.Deskripsi, &hotel.Rating, &hotel.Promo); err != nil {
			fmt.Println(err.Error())
		} else {
			list_hotel = append(list_hotel, hotel)
		}
	}

	var hotelResponse model.HotelResponse
	if err == nil {
		hotelResponse.Status = 200
		hotelResponse.Message = "Success"
		hotelResponse.Data = list_hotel

		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(hotelResponse)
	} else {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 400)
	}
}

func LihatListKamarHotel(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	vars := mux.Vars(r)
	id_hotel := vars["id_hotel"]

	rows, err := db.Query("SELECT nomor_kamar, tipe_kamar, harga_kamar, status_kamar, id_hotel FROM kamar_hotel WHERE id_hotel=? AND status_kamar='Kosong';", id_hotel)
	if err != nil {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 500)
		return
	}

	var kamarHotel model.KamarHotel
	var list_kamarHotel []model.KamarHotel

	for rows.Next() {
		if err := rows.Scan(&kamarHotel.Nomor_kamar, &kamarHotel.Tipe_kamar, &kamarHotel.Harga_kamar, &kamarHotel.Status_kamar, &kamarHotel.Id_hotel); err != nil {
			fmt.Println(err.Error())
		} else {
			list_kamarHotel = append(list_kamarHotel, kamarHotel)
		}
	}

	var kamarHotelResponse model.KamarHotelResponse
	if err == nil {
		kamarHotelResponse.Status = 200
		kamarHotelResponse.Message = "Success"
		kamarHotelResponse.Data = list_kamarHotel

		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(kamarHotelResponse)
	} else {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 400)
	}
}

func PesanKamarHotel(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	err := r.ParseForm()
	if err != nil {
		return
	}

	nomor_kamar := r.Form.Get("nomor_kamar")
	id_pengguna := r.Form.Get("id_pengguna")
	id_voucher := r.Form.Get("id_voucher")
	nama_penginap := r.Form.Get("nama_penginap")
	jenis_kelamin := r.Form.Get("jenis_kelamin")
	tanggal_lahir := r.Form.Get("tanggal_lahir")
	tanggal_inap := r.Form.Get("tanggal_inap")
	lama_inap := r.Form.Get("lama_inap")

	c_tanggal_lahir, err := time.Parse("2006-01-02", tanggal_lahir)
	if err != nil {
		fmt.Println(err)
		return
	}

	c_tanggal_inap, err := time.Parse("2006-01-02", tanggal_inap)
	if err != nil {
		fmt.Println(err)
		return
	}

	texthash := nomor_kamar + id_pengguna + id_voucher + nama_penginap + jenis_kelamin + tanggal_lahir + tanggal_inap + lama_inap + time.Now().String()
	log.Print(texthash)
	h := fnv.New32a()
	h.Write([]byte(texthash))
	sha := h.Sum32()

	_, errQuery := db.Exec("INSERT INTO `tiket_hotel` (`id_tiket_hotel`, `id_pengguna`, `id_voucher`, `nama_penginap`, `jenis_kelamin`, `tanggal_lahir`, `tanggal_inap`, `lama_inap`) VALUES (?, ?, NULLIF(?, ''), ?, ?, ?, ?, ?)", sha, id_pengguna, id_voucher, nama_penginap, jenis_kelamin, c_tanggal_lahir, c_tanggal_inap, lama_inap)

	if errQuery == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}

	_, errQuery2 := db.Exec("UPDATE kamar_hotel SET status_kamar = ?, id_tiket_hotel = ? WHERE nomor_kamar = ?", "Terisi", sha, nomor_kamar)

	if errQuery2 == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}

	_, errQuery3 := db.Exec("UPDATE voucher SET status_penggunaan = 'Tidak Berlaku' WHERE id_voucher = ? AND tipe_tiket='Hotel'", id_voucher)

	if errQuery3 == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery3.Error())
		SendErrorResponse(w, 400)
	}
}

func BatalPesanHotel(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	err := r.ParseForm()
	if err != nil {
		return
	}

	id_tiket_hotel := r.Form.Get("id_tiket_hotel")

	_, errQuery := db.Exec("UPDATE tiket_hotel SET status_pemesanan = 'Dikembalikan' WHERE id_tiket_hotel = ?", id_tiket_hotel)

	if errQuery == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}

	_, errQuery2 := db.Exec("UPDATE voucher a JOIN tiket_hotel b ON a.id_voucher=b.id_voucher SET a.status_penggunaan = 'Berlaku' WHERE b.id_tiket_hotel=?", id_tiket_hotel)

	if errQuery2 == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery2.Error())
		SendErrorResponse(w, 400)
	}

	_, errQuery3 := db.Exec("UPDATE kamar_hotel SET status_kamar = 'Kosong', id_tiket_hotel = NULL WHERE id_tiket_hotel = ?", id_tiket_hotel)

	if errQuery3 == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery3.Error())
		SendErrorResponse(w, 400)
	}
}

func SelesaiPesanHotel(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	err := r.ParseForm()
	if err != nil {
		return
	}

	id_tiket_hotel := r.Form.Get("id_tiket_hotel")

	log.Print(id_tiket_hotel)

	_, errQuery := db.Exec("UPDATE tiket_hotel SET status_pemesanan = 'Selesai' WHERE id_tiket_hotel = ?", id_tiket_hotel)

	if errQuery == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}

	var id_voucher int
	row := db.QueryRow("SELECT id_voucher FROM tiket_hotel WHERE id_tiket_hotel = ?", id_tiket_hotel)

	if err := row.Scan(&id_voucher); err != nil {
		fmt.Println(err.Error())
	}

	_, errQuery2 := db.Exec("UPDATE voucher SET status_penggunaan = 'Berlaku' WHERE id_voucher = ?", id_voucher)

	if errQuery2 == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery2.Error())
		SendErrorResponse(w, 400)
	}

	_, errQuery3 := db.Exec("UPDATE kamar_hotel SET status_kamar = 'Kosong', id_tiket_hotel = NULL WHERE id_tiket_hotel = ?", id_tiket_hotel)

	if errQuery3 == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery3.Error())
		SendErrorResponse(w, 400)
	}
}
