package model

import "time"

type Hotel struct {
	Id_hotel 		int 		`json:"id_hotel"`
	Nama_hotel 		string 		`json:"nama_hotel"`
	Alamat_hotel 	string 		`json:"alamat_hotel"`
	Deskripsi 		string 		`json:"deskripsi"`
	Rating 			int 		`json:"rating"`
	Promo 			float32 	`json:"promo"`
}

type HotelResponse struct {
	Status 		int 		`json:"status"`
	Message 	string 		`json:"message"`
	Data 		[]Hotel 	`json:"data"`
}