
public class BitFunctions {
	private ShiftRotateFunctions srf;
	private SecureFunctions sf;
	
	public BitFunctions() {	
		srf = new ShiftRotateFunctions();
		sf = new SecureFunctions();
	}

	//function to calculate T1
	public  String funT1(String str_h,String str_e,String str_ch,String str_w,String str_k) {
		return sf.addFun(	
				sf.addFun(	//1
						sf.addFun(
							str_h,
							bigSigmaOne(str_e)
						),
						sf.addFun(str_ch,str_w)
					),
					str_k	//1
				);
	}
	
	//function to calculate T1
	public  String funT2(String str_a,String str_maj) {
		return sf.addFun(bigSigmaZero(str_a),str_maj);
	}
	
	public String chFun(String str1,String str2,String str3) {
		return bitDifferentOr(bitAnd(str1, str2), bitAnd(bitNot(str1), str3));
	}
	
	public String majFun(String str1,String str2,String str3) {
		return bitDifferentOr(bitDifferentOr(bitAnd(str1, str2), bitAnd(str1, str3)), bitAnd(str2, str3));
	}
	
	//Sigma Functions
	public  String smallSigmaZero(String str) {
		return bitDifferentOr(
					bitDifferentOr(srf.rotateRight(str, 7), srf.rotateRight(str, 18)),
					srf.shiftRight(str, 3)
				);
	}
	
	public  String smallSigmaOne(String str) {
		return bitDifferentOr(
					bitDifferentOr(srf.rotateRight(str, 17), srf.rotateRight(str, 19)),
					srf.shiftRight(str, 10)
				);
	}
	
	public  String bigSigmaZero(String str) {
		return bitDifferentOr(
					bitDifferentOr(srf.rotateRight(str, 2), srf.rotateRight(str, 13)),
					srf.rotateRight(str, 22)
				);
	}
	
	public  String bigSigmaOne(String str) {
		return bitDifferentOr(
					bitDifferentOr(srf.rotateRight(str, 6), srf.rotateRight(str, 11)),
					srf.rotateRight(str, 25)
				);
	}
	
	//bitwise nor
	private String bitNot(String str1) {
		String str=new String();
		StringBuffer s=new StringBuffer();
		for(int i=0;i<str1.length();i++) {
			if(str1.charAt(i)=='0')
				str=s.append('1').toString();
			else
				str=s.append('0').toString();
		}
		return str;
	}
	
	// and function
	private String bitAnd(String str1,String str2) {
		String str = new String();
		StringBuffer s = new StringBuffer();
		
		for(int i=0;i<str1.length();i++) {
			if(str1.charAt(i)=='0'||str2.charAt(i)=='0')
				str=s.append('0').toString();
			else
				str=s.append('1').toString();
		}
		return str;
	}
	
	// or function
	private String bitOr(String str1,String str2) {
		String str = new String();
		StringBuffer s = new StringBuffer();
		
		for(int i=0;i<str1.length();i++) {
			if(str1.charAt(i)=='1'||str2.charAt(i)=='1')
				str=s.append('1').toString();
			else
				str=s.append('0').toString();
		}
		return str;
	}
	
	// different Or function
	private String bitDifferentOr(String str1, String str2) {
		String str = new String();
		StringBuffer s = new StringBuffer();
		
		for(int i=0; i<str1.length(); i++) {
			if(str1.charAt(i) == str1.charAt(i))
				str = s.append("0").toString();
			else
				str = s.append("1").toString();
		}
		return str;
	}
	
	// same Or Function
	private String bitSameOr(String str1, String str2) {
		String str = new String();
		StringBuffer s = new StringBuffer();
		
		for(int i=0; i<str1.length(); i++) {
			if(str1.charAt(i) == str1.charAt(i))
				str = s.append("1").toString();
			else
				str = s.append("0").toString();
		}
		return str;
	}
}
