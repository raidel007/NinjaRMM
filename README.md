# NinjaRMM

Spring Security Credentials

user:  ninjaone
password:   master
--------------------

SAMPLE Requests for main endpoints

http://localhost:8080/product/addServices
{
	"id":"03", 
	"serviceName":"Mac Antivirus", 
	"cost":"7",
    "devices":{
		"id":"001", 
		"systemName":"MacBook Pro", 
		"type":"Mac",
		"status": "ACTIVE"
    }
}

{
	"id":"05", 
	"serviceName":"PSA", 
	"cost":"2",
    "devices":{
        "id":"002", 
        "systemName":"HP Desktop", 
        "type":"Windows",
        "status": "ACTIVE"
    }
}

http://localhost:8080/product/addDevice

{
	"id":"001", 
	"systemName":"MacBook Pro", 
	"type":"Mac",
    "status": "ACTIVE",
    "services":[
		{
			"id":"010", 
			"serviceName":"Device Service", 
			"cost":"4"
		}
    ]
}

{
	"id":"002", 
	"systemName":"HP Desktop", 
	"type":"Windows",
    "status": "ACTIVE",
    "services":[
		{
			"id":"01", 
			"serviceName":"Device Service", 
			"cost":"4"
		}
    ]
}
