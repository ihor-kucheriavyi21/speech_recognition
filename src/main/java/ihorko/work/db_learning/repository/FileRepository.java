package ihorko.work.db_learning.repository;

import ihorko.work.db_learning.db.entity.File;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Transactional
@Repository
public interface FileRepository extends CrudRepository<File, UUID> {

}
