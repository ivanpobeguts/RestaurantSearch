> For windows use `Git Bash`
####Users

##### get all users
`curl -s --noproxy localhost http://localhost:8080/rest/admin/users --user admin@gmail.com:admin`

##### get user 100004
`curl -s --noproxy localhost http://localhost:8080/rest/admin/users/100004 --user admin@gmail.com:admin`

##### create user
`curl -s --noproxy localhost -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"newemail@gmail.com","password":"newPass","enabled":true,"roles":["ROLE_USER"]}' http://localhost:8080/rest/admin/users --user admin@gmail.com:admin`

##### update user
`curl -s --noproxy localhost -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"Updated","email":"updated@gmail.com","password":"newPass","enabled":true,"roles":["ROLE_USER"]}' http://localhost:8080/rest/admin/users/100005 --user admin@gmail.com:admin`

##### delete user
`curl -s --noproxy localhost -X DELETE http://localhost:8080/rest/admin/users/100004 --user admin@gmail.com:admin`