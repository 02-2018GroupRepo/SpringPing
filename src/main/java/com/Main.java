package com;

import java.util.List;

public class Main {
    public static void main(String[] args) {

     //   IpScanner.refreshIpTable();

        List<String> networkIpAddresses = new IpScanner().getIPsAsAList();

        for (String s : networkIpAddresses) {
            System.out.println(SpeedTracer.getRouteSpeed(s));
        }
       // SpeedTracer.getRouteSpeed("192.168.88.199");

    }
}
