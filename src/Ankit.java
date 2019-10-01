import java.io.*;

public class Ankit {

	public static void main(String[] args) throws IOException{
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter your input : ");
		String msg = br.readLine();
				
		SecureHashAlgorithm sha = new SecureHashAlgorithm(msg);
		String hashMsg = sha.getHash();
		System.out.println(hashMsg);
	
	}

}
