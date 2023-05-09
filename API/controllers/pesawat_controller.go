package controllers

import (
	"fmt"
	"hash/fnv"
	"log"
	"net/http"
	"time"

	"encoding/json"

	"github.com/Wego-Travel/API/model"
	"github.com/gorilla/mux"
)

func LihatListPesawat(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	err := r.ParseForm()
	if err != nil {
		return
	}

	param := mux.Vars(r)

	tempat_berangkat := param["tempat_berangkat"]
	tujuan_berangkat := param["tujuan_berangkat"]
	tanggal_berangkat := param["tanggal_berangkat"]

	query := "SELECT id_pesawat, maskapai, tempat_berangkat, tujuan_berangkat, tanggal_berangkat, jam_berangkat, promo FROM pesawat WHERE tempat_berangkat=? AND tujuan_berangkat=? AND tanggal_berangkat >= ?;"
	rows, err := db.Query(query, tempat_berangkat, tujuan_berangkat, tanggal_berangkat)
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

	rows, err := db.Query("SELECT nomor_kursi, tipe_kursi, harga_kursi, status_kursi, id_pesawat FROM kursi_pesawat WHERE id_pesawat=? AND status_kursi='kosong';", id_pesawat)
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

	err := r.ParseForm()
	if err != nil {
		return
	}

	nomor_kursi := r.Form.Get("nomor_kursi")
	id_pengguna := r.Form.Get("id_pengguna")
	id_voucher := r.Form.Get("id_voucher")
	nama_depan := r.Form.Get("nama_depan")
	nama_belakang := r.Form.Get("nama_belakang")
	jenis_kelamin := r.Form.Get("jenis_kelamin")
	tanggal_lahir := r.Form.Get("tanggal_lahir")
	email := r.Form.Get("email")
	nomor_telepon := r.Form.Get("nomor_telepon")

	c_tanggal_lahir, err := time.Parse("2006-01-02", tanggal_lahir)
	if err != nil {
		fmt.Println(err)
		return
	}

	texthash := nomor_kursi + id_pengguna + id_voucher + nama_depan + nama_belakang + jenis_kelamin + tanggal_lahir + email + nomor_telepon + time.Now().String()
	log.Print(texthash)
	h := fnv.New32a()
	h.Write([]byte(texthash))
	sha := h.Sum32()

	_, errQuery := db.Exec("INSERT INTO `tiket_pesawat` (`id_tiket_pesawat`, `id_pengguna`, `id_voucher`, `nama_depan`, `nama_belakang`, `jenis_kelamin`, `tanggal_lahir`, `email`, `nomor_telepon`) VALUES (?, ?, NULLIF(?, ''), ?, ?, ?, ?, ?, ?)", sha, id_pengguna, id_voucher, nama_depan, nama_belakang, jenis_kelamin, c_tanggal_lahir, email, nomor_telepon)

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
		log.Println("(ERROR)\t", errQuery2.Error())
		SendErrorResponse(w, 400)
	}

	_, errQuery3 := db.Exec("UPDATE voucher SET status_penggunaan = 'Tidak Berlaku' WHERE id_voucher = ? AND tipe_tiket='Pesawat'", id_voucher)

	if errQuery3 == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery3.Error())
		SendErrorResponse(w, 400)
	}
}

func BatalPesanPesawat(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	err := r.ParseForm()
	if err != nil {
		return
	}

	id_tiket_pesawat := r.Form.Get("id_tiket_pesawat")

	_, errQuery := db.Exec("UPDATE tiket_pesawat SET status_pemesanan = 'Dikembalikan' WHERE id_tiket_pesawat = ?", id_tiket_pesawat)

	if errQuery == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}

	_, errQuery2 := db.Exec("UPDATE voucher a JOIN tiket_pesawat b ON a.id_voucher=b.id_voucher SET a.status_penggunaan = 'Berlaku' WHERE b.id_tiket_pesawat=?", id_tiket_pesawat)

	if errQuery2 == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery2.Error())
		SendErrorResponse(w, 400)
	}

	_, errQuery3 := db.Exec("UPDATE kursi_pesawat SET status_kursi = 'Kosong', id_tiket_pesawat = NULL WHERE id_tiket_pesawat = ?", id_tiket_pesawat)

	if errQuery3 == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery3.Error())
		SendErrorResponse(w, 400)
	}
}

func SelesaiPesanPesawat(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	err := r.ParseForm()
	if err != nil {
		return
	}

	id_tiket_pesawat := r.Form.Get("id_tiket_pesawat")

	_, errQuery := db.Exec("UPDATE tiket_pesawat SET status_pemesanan = 'Selesai' WHERE id_tiket_pesawat = ?", id_tiket_pesawat)

	if errQuery == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}

	_, errQuery3 := db.Exec("UPDATE kursi_pesawat SET status_kursi = 'Kosong', id_tiket_pesawat = NULL WHERE id_tiket_pesawat = ?", id_tiket_pesawat)

	if errQuery3 == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery3.Error())
		SendErrorResponse(w, 400)
	}
}
