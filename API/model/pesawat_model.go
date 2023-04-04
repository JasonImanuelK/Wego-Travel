package model

import "time"

type Pesawat struct {
	Id_pesawat 			int 		`json:"id_pesawat"`
	Maskapai 			string 		`json:"maskapai"`
	Tempat_berangkat 	string 		`json:"tempat_berangkat"`
	Tujuan_berangkat 	string 		`json:"tujuan_berangkat"`
	Tanggal_berangkat 	time.Time 	`json:"tanggal_berangkat"`
	Jam_berangkat 		time.Time 	`json:"jam_berangkat"`
	Promo 				float32 	`json:"promo"`
}

type KursiPesawat struct {
	Nomor_kursi 	string 		`json:"nomor_kursi"`
	Tipe_kursi 		string 		`json:"tipe_kursi"`
	Harga_kursi 	float32 	`json:"harga_kursi"`
	Status_kursi 	string 		`json:"status_kursi"`
	Id_pesawat 		int 		`json:"id_pesawat"`
}

type PesawatResponse struct {
	Status 		int 		`json:"status"`
	Message 	string 		`json:"message"`
	Data 		[]Pesawat 	`json:"data"`
}

type KursiPesawatResponse struct {
	Status 		int 		`json:"status"`
	Message 	string 		`json:"message"`
	Data 		[]KursiPesawat 	`json:"data"`
}