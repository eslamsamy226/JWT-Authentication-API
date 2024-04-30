# JWT-Authentication-API
An API using Java Spring Boot that provides user authentication and authorization using Json Web Tokens  


## 📄Usage
After installing all rquierd dependencies User should setup their database configuration in the **application.properties**, by  accessing the ```/api/v1/auth/register``` and ```/api/v1/auth/login``` endpoints users can creat users in the database then login and get a JWT tokin.

For the ```/register``` endpoint users shoud send a json object as follows:
```
{
    "firstname":"name",
    "lastname":"name",
    "email":"eslam@email.com",
    "password":"password"
}
```

For the ```/login``` endpoint users shoud send a json object as follows:
```
{
    "email":"eslam@email.com",
    "password":"pass"
}
```
