package com.sh.memorydb.entity;

//db의 고유한 값
public interface PrimaryKey {
    void setId(Long id);
    Long getId();
}
