package ltw.nhom6.blog.blog.repository;

import ltw.nhom6.blog.blog.model.Rate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends MongoRepository<Rate, String> {
}
