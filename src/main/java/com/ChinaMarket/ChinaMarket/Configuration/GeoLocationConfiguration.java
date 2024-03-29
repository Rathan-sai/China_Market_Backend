package com.ChinaMarket.ChinaMarket.Configuration;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

@Configuration
public class GeoLocationConfiguration {

    @Bean
    public DatabaseReader databaseReader() throws IOException, GeoIp2Exception {
        File file = new File("C:\\Users\\RATHAN SAI\\IdeaProjects\\ChinaMarket\\ChinaMarket\\GeoLite2-city.mmdb");
        File file1 = new File("C:\\Users\\RATHAN SAI\\IdeaProjects\\ChinaMarket\\ChinaMarket\\dbip-city-lite-2024-03.mmdb");
        File file2 = new File("C:\\Users\\RATHAN SAI\\IdeaProjects\\ChinaMarket\\ChinaMarket\\dbip-country-lite-2024-03.mmdb");
        return new DatabaseReader.Builder(file1).build();
    }
}
