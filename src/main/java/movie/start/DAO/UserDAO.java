package movie.start.DAO;

import movie.start.domain.entity.Address;
import movie.start.domain.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDAO {
    EntityManager em ;
    EntityTransaction tx;

    public UserDAO(EntityManager em) {
        this.em = em;
        this.tx = em.getTransaction();
    }

    public User createUser(String name,Integer age, String city, String street, String zipcode){
        try{
            tx.begin();
            Address address = new Address(city, street, zipcode);
            User user = new User(name,age,address);
            em.persist(user);
            tx.commit();
            return user;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public User updateUser(User user){
        try{
            tx.begin();
            User findUser = em.find(User.class, user.getUserId());
            findUser.setName(user.getName());
            findUser.setAge(user.getAge());
            findUser.setAddress(user.getAddress());
            tx.commit();
            return findUser;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public User readUser(Long userId){
        try{
            tx.begin();
            User user = em.find(User.class, userId);
            tx.commit();
            return user;
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }

    public void deleteUser(Long userId){
        try{
            tx.begin();
            User user = em.find(User.class, userId);
            em.remove(user);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            throw e;
        }
    }
}
