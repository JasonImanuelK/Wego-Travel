package controllers

import (
	"fmt"
	"log"
	"net/http"

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
		if err := rows.Scan(&hotel.Id_hotel, &hotel.nama_hotel, &hotel.alamat_hotel, &hotel.deskripsi, &hotel.rating, &hotel.promo); err != nil {
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
	} else {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 400)
	}
}

func LihatListKamarHotel(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	query := "SELECT nomor_kamar, tipe_kamar, harga_kamar, status_kamar, id_hotel FROM kamar_hotel;"
	rows, err := db.Query(query)
	if err != nil {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 500)
		return
	}

	var kamarHotel model.KamarHotel
	var list_kamarHotel []model.KamarHotel

	for rows.Next() {
		if err := rows.Scan(&kamarHotel.nomor_kamar, &kamarHotel.tipe_kamar, &kamarHotel.harga_kamar, &kamarHotel.status_kamar, &kamarHotel.id_hotel); err != nil {
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
	} else {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 400)
	}
}

func PesanKamarHotel(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	param := mux.Vars(r)
	nomor_kamar := param["nomor_kursi"]
	id_pengguna := param["id_pengguna"]
	id_voucher := param["id_voucher"]
	nama_penginap := param["nama_depan"]
	jenis_kelamin := param["jenis_kelamin"]
	tanggal_lahir := param["tanggal_lahir"]
	tanggal_inap := param["tanggal_inap"]
	lama_inap := param["lama_inap"]

	_, errQuery := db.Exec("INSERT INTO `tiket_hotel` (`tanggal_pemesanan`, `id_pengguna`, `id_voucher`, `nama_penginap`, `jenis_kelamin`, `tanggal_lahir`, `tanggal_inap`, `lama_inap`) VALUES (?, ?, ? ?, ?, ?, ?, ?)", time.Now, id_pengguna, id_voucher, nama_peenginap, jenis_kelamin, tanggal_lahir, tanggal_inap, lama_inap)

	if errQuery == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}

	_, errQuery := db.Exec("UPDATE kamar_hotel SET status_kamar = ?, id_tiket_hotel = ? WHERE nomor_kamar = ?", "Terisi", 999, nomor_kamar)

	if errQuery == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}
}