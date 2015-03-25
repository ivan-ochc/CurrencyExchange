package currencyexchange.repository;

import org.springframework.data.repository.CrudRepository;
import currencyexchange.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);

}