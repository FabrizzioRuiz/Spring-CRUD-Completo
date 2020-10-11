
package mx.com.gm.service;

import java.util.List;
import mx.com.gm.domain.Persona;

public interface IPersonaService {
    
    public List<Persona> listarPersonas();
    
    public void guardar(Persona p);
    
    public void eliminar(Persona p);
    
    public Persona encontrarPersona(Persona p);
}
