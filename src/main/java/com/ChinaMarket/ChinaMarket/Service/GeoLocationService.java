package com.ChinaMarket.ChinaMarket.Service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class GeoLocationService {
    private final DatabaseReader databaseReader;

    public GeoLocationService(DatabaseReader dbReader){
        this.databaseReader = dbReader;
    }

    public GeoLocationService() throws IOException, GeoIp2Exception {
        File file = new File("C:\\Users\\RATHAN SAI\\IdeaProjects\\ChinaMarket\\ChinaMarket\\GeoLite2-city.mmdb");
        databaseReader = new DatabaseReader.Builder(file).build();
    }

    public CityResponse getGeolocation(InetAddress ipAddress) throws IOException, GeoIp2Exception{
        System.out.println(ipAddress.getHostAddress());
        return databaseReader.city(ipAddress);
    }

//    public <GeoIP2Reader> void geoLocation(InetAddress ipAddress) throws IOException, GeoIp2Exception{
//        Object GeoIP2Reader;
//        GeoIP2Reader reader = new GeoIP2Reader("C:\\Users\\RATHAN SAI\\IdeaProjects\\ChinaMarket\\ChinaMarket\\GeoLite2-city.mmdb");
//    }
}

