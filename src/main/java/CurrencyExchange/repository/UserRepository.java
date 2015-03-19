package CurrencyExchange.repository;

import org.springframework.data.repository.CrudRepository;
import CurrencyExchange.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);

}