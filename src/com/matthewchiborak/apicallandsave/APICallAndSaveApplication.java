package com.matthewchiborak.apicallandsave;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class APICallAndSaveApplication {

	public static void main(String[] args) {
		
		/*String url = "https://jsonplaceholder.typicode.com/todos/";
		int inputStart = 1;
		int inputEnd = 2;
		int outputStart = 1;
		String outputDir = "D:\\eclipse-workspace\\APICallAndSave\\output\\";
		String prefix = "Allen-P-";
		String suffix = "-Email.json";*/
		
		/*String[] args = new String[7];
		args[0] = "https://jsonplaceholder.typicode.com/todos/";
		args[1] = "1";
		args[2] = "2";
		args[3] = "1";
		args[4] = "D:\\eclipse-workspace\\APICallAndSave\\output\\";
		args[5] = "Allen-P-";
		args[6] = "-Email.json";*/
		
		if(args.length != 7)
		{
			System.out.println("Invalid Input. Use url, input start, input end, output start, output dir, prefix, suffix");
			return;
		}
		
		String url = args[0];
		int inputStart = Integer.parseInt(args[1]);
		int inputEnd = Integer.parseInt(args[2]);
		int outputStart = Integer.parseInt(args[3]);
		String outputDir = args[4];
		String prefix = args[5];
		String suffix = args[6];
		
		System.out.println("Starting The API calls: Inputs:");
		System.out.println("URL: " + url);
		System.out.println("Input Start: " + inputStart);
		System.out.println("Input End: " + inputEnd);
		System.out.println("Output Start: " + outputStart);
		System.out.println("Output Dir: " + outputDir);
		System.out.println("Prefix: " + prefix);
		System.out.println("Suffix: " + suffix);
		
		try {
			for(int i = inputStart; i < inputEnd; i++)
			{
				HttpURLConnection con = (HttpURLConnection) new URL(url + i).openConnection();
				con.setRequestMethod("GET");
				
				int responseCode = con.getResponseCode();
				System.out.println("Response: " + responseCode);
				
				Scanner scanner = new Scanner(con.getInputStream()).useDelimiter("\\A");;
				String s = new String("");

				while(scanner.hasNext())
					s += scanner.next();
								
				String outputPath = outputDir + prefix + (outputStart + (i - inputStart)) + suffix;
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
			    writer.write(s);
			    
			    System.out.println("Wrote to file: " + outputPath);
			    
			    writer.close();
			}
			
			System.out.println("Done");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
