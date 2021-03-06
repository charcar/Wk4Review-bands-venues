import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public/");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/add-band", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("bandName");
      String description = request.queryParams("description");
      Band newBand = new Band(name, description);
      newBand.save();
      response.redirect("/");
      return null;
    });

    post("/add-venue", (request, response) -> {
      String name = request.queryParams("venueName");
      Venue newVenue = new Venue(name);
      newVenue.save();
      response.redirect("/");
      return null;
    });

    get("/bands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Band> bands = Band.all();
        if(bands.size() == 0) {
          bands = null;
        }
      model.put("bands", bands);
      model.put("template", "templates/bands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bands/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Integer bandId = Integer.parseInt(request.params(":id"));
      Band band = Band.find(bandId);
      List<Venue> venues = Venue.all();
        if(venues.size() == 0) {
          venues = null;
        }
      model.put("band", band);
      model.put("venues", band.notplayedVenues());
      model.put("bandVenues", band.getVenues());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params(":id")));
      String [] addVenues = request.queryParamsValues("bandVenues");
      ArrayList<Venue> bandVenue = new ArrayList<Venue>();
      if (addVenues != null) {
        for(String venue : addVenues) {
          band.addVenue(Integer.parseInt(venue));
        }
      }
      model.put("band", band);
      model.put("bandVenues", band.getVenues());
      model.put("venues", band.notplayedVenues());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bands/:id/update", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params(":id")));
      model.put("band", band);
      model.put("template", "templates/band-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params("id")));
      String name = request.queryParams("name");
      String description = request.queryParams("description");
      band.update(name, description);
      response.redirect("/bands");
      return null;
    });

    post("/bands/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params("id")));
      band.delete();
      response.redirect("/bands");
      return null;
    });
  }
}
