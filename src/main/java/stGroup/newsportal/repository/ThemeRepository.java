package stGroup.newsportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stGroup.newsportal.entity.Theme;

@Repository
public interface ThemeRepository extends CrudRepository<Theme, String> {
}
