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
	
}

func PesanKamarHotel(w http.ResponseWriter, r *http.Request) {
	
}