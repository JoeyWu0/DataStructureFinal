
public class FastExponentiation {
	/** 不使用递归的快速求幂程序*/ 
	public static long P11(int a,int b) throws Exception{
		if(b<0) {
			throw new Exception("b需要大于0");
		}
		
	    long  temp = 1;
		while(b>0) {
	    	if(b%2==1) {
	    		temp*=a;
	    	}
	    	a*=a;
	    	b/=2;
	    }
		
		return temp;
	}
}
