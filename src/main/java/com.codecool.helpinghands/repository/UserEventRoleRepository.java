package com.codecool.helpinghands.repository;

import com.codecool.helpinghands.model.Event;
import com.codecool.helpinghands.model.User;
import com.codecool.helpinghands.model.UserEventRole;
import org.hibernate.sql.Delete;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.From;
import javax.transaction.Transactional;

import static org.hibernate.hql.internal.antlr.SqlTokenTypes.FROM;
import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface UserEventRoleRepository extends JpaRepository<UserEventRole, Integer>  {
    UserEventRole findByUserAndEvent(User user, Event event);
    @Modifying
    @Transactional
    @Query("DELETE FROM UserEventRole uer where uer.event.eventId = :eventId AND uer.user.userId = :userId")
    void deleteUserEventRoleByEventId(
            @Param("eventId") int eventId,
            @Param("userId") int userId
    );
}



