# Wego-Travel
Tugas besar RPLL
| No | Endpoint	            | Access	| Method	| Function                  | Response                          | Desc                                                                                                  |
|----| -------------------- | --------- | --------- | ------------------------- | --------------------------------- | ----------------------------------------------------------------------------------------------------- |
| 1  | /login	            | All		| POST		| Login                     | UserResponse    | Saat login berhasil, akan menghasilkan cookie berisi token.   |
| 2  | /logout	            | All		| GET		| Logout                    | MessageResponse                   | Menghapus cookie yang berisi token.                                                                   |
| 3  | /register	        | All		| POST		| AddNewUser	            | MessageResponse                   | Register user.                                                                                        |
| 4  | /update profile      | User		| PUT		| UpdateUser	            | MessageResponse                   | -                                                                                                     |
| 5  | /user/hotel	        | All		| GET		| GetHotelList              | HotelsResponse                    | -                                                                                                     |
| 6  | /user/hotel/room     | All		| GET		| GetRoomList               | RoomsResponse                     | -                                                                                                     |
| 7  | /user/hotel/room     | User		| POST		| AddNewOrder               | MessageResponse                   | Trigger update tabel rooms.                                                                           |
| 8  | /user/flight	        | All		| GET		| GetFlightList             | FlightsResponse                   | -                                                                                                     |
| 9  | /user/flight/seat    | All		| GET		| GetSeatList               | SeatsResponse                     | -                                                                                                     |
| 10 | /user/flight	        | User		| POST		| AddNewOrder               | MessageResponse                   | Trigger update tabel seats.                                                                           |
                                                                                                   |
| 18 | /user/order	        | User		| GET		| GetUserOrder              | UserOrdersResponse                |                                                                                                       |
| 19 | /user/order 	        | User		| PUT	    | RequestRefund             | MessageResponse                   | Update order_status.                                                                                  |                                                                                                
| 26 | /partner/airplane    | Partner	| POST  	| AddNewAirplane   	        | MessageResponse                   | -                                                                                                     |
| 27 | /admin/refund        | Admin		| GET		| GetRefundList   	        | RefundsResponse                   | -                                                                                                     |
| 28 | /admin/refund        | Admin		| DELETE	| ApproveRefund   	        | MessageResponse                   | -                                                                                                     |