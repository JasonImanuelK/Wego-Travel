package model

type Pengguna struct {
	Id_pengguna   int    `json:"id_pengguna"`
	Nama          string `json:"nama"`
	Email         string `json:"email"`
	Password      string `json:"password"`
	Jenis_kelamin string `json:"jenis_kelamin"`
	Tanggal_lahir string `json:"tanggal_lahir"`
	Nomor_telepon int    `json:"nomor_telepon"`
	Alamat        string `json:"alamat"`
}
type MessageResponse struct {
	Status  int    `json:"status"`
	Message string `json:"message"`
}

type UserResponse struct {
	Status  int      `json:"status"`
	Message string   `json:"message"`
	Data    Pengguna `json:"data"`
	Token   string   `json:"token"`
}

type UsersResponse struct {
	Status  int        `json:"status"`
	Message string     `json:"message"`
	Data    []Pengguna `json:"data"`
}
