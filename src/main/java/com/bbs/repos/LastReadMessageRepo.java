package com.bbs.repos;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bbs.entites.LastReadMessage;

@Repository
public interface LastReadMessageRepo extends JpaRepository<LastReadMessage, BigInteger>{

	@Query(value=
			"""
			select id, max(msgid) from (
					(select mf.id as id, m.id as msgid from messages m, message_forums mf, 
					last_read_messages lrm, bbs_user_details ud
					where m.id = lrm.message_id and mf.id = m.message_forum_id and 
					mf.id = lrm.message_forum_id and 
					lrm.bbs_user_details_id = ud.id and
					ud.id = :userDetailsId and mf.id> :forumId)
					union all select mf.id as id, 0 as msgid from messages m, message_forums mf 
					where mf.id>:forumId and m.message_forum_id = mf.id)
					group  by id limit 1		
			""", nativeQuery=true)
	public List<BigInteger[]> getNextForumWithUnreadMessages(BigInteger userDetailsId, BigInteger forumId);
}
