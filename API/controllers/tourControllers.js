const Tour = require('./../models/tourModels')

exports.createTour = async (req,res) => {
    try{
        const newTour = await Tour.create(req.body);

        res.status(201).json({
            status: 'success',
            data: {
                tour:newTour
            }
        });
    }catch (err) {
        res.status(400).json({
            status: 'fail',
            message: err
        });
    }  
};

exports.getAllTours = async (req, res) => {
    try{
        const tours = await Tour.find();
        
        res.status(201).json({
            status: 'success',
            results: tours.length,
            data:{
                tours
            }
        });
    } catch(err){
        res.status(400).json({
            status: 'fail',
            message: 'Invalid data sent!'
        });
    }
};

exports.getTour = async (req, res) => {
    try {
        id = mongoose.Types.ObjectId(req.params.id)
        const tour = await Tour.findById(id);
        res.status(201).json({
            status: 'success',
            data:{
                tour
            }
        });
    } catch(err) {
        res.status(400).json({
            status: 'fail',
            message: err
        });
    }
};