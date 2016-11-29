import java.util.Stack;

public class BalancingSymbols {
    
	/*检验符号是否平衡**/ 
	public static void P14(String str) throws Exception{
		String tag = "错误:符号不平衡";
		Stack<Character> stack = new Stack<>();
		for(int i = 0 ; i != str.length() ; i++ ) {
			switch(str.charAt(i)) {
			case '[':
			  stack.push('[');  
			  break;
			case '(':
			  stack.push('(');
			  break;
			case '{':
			  stack.push('{');
              break;
			case '/': 
		      if(i < str.length()-1 && str.charAt(i+1) == '*') {
		    	  stack.push('/');
		    	  stack.push('*');
		      }
			  break;
			case ']':
			  if(stack.pop() != '[') {
				  throw new Exception(tag);
			  }
			  break;
			case ')':
				if(stack.pop() != '(') {
				  throw new Exception(tag);
				}
			    break;
			case '}':
				if(stack.pop() != '{') {
			      throw new Exception(tag);
				}
				break;
			case '*':
				if( (stack.size()<2 || (stack.pop() != '*' || stack.pop() != '/'))) {
					throw new Exception(tag);
				}
			default:
			  break;
			}
			
		}
		if(stack.size() != 0) {
			throw new Exception("err");
		}
		System.out.println("Right");

	}
	
}
