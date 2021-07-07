const express = require('express'),
      app = express(),
      port = 3000,
      cors = require('cors');

app.use(cors());
app.use(express.json());

app.listen(port, '127.0.0.1', () => console.log("Server listening on Port:"+port));

app.get('/account',function(req, res) {
    console.log(req.query);
});