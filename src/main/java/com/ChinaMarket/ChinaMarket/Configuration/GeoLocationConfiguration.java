package com.ChinaMarket.ChinaMarket.Configuration;

import com.maxmind.geoip2.DatabaseReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class GeoLocationConfiguration {

    @Bean
    public DatabaseReader databaseReader() throws IOException {
        File database = new File("C:\\Users\\RATHAN SAI\\IdeaProjects\\ChinaMarket\\ChinaMarket\\GeoLite2-city.mmdb");
        return new DatabaseReader.Builder(database).build();
    }
}
