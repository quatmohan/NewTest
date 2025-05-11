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
        claim.setClaimId(rs.getLong("claim_id"));
        claim.setMemberId(rs.getString("member_id"));
        claim.setClaimNumber(rs.getString("claim_number"));
        claim.setClaimDate(rs.getString("claim_date"));
        claim.setClaimStatus(rs.getString("claim_status"));
        claim.setClaimAmount(rs.getDouble("claim_amount"));
        claim.setProviderId(rs.getString("provider_id"));
        claim.setServiceType(rs.getString("service_type"));
        claim.setCreatedDate(rs.getString("created_date"));
        claim.setUpdatedDate(rs.getString("updated_date"));
        return claim;
    };

    public Claim findById(Long claimId) {
        String sql = "SELECT * FROM claims WHERE claim_id = ?";
        return jdbcTemplate.queryForObject(sql, claimRowMapper, claimId);
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