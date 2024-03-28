package com.ChinaMarket.ChinaMarket.Controller;

import com.ChinaMarket.ChinaMarket.ResponseDTO.GeolocationResponseDto;
import com.ChinaMarket.ChinaMarket.Service.GeoLocationService;
//import com.ChinaMarket.ChinaMarket.Model.Geolocation;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;

@RestController
@RequestMapping("/location")
public class GeoLocationController {

    @Autowired
    private final GeoLocationService geoLocationService;

    public GeoLocationController(GeoLocationService geoLocationService) {
        this.geoLocationService = geoLocationService;
    }

    @GetMapping("/geolocationDevice")
    public ResponseEntity getGeoLocationDevice(HttpServletRequest request) throws IOException, GeoIp2Exception{
//        String ipAddress = request.getRemoteAddr();
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_FORWARDED");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        System.out.println(ipAddress);
        CityResponse cityResponse = geoLocationService.getGeolocation(inetAddress);
        GeolocationResponseDto geolocationResponseDto = new GeolocationResponseDto();
        geolocationResponseDto.setCityName(cityResponse.getCity().getName());
        geolocationResponseDto.setCountryName(cityResponse.getCountry().getName());
        geolocationResponseDto.setPostal(cityResponse.getPostal().getCode());
        geolocationResponseDto.setState(cityResponse.getLeastSpecificSubdivision().getName());
        return new ResponseEntity<>(geolocationResponseDto, HttpStatus.ACCEPTED);
    }
//    @GetMapping("/geoLocation")
//    public ResponseEntity getGeoLocation() throws IOException, GeoIp2Exception {
//        InetAddress inetAddress = InetAddress.getByName("192.168.0.101");
//        InetAddress inetAddress1 = InetAddress.getLocalHost();
//        System.out.println(inetAddress1.getHostAddress());
////        CityResponse cityResponse = geoLocationService.getGeolocation(request.getRemoteAddr());
//        CityResponse cityResponse = geoLocationService.getGeolocation(inetAddress);
//        GeolocationResponseDto geolocationResponseDto = new GeolocationResponseDto();
//        geolocationResponseDto.setCityName(cityResponse.getCity().getName());
//        geolocationResponseDto.setCountryName(cityResponse.getCountry().getName());
//        geolocationResponseDto.setPostal(cityResponse.getPostal().getCode());
//        geolocationResponseDto.setState(cityResponse.getLeastSpecificSubdivision().getName());
//        return new ResponseEntity<>(geolocationResponseDto, HttpStatus.ACCEPTED);
//    }
}
