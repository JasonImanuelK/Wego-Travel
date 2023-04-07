package controllers

import (
	"fmt"
	"log"
	"net/http"
	"time"

	"crypto/sha1"
	"encoding/base64"
	"encoding/json"

	"github.com/Wego-Travel/API/model"
	"github.com/gorilla/mux"
)

func LihatListPesawat(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	query := "SELECT id_pesawat, maskapai, tempat_berangkat, tujuan_berangkat, tanggal_berangkat, jam_berangkat, promo FROM pesawat;"
	rows, err := db.Query(query)
	if err != nil {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 500)
		return
	}

	var pesawat model.Pesawat
	var list_pesawat []model.Pesawat

	for rows.Next() {
		if err := rows.Scan(&pesawat.Id_pesawat, &pesawat.Maskapai, &pesawat.Tempat_berangkat, &pesawat.Tujuan_berangkat, &pesawat.Tanggal_berangkat, &pesawat.Jam_berangkat, &pesawat.Promo); err != nil {
			fmt.Println(err.Error())
		} else {
			list_pesawat = append(list_pesawat, pesawat)
		}
	}

	var pesawatResponse model.PesawatResponse
	if err == nil {
		pesawatResponse.Status = 200
		pesawatResponse.Message = "Success"
		pesawatResponse.Data = list_pesawat

		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(pesawatResponse)
	} else {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 400)
	}
}

func LihatListKursiPesawat(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	param := mux.Vars(r)
	id_pesawat := param["id_pesawat"]

	rows, err := db.Query("SELECT nomor_kursi, tipe_kursi, harga_kursi, status_kursi, id_pesawat FROM kursi_pesawat WHERE id_pesawat=?;", id_pesawat)
	if err != nil {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 500)
		return
	}

	var kursiPesawat model.KursiPesawat
	var list_kursiPesawat []model.KursiPesawat

	for rows.Next() {
		if err := rows.Scan(&kursiPesawat.Nomor_kursi, &kursiPesawat.Tipe_kursi, &kursiPesawat.Harga_kursi, &kursiPesawat.Status_kursi, &kursiPesawat.Id_pesawat); err != nil {
			fmt.Println(err.Error())
		} else {
			list_kursiPesawat = append(list_kursiPesawat, kursiPesawat)
		}
	}

	var kursiPesawatResponse model.KursiPesawatResponse
	if err == nil {
		kursiPesawatResponse.Status = 200
		kursiPesawatResponse.Message = "Success"
		kursiPesawatResponse.Data = list_kursiPesawat

		w.Header().Set("Content-Type", "application/json")
		json.NewEncoder(w).Encode(kursiPesawatResponse)
	} else {
		log.Println("(ERROR)\t", err)
		SendErrorResponse(w, 400)
	}
}

func PesanKursiPesawat(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	nomor_kursi := r.FormValue("nomor_kursi")
	id_pengguna := r.FormValue("id_pengguna")
	id_voucher := r.FormValue("id_voucher")
	nama_depan := r.FormValue("nama_depan")
	nama_belakang := r.FormValue("nama_belakang")
	jenis_kelamin := r.FormValue("jenis_kelamin")
	tanggal_lahir := r.FormValue("tanggal_lahir")
	email := r.FormValue("email")
	nomor_telepon := r.FormValue("nomor_telepon")

	c_tanggal_lahir, err := time.Parse("2006-01-02", tanggal_lahir)
	if err != nil {
		fmt.Println(err)
		return
	}

	texthash := nomor_kursi + id_pengguna + id_voucher + nama_depan + nama_belakang + jenis_kelamin + tanggal_lahir + email + nomor_telepon + time.Now().Format("yyyyMMddHHmmss")
	hasher := sha1.New()
	hasher.Write([]byte(texthash))
	sha := base64.URLEncoding.EncodeToString(hasher.Sum(nil))

	_, errQuery := db.Exec("INSERT INTO `tiket_pesawat` (`id_tiket_pesawat`, `id_pengguna`, `nama_depan`, `nama_belakang`, `jenis_kelamin`, `tanggal_lahir`, `email`, `nomor_telepon`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", sha, id_pengguna, nama_depan, nama_belakang, jenis_kelamin, c_tanggal_lahir, email, nomor_telepon)

	if errQuery == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}

	_, errQuery2 := db.Exec("UPDATE kursi_pesawat SET status_kursi = ?, id_tiket_pesawat = ? WHERE nomor_kursi = ?", "Terisi", sha, nomor_kursi)

	if errQuery2 == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}
}

func BatalPesanPesawat(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	param := mux.Vars(r)
	id_tiket_pesawat := param["id_tiket_pesawat"]

	_, errQuery := db.Exec("UPDATE tiket_pesawat tp INNER JOIN kursi_pesawat kp ON tp.id_tiket_pesawat = kp.id_tiket_pesawat SET tp.status_pemesanan = 'Dikembalikan' AND kp.status_kamar = 'Kosong' WHERE th.id_tiket_pesawat = ?", id_tiket_pesawat)

	if errQuery == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}
}
