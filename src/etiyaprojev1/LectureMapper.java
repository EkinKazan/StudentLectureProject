package etiyaprojev1;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class LectureMapper implements RowMapper<Lecture> {

   public Lecture mapRow(ResultSet rs, int rowNum) throws SQLException {
	   	  Lecture lecture = new Lecture();
	   	  lecture.setLectureId(rs.getInt("id"));
	   	  lecture.setLectureName(rs.getString("name"));
	   	  lecture.setLectureInstructure(rs.getString("instructure"));
	      return lecture;
	   }
}