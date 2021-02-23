package com.example.trackerdemo.services;

import com.example.trackerdemo.models.DataRow;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";

    private List<DataRow> dataRows = new ArrayList<>();

    public List<DataRow> getAllRows() {
        return dataRows;
    }

    @PostConstruct
    //spring cron format: second, minute, hour, day of month, month, day(s) of week
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
        List<DataRow> newRows = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        //commons-csv printing records from http body, maven dependencies required
        StringReader csvBodyReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            DataRow row = new DataRow();
            row.setState(record.get("Province/State"));
            row.setCountry(record.get("Country/Region"));
            int latestTotalCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            row.setLatestTotalCases(latestTotalCases);
            row.setDiffFromPrevDay(latestTotalCases - prevDayCases);
            newRows.add(row);
        }
        this.dataRows = newRows;
    }

}
