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
	} else {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 400)
	}
}

func PesanKamarHotel(w http.ResponseWriter, r *http.Request) {

}

func BatalPesanHotel(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

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
