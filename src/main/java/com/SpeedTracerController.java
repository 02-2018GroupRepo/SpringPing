package com;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SpeedTracerController {


    @GetMapping("/getsppeds")
    List<String> getspeeds() {
        List<String> output = new ArrayList<>();
        List<SpeedTracer> speedTracers = new ArrayList<>();

        List<String> networkIpAddresses = new ArrayList<>();

        URL ipScannerURL;
        try {
            ipScannerURL = new URL("http://192.168.88.91:8801/getIps");
            networkIpAddresses = new ObjectMapper().readValue(ipScannerURL, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String s : networkIpAddresses) {
            speedTracers.add(new SpeedTracer(s));

        }

        for (SpeedTracer st : speedTracers) {
            st.start();
        }

        try {
            for (SpeedTracer st : speedTracers) {

                st.join();
                if (st.getSpeed()!= null)
                    output.add(st.getSpeed());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }
}
