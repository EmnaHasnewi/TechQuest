package com.example.ManagementApp.service;

import com.example.ManagementApp.dto.RequestDTO;
import com.example.ManagementApp.entity.Request;
import com.example.ManagementApp.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private EmailService emailService;

   /* public List<RequestDTO> getAllRequests() {
        return requestRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }*/
   public List<RequestDTO> getAllRequests() {
       return requestRepository.findAllByOrderByIdDesc().stream()
               .map(this::convertToDTO)
               .collect(Collectors.toList());
   }

    private RequestDTO convertToDTO(Request request) {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setId(request.getId());
        requestDTO.setEquipmentType(request.getEquipmentType());
        requestDTO.setJustification(request.getJustification());
        requestDTO.setAdditionalInfo(request.getAdditionalInfo());
        requestDTO.setState(request.getState());

        if (request.getUser() != null) {
            requestDTO.setUsername(request.getUser().getUsername());
            requestDTO.setUserEmail(request.getUser().getEmail()); // Set userEmail

        }

        return requestDTO;
    }

    public List<Request> getRequestsByUserId(Long userId) {
        return requestRepository.findByUserId(userId);
    }

    public Request saveRequest(Request request) {
        System.out.println("Equipment Type: " + request.getEquipmentType());
        System.out.println("Justification: " + request.getJustification());
        System.out.println("Additional Info: " + request.getAdditionalInfo());
        System.out.println("User ID: " + request.getUserId());
        return requestRepository.save(request);
    }

    public void approveRequest(Long requestId) {
        Optional<Request> optionalRequest = requestRepository.findById(requestId);
        optionalRequest.ifPresent(request -> {
            request.setState("approved");
            requestRepository.save(request);
            sendApprovalEmail(request);
        });
    }

    public void refuseRequest(Long requestId) {
        Optional<Request> optionalRequest = requestRepository.findById(requestId);
        optionalRequest.ifPresent(request -> {
            request.setState("refused");
            requestRepository.save(request);
            sendRefusalEmail(request);
        });
    }

    private void sendApprovalEmail(Request request) {
        if (request.getUser() != null && request.getUser().getEmail() != null) {
            String to = request.getUser().getEmail();
            String subject = "Request Approved";
            String text = "Hi,\n\n" +
                    "Your request for " + request.getEquipmentType() + " has been approved.\n\n" +
                    "Best regards,\n" +
                    "Your IT Team";
            emailService.sendEmail(to, subject, text);
        }
    }

    private void sendRefusalEmail(Request request) {
        if (request.getUser() != null && request.getUser().getEmail() != null) {
            String to = request.getUser().getEmail();
            String subject = "Request Refused";
            String text = "Hi,\n\n" +
                    "Your request for " + request.getEquipmentType() + " has been refused.\n\n" +
                    "Best regards,\n" +
                    "Your IT Team";
            emailService.sendEmail(to, subject, text);
        }
    }

}
