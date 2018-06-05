package com;

import jdk.nashorn.internal.runtime.ECMAException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SpeedTracer {

    SpeedTracer() {
    }

    static String getRouteSpeed(String ipAddress) {
        List<String> output = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        String s = null;

        try {
            Process p = Runtime.getRuntime().exec("ping -c 5 -A " + ipAddress);
            if (!p.waitFor(5000, TimeUnit.MILLISECONDS)) {
                //timeout - kill the process.
                p.destroy(); // consider using destroyForcibly instead
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
            return ipAddress + " is unresponsive";
        } catch (Exception e) {
            e.printStackTrace();
        }

        String lastLine = output.get(output.size() - 1);
        String[] tokens = lastLine.split(" ");
        String[] speeds = tokens[3].split("/");



       // System.out.println(speeds[1] +" is the average Speed for ip address "+ ipAddress);


        return speeds[1] +" is the average Speed for ip address "+ ipAddress;
    }

}
