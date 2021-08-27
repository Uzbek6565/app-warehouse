package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Attachment;
import com.example.appwarehouse.entity.AttachmentContent;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.repository.AttachmentContentRepository;
import com.example.appwarehouse.repository.AttachmentRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public Result uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = new Attachment(null, file.getOriginalFilename(), file.getSize(), file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent(null, file.getBytes(), savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("File uploaded", true, savedAttachment.getId());
    }

    public List<Attachment> getAllAttachments() {
        return attachmentRepository.findAll();
    }

    public Attachment getAttachmentById(Integer id) {
        if (attachmentRepository.existsById(id))
            return attachmentRepository.getById(id);
        return null;
    }


    public Result editAttachment(Integer id, Attachment attachment) {
        if (!attachmentRepository.existsById(id))
            return new Result("File not found", false);
        Attachment attachmentById = attachmentRepository.getById(id);
        attachmentById.setName(attachment.getName());
        attachmentById.setContentType(attachment.getContentType());
        attachmentById.setSize(attachment.getSize());
        attachmentRepository.save(attachment);
        return new Result("File is edited", true);
    }

    public Result deleteAttachment(Integer id) {
        if (!attachmentRepository.existsById(id))
            return new Result("File not found", false);
        attachmentRepository.deleteById(id);
        return new Result("File is deleted", true);
    }
}
