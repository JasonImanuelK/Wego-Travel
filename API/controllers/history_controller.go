package controllers

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"

	"github.com/Wego-Travel/API/model"
	"github.com/gorilla/mux"
)

func MelihatHistoryPesawat(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	param := mux.Vars(r)
	id_pengguna := param["id_pengguna"]

	query := "SELECT id_tiket_pesawat, tanggal_pemesanan, status_pemesanan, id_voucher FROM tiket_pesawat WHERE id_pengguna = " + id_pengguna + " ORDER BY tanggal_pemesanan DESC ;"
	rows, err := db.Query(query)
	if err != nil {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 500)
		return
	}

	var tiket_pesawat model.HistoryPesawat
	var list_tiket_pesawat []model.HistoryPesawat

	for rows.Next() {
		if err := rows.Scan(&tiket_pesawat.Id_tiket_pesawat, &tiket_pesawat.Tanggal_pemesanan, &tiket_pesawat.Status_pemesanan, &tiket_pesawat.Id_voucher); err != nil {
			fmt.Println(err.Error())
		} else {
			list_tiket_pesawat = append(list_tiket_pesawat, tiket_pesawat)
		}
	}

	var tiketPesawatResponse model.HistoryPesawatResponse
	if err == nil {
		tiketPesawatResponse.Status = 200
		tiketPesawatResponse.Message = "Success"
		tiketPesawatResponse.Data = list_tiket_pesawat
		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(tiketPesawatResponse)
		log.Println("(SUCCESS)\t", "Login request")
	} else {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 400)
	}
}

func MelihatHistoryHotel(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	param := mux.Vars(r)
	id_pengguna := param["id_pengguna"]

	query := "SELECT id_tiket_hotel, tanggal_pemesanan, status_pemesanan, id_voucher FROM tiket_hotel WHERE id_pengguna = " + id_pengguna + " ORDER BY tanggal_pemesanan DESC ;"
	rows, err := db.Query(query)
	if err != nil {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 500)
		return
	}

	var tiket_hotel model.HistoryHotel
	var list_tiket_hotel []model.HistoryHotel

	for rows.Next() {
		if err := rows.Scan(&tiket_hotel.Id_tiket_hotel, &tiket_hotel.Tanggal_pemesanan, &tiket_hotel.Status_pemesanan, &tiket_hotel.Id_voucher); err != nil {
			fmt.Println(err.Error())
		} else {
			list_tiket_hotel = append(list_tiket_hotel, tiket_hotel)

		}
	}

	var tiketHotelResponse model.HistoryHotelResponse
	if err == nil {
		tiketHotelResponse.Status = 200
		tiketHotelResponse.Message = "Success"
		tiketHotelResponse.Data = list_tiket_hotel
		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(tiketHotelResponse)
		log.Println("(SUCCESS)\t", "Login request")
	} else {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 400)
	}
}
