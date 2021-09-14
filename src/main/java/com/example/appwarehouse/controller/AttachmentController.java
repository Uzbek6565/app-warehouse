package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Attachment;
import com.example.appwarehouse.message.Result;
import com.example.appwarehouse.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request){
        return attachmentService.uploadFile(request);
    }

    @GetMapping
    public List<Attachment> getAllAttachments(){
        return attachmentService.getAllAttachments();
    }

    @GetMapping("/upload/{id}")
    public Result getAttachmentById(@PathVariable Integer id){
        return attachmentService.getAttachmentById(id);
    }

    @PutMapping("/upload/{id}")
    public Result editAttachment(@PathVariable Integer id, @RequestBody Attachment attachment){
        return attachmentService.editAttachment(id, attachment);
    }

    @DeleteMapping("/upload/{id}")
    public Result deleteAttachment(@PathVariable Integer id){
        return attachmentService.deleteAttachment(id);
    }
}
