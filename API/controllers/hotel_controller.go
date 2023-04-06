package controllers

import (
	"fmt"
	"log"
	"net/http"
	"time"

	"crypto/sha1"
	"encoding/base64"
	"encoding/json"

	"github.com/Wego-Travel/API/model"
	"github.com/gorilla/mux"
)

func LihatListHotel(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	query := "SELECT id_hotel, nama_hotel, alamat_hotel, deskripsi, rating, promo FROM hotel;"
	rows, err := db.Query(query)
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

	param := mux.Vars(r)
	id_hotel := param["id_hotel"]

	rows, err := db.Query("SELECT nomor_kamar, tipe_kamar, harga_kamar, status_kamar, id_hotel FROM kamar_hotel WHERE id_hotel=?;", id_hotel)
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

	nomor_kamar := r.FormValue("nomor_kamar")
	id_pengguna := r.FormValue("id_pengguna")
	id_voucher := r.FormValue("id_voucher")
	nama_penginap := r.FormValue("nama_penginap")
	jenis_kelamin := r.FormValue("jenis_kelamin")
	tanggal_lahir := r.FormValue("tanggal_lahir")
	tanggal_inap := r.FormValue("tanggal_inap")
	lama_inap := r.FormValue("lama_inap")

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

	texthash := nomor_kamar + id_pengguna + id_voucher + nama_penginap + jenis_kelamin + tanggal_lahir + tanggal_inap + lama_inap + time.Now().Format("yyyyMMddHHmmss")
	hasher := sha1.New()
	hasher.Write([]byte(texthash))
	sha := base64.URLEncoding.EncodeToString(hasher.Sum(nil))

	_, errQuery := db.Exec("INSERT INTO `tiket_hotel` (`id_tiket_hotel`, `id_pengguna`, `nama_penginap`, `jenis_kelamin`, `tanggal_lahir`, `tanggal_inap`, `lama_inap`) VALUES (?, ?, ?, ?, ?, ?, ?)", sha, id_pengguna, nama_penginap, jenis_kelamin, c_tanggal_lahir, c_tanggal_inap, lama_inap)

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
}

func BatalPesanHotel(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	err := r.ParseForm()
	if err != nil {
		log.Println("(ERROR)\t", err.Error())
		SendErrorResponse(w, 400)
		return
	}

	param := mux.Vars(r)
	id_tiket_hotel := param["id_tiket_hotel"]

	_, errQuery := db.Exec("UPDATE tiket_hotel th INNER JOIN kamar_hotel kh ON th.id_tiket_hotel = kh.id_tiket_hotel SET th.status_pemesanan = 'Dikembalikan' AND kh.status_kamar = 'Kosong' WHERE th.id_tiket_hotel = ?", id_tiket_hotel)

	if errQuery == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}
}
