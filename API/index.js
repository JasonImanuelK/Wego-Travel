// const http = require('http');
// const hello = 'Hello world';
// console.log(hello);

// const server = http.createServer((req, res) => {
//     res.end('Hello from the server');
// });

// server.listen(8000, '127.0.0.1', () => {
//     console.log('Listening to request on port 8000');
// });

//const fs = require('fs');
const express = require('express');

const app = express();

// const tours = JSON.parse(
//     fs.readFileSync(`${__dirname}/dev_data/data/tours-simple.json`)
// );

// app.get('/api/v1/tours', (req, res) => {
//     res.status(200).json({
//         status:'success',
//         result: tours.length,
//         data: {
//             tours: tours
//         }
//     });
// });

// app.get('/', (req, res) => {
//     res
//     .status(200)
//     .json({message : 'hello from server side!', app : 'app saya pribadi'});
// });

// const port = 3000;
// app.listen(port, () => {
//     console.log('app running on port '+port+' ....');
// });

const tourRouter = require('./routes/tourRoutes');

app.use(express.json());

app.use((req, res, next) => {
    req.requestTime = new Date().toISOString();
    next();
});

app.use('/api/v1/tours',tourRouter);

module.exports = app;
