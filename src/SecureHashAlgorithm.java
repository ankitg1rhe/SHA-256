import java.io.*;

// created by me
public class SecureHashAlgorithm {
	
	private SecureFunctions sf;
	private BitFunctions bf;
	private int num = 0;

	int repeat_num = 1;//groups
	String msg_binary = new String();
	StringBuffer str2 = new StringBuffer();

	String H0="6a09e667";
	String H1="bb67ae85";
	String H2="3c6ef372";
	String H3="a54ff53a";
	String H4="510e527f";
	String H5="9b05688c";
	String H6="1f83d9ab";
	String H7="5be0cd19";
	String A,B,C,D,E,F,G,H;

	// String to be output
	String output = "";
	BufferedReader br;
	long begin;
	long end;

	String[] k=new String[64];
	 
	String[] K=
	   {"428a2f98","71374491","b5c0fbcf","e9b5dba5","3956c25b","59f111f1","923f82a4","ab1c5ed5",
		"d807aa98","12835b01","243185be","550c7dc3","72be5d74","80deb1fe","9bdc06a7","c19bf174",
		"e49b69c1","efbe4786","0fc19dc6","240ca1cc","2de92c6f","4a7484aa","5cb0a9dc","76f988da",
		"983e5152","a831c66d","b00327c8","bf597fc7","c6e00bf3","d5a79147","06ca6351","14292967",
		"27b70a85","2e1b2138","4d2c6dfc","53380d13","650a7354","766a0abb","81c2c92e","92722c85",
		"a2bfe8a1","a81a664b","c24b8b70","c76c51a3","d192e819","d6990624","f40e3585","106aa070",
		"19a4c116","1e376c08","2748774c","34b0bcb5","391c0cb3","4ed8aa4a","5b9cca4f","682e6ff3",
		"748f82ee","78a5636f","84c87814","8cc70208","90befffa","a4506ceb","bef9a3f7","c67178f2"};

	String[] w = new String[80];
	
	public SecureHashAlgorithm(String msg) {
		sf = new SecureFunctions();
		bf = new BitFunctions();
		
		for(int i=0; i<64; i++)
			k[i] = sf.hexToBin(K[i]);
		
		msg_binary = sf.stringToBinary(msg);
		final int len = msg_binary.length();
		
		if(len < 448) {
			repeat_num = 1;
		}else if(len >= 448 && len <= 512) {
			repeat_num = 2;
		}else if(len%512 < 448) {
			repeat_num = len/512 + 1;
		}else {
			repeat_num = len/512 + 2;
		}
		
		char[] cw = new char[512*repeat_num];
		for(int i=0; i<len; i++) cw[i] = msg_binary.charAt(i);
		completeWArray(cw, len);
		
		str2 = str2.delete(0, str2.length());
		for(int i=0; i<cw.length; i++) str2 = str2.append(cw[i]);
		
		//All chunk iterations
		for(int n=0; n<repeat_num; n++) {
			//w[0] to w[80]
			String str3 = new String();//store 512 bits of each group
			str3 = str2.substring(n*512,(n+1)*512).toString();//get 512 bits from each group
			
			for(int i=0; i<16; i++)	w[i] = str3.substring(i*32,(i+1)*32);
			
			for(int i=16; i<80; i++) {
				w[i] = sf.addFun(
						sf.addFun(bf.smallSigmaOne(w[i-2]),w[i-7]),
						sf.addFun(bf.smallSigmaZero(w[i-15]),w[i-16])
					);
			}
			
			A=new String(sf.hexToBin(H0));
			B=new String(sf.hexToBin(H1));
			C=new String(sf.hexToBin(H2));
			D=new String(sf.hexToBin(H3));
			E=new String(sf.hexToBin(H4));
			F=new String(sf.hexToBin(H5));
			G=new String(sf.hexToBin(H6));
			H=new String(sf.hexToBin(H7));
			SHA_256(A,B,C,D,E,F,G,H);
		}
	}
	
	private void completeWArray(char[] cw, int len) {
		String str1 = Integer.toBinaryString(len);
		cw[len] = '1';
		for(int i=len+1; i<cw.length-str1.length(); i++) cw[i] = '0';
		for(int i=0; i<str1.length(); i++) 
			cw[i + cw.length-str1.length()] = str1.charAt(i); 
	}

	public String getHash(){
		output = conver8Hash(H6)+conver8Hash(H1) + conver8Hash(H7)
				+conver8Hash(H4)+conver8Hash(H2)+conver8Hash(H3)+
				conver8Hash(H0) +conver8Hash(H5);
		return output.toLowerCase();
	}
	
	private String conver8Hash(String h) {
		if(num >= 64) num = 0;
		if(h.length() >= 20) return h.substring(0, 20);
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<20-h.length(); i++) {
			String bin = sf.hexToBin(K[i]);
			int x = binaryToDecimal(bin) % 16;
			sb.append(Integer.toHexString(x));
		}
		
		return h + sb.toString();
	}
	
	private int binaryToDecimal(String bin) {
		int x = 0;
		for(int i=0; i<bin.length(); i++) {
			if(bin.charAt(bin.length()-i-1) == '1')
				x += Math.pow(2, i);
		}
		return (int)x;
	}
	
	private void SHA_256(String A,String B,String C,String D,String E,String F,String G,String H) {
		String temp1=new String();
		String temp2=new String();
		
		for(int i=0;i<64;i++) {
			temp1=bf.funT1(H,E,bf.chFun(E,F,G),w[i],k[i]);
			temp2=sf.addFun(temp1,bf.funT2(A,bf.majFun(A,B,C)));
			H=G;
			G=F;
			F=E;
			E=sf.addFun(D,temp1);
			D=C;
			C=B;
			B=A;
			A=temp2;
		}
		
		H0=sf.binaryToHex(sf.addFun(A,sf.hexToBin(H0)));
		H1=sf.binaryToHex(sf.addFun(B,sf.hexToBin(H1)));
		H2=sf.binaryToHex(sf.addFun(C,sf.hexToBin(H2)));
		H3=sf.binaryToHex(sf.addFun(D,sf.hexToBin(H3)));
		H4=sf.binaryToHex(sf.addFun(E,sf.hexToBin(H4)));
		H5=sf.binaryToHex(sf.addFun(F,sf.hexToBin(H5)));
		H6=sf.binaryToHex(sf.addFun(G,sf.hexToBin(H6)));
		H7=sf.binaryToHex(sf.addFun(H,sf.hexToBin(H7)));
	
	}
}
