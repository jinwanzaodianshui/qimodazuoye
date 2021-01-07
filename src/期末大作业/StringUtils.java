package ��ĩ����ҵ;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author tlm
 * ������
 */
public class StringUtils {
	/**
	 * �ж��ַ����Ƿ�Ϊ��
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
	   * ��֤�ֻ�����
	   * ����������ʽ
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
	  * �ж��Ƿ�������
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
	    * �ж��Ƿ�������������С��
	    * ����������ʽ	 
	  * @param str 
	  * @return true���ǣ�false���� 
	  */
	 
	  public static boolean validateNumber(String str) {
	 	 
	    return str.matches("[0-9]+(\\.[0-9]+)?");  
	 
	  }
}
