package CurrencyExchange.repository;

import org.springframework.data.repository.CrudRepository;
import CurrencyExchange.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
//    User findByEmail(String email);
    User findByName(String name);

}