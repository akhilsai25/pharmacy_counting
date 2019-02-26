import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Main {
	
	static Map<String,Double> mpprice=new HashMap();
	static Map<String,Set<String>> mpdoc=new HashMap();
	
//Method to read data and load it to the maps
public static void readFile(String path) throws IOException
{
	//Using buffered reader to read data
	Reader inputStreamReader = new InputStreamReader(new FileInputStream(path));
	BufferedReader buffer_reader=new BufferedReader(inputStreamReader);
	
	//skipping the header
	buffer_reader.readLine();
	
	String s;
	String[] str;
    Double d;
    //Iterating through the records until last line is reached
	while ((s = buffer_reader.readLine()) != null) 
	{
		
	str=s.split(",");
	
	//checking for additional characters in the record
	if(str.length>5)
	{
		for(int i=0;i<str.length;i++)
		{
			if(str[i].contains("\""))
			{
				for(int j=i+1;j<str.length;j++)
				{
					if(str[j].contains("\""))
					{
						for(int p=i+1;p<=j;p++)
						{
							str[i]+=str[p];
						}
						for(int p=j+1;p<str.length;p++)
						{
							str[i+1]=str[p];
							i++;
						}
						break;
					}
					else
						continue;
				}
			}
		}
	}
	
	//Logic for adding the elements that are already existed in the map
	if(mpprice.get(str[3])!=null)
	{
		d=mpprice.remove(str[3]);
		d+=Double.valueOf(str[4]);
		mpprice.put(str[3],d);
		mpdoc.get(str[3]).add(str[1]+str[2]);
	}
	
	//Logic for adding the elements that are new to the map
	else
	{
		mpprice.put(str[3],Double.valueOf(str[4]));
		Set<String> st=new HashSet();
		st.add(str[1]+str[2]);
		mpdoc.put(str[3], st);
	}	
	str=null;
			}
	
	buffer_reader.close();
	System.out.println("Reading done");
}

//Method to filter and write the data to file
public static void writeFile(String path_output)
{
	List list_prices = new LinkedList(mpprice.entrySet());
	Collections.sort(list_prices, new CustomComparator());
	
	try {
		//creating the buffered writer to write the file
		BufferedWriter buffer_writer = new BufferedWriter(new FileWriter(path_output));
		
		//Writing header
		String head_line="drug_name,num_prescriber,total_cost";
		buffer_writer.write(head_line);
		buffer_writer.newLine();
		
		//Extracting the list elements
		Iterator it2=list_prices.iterator();
		
		//Iterating through the iterator and writing them to the external file
		while(it2.hasNext())
		{
			Map.Entry<String, Double> me2=(Entry<String, Double>)it2.next();
			String doc_count=String.valueOf(mpdoc.get(me2.getKey()).size());
			String record=me2.getKey()+","+doc_count+","+me2.getValue();
			buffer_writer.write(record);
			buffer_writer.newLine();
		}
		buffer_writer.close();
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
		}
	System.out.println("Filtering Done");
	System.out.println("Writing Done");
}

public static void main(String[] args) throws IOException
{
	String path=args[0];
	String path_output=args[1];
	System.out.println("Program Started");
	//Reading part
	readFile(path);
	
	//Filtering and writing part
     writeFile(path_output);
	System.out.println("!!KABOOM!!");
	System.out.println("CHECK THE OUTPUT FILE FOR THE DATA");
		}
}