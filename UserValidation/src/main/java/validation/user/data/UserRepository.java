package validation.user.data;

import org.springframework.data.repository.CrudRepository;

import validation.user.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,Integer> {

	public UserEntity findByName(String name);
}
