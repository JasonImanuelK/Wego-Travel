package model

import "time"

type TiketPesawat struct {
	Id_tiket_pesawat  int       `json:"id_tiket_pesawat"`
	Tanggal_pemesanan time.Time `json:"tanggal_pemesanan"`
	Status_pemesanan  string    `json:"status_pemesanan"`
	Id_voucher        int       `json:"id_voucher"`
}

type TiketPesawatResponse struct {
	Status  int            `json:"status"`
	Message string         `json:"message"`
	Data    []TiketPesawat `json:"data"`
}
