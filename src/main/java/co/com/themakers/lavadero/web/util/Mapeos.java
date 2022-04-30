/**
 * 
 */
package co.com.themakers.lavadero.web.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.themakers.lavadero.entity.Usuario;

/**
 * 
 * @author Luis Llanos (Developer)
 *
 */
public class Mapeos {
	
	public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
	
	public Usuario convertUsuarioToJson(String str) throws IOException {
        if (str == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();        
        Reader reader = new StringReader(str);
        return mapper.readValue(reader, Usuario.class);        
    }
	

}
