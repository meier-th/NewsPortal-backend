package stGroup.newsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stGroup.newsportal.entity.Theme;
import stGroup.newsportal.repository.ThemeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository repository;

    public void addTheme (Theme theme) {
        repository.save(theme);
    }

    public List<Theme> getAllThemes () {
        List<Theme> themes = new ArrayList<>();
        repository.findAll().forEach(themes::add);
        return themes;
    }

    public Theme getThemeByName (String name) {
        return repository.findById(name).orElse(null);
    }

}
