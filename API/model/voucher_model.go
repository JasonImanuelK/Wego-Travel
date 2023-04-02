package model

type Voucher struct {
	Id_voucher        int     `json:"id_voucher"`
	Nama_voucher      string  `json:"nama_voucher"`
	Nilai             float32 `json:"nilai"`
	Status_penggunaan string  `json:"status_penggunaan"`
}

type VouchersResponse struct {
	Status  int       `json:"status"`
	Message string    `json:"message"`
	Data    []Voucher `json:"data"`
}
