package stGroup.newsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stGroup.newsportal.entity.Viewer;
import stGroup.newsportal.repository.ViewerRepository;

@Service
public class ViewerService {

    @Autowired
    private ViewerRepository repository;

    public void updateViewer (Viewer viewer) {
        repository.save(viewer);
    }

    public Viewer getViewerByLogin (String login) {
        return repository.findById(login).orElse(null);
    }

}
