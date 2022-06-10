package com.ehelth.rs.repositories;

import com.ehelth.rs.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getAllByDoctor_Email(String email);
    @Transactional
    void deleteByCommentId(long id);
    Optional<Comment> getCommentByCommentId(long id);
}
