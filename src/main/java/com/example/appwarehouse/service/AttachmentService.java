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
}
