package com;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class);

    }

    @Bean
    @Qualifier("ipList")
    List<String> getList(){
        List<String> networkIpAddresses = new ArrayList<>();

        try {
            URL ipScannerURL = new URL("http://192.168.88.91:8801/getIps");
            networkIpAddresses = new ObjectMapper().readValue(ipScannerURL, List.class);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return networkIpAddresses;
    }
}
