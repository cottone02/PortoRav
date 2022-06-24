package it.rjcsoft.prv.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;

import it.rjcsoft.prv.model.QUserEntity;
import it.rjcsoft.prv.model.UserEntity;
@Repository
public interface IUsersEntityRepository extends JpaRepository<UserEntity, Integer>,
		QuerydslPredicateExecutor<UserEntity>, QuerydslBinderCustomizer<QUserEntity> {

	@Override
	default void customize(QuerydslBindings bindings, QUserEntity userEntity) {

		bindings.bind(String.class)
				.first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

		bindings.bind(userEntity.createdDate).all((path, value) -> {
			List<? extends Date> dates = new ArrayList<>(value);
			if (dates.size() == 1) {
				return Optional.of(path.eq(dates.get(0)));
			} else {
				Date from = dates.get(0);
				Date to = dates.get(1);
				return Optional.of(path.between(from, to));
			}
		});

	}

}
