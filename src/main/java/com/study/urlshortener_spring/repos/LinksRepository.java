package com.study.urlshortener_spring.repos;

import com.study.urlshortener_spring.models.Links;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinksRepository extends CrudRepository<Links, Long> {
}
