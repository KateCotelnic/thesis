package com.ehelth.rs.controllers;

import com.ehelth.rs.entities.dto.CommentDTO;
import com.ehelth.rs.entities.dto.NewCommentDTO;
import com.ehelth.rs.entities.dto.UpdateCommentDTO;
import com.ehelth.rs.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rs/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/getAllByDoctor")
    public ResponseEntity<CommentDTO[]> getAllComments(@RequestParam(name = "doctorEmail", defaultValue = "")String doctorEmail){
        return ResponseEntity.ok(commentService.getCommentsByDoctor(doctorEmail));
    }

    @PostMapping("/new")
    public ResponseEntity<NewCommentDTO> createComment(@RequestBody NewCommentDTO newCommentDTO){
        return ResponseEntity.ok(commentService.create(newCommentDTO));
    }

    @PostMapping("/update")
    public ResponseEntity<NewCommentDTO> updateComment(@RequestBody UpdateCommentDTO updateCommentDTO){
        return ResponseEntity.ok(commentService.update(updateCommentDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteComment(@RequestParam(name = "id", defaultValue = "")String id){
        commentService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<NewCommentDTO> getComment(@RequestParam(name = "id", defaultValue = "")String id){
        return ResponseEntity.ok(commentService.getComment(id));
    }
}
