const Tour = require('./../models/tourModels')

function lihatKupon(userId,tipeTiket,callback){
    pool.query('SELECT * FROM vouchers WHERE id = ? AND tipe_tiket = ?', [userId,tipeTiket], (error, results) => {
        if (error) throw error;
        callback(results);
      });
}

function getUsers(callback) {
    pool.query('SELECT * FROM users', (error, results) => {
      if (error) throw error;
      callback(results);
    });
  }
  
  function getUserById(userId, callback) {
    pool.query('SELECT * FROM users WHERE id = ?', [userId], (error, results) => {
      if (error) throw error;
      callback(results);
    });
  }
  
  module.exports = { getUsers, getUserById, lihatKupon };