package com.beyond.basic.b2_board.post.repository;

import com.beyond.basic.b2_board.post.domain.Post;
import jakarta.persistence.Entity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByDelYn(String delYn);
//    List<Post> findAllByAuthorIdAndDelYn(Long authorId, String delYn);

//    jpql을 활용한 일반 inner join : N+1 문제 해결X
//    jpql과 raw쿼리의 차이점
//    1) jpql을 사용한 inner join시 별도의 on조건 필요X
//    2) jpql은 컴파일타임에 에러를 check
//    순수 raw쿼리 : select p.* from post p inner join author a on a.id=p.author_id;
    @Query("select p from Post p inner join p.author")
    List<Post> findAllInnerJoin();


//    jpql을 활용한 inner join(fetch) : N+1 문제 해결O
//    순수 raw쿼리 : select * from post p inner join author a on a.id=p.author_id;
    @Query("select p from Post p inner join fetch p.author")
    List<Post> findAllFetchInnerJoin();


//    Page객체 안에는 content(List<Post>), totalPages, totalElement 포함
//    Page<Post> findAllByDelYn(Pageable pageable, String delYn);

//    검색 + 페이징처리까지 할경우 아래와같이 매개변수 선언(순서 : Specification, Pageable - SimpleJpaRepository에서 순서가 정의되어있음)

//    @EntityGraph(attributePaths = "author") -> N+1일경우 이 어노테이션을 붙이면 fetch join이 자동적용
    Page<Post> findAll(Specification<Post> specification, Pageable pageable);

//    예약 글쓰기를 위한 findAllByAppointment 메서드 추가
    List<Post> findAllByAppointment(String appointment);
}
