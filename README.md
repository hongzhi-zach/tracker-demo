# Covid-19 tracker

This is a covid-19 case tracker using Spring Boot

* 23rd Feb: init project, fetching data from [Novel Coronavirus](https://github.com/CSSEGISandData/COVID-19)
* 23rd Feb: service & model
  * Sending HTTP request, data from HTTP body read by [common-CSV](https://commons.apache.org/proper/commons-csv/index.html), store them to record array.
  * data fetching is automatically performed upon creation, and scheduled to update every hour 