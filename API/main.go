package main

import (
	"log"
	"net/http"

	controllers "github.com/Wego-Travel/API/controllers"
	"github.com/gorilla/mux"
	"github.com/rs/cors"
)

func main() {
	log.Println("RUNNING ...")
	router := mux.NewRouter()

	svrPort := controllers.LoadEnv("SVR_PORT")
	log.Println("Connected to port " + svrPort)
	addr := ":" + svrPort

	corsHandler := cors.New(cors.Options{
		AllowedOrigins:   []string{"localhost:8080,https://wegotravel.com"},
		AllowedMethods:   []string{"GET", "POST", "PUT", "DELETE"},
		AllowCredentials: true,
	})
	handler := corsHandler.Handler(router)

	log.Fatal(http.ListenAndServe(addr, handler))
	log.Fatal(http.ListenAndServe(addr, router))

	http.Handle("/", router)
}
