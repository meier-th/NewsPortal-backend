package stGroup.newsportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stGroup.newsportal.entity.Viewer;

@Repository
public interface ViewerRepository extends CrudRepository<Viewer, String> {
}
