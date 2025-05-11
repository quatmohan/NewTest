package com.automation.repository;

import com.automation.model.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EnrollmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Enrollment> enrollmentRowMapper = (ResultSet rs, int rowNum) -> {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(rs.getLong("id"));
        enrollment.setMemberId(rs.getString("member_id"));
        enrollment.setFirstName(rs.getString("first_name"));
        enrollment.setLastName(rs.getString("last_name"));
        enrollment.setDateOfBirth(rs.getString("date_of_birth"));
        enrollment.setPlanId(rs.getString("plan_id"));
        enrollment.setEffectiveDate(rs.getString("effective_date"));
        enrollment.setTerminationDate(rs.getString("termination_date"));
        enrollment.setStatus(rs.getString("status"));
        return enrollment;
    };

    public Enrollment findById(Long id) {
        String sql = "SELECT * FROM enrollments WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, enrollmentRowMapper, id);
    }

    public List<Enrollment> findByMemberId(String memberId) {
        String sql = "SELECT * FROM enrollments WHERE member_id = ?";
        return jdbcTemplate.query(sql, enrollmentRowMapper, memberId);
    }
} 