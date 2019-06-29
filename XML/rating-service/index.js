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