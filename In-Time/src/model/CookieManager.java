package model;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import model.CatalogoBean;
import model.ProductBean;
import model.ProductManager;
import javax.servlet.http.Cookie;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URLEncoder;
import model.CatalogoBean;
import model.ProductBean;
import model.ProductManager;
import javax.servlet.http.Cookie;
 
public class CookieManager {
	
	public String ListToStringJSON(ArrayList<ProductBean> cartArray) {
		 
		Gson gson = new Gson();
   	    String json = gson.toJson(cartArray);
   	    String cart;
		try {
			cart = URLEncoder.encode(json, "UTF-8");
			return cart;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return null;
	}
	
	public ArrayList<ProductBean> JSONStringToList(String Cart){
		 
    	 String cartString;
		try {
			cartString = URLDecoder.decode(Cart, "UTF-8");
			Gson gson = new Gson();
	    	 ArrayList<ProductBean> cartList = gson.fromJson(cartString, new TypeToken<ArrayList<ProductBean>>() {}.getType());
	    	 return cartList;
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
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