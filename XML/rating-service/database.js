const mysql = require('mysql');

let connection = mysql.createConnection({
    host: "35.239.233.181",
    user: "root",
    database: "megatravel_rating",
    password: "megatravel"
});

connection.connect(function(err) {
    if (err) {
        console.error('Error connecting: ' + err.stack);
        return;
    }
    console.log('Connected as thread id: ' + connection.threadId);
});

module.exports = connection;