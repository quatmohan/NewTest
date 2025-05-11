package com.automation.repository;

import com.automation.model.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClaimRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Claim> claimRowMapper = (ResultSet rs, int rowNum) -> {
        Claim claim = new Claim();
        claim.setId(rs.getLong("id"));
        claim.setClaimNumber(rs.getString("claim_number"));
        claim.setMemberId(rs.getString("member_id"));
        claim.setServiceDate(rs.getString("service_date"));
        claim.setAmount(rs.getDouble("amount"));
        claim.setStatus(rs.getString("status"));
        claim.setProviderId(rs.getString("provider_id"));
        return claim;
    };

    public Claim findById(Long id) {
        String sql = "SELECT * FROM claims WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, claimRowMapper, id);
    }

    public List<Claim> findByMemberId(String memberId) {
        String sql = "SELECT * FROM claims WHERE member_id = ?";
        return jdbcTemplate.query(sql, claimRowMapper, memberId);
    }

    public List<Claim> findAll() {
        String sql = "SELECT * FROM claims";
        return jdbcTemplate.query(sql, claimRowMapper);
    }
} 