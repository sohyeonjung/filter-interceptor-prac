package com.sh.simpleboard.common;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Api<T> {
    private T body;

    private Pagination pagination;
}
