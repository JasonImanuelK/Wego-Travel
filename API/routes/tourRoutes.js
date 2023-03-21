const express = require('express');
const tourControllers = require('./../controllers/tourControllers.js');

const router = express.Router();

router
.route('/')
.post(tourControllers.createTour)
.get(tourControllers.getAllTours);

router
.route('/satu/:id')
.get(tourControllers.getTour);

module.exports = router;