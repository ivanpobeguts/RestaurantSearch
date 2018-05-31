> For windows use `Git Bash`

### Users

#### get all users
    curl -s --noproxy localhost http://localhost:8080/rest/admin/users --user admin@gmail.com:admin

#### get user 100004
    curl -s --noproxy localhost http://localhost:8080/rest/admin/users/100004 --user admin@gmail.com:admin

#### create user
    curl -s --noproxy localhost -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New","email":"newemail@gmail.com","password":"newPass","enabled":true,"roles":["ROLE_USER"]}' http://localhost:8080/rest/admin/users --user admin@gmail.com:admin

#### update user 100005
    curl -s --noproxy localhost -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"Updated","email":"updated@gmail.com","password":"newPass","enabled":true,"roles":["ROLE_USER"]}' http://localhost:8080/rest/admin/users/100005 --user admin@gmail.com:admin

#### delete user 100004
    curl -s --noproxy localhost -X DELETE http://localhost:8080/rest/admin/users/100004 --user admin@gmail.com:admin

### Profile

#### vote for restaurant 100002
    curl -s --noproxy localhost -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" http://localhost:8080/rest/profile/100001 --user user1@yandex.ru:password1

#### get user restaurant
    curl -s http://localhost:8080/voiting-system/rest/profile --user new@gmail.com:newPass
    
### Restaurants

#### get all restaurants
    curl -s --noproxy localhost http://localhost:8080/rest/restaurants --user admin@gmail.com:admin

#### get restaurant 100000
    curl -s --noproxy localhost http://localhost:8080/rest/restaurants/100000 --user admin@gmail.com:admin

#### create restaurant 
    curl -s --noproxy localhost -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"New restaurant"}' http://localhost:8080/rest/restaurants --user admin@gmail.com:admin

#### update restaurant 100003
    curl -s --noproxy localhost -X PUT -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"name":"Updated Restaurant"}' http://localhost:8080/rest/restaurants/100003 --user admin@gmail.com:admin

#### delete restaurant 100002
    curl -s --noproxy localhost -X DELETE http://localhost:8080/rest/restaurants/100002 --user admin@gmail.com:admin
    
### Menu

#### get all menu history for all restaurants
    curl -s --noproxy localhost  http://localhost:8080/rest/menu --user admin@gmail.com:admin
    
#### get menu 100008
    curl -s --noproxy localhost http://localhost:8080/rest/menu/100008 --user admin@gmail.com:admin
    
#### create new menu for restaurant
    curl -s --noproxy localhost -X POST -H "Content-Type:application/json;Content-Encoding:UTF-8" -d '{"menu":"{\"name\":\"pork\",\"value\":25},{\"name\":\"pasta\",\"value\":17},{\"name\":\"cola\", \"value\":10}"}' http://localhost:8080/rest/menu/100000 --user admin@gmail.com:admin
    
#### delete menu 100007
    curl -s --noproxy localhost -X DELETE http://localhost:8080/rest/menu/100007 --user admin@gmail.com:admin
    
### Vote history

#### get history
        curl -s --noproxy localhost http://localhost:8080/rest/history --user admin@gmail.com:admin
    