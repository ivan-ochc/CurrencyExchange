package currencyexchange.repository;

import currencyexchange.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String userName);
}
