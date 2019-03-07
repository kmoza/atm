//@ Krishna M Oza
//file name:: ATM_Simulation:ver 1.0
//date::6-09-2010
//Demonstrate ATM Simulation using TCP/ip socket

import java.net.*;
import java.io.*;
import java.util.BitSet;


/////////////////////void set(int index)

// ATM Packet Format:

/*class ATM_Packet implements Serializable
{
	BitSet GFC = new BitSet(4);
	BitSet VPI = new BitSet(8);
	BitSet VCI = new BitSet(16);
	BitSet PT = new BitSet(3);
	BitSet CLP = new BitSet(1);
	BitSet HEC = new BitSet(8);
	BitSet Payload = new BitSet(384);


};*/


public class ATM_IMNODE
{
	public static void main(String args[]) throws Exception 
	{
		ObjectOutput s1=null;
		ATM_Packet cellim=new ATM_Packet();
		
		try
			{
			
			ATM_Packet cellsnd=new ATM_Packet();	
			//acting as a server for client
			ServerSocket ser_soc;
			Socket soc=null;
			ser_soc=new ServerSocket(1234);
			soc=ser_soc.accept();
			//System.out.println("Virtual  connection successfully established with server on port ::1234");


	
		//receiving the packet from client
			

			InputStream in = soc.getInputStream();
			ObjectInput s=new ObjectInputStream(in);
			//OutputStream out = s.getOutputStream();

			//System.out.println("Enter the Data to send to server");

			//accept the atm cell

			
		
			cellim=(ATM_Packet)s.readObject();
	
			System.out.println("DATA FROM CLIENT==>>.."+cellim);
		
			boolean chk=true;
			System.out.println("/////////Received GFC bits:");
			for(int i=0;i<4;i++)
			{
				if(chk==cellim.GFC.get(i))
					{
					cellsnd.GFC.set(i);
					System.out.print("1");
					}
				else
					System.out.println("0");
			}
			
			System.out.print("\n");
			
			System.out.println("/////////Received VPI bits:");
			for(int i=0;i<8;i++)
			{
				if(chk==cellim.VPI.get(i))
					{
					cellsnd.VPI.set(i);
					System.out.print("1");
					}
				else
					System.out.println("0");
				
			}
			System.out.print("\n");

			System.out.println("/////////Received VCI bit:");
			for(int i=0;i<16;i++)
			{
				if(chk==cellim.VCI.get(i))
					{
					cellsnd.VCI.set(i);
					System.out.print("1");
					}
				else
					System.out.println("0");

				
			}

			System.out.print("\n");
			System.out.println("/////////Received PT bit:");
			for(int i=0;i<3;i++)
			{	
				if(chk==cellim.PT.get(i))
					{					
					cellsnd.PT.set(i);
					System.out.print("1");
					}
					

				else
					System.out.println("0");
				
			}

			System.out.print("\n");
			System.out.println("/////////Received CLP bit");
			for(int i=0;i<1;i++)
			{			
				if(chk==cellim.CLP.get(i))
					{
					cellsnd.CLP.set(i);
					System.out.print("1");
					}
				else
					System.out.println("0");

				
			}
			
			System.out.print("\n");
			System.out.println("/////////Received HEC bit");
			for(int i=0;i<8;i++)
			{
				if(chk==cellim.HEC.get(i))
					{
					cellsnd.HEC.set(i);
					System.out.print("1");
					}
				else
					System.out.println("0");

				
			}

			System.out.print("\n");			
			System.out.println("/////////Received payload");
			for(int i=0;i<384;i++)
			{		
				if(chk==cellim.Payload.get(i))
					{
					cellsnd.Payload.set(i);		
					System.out.print("1");
					}
				else
					System.out.println("0");

				
			}
			//connection to final server
				Socket soc1=new Socket(InetAddress.getByName("localhost"),2346); //Socket("127.0.0.1",2346);
				System.out.println("Virtual  connection successfully established with server on port ::2346");

		//sending the data to server
		
		//PrintWriter dout=new PrintWriter(soc.getOutputStream(),true);
		//dout.println("Path 1 is followed");
		OutputStream o2=soc1.getOutputStream();
		s1=new ObjectOutputStream(o2); 	
		s1.writeObject(cellsnd);
		

			}
			catch (Exception e)
		 	{
			 e.printStackTrace();
		 	}
			/*finally()
			{
				soc.close();

			}*/
			
	}

}