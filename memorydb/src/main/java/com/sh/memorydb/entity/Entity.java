package com.sh.memorydb.entity;

import lombok.Getter;
import lombok.Setter;

public abstract class Entity implements PrimaryKey{

    @Getter
    @Setter
    protected Long id;
}
