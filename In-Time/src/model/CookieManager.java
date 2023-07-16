package model;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import model.ProductBean;
import javax.servlet.http.Cookie;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


 
public class CookieManager {
	
	public String listToStringJSON(List<ProductBean> cartArray) {
		 
		Gson gson = new Gson();
   	    String json = gson.toJson(cartArray);
   	    String cart;
		try {
			cart = URLEncoder.encode(json, "UTF-8");
			return cart;
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
	 return null;
	}
	
	public List<ProductBean> jSONStringToList(String cart){
		 
    	 String cartString;
		try {
			cartString = URLDecoder.decode(cart, "UTF-8");
			Gson gson = new Gson();
			return gson.fromJson(cartString, new TypeToken<ArrayList<ProductBean>>() {}.getType());
			
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
     
    	 return null;
		
	}
	
	public Cookie findCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}