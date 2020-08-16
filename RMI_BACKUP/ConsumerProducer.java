import java.util.*;
import java.io.*;
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*; 

public class ConsumerProducer extends Thread {
	String info;
	static int length;
	static int consumenum;
	static int producenum;
	static int emptyspace=10;
	static private 	Vector aVector = new Vector();
	static int count=0;
	static int count1=0;
	public ConsumerProducer() {
		
	}
	
	public ConsumerProducer(String info,int length,int consumenum,int producenum) {
		this.info=info;
		this.length=length;
		this.consumenum=consumenum;
		this.producenum=producenum;
	}
	
	public void isProtected2() {
		synchronized ( aVector ){
			count1+=1;
			System.out.println("count1:"+count1);
			if(count1==500) System.exit(1);
			aVector.notifyAll();
			while(length-emptyspace>=consumenum) {
					System.out.println("consume "+consumenum+" items");
					emptyspace+=consumenum;
					if( emptyspace>=producenum ) {
						break;
					}
			}
				try {
					aVector.wait();
					isProtected2();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public void isProtected1() {
			synchronized ( aVector ){
				count+=1;
				System.out.println("count:"+count);
				if(count==500) System.exit(1);
				aVector.notifyAll();
				while(emptyspace>=producenum) {
						System.out.println("produce "+producenum+" items");
						emptyspace-=producenum;
						if(length-emptyspace>=consumenum) {
							break;
						}
//						System.out.println(emptyspace);
				}
					try {
						aVector.wait();
						isProtected1();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
	}


	public void run() {
		if(this.info.equals("producer")) {
			isProtected1();
		}else {
			isProtected2();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Vector aVector = new Vector();
		//List alist = new ArrayList();
		try {
//			int c = 0;
//			int p = 0;
//			System.out.println(InetAddress.getLocalHost().getHostAddress());
//			ServerSocket ss=new ServerSocket(2888);
//			Socket s1= ss.accept();
//			sleep(30000);
//			Socket s2=ss.accept();
//			
//            BufferedReader br=new BufferedReader(new InputStreamReader(s1.getInputStream()));
//			String str=br.readLine();
//			c=Integer.parseInt(str);
//			
//			BufferedReader br1=new BufferedReader(new InputStreamReader(s2.getInputStream()));
//			String str1=br1.readLine();
//			p=Integer.parseInt(str1);
//        	br.close();
//			s1.close();
//			s2.close();
//			ss.close();
			 String value="Reflection in Java"; 
			 String value1="another way";
			 
			 
		     
          // lookup method to find reference of remote object 
            Search access = (Search)Naming.lookup("rmi://"+args[1]+":1900"+ "/geeksforgeeks"); 
            Search access1 = (Search)Naming.lookup("rmi://"+args[2]+":1901"+ "/hp");
                                    
            String answer = access.query(value); 
            int c=Integer.parseInt(answer);
            String answer1=access1.query(value1);
            int p=Integer.parseInt(answer1);
            System.out.println("Article on " + c + " " + p+" at GeeksforGeeks"); 
	
		    
			length=Integer.parseInt(args[0]);
			ConsumerProducer addc[]=new ConsumerProducer[10];
			ConsumerProducer addp[]=new ConsumerProducer[10];
			for(int index=0;index<10;index++) {
				 addc[index]=new ConsumerProducer("consumer",length,c,p);
				 addp[index]=new ConsumerProducer("producer",length,c,p);
				 addc[index].start();
				 addp[index].start();
			}
//			for(int index=0;index<1000;index++) {
//				addc[index]=new ConsumerProducer("consumer",length,4,2);
//				addp[index]=new ConsumerProducer("producer",length,4,2);
//				addc[index].start();
//				addp[index].start();
//			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
}
}


