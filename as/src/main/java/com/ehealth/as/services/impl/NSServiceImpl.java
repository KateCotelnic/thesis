package com.ehealth.as.services.impl;

import com.ehealth.as.entities.dto.AppointmentDTO;
import com.ehealth.as.entities.dto.CommentDTO;
import com.ehealth.as.entities.dto.MessageDTO;
import com.ehealth.as.services.AMQPConfiguration;
import com.ehealth.as.services.NSService;
import com.ehealth.as.services.RSService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NSServiceImpl implements NSService {
    private final RSService rsService;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Override
    public void sendDoctorNewAppointment(AppointmentDTO appointmentDTO) throws JsonProcessingException {
        String messageText = "Hello " + rsService.getNameByEmail(appointmentDTO.getDoctorEmail()) + ", \n\nThere is a new request for appointment from " + appointmentDTO.getPatientEmail() + " at " + appointmentDTO.getHospitalName() + ". Date: " + reformatDate(appointmentDTO.getStartDate()) + " - " + reformatDate(appointmentDTO.getEndDate()) + ".\nTo approve or decline the request please enter the eHealth application. \n\nBest regards,\neHealth team";
        String message = ow.writeValueAsString(MessageDTO.builder().receiverEmail(appointmentDTO.getDoctorEmail()).message(messageText).build());
        rabbitTemplate.convertAndSend(AMQPConfiguration.EXCHANGE_NAME, AMQPConfiguration.ROUTING_KEY_EMAIL, message);
        messageText = "New appointment!\nDate: " + reformatDate(appointmentDTO.getStartDate()) + " - " + reformatDate(appointmentDTO.getEndDate()) + "\nHospital: " + appointmentDTO.getHospitalName() + "\nPatient email: " + appointmentDTO.getPatientEmail();
        message = ow.writeValueAsString(MessageDTO.builder().receiverEmail(appointmentDTO.getDoctorEmail()).message(messageText).subject("Request for a new appointment").build());
        rabbitTemplate.convertAndSend(AMQPConfiguration.EXCHANGE_NAME, AMQPConfiguration.ROUTING_KEY_APP, message);
    }

    @Override
    public void sendAdminRequestDeleteComment(CommentDTO commentDTO) throws JsonProcessingException {
        String messageText = "There is a new request for removing the comment.\nDoctor email: " + commentDTO.getDoctorEmail() + ".\nPatient email: " + commentDTO.getPatientEmail() + ".\nDate: " + reformatDate(commentDTO.getDate()) + ".\nRating: " + commentDTO.getRating() + ".\ncomment body: " + commentDTO.getBody() + ".\nComment id: " + commentDTO.getCommentId() + ".\nReason: " + commentDTO.getReason();
        String message = ow.writeValueAsString(MessageDTO.builder().receiverEmail(commentDTO.getAdminEmail()).message(messageText).subject("Request for removing a comment").build());
        rabbitTemplate.convertAndSend(AMQPConfiguration.EXCHANGE_NAME, AMQPConfiguration.ROUTING_KEY_APP, message);
        rabbitTemplate.convertAndSend(AMQPConfiguration.EXCHANGE_NAME, AMQPConfiguration.ROUTING_KEY_EMAIL, message);
    }

    @Override
    public void sendDoctorCanceledAppointment(AppointmentDTO appointmentDTO) throws JsonProcessingException {
        String messageText = "The appointment was canceled by patient.\nDate: " + reformatDate(appointmentDTO.getStartDate()) + " - " + reformatDate(appointmentDTO.getEndDate()) + ".\nHospital: " + appointmentDTO.getHospitalName();
        String message = ow.writeValueAsString(MessageDTO.builder().receiverEmail(appointmentDTO.getDoctorEmail()).message(messageText).build());
        rabbitTemplate.convertAndSend(AMQPConfiguration.EXCHANGE_NAME, AMQPConfiguration.ROUTING_KEY_APP, message);
        messageText = "Hello " + rsService.getNameByEmail(appointmentDTO.getDoctorEmail()) + ", \n\n" + messageText + "\n\nBest regards,\neHealth team";
        message = ow.writeValueAsString(MessageDTO.builder().receiverEmail(appointmentDTO.getDoctorEmail()).message(messageText).subject("Canceled appointment").build());
        rabbitTemplate.convertAndSend(AMQPConfiguration.EXCHANGE_NAME, AMQPConfiguration.ROUTING_KEY_EMAIL, message);
    }

    @Override
    public void sendPatientAppointment(AppointmentDTO appointmentDTO, boolean accepted) throws JsonProcessingException {
        String acceptedMessage = accepted ? "accepted" : "declined";
        String subject = accepted ? "Accepted " : "Declined ";
        String messageText = "The appointment was " + acceptedMessage + " by doctor " + rsService.getNameByEmail(appointmentDTO.getDoctorEmail()) + "(" + rsService.getSpecialityByEmail(appointmentDTO.getDoctorEmail()) + ")" + ".\nDate: " + reformatDate(appointmentDTO.getStartDate()) + " - " + reformatDate(appointmentDTO.getEndDate()) + ".\nHospital: " + appointmentDTO.getHospitalName() + ".\nHospital address: " + rsService.getHospitalAddress(appointmentDTO.getHospitalName());
        String message = ow.writeValueAsString(MessageDTO.builder().receiverEmail(appointmentDTO.getPatientEmail()).message(messageText).build());
        rabbitTemplate.convertAndSend(AMQPConfiguration.EXCHANGE_NAME, AMQPConfiguration.ROUTING_KEY_APP, message);
        messageText = "Hello " + rsService.getNameByEmail(appointmentDTO.getPatientEmail()) + ", \n\n" + messageText + "\n\nBest regards,\neHealth team";
        message = ow.writeValueAsString(MessageDTO.builder().receiverEmail(appointmentDTO.getPatientEmail()).message(messageText).subject(subject + " request for the appointment").build());
        rabbitTemplate.convertAndSend(AMQPConfiguration.EXCHANGE_NAME, AMQPConfiguration.ROUTING_KEY_EMAIL, message);
    }

    private void sendNotification(AppointmentDTO appointmentDTO) throws JsonProcessingException {
        String messageText = "You have an upcoming appointment to the doctor " + rsService.getNameByEmail(appointmentDTO.getDoctorEmail()) + "(" + rsService.getSpecialityByEmail(appointmentDTO.getDoctorEmail()) + ")" + "!\nDate: " + reformatDate(appointmentDTO.getStartDate()) + " - " + reformatDate(appointmentDTO.getEndDate()) + ".\nHospital: " + appointmentDTO.getHospitalName() + ".\nHospital address: " + rsService.getHospitalAddress(appointmentDTO.getHospitalName());
        String message = ow.writeValueAsString(MessageDTO.builder().receiverEmail(appointmentDTO.getPatientEmail()).message(messageText).build());
        rabbitTemplate.convertAndSend(AMQPConfiguration.EXCHANGE_NAME, AMQPConfiguration.ROUTING_KEY_APP, message);
        messageText = "Hello " + rsService.getNameByEmail(appointmentDTO.getPatientEmail()) + ", \n\n" + messageText + "\n\nBest regards,\neHealth team";
        message = ow.writeValueAsString(MessageDTO.builder().receiverEmail(appointmentDTO.getPatientEmail()).message(messageText).subject("Reminder for the appointment").build());
        rabbitTemplate.convertAndSend(AMQPConfiguration.EXCHANGE_NAME, AMQPConfiguration.ROUTING_KEY_EMAIL, message);
    }

    @Scheduled(cron = "0 * * * * ?")
    public void sendReminder() throws JsonProcessingException {
        List<AppointmentDTO> appointments = rsService.getAllAppointments();
        for (AppointmentDTO appointment : appointments) {
            LocalDateTime startDate = LocalDateTime.parse(appointment.getStartDate());
            if (appointment.getStatus().equals("APPROVED") && appointment.getSentNotification().equals("false") && startDate.isBefore(LocalDateTime.now().plusHours(24))) {
                sendNotification(appointment);
                rsService.setSentNotification(appointment.getId());
            }
        }
    }

    private String reformatDate(String date){
        return date.replace("T", " ");
    }
}
