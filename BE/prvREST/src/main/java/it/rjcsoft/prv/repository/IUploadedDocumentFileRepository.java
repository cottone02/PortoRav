package it.rjcsoft.prv.repository;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.rjcsoft.prv.model.QUploadedDocumentFile;
import it.rjcsoft.prv.model.UploadedDocumentFile;
import it.rjcsoft.prv.model.UploadedDocumentFilePKEY;

@Repository
public interface IUploadedDocumentFileRepository extends JpaRepository<UploadedDocumentFile, UploadedDocumentFilePKEY>,
QuerydslPredicateExecutor<UploadedDocumentFile>, QuerydslBinderCustomizer<QUploadedDocumentFile>{
	
	@Override
    default void customize(QuerydslBindings bindings, QUploadedDocumentFile uploadedDocumentFile) {

        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
	
	public Optional<UploadedDocumentFile> findByIdDocumentAndFileName(int id, String name);
	
	@Modifying
    @Query("DELETE FROM uploaded_documents_files WHERE id_document = :id")
	public void deleteAllById(int id);

	@Query("FROM uploaded_documents_files where id_document=:id")    
	List<UploadedDocumentFile> findAllFile(@Param("id")int id);
	

}
