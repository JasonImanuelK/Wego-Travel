package main

import (
	"log"
	"net/http"

	//controllers "github.com/Wego-Travel/API/controllers"
	"github.com/gorilla/mux"
)

func main() {
	log.Println("RUNNING ...")
	controllers.GocronEvent()
	router := mux.NewRouter()

	svrPort := controllers.LoadEnv("SVR_PORT")
	log.Println("Connected to port " + svrPort)
	addr := ":" + svrPort
	http.ListenAndServe(addr, router)
}
