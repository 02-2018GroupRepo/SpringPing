package com;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SpeedTracerController {


    @Autowired
    @Qualifier("ipList")
    List<String> ipList;

 //   @Scheduled(cron = "0 1 * * * *")
    @GetMapping("/getspeeds")
    Map<String,String> getspeeds() {
        Map<String,String> output = new HashMap<>();
        List<SpeedTracer> speedTracers = new ArrayList<>();



        for (String s : ipList) {
            speedTracers.add(new SpeedTracer(s));

        }

        for (SpeedTracer st : speedTracers) {
            st.start();
        }

        try {
            for (SpeedTracer st : speedTracers) {

                st.join();
                if (st.getSpeed()!= null)
                    output.put(st.getIpAddress(), st.getSpeed());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }
}
