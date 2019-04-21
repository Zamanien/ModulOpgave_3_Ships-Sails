package kea.shipsandsails.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kea.shipsandsails.models.ShipType;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

// Maybe this should only handle shipTypes, or it should be renamed to make it clear it interacts with multiple tables?
@Repository
public class ShipTypeRepo {

  @Autowired
  JdbcTemplate template;

  // Basically a lookup table?
  public Collection<ShipType> fetchShipTypes() {
    String sql = "SELECT * FROM ship_types";
    RowMapper<ShipType> rowMapper = new BeanPropertyRowMapper<>(ShipType.class);
    return template.query(sql, rowMapper);
  }

}
