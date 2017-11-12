package com.hydeparksafety.service;

import com.hydeparksafety.entity.Address;
import com.hydeparksafety.entity.Incident;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReadIncidentStringService {
	private Logger logger = LogManager.getLogger(ReadIncidentStringService.class);

	public List<Incident> readString(String arg)
	{
		String lines[] = arg.split("[\\r\\n]+");
		List<Incident> incidents = new ArrayList<>();

		int i = 0;
		while (i < lines.length)
		{
			String inputLine = lines[i];			
			if (inputLine.contains("<td>"))
			{
				//reads crime name
				Incident incident = new Incident();	
				String[] splits = inputLine.split("<td>|</td>");
				
				if (splits[1].toLowerCase().contains("void")) //weird case where they just have void in everything
				{
					i += 7; //to get to next part (6 blank lines, 6 lines of data
					continue;
				}
				
				splits = inputLine.split("<td>|</td>");
				String[] crime = splits[1].split("/");
				incident.setIncident(crime);
				
				//reads address
				i = i + 1;
				inputLine = lines[i];	
				splits = inputLine.split("<td>|</td>");
				if (inputLine.contains("("))
				{
					int firstIndex = splits[1].indexOf('(');
					int lastIndex = splits[1].indexOf(')');
					
					Address address = new Address();
					if (lastIndex > 0)
						address.setLocationName(splits[1].substring(firstIndex, lastIndex));
					address.setStreet(splits[1].substring(0, firstIndex-1));
					incident.setAddress(address);
				}
				else if (StringUtils.isNotBlank(inputLine))
				{
					Address address = new Address();
					address.setStreet(splits[1]);
					incident.setAddress(address);
				}
				
				
				//reads date/time reported
				i = i + 1;
				inputLine = lines[i];
				splits = inputLine.split("<td>|</td>");

				String pattern = "M/d/yy h:mm a";
				SimpleDateFormat formatter = new SimpleDateFormat(pattern);
				
				try{
					Date date = formatter.parse(splits[1]);
					incident.setReported(date);
				}
				catch (ParseException e) {
					logger.error("this is the error", e);
				}
				
				//read occurred time
				i = i + 1;
				inputLine = lines[i];
				splits = inputLine.split("<td>|</td>");
				incident.setOccurred(splits[1]);
				
				//read the comments
				i = i + 1;
				inputLine = lines[i];
				splits = inputLine.split("<td>|</td>");
				incident.setComment(splits[1]);
				
				//reads whether it's open or closed
				i = i + 1;
				inputLine = lines[i];
				if (inputLine.toLowerCase().contains("closed"))
					incident.setClosed(true);
				else
					incident.setClosed(false);
				
				//reads in the weird number at the end
				i = i + 1;
				inputLine = lines[i];
				splits = inputLine.split("<td>|</td>");
				incident.setUcpdiNumber(splits[1]);
				
				i = i + 1;
				incidents.add(incident);
				}
			else
			{
				i = i + 1;
			}

			
		}//end of while
		return incidents;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
