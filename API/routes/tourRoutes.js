const express = require('express');

const tourControllers = require('./../controllers/tourControllers.js');

const router = express.Router();

router.get('/lihatKupon', (req, res) => {
    const userId = req.body.id;
    const tipeTiket = req.body.tipe_tiket
    tourControllers.lihatKupon(userId, tipeTiket, (results) => {
        res.send(results);
    });
});

router.get('/users/:id', (req, res) => {
    const userId = req.params.id;
    tourControllers.getUserById(userId, (results) => {
        res.send(results);
    });
});

module.exports = router;

