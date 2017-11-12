package com.hydeparksafety.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.hydeparksafety.entity.Address;
import com.hydeparksafety.entity.Incident;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.net.*;
import java.io.*;


public class ReadIncidentStringService {
	private Logger logger = LogManager.getLogger(ReadIncidentStringService.class);

	public void readString(String arg)
	{
		String lines[] = arg.split("[\\r\\n]+");

		for (int i=0; i<lines.length; i++)
		{
			String inputLine = lines[i];
			if (inputLine.contains("<td>"))
			{
				//reads crime name
				Incident incident = new Incident();	
				String[] splits = inputLine.split("<td>|</td>");
				
				if (splits[1].toLowerCase().contains("void")) //weird case where they just have void in everything
				{
					i += 6; //to get to next part (6 blank lines, 6 lines of data
					continue;
				}

				String[] crime = splits[1].split("/");
				incident.setIncident(crime);
				
				//reads address
				inputLine = in.readLine();
				splits = inputLine.split("<td>|</td>");
				int firstIndex = splits[1].indexOf('(');
				int lastIndex = splits[1].indexOf(')');
				
				Address address = new Address();
				address.setLocationName(splits[1].substring(firstIndex, lastIndex));
				address.setStreet(splits[1].substring(0, firstIndex-1));
				incident.setAddress(address);
				
				//reads date/time reported
				inputLine = in.readLine();
				splits = inputLine.split("<td>|</td>");
				
				Date date = new Date();
				String pattern = "M/d/yy h:mm a";
				SimpleDateFormat formatter = new SimpleDateFormat(pattern);
				
				try{
					date = formatter.parse(splits[1]);
				}
				catch (ParseException e) {
					logger.error("this is the error", e);
				}
				
				incident.setReported(date);
				
				//read occurred time
				inputLine = in.readLine();
				splits = inputLine.split("<td>|</td>");
				incident.setOccurred(splits[1]);
				
				//read the comments
				inputLine = in.readLine();
				splits = inputLine.split("<td>|</td>");
				incident.setComment(splits[1]);
				
				//reads whether it's open or closed
				inputLine = in.readLine();
				if (inputLine.toLowerCase().contains("closed"))
					incident.setClosed(true);
				else
					incident.setClosed(false);
				
				//reads in the weird number at the end
				inputLine = in.readLine();
				splits = inputLine.split("<td>|</td>");
				incident.setUcpdiNumber(splits[1]);
				
				System.out.println(incident);
				}

			
		}//end of while
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
