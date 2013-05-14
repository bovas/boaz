package com.mycare.actions.test;

import java.io.*;
import java.net.*;
import java.util.*;
import static java.lang.System.out;

public class ListNets {

    public static void main(String args[]) throws IOException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
            displayInterfaceInformation(netint);
    }

    static void displayInterfaceInformation(NetworkInterface netint) throws IOException {
        out.printf("Display name: %s\n", netint.getDisplayName());
        out.printf("Name: %s\n", netint.getName());        
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            out.printf("InetAddress: %s\n", inetAddress);
        }
        out.printf("\n");
        InetAddress localhost = InetAddress.getLocalHost();  
        byte[] ip = localhost.getAddress();  
        for (int i = 1; i <= 254; i++)  
        {  
            ip[3] = (byte)i;  
            InetAddress address = InetAddress.getByAddress(ip);  
            System.out.println(address.toString());
            if (address.isReachable(1000))  
            {  
                System.out.println("machine is turned on and can be pinged");  
            }  
            else if (!address.getHostAddress().equals(address.getHostName()))  
            {  
            	System.out.println("machine is known in a DNS lookup");  
            }  
            else  
            {  
            	System.out.println("the host address and host name are equal, meaning the host name could not be resolved");  
            }  
        }  
     }
} 