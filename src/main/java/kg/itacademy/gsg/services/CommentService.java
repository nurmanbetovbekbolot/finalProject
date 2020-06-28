package kg.itacademy.gsg.services;

import kg.itacademy.gsg.entities.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();

    Comment getCommentById(Long id);

    void updateComment(Long id, Comment comment);

    void saveComment(Comment comment);

    void deleteCommentById(Long id);
}
