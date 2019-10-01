
public class ShiftRotateFunctions {
	
	public ShiftRotateFunctions()  {	}

	//rotate left n bits
	public String rotateLeft(String str,int n) {
		return str.substring(n) + str.substring(0, n);
	}
	
	//rotate right n bits
	public String rotateRight(String str,int n) {
		return str.substring(str.length()-n) + str.substring(0, str.length()-n);
	}

	//right shift n bits
	public String shiftRight(String str, int n) {
		StringBuffer s = new StringBuffer();
		for(int i=0; i<n; i++) s.append("0");
		return s.toString() + str.substring(0, str.length()-n);
	}
	
	//left shift n bits
	public String shiftLeft(String str, int n) {
		StringBuffer s = new StringBuffer();
		for(int i=0; i<n; i++) s.append("0");
		return str.substring(n) + s.toString();
	}
	
}
