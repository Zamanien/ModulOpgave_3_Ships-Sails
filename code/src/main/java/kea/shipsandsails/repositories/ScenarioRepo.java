package kea.shipsandsails.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Types;

import kea.shipsandsails.models.Scenario;

@Repository
public class ScenarioRepo {

  @Autowired
  JdbcTemplate template;

  // Basically a lookup table?
  public Scenario fetchScenario(String scenario) {
    String sql = "SELECT * FROM scenarios WHERE name=?";

    RowMapper<Scenario> rowMapper = new BeanPropertyRowMapper<>(Scenario.class);
    return template.queryForObject(sql, rowMapper, scenario);
  }
    
}
