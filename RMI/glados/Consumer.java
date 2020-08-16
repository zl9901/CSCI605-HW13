import java.io.*;
import java.net.*;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Consumer {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
//		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//		String address=br.readLine();
//		Socket socket=new Socket(address,2888);
//		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//		bw.write("4");
//		bw.flush();
//		bw.close();
//		socket.close();

		
		String address1=InetAddress.getLocalHost().getHostAddress();
		System.out.println(address1);
        // Create an object of the interface 
        // implementation class 
			Search obj1 = new SearchQuery(); 

        // rmiregistry within the server JVM with 
        // port number 1900 
			LocateRegistry.createRegistry(1901); 

        // Binds the remote object by the name 
        // geeksforgeeks 
			Naming.rebind("rmi://"+address1+":1901"+ "/hp",obj1); 
	}

}
