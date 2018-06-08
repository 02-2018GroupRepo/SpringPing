package com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        //   IpScanner.refreshIpTable();
        String ipScannerURL = "http://192.168.88.91:8801/getIps";
        List<String> networkIpAddresses = new ArrayList<>();
        try {
           networkIpAddresses = new ObjectMapper().readValue(ipScannerURL, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
       // List<String> networkIpAddresses = new IpScanner().getIPsAsAList();

        for (String s : networkIpAddresses) {
            Thread ipSpeed = new SpeedTracer(s);
            //System.out.println(SpeedTracer.getRouteSpeed(s));
            ipSpeed.start();
        }

    }
}
