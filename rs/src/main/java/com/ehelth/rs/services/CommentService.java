package com.ehelth.rs.services;

import com.ehelth.rs.entities.dto.CommentDTO;
import com.ehelth.rs.entities.dto.NewCommentDTO;
import com.ehelth.rs.entities.dto.UpdateCommentDTO;

public interface CommentService {
    CommentDTO[] getCommentsByDoctor(String doctorEmail);
    NewCommentDTO create(NewCommentDTO newCommentDTO);
    NewCommentDTO update(UpdateCommentDTO updateCommentDTO);
    void delete(String id);
    NewCommentDTO getComment(String id);
}
