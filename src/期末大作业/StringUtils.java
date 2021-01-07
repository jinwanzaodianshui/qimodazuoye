package 期末大作业;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author tlm
 * 工具类
 */
public class StringUtils {
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str==null||str.equals("")) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	   * 验证手机号码
	   * 利用正则表达式
	  * @param mobiles
	  * @return
	 */
	 public static boolean isMobilePhone(String mobiles){
		  boolean flag = false;
		  try{
			   Pattern p = Pattern.compile("^1[3456789]\\d{9}$");
			   Matcher m = p.matcher(mobiles);
			   flag = m.matches();
		  }catch(Exception e){
			  flag = false;
		  }
		  return flag;
	 }
	
	 /**
	  * 判断是否是数字
	  * @param str
	  * @return
	  */
	 public static boolean isNumeric(String str){  
		 for (int i = 0; i < str.length(); i++){  		    
		        if (!Character.isDigit(str.charAt(i))){  
		            return false;  
		         }  
		    }  
		     return true;  
	 }
	 
	 /**	 
	    * 判断是否是整数或者是小数
	    * 利用正则表达式	 
	  * @param str 
	  * @return true：是，false不是 
	  */
	 
	  public static boolean validateNumber(String str) {
	 	 
	    return str.matches("[0-9]+(\\.[0-9]+)?");  
	 
	  }
}
