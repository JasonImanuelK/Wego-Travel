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

type KamarHotel struct {
	Nomor_kamar 	string 		`json:"nomor_kamar"`
	Tipe_kamar 		string 		`json:"tipe_kamar"`
	Harga_kamar 	float32 	`json:"harga_kamar"`
	Status_kamar 	string 		`json:"status_kamar"`
	Id_hotel 		int 		`json:"id_hotel"`
}

type HotelResponse struct {
	Status 		int 		`json:"status"`
	Message 	string 		`json:"message"`
	Data 		[]Hotel 	`json:"data"`
}

type KamarHotelResponse struct {
	Status 		int 		`json:"status"`
	Message 	string 		`json:"message"`
	Data 		[]KamarHotel 	`json:"data"`
}