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

	tipe_tiket := r.FormValue("tipe_tiket")
	id_pengguna := r.FormValue("id_pengguna")

	rows, _ := db.Query("SELECT id_voucher, nama_voucher, nilai, status_penggunaan FROM Voucher WHERE id_pengguna = ? AND tipe_tiket = ?", id_pengguna, tipe_tiket)
	var vouchersResponse model.VouchersResponse
	var voucher model.Voucher

	for rows.Next() {
		if err := rows.Scan(&voucher.Id_voucher, &voucher.Nama_voucher, &voucher.Nilai, &voucher.Status_penggunaan); err != nil {
			log.Println(err)
			SendErrorResponse(w, 500)
			return
		} else {
			vouchersResponse.Data = append(vouchersResponse.Data, voucher)
			vouchersResponse.Status = 200
			vouchersResponse.Message = "Success"
			w.Header().Set("Content-Type", "application/json")
			json.NewEncoder(w).Encode(vouchersResponse)
			log.Println("(SUCCESS)\t", "Login request")
		}
	}
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
