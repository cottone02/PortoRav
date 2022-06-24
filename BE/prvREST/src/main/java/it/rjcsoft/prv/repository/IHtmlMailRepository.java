package it.rjcsoft.prv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.HtmlMail;
import it.rjcsoft.prv.model.HtmlMailPKEY;

@Repository
public interface IHtmlMailRepository extends JpaRepository<HtmlMail, HtmlMailPKEY> {

}
