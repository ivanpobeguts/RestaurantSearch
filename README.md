## Voting system

##### This is Spring/JPA Enterprise rest API, based on such technologies as: Tomcat, Maven, Spring MVC, Security, JPA(Hibernate), REST(Jackson), Java 8 Time API, HSQLDB. 

### API description

Curl commands are described in [curl.md](curl.md).

API is described in [description.md](description.md).

Besides this functionality there is Menu and vote history. You can get the menu history of a restaurant using 'get restaurant' method:

    curl -s --noproxy localhost http://localhost:8080/rest/restaurants/100000 --user admin@gmail.com:admin

Example:

    {"id":100000,"name":"Restaurant1","registered":"2018-05-31T16:40:15.757+0300","menu":[{"id":100014,"registered":"2018-05-31T17:14:32.190+0300","menu":"{\"name\":\"pork\",\"value\":25},{\"name\":\"pasta\",\"value\":17},{\"name\":\"cola\", \"value\":10}"},{"id":100007,"registered":"2018-05-31T16:40:15.758+0300","menu":"{\"name\":\"chicken wings\",\"value\":25},{\"name\":\"fries\",\"value\":15},{\"name\":\"apple juice\", \"value\":10}"}]}

The actual menu is the first nemu in the menu list:

    "menu":"{\"name\":\"pork\",\"value\":25},{\"name\":\"pasta\",\"value\":17},{\"name\":\"cola\", \"value\":10}"},{"id":100007,"registered":"2018-05-31T16:40:15.758+0300"

You can see the history of votes, using this 'get history' method:

    curl -s --noproxy localhost http://localhost:8080/rest/history --user admin@gmail.com:admin

    