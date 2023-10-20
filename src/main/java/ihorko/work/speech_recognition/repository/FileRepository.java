package ihorko.work.speech_recognition.repository;

import ihorko.work.speech_recognition.db.entity.File;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Transactional
@Repository
public interface FileRepository extends CrudRepository<File, UUID> {

}
