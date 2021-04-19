package com.thanhxv.controller;

import com.thanhxv.model.Tag;
import com.thanhxv.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping()
    public ResponseEntity<?> getTags() {
        List<Tag> tagList = tagService.getTags().isEmpty() ? new ArrayList<Tag>() : tagService.getTags();
        return new ResponseEntity<>(tagList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addTag(@RequestBody Tag tag) {
        Tag tagResp = tagService.addTag(tag);
        return new ResponseEntity<>(tagResp, HttpStatus.OK);
    }
}

enum Response {
    SUCCESS("0000", "Thành Công");

    private String responseCode;
    private String responseMessage;

    private Response(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

}