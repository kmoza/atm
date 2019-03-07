//@ Krishna M Oza
//file name:: ATM_Simulation:ver 1.0
//date::6-09-2010
//Demonstrate ATM Simulation using TCP/ip socket

import java.net.*;
import java.io.*;
import java.util.BitSet;



/////////////////////void set(int index)

// ATM Packet Format:

class ATM_Packet implements Serializable
{
	BitSet GFC = new BitSet(4);
	BitSet VPI = new BitSet(8);
	BitSet VCI = new BitSet(16);
	BitSet PT = new BitSet(3);
	BitSet CLP = new BitSet(1);
	BitSet HEC = new BitSet(8);
	BitSet Payload = new BitSet(384);
}

public class ATM_Client 
{

	public static void main(String args[]) throws Exception 
		{
		
		ATM_Packet cell1=new ATM_Packet();
		int choice;
		ObjectOutput s=null;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		try
			{
			System.out.println("////////////////////////Simulation of Asysnchronous Transfer Mode using TCP/IP socket........../////");
			System.out.println("Enter the choice");
			System.out.println(" 0 for direct client to server");
			System.out.println("1 for client-inetrmidiatenode-server");
			choice=Integer.parseInt(br.readLine());
			//accept the atm cell
			for(int i=0;i<4;i++)
			{
				System.out.println("/////////Enter the GFC bit i="+i);
				cell1.GFC.set(i);
			}
			for(int i=0;i<8;i++)
			{
				System.out.println("/////////Enter the VPI bit i="+i);
				cell1.VPI.set(i);
			}
			for(int i=0;i<16;i++)
			{
				System.out.println("/////////Enter the VCI bit i="+i);
				cell1.VCI.set(i);
			}
			for(int i=0;i<3;i++)
			{
				System.out.println("/////////Enter the PT bit i="+i);
				cell1.PT.set(i);
			}
			for(int i=0;i<1;i++)
			{
				System.out.println("/////////Enter the CLP bit i="+i);
				cell1.CLP.set(i);
			}
			for(int i=0;i<8;i++)
			{
				System.out.println("/////////Enter the HEC bit i="+i);
				cell1.HEC.set(i);
			}
			for(int i=0;i<384;i++)
			{
				//System.out.println("/////////Enter the GFC bit i="+i);
				//if(i%2==0)
				cell1.Payload.set(i);
			}


			if(choice==1)
			{

				System.out.println("Client ready  to send data to server via INtermediate node:::::");
		
				Socket soc=new Socket(InetAddress.getByName("localhost"),1234); //Socket("127.0.0.1",1234);

				System.out.println("Virtual  connection successfully established with server on port ::1234");
	
				//InputStream in = s.getInputStream();
				//OutputStream out = s.getOutputStream();

				OutputStream o=soc.getOutputStream();
				s=new ObjectOutputStream(o); 	
				//System.out.println("Enter the Data to send to server");
						
				//out.write(cell.Payload);//////////// ORR/////////
				//ObjectOutput o=new ObjectOutputStream(s);
				s.writeObject(cell1);
			}
			if(choice==0)
			{		
				System.out.println("Client ready  to send data to server directly:::::");
				Socket soc=new Socket(InetAddress.getByName("localhost"),2346); //Socket("127.0.0.1",2346);
				System.out.println("Virtual  connection successfully established with server on port :2346");
				/*PrintWriter dout=new PrintWriter(soc.getOutputStream(),true);
				dout.println("Path 0 is followed");*/
				OutputStream o=soc.getOutputStream();
				s=new ObjectOutputStream(o);
				s.writeObject(cell1); 	
			}
			
			}

			catch (Exception e)
		 	{
			 e.printStackTrace();
		 	}
			finally
			{
				s.close();
			}
		}
		
}