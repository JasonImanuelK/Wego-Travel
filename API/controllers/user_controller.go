package controllers

import (
	// "crypto/md5"
	// "encoding/hex"
	"encoding/json"
	"log"
	"net/http"
	"os"

	"github.com/Wego-Travel/API/model"
	"github.com/gorilla/mux"
	"github.com/joho/godotenv"

	_ "github.com/go-sql-driver/mysql"
)

func Login(w http.ResponseWriter, r *http.Request) {

	// Connect to database
	db := Connect()
	defer db.Close()

	// Get value from form
	errForm := r.ParseForm()
	if errForm != nil {
		SendErrorResponse(w, 400)
		return
	}

	email := r.Form.Get("email")
	password := r.Form.Get("password")
	log.Print(email)
	log.Print(password)

	// Encrypt password
	// hasher := md5.New()
	// hasher.Write([]byte(password))
	// encryptedPassword := hex.EncodeToString(hasher.Sum(nil))

	// User / admin login
	row := db.QueryRow("SELECT id_pengguna, nama, email, password, jenis_kelamin, tanggal_lahir, nomor_telepon, alamat FROM pengguna WHERE email=? AND password=?", email, password)
	log.Print(row)
	var user model.Pengguna
	if err := row.Scan(&user.Id_pengguna, &user.Nama, &user.Email, &user.Password, &user.Jenis_kelamin, &user.Tanggal_lahir, &user.Nomor_telepon, &user.Alamat); err != nil {
		log.Println("(ERROR)\t", err)
		log.Print("masuk sini")
		SendErrorResponse(w, 400)
		return
	}

	GenerateToken(w, user.Id_pengguna, user.Nama)

	// Response
	var userResponse model.UserResponse
	userResponse.Status = 200
	userResponse.Message = "Request success"
	userResponse.Data = user

	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(userResponse)
	log.Println("(SUCCESS)\t", "Login request")
}

func Logout(w http.ResponseWriter, r *http.Request) {
	ResetUserToken(w)
	SendSuccessResponse(w)
	log.Println("(SUCCESS)\t", "Logout request")
}

func Register(w http.ResponseWriter, r *http.Request) {

	// Connect to database
	db := Connect()
	defer db.Close()

	errForm := r.ParseForm()
	if errForm != nil {
		SendErrorResponse(w, 400)
		return
	}

	nama := r.Form.Get("nama")
	email := r.Form.Get("email")
	password := r.Form.Get("password")
	jenis_kelamin := r.Form.Get("jenis_kelamin")
	tanggal_lahir := r.Form.Get("tanggal_lahir")
	nomor_telepon := r.Form.Get("nomor_telepon")
	alamat := r.Form.Get("alamat")
	log.Print(email)
	log.Print(password)
	log.Print(tanggal_lahir)

	// Encrypt password
	// hasher := md5.New()
	// hasher.Write([]byte(password))
	// encryptedPassword := hex.EncodeToString(hasher.Sum(nil))

	//convert string time to date format
	// date, error := time.Parse("2006-01-02", tanggal_lahir)
	// if error != nil {
	// 	fmt.Println(error)
	// 	return
	// }

	// Query
	_, errQuery := db.Exec("INSERT INTO pengguna(nama, email, password, jenis_kelamin, tanggal_lahir, nomor_telepon, alamat) values (?,?,?,?,?,?,?)", nama, email, password, jenis_kelamin, tanggal_lahir, nomor_telepon, alamat)

	if errQuery == nil {
		log.Println("(SUCCESS)\t", "Add new user request")
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery)
		SendErrorResponse(w, 400)
	}
}

func LupaPassword(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	err := r.ParseForm()
	if err != nil {
		log.Println("(ERROR)\t", err.Error())
		SendErrorResponse(w, 400)
		return
	}
	password := r.Form.Get("password")
	email := r.Form.Get("email")
	_, errQuery := db.Exec("UPDATE pengguna SET password = ? WHERE email = ?", password, email)

	if errQuery == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery.Error())
		SendErrorResponse(w, 400)
	}
}

func PerbaruiProfil(w http.ResponseWriter, r *http.Request) {
	db := Connect()
	defer db.Close()

	err := r.ParseForm()
	if err != nil {
		log.Println("(ERROR)\t", err.Error())
		SendErrorResponse(w, 400)
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
		query = query + "nama = '" + nama + "', "
	}
	if tanggalLahir != "" {
		query += "tanggal_lahir = '" + tanggalLahir + "', "
	}
	if alamat != "" {
		query += "alamat = '" + alamat + "', "
	}
	if jenisKelamin != "" {
		query += "jenis_kelamin = '" + jenisKelamin + "', "
	}
	if email != "" {
		query += "email = '" + email + "', "
	}
	if nomorTelepon != "" {
		query += "nomor_telepon = '" + nomorTelepon + "', "
	}
	query = query[:len(query)-2]
	query += " WHERE id_pengguna = " + idPengguna

	_, errQuery := db.Query(query)
	if errQuery == nil {
		SendSuccessResponse(w)
	} else {
		log.Println("(ERROR)\t", errQuery)
		SendErrorResponse(w, 400)
	}
}

func LoadEnv(input string) string {
	err := godotenv.Load("API/catatan.env")
	if err != nil {
		log.Fatalf(err.Error())
	}
	return os.Getenv(input)
}
