package com.ChinaMarket.ChinaMarket.Controller;

import com.ChinaMarket.ChinaMarket.Convertor.GeoLocationConverter;
import com.ChinaMarket.ChinaMarket.Model.GeoLocationDetails;
import com.ChinaMarket.ChinaMarket.ResponseDto.GeolocationResponseDto;
import com.ChinaMarket.ChinaMarket.Service.GeoLocationService;
//import com.ChinaMarket.ChinaMarket.Model.Geolocation;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;

@RestController
@RequestMapping("/location")
public class GeoLocationController {

    @Autowired
    private final GeoLocationService geoLocationService;

    private final RestTemplate restTemplate;

    public GeoLocationController(GeoLocationService geoLocationService, RestTemplate restTemplate) {
        this.geoLocationService = geoLocationService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/geoLocation")
    public ResponseEntity getGeoLocation(HttpServletRequest request) throws IOException, GeoIp2Exception {
        String ipAddress1 = request.getRemoteAddr();
        String ipAddress = geoLocationService.getPublicIPAddress();
//        InetAddress inetAddress = InetAddress.getByName("103.121.151.118");
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        CityResponse cityResponse = geoLocationService.getGeolocation(inetAddress);

//        GeoLocationDetails geoLocationDetails = geoLocationService.getLocationDetails(ipAddress);
//        System.out.println(geoLocationDetails.getLongitude()+" "+geoLocationDetails.getLatitude());
        GeolocationResponseDto geolocationResponseDto = GeoLocationConverter.LocationToGeoLocationResponseConverter(cityResponse);

        return new ResponseEntity<>(geolocationResponseDto, HttpStatus.ACCEPTED);
    }
}
