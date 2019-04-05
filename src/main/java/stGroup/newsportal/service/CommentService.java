package stGroup.newsportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stGroup.newsportal.entity.Article;
import stGroup.newsportal.entity.Comment;
import stGroup.newsportal.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public void addComment(Comment comment) {
        repository.save(comment);
    }

    public List<Comment> getArticleComments (Article article, int pageNum, int number) {
        Pageable pageNeeded = PageRequest.of(pageNum, number);
        return repository.getComments(article, pageNeeded);
    }

    public void deleteComment (Long id) {
        repository.deleteById(id);
    }

    public void upVoteComment (Long id) {
        Comment comment = repository.findById(id).get();
        comment.setUpVotes(comment.getUpVotes()+1);
        repository.save(comment);
    }

    public void downVoteComment (Long id) {
        Comment comment = repository.findById(id).get();
        comment.setDownVotes(comment.getDownVotes()+1);
        repository.save(comment);
    }

    public void disDownVoteComment (Long id) {
        Comment comment = repository.findById(id).get();
        comment.setDownVotes(comment.getDownVotes()-1);
        repository.save(comment);
    }

    public void disUpVoteComment (Long id) {
        Comment comment = repository.findById(id).get();
        comment.setUpVotes(comment.getUpVotes()-1);
        repository.save(comment);
    }

}
