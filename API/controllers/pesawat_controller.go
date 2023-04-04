package controllers

import (
	"fmt"
	"log"
	"net/http"

	"github.com/Wego-Travel/API/model"
	"github.com/gorilla/mux"
)

func LihatListPesawat(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	query := "SELECT id_pesawat, maskapai, tempat_berangkat, tujuan_berangkat, tanggal_berangkat, jam_berangkat, promo FROM pesawat;"
	rows, err := db.Query(query)
	if err != nil {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 500)
		return
	}

	var pesawat model.Pesawat
	var list_pesawat []model.Pesawat

	for rows.Next() {
		if err := rows.Scan(&pesawat.Id_pesawat, &pesawat.maskapai, &pesawat.tempat_berangkat, &pesawat.tujuan_berangkat, &pesawat.tanggal_berangkat, &pesawat.jam_berangkat, &pesawat.promo); err != nil {
			fmt.Println(err.Error())
		} else {
			list_pesawat = append(list_pesawat, pesawat)
		}
	}

	var pesawatResponse model.PesawatResponse
	if err == nil {
		pesawatResponse.Status = 200
		pesawatResponse.Message = "Success"
		pesawatResponse.Data = list_pesawat
	} else {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 400)
	}
}

func LihatListKursiPesawat(w http.ResponseWriter, r *http.Request) {
	
}

func PesanKursiPesawat(w http.ResponseWriter, r *http.Request) {
	
}