package ltw.nhom6.blog.blog.repository;

import ltw.nhom6.blog.blog.model.Rate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RateRepository extends MongoRepository<Rate, String> {
}
