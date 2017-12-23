import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class BetUserBean {

    @PersistenceContext
    private EntityManager em;

    public BetUser checkLoginData(String username, String password){
        TypedQuery query = em.createQuery("SELECT u FROM BetUser u WHERE u.username=:username AND u.password=:password", BetUser.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        try {
            return (BetUser)query.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }
}
