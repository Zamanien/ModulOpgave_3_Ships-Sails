package kea.shipsandsails.repositories;

import kea.shipsandsails.models.Ship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.ArrayList;
import java.sql.Types;

// Maybe this should only handle shipTypes, or it should be renamed to make it clear it interacts with multiple tables?
@Repository
public class ShipRepo {

  @Autowired
  JdbcTemplate template;

  public Collection<Ship> fetchScenarioShips(String scenario) {
    String sql = "SELECT * FROM ship WHERE scenario=?";
    // template.
    RowMapper<Ship> rowMapper = new BeanPropertyRowMapper<>(Ship.class);
    String[] args = {scenario};    // Converting to array because template query wants arrays
    int[] types = {Types.VARCHAR}; // Converting to array because template query wants arrays
    return template.query(sql, args, types, rowMapper);
  }
}
