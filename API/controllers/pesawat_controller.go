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
	db := Connect()
	defer db.Close()

	query := "SELECT nomor_kursi, tipe_kursi, harga_kursi, status_kursi, id_pesawat FROM kursi_pesawat;"
	rows, err := db.Query(query)
	if err != nil {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 500)
		return
	}

	var kursiPesawat model.KursiPesawat
	var list_kursiPesawat []model.KursiPesawat

	for rows.Next() {
		if err := rows.Scan(&kursiPesawat.nomor_kursi, &kursiPesawat.tipe_kursi, &kursiPesawat.harga_kursi, &kursiPesawat.status_kursi, &kursiPesawat.id_pesawat); err != nil {
			fmt.Println(err.Error())
		} else {
			list_kursiPesawat = append(list_kursiPesawat, kursiPesawat)
		}
	}

	var kursiPesawatResponse model.KursiPesawatResponse
	if err == nil {
		kursiPesawatResponse.Status = 200
		kursiPesawatResponse.Message = "Success"
		kursiPesawatResponse.Data = list_kursiPesawat
	} else {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 400)
	}
}

func PesanKursiPesawat(w http.ResponseWriter, r *http.Request) {

}

func BatalPesanPesawat(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	err := r.ParseForm()
	if err != nil {
		log.Println("(ERROR)\t", err.Error())
		SendErrorResponse(w, 400)
		return
	}

	param := mux.Vars(r)
	id_tiket_pesawat := param["id_tiket_pesawat"]

	_, errQuery := db.Exec("UPDATE tiket_pesawat tp INNER JOIN kursi_pesawat kp ON tp.id_tiket_pesawat = kp.id_tiket_pesawat SET tp.status_pemesanan = 'Dikembalikan' AND kp.status_kamar = 'Kosong' WHERE th.id_tiket_pesawat = ?", id_tiket_pesawat)

	if errQuery == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}
}
