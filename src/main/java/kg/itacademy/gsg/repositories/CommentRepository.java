package kg.itacademy.gsg.repositories;

import kg.itacademy.gsg.entities.Comment;
import kg.itacademy.gsg.models.CommentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select new kg.itacademy.gsg.models.CommentModel(c.id,c.commentText,c.user,c.clientTask,c.createdDate) FROM Comment c join ClientTasks ct on ct.id = c.clientTask.id where ct.id = :taskId")
    Page<CommentModel> getAllCommentsByTask(@Param("taskId") Long taskId, Pageable pageable);
}
