
package mx.com.gm.service;

import java.util.List;
import mx.com.gm.dao.PersonaDao;
import mx.com.gm.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaService implements IPersonaService{

    @Autowired
    private PersonaDao personaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Persona p) {
        personaDao.save(p);
    }

    @Override
    @Transactional
    public void eliminar(Persona p) {
        personaDao.delete(p);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona p) {
        return personaDao.findById(p.getIdPersona()).orElse(null);
    }
    
}
