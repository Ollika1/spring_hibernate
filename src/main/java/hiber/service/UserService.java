package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(User user);
    List<User> listUsers();
    void addCar(Car car);
    Optional<User> getUserWithCar(String model, int series);
}
