import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Band {
  private int id;
  private String name;
  private String description;

  public Band(String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }


  @Override
  public boolean equals(Object otherBand){
    if (!(otherBand instanceof Band)) {
      return false;
    } else {
      Band newBand = (Band) otherBand;
      return this.getName().equals(newBand.getName()) &&
             this.getId() == newBand.getId();
    }
  }

  public static List<Band> all() {
    String sql = "SELECT * FROM bands";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Band.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands(name, description) VALUES (:name, :description)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("description", description)
        .executeUpdate()
        .getKey();
    }
  }

  public static Band find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM bands where id=:id";
      Band band = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Band.class);
      return band;
    }
  }

  public void update(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE bands SET name = :newName, description = :newDescription WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void addVenue(Venue venue) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands_venues (venue_id, band_id) VALUES (:venue_id, :band_id)";
      con.createQuery(sql)
        .addParameter("venue_id", venue.getId())
        .addParameter("band_id", this.getId())
        .executeUpdate();
    }
  }

  public List<Venue> getVenues() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT venues.* FROM bands JOIN bands_venues ON (bands.id = bands_venues.band_id) JOIN venues ON (bands_venues.venue_id = venues.id) WHERE bands.id = :id";
      return con.createQuery(sql).addParameter("id", id).executeAndFetch(Venue.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String deleteQuery = "DELETE FROM bands WHERE id = :id;";
        con.createQuery(deleteQuery)
          .addParameter("id", id)
          .executeUpdate();

      String joinDeleteQuery = "DELETE FROM bands_venues WHERE band_id = :band_id";
        con.createQuery(joinDeleteQuery)
          .addParameter("band_id", this.getId())
          .executeUpdate();
    }
  }

  public static void clear(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM bands";
      con.createQuery(sql)
      .executeUpdate();
    }
  }

  public void update(String newName, String newDescription) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE bands SET name = :newName, description = :newDescription WHERE id = :id";
      this.name = newName;
      con.createQuery(sql)
      .addParameter("newName", newName)
      .addParameter("newDescription", newDescription)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
