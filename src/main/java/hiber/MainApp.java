package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
   public static void main(String[] args)  {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Mercedes",34567);
      Car car2 = new Car("BMW",39876);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(car1);
      car1.setUser(user1);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(car2);
      car2.setUser(user2);
      userService.add(user1);
      userService.add(user2);
      Car car3 = new Car("Mazda", 475245);

      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      userService.getUserWithCar("Mercedes",34567)
              .ifPresent(x -> System.out.println("User с Mercedes серии 34567  "+x));
      userService.getUserWithCar("BMW",39876)
              .ifPresent(x -> System.out.println("User c BMW серии 39876  "+x));
      userService.getUserWithCar("Volvo",10543)
              .ifPresent(x -> System.out.println("User c Volvo серии 10543" +x));

      List<User> users = userService.listUsers();

      for (User user : users) {
         if (user.getCar()!=null){
            System.out.println("Id = "+user.getId());
            System.out.println("First Name = "+user.getFirstName());
            System.out.println("Last Name = "+user.getLastName());
            System.out.println("Email = "+user.getEmail());
            System.out.println("Car = "+ user.getCar().getModel()+" "+user.getCar().getSeries());
            System.out.println();
         }
      }

      context.close();
   }
}
