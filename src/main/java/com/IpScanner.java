package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class IpScanner {

    IpScanner() {


    }


    List<String> getIPsAsAList() {
        String s = null;

        List<String> listOfIPs = new ArrayList<String>();
        List<String> listOfIPsClean = new ArrayList<String>();


        try {

            Process p = Runtime.getRuntime().exec("arp -e");

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            while ((s = stdInput.readLine()) != null) {
                listOfIPs.add(s);
            }

            while ((s = stdError.readLine()) != null) {
               // System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String string : listOfIPs) {
            String firstString = string.split(" ")[0];

            if (!firstString.equals("_gateway") && !firstString.equals("Address")) {
                listOfIPsClean.add(firstString);
            }

        }

        return listOfIPsClean;
    }

    static void refreshIpTable() {
        try {

            Process p = Runtime.getRuntime().exec("nmap 192.168.88.0/24 -p22");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
