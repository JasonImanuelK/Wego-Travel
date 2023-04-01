package controllers

import (
	"encoding/json"
	"fmt"
	"net/http"
	"os"

	"github.com/Wego-Travel/API/model"
	"github.com/gorilla/mux"

	_ "github.com/go-sql-driver/mysql"
	"github.com/joho/godotenv"
)

func LupaPassword(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	err := r.ParseForm()
	var response model.GeneralResponse
	w.Header().Set("Content-Type", "application/json")
	if err != nil {
		response.Status = 400
		response.Message = "Bad Request"
		json.NewEncoder(w).Encode(response)
		return
	}
	password := r.Form.Get("password")
	email := r.Form.Get("email")
	_, errQuery := db.Exec("UPDATE pengguna SET password = ? WHERE email = ", password, email)

	if errQuery == nil {
		response.Status = 200
		response.Message = "Success"
		json.NewEncoder(w).Encode(response)
	} else {
		response.Status = 400
		response.Message = "Bad Request"
		json.NewEncoder(w).Encode(response)
	}

}

func PerbaruiProfil(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	err := r.ParseForm()
	var response model.GeneralResponse
	w.Header().Set("Content-Type", "application/json")
	if err != nil {
		response.Status = 400
		response.Message = "Bad Request"
		json.NewEncoder(w).Encode(response)
		return
	}

	param := mux.Vars(r)
	idPengguna := param["id_pengguna"]

	nama := r.Form.Get("nama")
	tanggalLahir := r.Form.Get("tanggal_lahir")
	alamat := r.Form.Get("alamat")
	jenisKelamin := r.Form.Get("jenis_kelamin")
	email := r.Form.Get("email")
	nomorTelepon := r.Form.Get("nomor_telepon")

	query := "UPDATE pengguna SET "

	if nama != "" {
		query = query + "nama = " + nama + ", "
	}
	if tanggalLahir != "" {
		query += "tanggal_lahir = " + tanggalLahir + ", "
	}
	if alamat != "" {
		query += "alamat = " + alamat + ", "
	}
	if jenisKelamin != "" {
		query += "jenis_kelamin = " + jenisKelamin + ", "
	}
	if email != "" {
		query += "email = " + email + ", "
	}
	if nomorTelepon != "" {
		query += "nomor_telepon = " + nomorTelepon + ", "
	}
	query = query[:len(query)-2]
	query += " WHERE id_pengguna = " + idPengguna

	_, errQuery := db.Query(query)

	if errQuery == nil {
		response.Status = 200
		response.Message = "Success"
		json.NewEncoder(w).Encode(response)
	} else {
		response.Status = 400
		response.Message = "Bad Request"
		json.NewEncoder(w).Encode(response)
	}

}

func LoadEnv(input string) string {
	err := godotenv.Load(".env.sample")
	if err != nil {
		fmt.Print("File envnya ga ada")
		return ""
	}
	hasilENV := os.Getenv(input)
	return hasilENV
}
