package com.ehealth.as.services.impl;

import com.ehealth.as.entities.dto.AppointmentDTO;
import com.ehealth.as.entities.dto.CommentDTO;
import com.ehealth.as.entities.dto.RequestDeleteCommentDTO;
import com.ehealth.as.services.NSService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NSServiceImpl implements NSService {

    @Override
    public void sendDoctorNewAppointment(AppointmentDTO appointmentDTO) {
        //TODO send for a doctor a notification about the new appointment
    }

    @Override
    public void sendAdminRequestDeleteComment(CommentDTO commentDTO) {
        //TODO send admin request from doctor to delete comment
    }
}
