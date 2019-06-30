const connection = require('./database')

exports.getAllRatings = function getAllRatings(req, res) {
    connection.query("select * from new_table", (err, result)=> {
	if (err) res.status(400).send(err);
	else {
		res.status(200).send(result);
	}
  });
};


exports.getNotAllowedCommentsOfAccommodation = function getNotAllowedCommentsOfAccommodation(req, res) {
    connection.query("select id, comment from ratings where allowed=false and accommodation_id ="+req.query.accommodation_id,
	(err, result)=> {
	    if (err) res.status(400).send(err);
	    else {
		res.status(200).send(result);
	    }
	});
};

exports.getAllowedCommentsOfAccommodation = function getNotAllowedCommentsOfAccommodation(req, res) {
    connection.query("select id, comment from ratings where allowed=true and accommodation_id ="+req.query.accommodation_id,
	(err, result)=> {
	    if (err) res.status(400).send(err);
	    else {
		res.status(200).send(result);
	    }
	});
};

exports.approveComment = function approveComment(req, res) {
    let allowed = req.body.allowed;
    let id = req.body.id;
    
    connection.query("UPDATE `ratings` SET `allowed` = "+allowed+" where id = "+id,
	(err, result)=> {
	    if (err) res.status(400).send(err);
	    else {
		res.status(200).send("Successfully allowed comment");
	    }
	});
};

exports.newRating = function newRating(req, res) {
    let userID = req.body.userID;
    let comment = req.body.comment;
    let rating = req.body.rating;
    let accommodationID = req.body.accommodationID;
	let reservationID = req.body.reservationID;
    connection.query("insert into ratings (userID, comment, rating, accommodationID, published, reservationID) values (?, ?, ?, ?, ?, ?)", [userID, comment, rating, accommodationID, 0, reservationID], (err, result) => {
	if (err) res.status(400).send(err);
	else {
		
		res.status(200).send('upisano');

	}
	
    });
};

exports.newRating = function newRating(req, res) {
    let accommodation_id = req.body.accommodation_id;
    let client_id = req.body.client_id;
    let rating = req.body.rating;
    let comment = req.body.comment;
	let reservation_id = req.body.reservation_id;
	let allowed = req.body.allowed;
    connection.query("insert into ratings (accommodation_id, client_id, rating, comment, reservation_id, allowed) values (?, ?, ?, ?, ?, ?)", [accommodation_id, client_id, rating, comment, reservation_id, allowed], (err, result) => {
	if (err) res.status(400).send(err);
	else {
		
		res.status(200).send('new ratin successfully saved');

	}
	
    });
};

exports.getAverageRating = function getAverageRating(req, res) {
    connection.query("select avg(rating) as avgRating from ratings where accommodation_id="+req.query.id,
	(err, result)=> {
	    if (err) res.status(400).send(err);
	    else {
		res.status(200).send(result[0]);
	    }
	});
};