package model

type HistoryPesawat struct {
	Id_tiket_pesawat  int    `json:"id_tiket_pesawat"`
	Tanggal_pemesanan string `json:"tanggal_pemesanan"`
	Status_pemesanan  string `json:"status_pemesanan"`
	Id_voucher        int    `json:"id_voucher"`
}

type HistoryPesawatResponse struct {
	Status  int              `json:"status"`
	Message string           `json:"message"`
	Data    []HistoryPesawat `json:"data"`
}

type HistoryHotel struct {
	Id_tiket_hotel    int    `json:"id_tiket_hotel"`
	Tanggal_pemesanan string `json:"tanggal_pemesanan"`
	Status_pemesanan  string `json:"status_pemesanan"`
	Id_voucher        int    `json:"id_voucher"`
}

type HistoryHotelResponse struct {
	Status  int            `json:"status"`
	Message string         `json:"message"`
	Data    []HistoryHotel `json:"data"`
}
