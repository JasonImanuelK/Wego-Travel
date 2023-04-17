package controllers

import (
	"encoding/json"
	"log"
	"net/http"

	"github.com/Wego-Travel/API/model"
	"github.com/gorilla/mux"
)

func LihatKupon(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	param := mux.Vars(r)
	id_pengguna := param["id_pengguna"]

	url := "SELECT id_voucher, nama_voucher, nilai, tipe_tiket, status_penggunaan FROM Voucher WHERE id_pengguna = ?"
	rows, _ := db.Query(url, id_pengguna)

	var vouchersResponse model.VouchersResponse
	var voucher model.Voucher

	for rows.Next() {
		if err := rows.Scan(&voucher.Id_voucher, &voucher.Nama_voucher, &voucher.Nilai, &voucher.Tipe_tiket, &voucher.Status_penggunaan); err != nil {
			log.Println(err)
			SendErrorResponse(w, 500)
			return
		} else {
			vouchersResponse.Data = append(vouchersResponse.Data, voucher)
			vouchersResponse.Status = 200
			vouchersResponse.Message = "Success"
		}
	}
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(vouchersResponse)
}

func LihatKuponBerdasarkanTipeTiket(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	param := mux.Vars(r)
	id_pengguna := param["id_pengguna"]
	tipe_tiket := param["tipe_tiket"]

	url := "SELECT id_voucher, nama_voucher, nilai, tipe_tiket, status_penggunaan FROM Voucher WHERE id_pengguna = ? AND tipe_tiket = ?"
	rows, _ := db.Query(url, id_pengguna, tipe_tiket)

	var vouchersResponse model.VouchersResponse
	var voucher model.Voucher

	for rows.Next() {
		if err := rows.Scan(&voucher.Id_voucher, &voucher.Nama_voucher, &voucher.Nilai, &voucher.Tipe_tiket, &voucher.Status_penggunaan); err != nil {
			log.Println(err)
			SendErrorResponse(w, 500)
			return
		} else {
			vouchersResponse.Data = append(vouchersResponse.Data, voucher)
			vouchersResponse.Status = 200
			vouchersResponse.Message = "Success"
		}
	}
	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(vouchersResponse)
}

func PakaiKupon(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	param := mux.Vars(r)
	id_voucher := param["id_voucher"]

	_, errQuery := db.Exec("UPDATE voucher SET status_penggunaan = 'Tidak Berlaku' WHERE id_voucher = ?", id_voucher)

	if errQuery == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}
}
