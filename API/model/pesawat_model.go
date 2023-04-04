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

type PesawatResponse struct {
	Status 		int 		`json:"status"`
	Message 	string 		`json:"message"`
	Data 		[]Pesawat 	`json:"data"`
}