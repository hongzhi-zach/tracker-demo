# Covid-19 tracker

This is a covid-19 case tracker using Spring Boot

* 23rd Feb: init project, fetching data from [Novel Coronavirus](https://github.com/CSSEGISandData/COVID-19)
* 23rd Feb: service & model
  * Sending HTTP request, data from HTTP body read by [common-CSV](https://commons.apache.org/proper/commons-csv/index.html), store them to record array.
  * data fetching is automatically performed upon creation, and scheduled to update every hour
* 2nd Mar: controller and html page
  * adding controller mapped with homepage, autowired with service
  * using thymeleaf (maven dependency) to render HTML file, for each row in data table insert row attributes