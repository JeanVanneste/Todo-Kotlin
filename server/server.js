const logger = require('morgan')
const NeDB = require('nedb')
const express = require('express')
const bodyParser = require('body-parser')
const path = require('path')

const todos = new NeDB({
	filename: path.join(__dirname, 'todo.db'),
	autoload: true
})

const app = express()

app.use(logger('dev'))

app.use(bodyParser.json())

app.get('/todos', (req, res) => {
	todos.find({}, (err, docs) => {
		res.send({
			todos: docs
		})
	})
})

app.post('/todos', (req, res) => {
	if(req.body) {
		console.log(req.body)
		todos.insert({
			value: req.body.value
		}, (err) => {
			if(err) {
				res.send({
					ok: false,
					error: err
				})
			}

			res.send({
				ok: true
			})
		})
	}
	else {
		res.send({
			ok: false,
			error: "No Body Provided"
		})
	}
})

// Start the server.
const port = 3030

app.listen(port, () => {
	console.log(`Feathers server listening on port ${port}`)
})