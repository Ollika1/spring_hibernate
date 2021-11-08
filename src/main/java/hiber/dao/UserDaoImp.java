package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   public void addCar(Car car){
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("select a from User a");
      return query.getResultList();
   }
   @Override
   @SuppressWarnings("unchecked")
   public Optional<User> getUserWithCar(String model, int series){
      try {
         TypedQuery<User>
                 query=sessionFactory.getCurrentSession().createQuery(
                 "select a from User a WHERE a.car.model=:model AND a.car.series=:series");
         query.setParameter("model",model);
         query.setParameter("series",series);
         return Optional.of(query.getSingleResult());
      } catch (NoResultException e){
         System.out.println("Пользователь не найден. Проверьте данные запроса.");
         return Optional.empty();
        //throw new NoResultException("User не найден");
      }
   }

}
