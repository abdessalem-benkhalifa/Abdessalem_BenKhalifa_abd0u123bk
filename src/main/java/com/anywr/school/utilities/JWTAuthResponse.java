package com.anywr.school.utilities;

import java.io.Serializable;

public class JWTAuthResponse implements Serializable {
	 
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
 
    public JWTAuthResponse(String token) {
        this.token = token;
    }
 
    public String getToken() {
        return this.token;
    }
    
    /*
     public void setToken(String toekn) {
        this.token = token;
    }
    */
    
    
}


