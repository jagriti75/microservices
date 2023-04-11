package external.api.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import external.api.entity.LogData;

@Repository
public interface LogDataRepository extends CrudRepository<LogData ,Long> {

}
