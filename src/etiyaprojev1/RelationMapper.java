package etiyaprojev1;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class RelationMapper implements RowMapper<Relation> {

   public Relation mapRow(ResultSet rs, int rowNum) throws SQLException {
	   	  Relation relation = new Relation();
	   	  relation.setStudentId(rs.getInt("student_id"));
	   	  relation.setLectureId(rs.getInt("lecture_id"));
	      return relation;
	   }
}