package beans;

import org.apache.camel.Exchange;
import org.apache.camel.Header;

/*
 * user specification: the function's comment should contain keys as follows: 1. write about the function's comment.but
 * it must be before the "{talendTypes}" key.
 * 
 * 2. {talendTypes} 's value must be talend Type, it is required . its value should be one of: String, char | Character,
 * long | Long, int | Integer, boolean | Boolean, byte | Byte, Date, double | Double, float | Float, Object, short |
 * Short
 * 
 * 3. {Category} define a category for the Function. it is required. its value is user-defined .
 * 
 * 4. {param} 's format is: {param} <type>[(<default value or closed list values>)] <name>[ : <comment>]
 * 
 * <type> 's value should be one of: string, int, list, double, object, boolean, long, char, date. <name>'s value is the
 * Function's parameter name. the {param} is optional. so if you the Function without the parameters. the {param} don't
 * added. you can have many parameters for the Function.
 * 
 * 5. {example} gives a example for the Function. it is optional.
 */
public class Receipt {
	

    /**
     * {talendTypes} Exchange
     * 
     * {Category} User Beans
     * 
     * {param} exchange("exch") input: .
     * 
     * {example} initReceipt(Exchange exch) # hello world !.
     */  
    public static void initReceipt(Exchange exch) {
    	exch.getIn().setBody("Name and Phone # of Delivery Agent (Typed or Stamped)\nJane Doe's Agency 123 Fifth Avenue\n"
    +"Brooklyn, NY 11209(718)748-5252\n"
    +"Delivery Agency Code #18/123");
    }
    
    /**
     * {talendTypes} Exchange
     * 
     * {Category} User Beans
     * 
     * {param} exchange("exch") input: The string need to be printed.
     * {param} string("country") input: The string need to be printed.
     * {param} string("price") input: The string need to be printed.
     * 
     * {example} setPrice(Exchange exh, @Header("Price") String price, @Header("Country") String country) 
     */  
    public static void setPrice(Exchange exch, @Header("Price") String price, @Header("Country") String country) {
    	initReceipt(exch);
    	String body = exch.getIn().getBody(String.class);
        exch.getIn().setBody(body +
        		"\nCounty : "+country+ 
        		"\nPrice : "+price);
    
    }
    
}
