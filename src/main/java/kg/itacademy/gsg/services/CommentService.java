package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.Comment;
import kg.itacademy.gsg.models.CommentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();

    Comment getCommentById(Long id);

    void updateComment(Long id, Comment comment);

    void saveComment(Comment comment);

    void deleteCommentById(Long id);

    Page<CommentModel> getAllCommentsByTask(Long taskId, Pageable pageable);
}
