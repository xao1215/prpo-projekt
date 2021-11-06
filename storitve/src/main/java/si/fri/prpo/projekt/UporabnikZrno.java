package si.fri.prpo.projekt;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class UporabnikZrno {

    @PersistenceContext(unitName = "polnilne-postaje")
    private EntityManager em;

    public List<Uporabnik> getUporabniki() {
        return em.createNamedQuery("Uporabnik.getAll").getResultList();
    }
}