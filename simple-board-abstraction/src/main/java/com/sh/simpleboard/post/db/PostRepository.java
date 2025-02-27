package com.sh.simpleboard.post.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    // select * from post where id = ? and status = ? order by id desc limit 1;
    public Optional<PostEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, String status);

    Page<PostEntity> findByStatusOrderByIdDesc(String status, Pageable pageable);
}
