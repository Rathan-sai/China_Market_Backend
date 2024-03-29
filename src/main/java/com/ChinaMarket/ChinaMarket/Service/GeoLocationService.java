package com.ChinaMarket.ChinaMarket.Service;

import com.ChinaMarket.ChinaMarket.Model.GeoLocationDetails;
import com.ChinaMarket.ChinaMarket.ResponseDto.IP2LocationResponseDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class GeoLocationService {

    private static final String API_KEY = "YOUR_IP2LOCATION_API_KEY";

//    private static final String API_KEY = "30768C94984D1338C597DC88F7EB6E63";
    private static final String API_URL = "https://api.ip2location.com/v2/?ip=%s&key=%s&package=WS11";
    private final DatabaseReader databaseReader;

    private final RestTemplate restTemplate;

    public GeoLocationService(DatabaseReader dbReader, RestTemplate restTemplate){
        this.databaseReader = dbReader;
        this.restTemplate = restTemplate;
    }

    public CityResponse getGeolocation(InetAddress ipAddress) throws IOException, GeoIp2Exception{
        return databaseReader.city(ipAddress);
    }

    public CountryResponse getGeolocationCountry(InetAddress ipAddress) throws  IOException, GeoIp2Exception{
        return databaseReader.country(ipAddress);
    }

    public String getPublicIPAddress() {
        String ip = restTemplate.getForObject("https://httpbin.org/ip", String.class);
        String ipAddress = extractNumbers(ip);
        return ipAddress;
    }

    public static String extractNumbers(String response) {
        // Use regular expression to remove non-numeric characters
        response = response.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\"origin\":", "");
        // Trim any leading/trailing spaces or double quotes
        response = response.trim().replaceAll("^\"|\"$", "");
        return response;
    }

    public GeoLocationDetails getLocationDetails(String ipAddress) {
        String url = String.format(API_URL, ipAddress, API_KEY);

        // Make a request to the IP2Location API
        ResponseEntity<IP2LocationResponseDto> responseEntity = restTemplate.getForEntity(url, IP2LocationResponseDto.class);
        IP2LocationResponseDto response = responseEntity.getBody();

        if (response != null && response.getLongitude() != 0.0 && response.getLatitude() != 0.0) {
            return new GeoLocationDetails(response.getLongitude(), response.getLatitude());
        } else {
            // Handle error or return default coordinates
            return new GeoLocationDetails(0.0, 0.0);
        }
    }
}

