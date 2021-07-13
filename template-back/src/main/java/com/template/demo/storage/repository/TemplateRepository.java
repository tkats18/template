package com.template.demo.storage.repository;

import com.template.demo.storage.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<Template,Integer> {

    List<Template> findByKeywordLike(String keyword);

    Template findByKeyword(String keyWord);

}
