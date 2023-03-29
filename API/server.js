// const mongoose = require('mongoose');
const dotenv = require('dotenv');
const app = require('./index.js');

//FOR MYSQL
const mysql = require('mysql2');

const pool = mysql.createPool({
  host: 'localhost',
  user: 'root',
  password: '',
  database: process.env.DATABASE_NAME,
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0
});

// FOR NO-SQL (MongoDB)

// dotenv.config({path: './config.env'});

// const DB = process.env.DATABASE.replace(
//     '<password>',process.env.DATABASE_PASSWORD
// )

// mongoose.connect(DB, {
//     useNewUrlParser: true,
//     useCreateIndex: true,
//     useFindAndModify: false,
//     useUnifiedTopology: true
// }).then(con => {
//     console.log(con.connections);
//     console.log('DB connection successful !')
// });

const port = process.env.PORT || 3000 ;
app.listen(port, () => {
    console.log(`app running on port ${port} ....`);
})