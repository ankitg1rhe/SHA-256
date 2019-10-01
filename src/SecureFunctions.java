
public class SecureFunctions {
	
	public SecureFunctions()  {	}
	
	public String addFun(String str1, String str2) {
		char[] cArray=new char[32];
		int flag=0;
		for(int i=str1.length()-1;i>=0;i--) {
			cArray[i]=(char)(((str1.charAt(i)-'0')+((str2.charAt(i)-'0'))+flag)%2+'0');
			if(((str1.charAt(i)-'0')+(str2.charAt(i)-'0')+flag)>=2)
				flag=1;
			else
				flag=0;
		}
		return new String(cArray);
	}

	public String stringToBinary(String str) {
		StringBuffer str2 = new StringBuffer();
		for(int i=0; i<str.length(); i++) {
			str2 = str2.append(fillZero(Integer.toBinaryString(Integer.valueOf(str.charAt(i))), 8));
		}
		return str2.toString();
	}

	private String fillZero(String str, int n) {
		String str2 = new String();
		StringBuffer str1=new StringBuffer();
		
		if(str.length() < n) {
			for(int i=0;i<n-str.length();i++) {
				str2=str1.append('0').toString();
			}
		}

		return str2+str;
	}
	
	public String binaryToHex(String str) {
		if(str.length() < 32)
			str = fillZero(str, 32-str.length());
		else if(str.length() > 32)
			str = str.substring(0,32);
		
		int a;
		StringBuffer st = new StringBuffer();
		
		for(int i=0; i<str.length()/4; i++) {
			a = Integer.valueOf(str.substring(i*4, 4*(i+1)));
			st.append(Integer.toHexString(a));
		}
		return st.toString();
	}
	
	public String hexToBin(String str) {
		String temp = "";
		String st = "";
		
		for(int i=0; i<str.length(); i++) {
			switch(str.charAt(i)) {
				case '0':st="0000";	break;
				case '1':st="0001";	break;
				case '2':st="0010";	break;
				case '3':st="0011";	break;
				case '4':st="0100";	break;
				case '5':st="0101";	break;
				case '6':st="0110";	break;
				case '7':st="0111";	break;
				case '8':st="1000";	break;
				case '9':st="1001";	break;
				case 'a':st="1010";	break;
				case 'b':st="1011";	break;
				case 'c':st="1100";	break;
				case 'd':st="1101";	break;
				case 'e':st="1110";	break;
				case 'f':st="1111";	break;
			}
			temp=temp + st;
		}
		if(temp.length() < 32)
			temp = fillZero(temp, 32-temp.length());
		else if(temp.length() > 32)
			temp = temp.substring(0,32);
		
		return temp.toString();
	}

}
