import java.io.*;
import java.net.*;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Producer {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
//		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//		String address=br.readLine();
//		//localhost
//		Socket socket=new Socket(address,2888);
//		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//		bw.write("2");
//		bw.flush();
//		bw.close();
//		socket.close();

		String address=InetAddress.getLocalHost().getHostAddress();
		System.out.println(address);
        // Create an object of the interface 
        // implementation class 
			Search obj = new SearchQuery(); 

        // rmiregistry within the server JVM with 
        // port number 1900 
			LocateRegistry.createRegistry(1900); 

        // Binds the remote object by the name 
        // geeksforgeeks 
			Naming.rebind("rmi://"+address+":1900"+ "/geeksforgeeks",obj); 
	}

}
