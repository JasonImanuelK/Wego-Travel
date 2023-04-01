package controllers

import (
	"encoding/json"
	"fmt"
	"net/http"
	"os"

	model "github.com/Wego-Travel/API/model"

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
	id_pengguna := r.Form.Get("id_pengguna")
	_, errQuery := db.Exec("UPDATE pengguna SET password = ? WHERE id_pengguna = ", password, id_pengguna)

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
