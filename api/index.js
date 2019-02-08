const express = require('express')
const request = require('request');
const app = express()

const baseURL = 'https://sandbox-api-pay.line.me';
const headers = {
    'X-LINE-ChannelId': '1644564819',
    'X-LINE-ChannelSecret': '73ab3dbda69f259404ef39d762c4f28d',
    'Content-Type': 'application/json',
};

app.get('/paymentok', (req, res) => {
    
    let transactionId  =  req.query['transactionId']
    let amount  =  req.query['amount']

    let api = baseURL+'/v2/payments/'+transactionId+'/confirm'

    let body = {
        amount : amount,
        currency: 'THB',
    }

    let options = {
        url: api,
        method: 'POST',
        headers: headers,
        body: body,
        json: true
    }
    request(options, function (error, response, body) {
        if (!error && response.statusCode == 200) {

            res.header('Content-Type', 'application/json');
            res.send(JSON.stringify(body))
            console.log(body)
            
        }
    })
})
app.get('/', (req, res) => {
    let api = baseURL+'/v2/payments/request';

    // from req
    let random = req.query['orderID'];  
    let amount =  req.query['amount'];
	
    let payload = {
        productName : 'TEST Product Line pay' , 
        amount : amount,
        orderId :random,
        currency: 'THB',
        confirmUrl: 'http://128.199.225.90:3000/paymentok?orderId='+random+'&amount='+amount,
        langCd: 'th',
        confirmUrlType: 'CLIENT',
    };

    let options = {
        url: api,
        method: 'POST',
        headers: headers,
        body: payload,
        json: true
    }

    request(options, function (error, response, body) {
        if (!error && response.statusCode == 200) {
            console.log(JSON.stringify(body))

            res.header('Content-Type', 'application/json');
            res.send(JSON.stringify(body))
        }
    })
    
})

app.listen(3000, () => {
    
    console.log('Start server at port 3000.')
})
