package kg.itacademy.gsg.services.impls;

import kg.itacademy.gsg.entities.Comment;
import kg.itacademy.gsg.models.CommentModel;
import kg.itacademy.gsg.repositories.CommentRepository;
import kg.itacademy.gsg.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElse(new Comment());
    }

    @Override
    public void updateComment(Long id, Comment comment) {

    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Page<CommentModel> getAllCommentsByTask(Long taskId, Pageable pageable) {
        return commentRepository.getAllCommentsByTask(taskId, pageable);
    }
}
