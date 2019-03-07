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

public class ATM_Server
{

	
	
		public static void main(String args[]) throws Exception 
		{
			
			ATM_Packet cell2=new ATM_Packet();
		//	cell2=null;
			ServerSocket ser_soc;
			Socket soc1=null;
		

		try
			{

			System.out.println("...............Simulation of Asysnchronous Transfer Mode using TCP/IP socket..........//////////////////////////////////");
		
			ser_soc=new ServerSocket(2346);
	
			System.out.println("///Server started  successfully on port 2346 :: >>>>>>waiting for client to request............");

			soc1=ser_soc.accept();

			System.out.println("Connection Established successfully with client :::: "+soc1 );
			
		/*	DataInputStream din=new DataInputStream(soc1.getInputStream());
			String str=din.readLine();
			System.out.println(str);*/

		
			InputStream in = soc1.getInputStream();
			ObjectInput s=new ObjectInputStream(in);
			//OutputStream out = s.getOutputStream();

			//System.out.println("Enter the Data to send to server");

			//accept the atm cell

			
		
			cell2=(ATM_Packet)s.readObject();
	
			System.out.println("DATA FROM CLIENT==>>.."+cell2);
		
			boolean chk=true;
			System.out.println("/////////Received GFC bits:");
			for(int i=0;i<4;i++)
			{
				if(chk==cell2.GFC.get(i))
					System.out.print("1");
				else
					System.out.println("0");
			}
			
			System.out.print("\n");
			
			System.out.println("/////////Received VPI bits:");
			for(int i=0;i<8;i++)
			{
				if(chk==cell2.VPI.get(i))
					System.out.print("1");
				else
					System.out.println("0");
				
			}
			System.out.print("\n");

			System.out.println("/////////Received VCI bit:");
			for(int i=0;i<16;i++)
			{
				if(chk==cell2.VCI.get(i))
					System.out.print("1");
				else
					System.out.println("0");

				
			}

			System.out.print("\n");
			System.out.println("/////////Received PT bit:");
			for(int i=0;i<3;i++)
			{	
				if(chk==cell2.PT.get(i))
					System.out.print("1");

				else
					System.out.println("0");
				
			}

			System.out.print("\n");
			System.out.println("/////////Received CLP bit");
			for(int i=0;i<1;i++)
			{			
				if(chk==cell2.CLP.get(i))
					System.out.print("1");
				else
					System.out.println("0");

				
			}
			
			System.out.print("\n");
			System.out.println("/////////Received HEC bit");
			for(int i=0;i<8;i++)
			{
				if(chk==cell2.HEC.get(i))
					System.out.print("1");
				else
					System.out.println("0");

				
			}

			System.out.print("\n");			
			System.out.println("/////////Received payload");
			for(int i=0;i<384;i++)
			{		
				if(chk==cell2.Payload.get(i))		
					System.out.print("1");
				else
					System.out.println("0");

				
			}
		
		
			}

			catch (Exception e)
		 	{
			 e.printStackTrace();
		 	}
			finally
			{
				soc1.close();
			}
		}
		
}