package controllers

import (
	"encoding/json"
	"log"
	"net/http"

	"github.com/Wego-Travel/API/model"
)

func LihatKupon(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	tipe_tiket := r.Form.Get("tipe_tiket")
	id_pengguna := r.Form.Get("id_pengguna")

	rows, _ := db.Query("SELECT id_voucher, nama_voucher, nilai, status_penggunaan FROM Voucher WHERE id_pengguna = ? AND tipe_tiket = ?", id_pengguna, tipe_tiket)
	var vouchersResponse model.VouchersResponse
	var voucher model.Voucher

	for rows.Next() {
		if err := rows.Scan(&voucher.Id_voucher, &voucher.Nama_voucher, &voucher.Nilai, &voucher.Status_penggunaan); err != nil {
			log.Println(err)
			vouchersResponse.Status = 500
			vouchersResponse.Message = "internal error"
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
