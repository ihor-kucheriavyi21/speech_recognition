package ihorko.work.speech_recognition.repository;

import ihorko.work.speech_recognition.db.entity.DBFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DBFileRepository extends CrudRepository<DBFile, UUID> {

}
