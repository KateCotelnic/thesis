package com.ehelth.rs.services.impl;

import com.ehelth.rs.entities.Comment;
import com.ehelth.rs.entities.dto.CommentDTO;
import com.ehelth.rs.entities.dto.NewCommentDTO;
import com.ehelth.rs.entities.dto.UpdateCommentDTO;
import com.ehelth.rs.repositories.CommentRepository;
import com.ehelth.rs.services.CommentService;
import com.ehelth.rs.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;

    @Override
    public CommentDTO[] getCommentsByDoctor(String doctorEmail) {
        List<Comment> comments = commentRepository.getAllByDoctor_Email(doctorEmail);
        return comments.stream().map(Comment::toCommentDTO).toArray(CommentDTO[]::new);
    }

    @Override
    public NewCommentDTO create(NewCommentDTO newCommentDTO) {
        return commentRepository.save(toCommentFromNew(newCommentDTO)).toNewCommentDTO();
    }

    @Override
    public NewCommentDTO update(UpdateCommentDTO updateCommentDTO) {
        Comment comment = commentRepository.getById(Long.parseLong(updateCommentDTO.getCommentId()));
        comment.setBody(updateCommentDTO.getBody());
        comment.setRating(Integer.parseInt(updateCommentDTO.getRating()));
        comment.setDate(LocalDate.now());
        return commentRepository.save(comment).toNewCommentDTO();
    }

    @Override
    public void delete(String id) {
        commentRepository.deleteByCommentId(Long.parseLong(id));
    }

    @Override
    public NewCommentDTO getComment(String id) {
        return commentRepository.getCommentByCommentId(Long.parseLong(id)).toNewCommentDTO();
    }

    Comment toCommentFromNew(NewCommentDTO commentDTO){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return Comment.builder()
                .body(commentDTO.getBody())
                .rating(Integer.parseInt(commentDTO.getRating()))
                .date(LocalDate.parse(commentDTO.getDate(),formatter))
                .patient(userService.getUserByEmail(commentDTO.getPatientEmail()))
                .doctor(userService.getUserByEmail(commentDTO.getDoctorEmail()))
                .build();
    }
}
