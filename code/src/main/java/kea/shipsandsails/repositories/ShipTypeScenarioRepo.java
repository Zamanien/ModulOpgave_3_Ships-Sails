package kea.shipsandsails.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

// Maybe this should only handle shipTypes, or it should be renamed to make it clear it interacts with multiple tables?
@Repository
public class ShipTypeScenarioRepo {

  @Autowired
  JdbcTemplate template;

  // Basically a lookup table?
  public Collection<Map<String, String>> fetchShipTypes() {
    String sql = "";
    // template.
    Collection<Map<String,String>> shipTypes = new ArrayList<Map<String, String>>();
    
    return shipTypes;
  }

  // Also basically a lookup table?
  // Since there's no model for scenario, maybe we should just 
  public Map<String, String> fetchScenario(String scenario) {
    String sql = "";
    // template.
    //scenario = ;
    return new HashMap<String, String>();
  }
    
}
