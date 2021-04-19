package com.thanhxv.service;

import com.thanhxv.model.Tag;

import java.util.List;

public interface TagService {
    public Tag addTag(Tag tag);
    public List<Tag> getTags();
}
