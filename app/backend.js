/* Express Js */
const express = require('express');
const app = express();

/* Enter port for the node server */
const port = 3000;

/* Cors for Crosssite compatability, and Mysql library */
const cors = require('cors');
const mysql = require('mysql');

/* User Accounts Database connection 
const connection = mysql.createConnection ({
    host        : '',
    user        : '',
    password    : '',
    database    : '',
    port        : ''
});

connection.connect();*/
app.use(cors());

app.get('',function(req, res) {

});

app.post('',function(req, res) {
    
});

app.listen(port, '127.0.0.1', () => console.log("Server listening on Port:"+port));
