package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class SpeedTracer extends Thread {

    private String ipAddress;



    private String speed;

    SpeedTracer(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public void run() {
        List<String> output = new ArrayList<String>();
        String s;

        try {
            Process p = Runtime.getRuntime().exec("ping -c 5 -A " + ipAddress);
            if (!p.waitFor(1250, TimeUnit.MILLISECONDS)) {
                //timeout - kill the process.
                p.destroy();
            }

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));
            while ((s = stdInput.readLine()) != null) {
                output.add(s);
            }
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }


        } catch (IOException e) {
            System.out.println(ipAddress + " is unresponsive");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        String lastLine = output.get(output.size() - 1);
        String[] tokens = lastLine.split(" ");
        String[] speeds = tokens[3].split("/");


        // System.out.println(speeds[1] +" is the average Speed for ip address "+ ipAddress);

        double milisecs = Double.parseDouble(speeds[1]);
        double rate = ( 64 * 8)/ (milisecs /1000) /1024;
        speed = Math.round(rate) +" Mbps is the average Speed for ip address " + ipAddress;

    }
    public String getSpeed() {
        return speed;
    }

/*    static String getRouteSpeed(String ipAddress) {
        }*/

}
