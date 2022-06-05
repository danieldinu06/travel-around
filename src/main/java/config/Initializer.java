package config;

import model.Hotel;
import model.Restaurant;
import model.TouristAttraction;
import service.ApplicationService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;


@WebListener
public class Initializer implements ServletContextListener {
    ApplicationService applicationService;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        applicationService = ApplicationService.getInstance();
        List<TouristAttraction> touristAttractions = applicationService.touristAttractionDao.getAll();

        if (touristAttractions.size() != 0) return;


        List<String> attractionImages = new ArrayList<>();

        TouristAttraction branCastle = new TouristAttraction("Bran Castle", "", (float)5.0);

        Hotel conaculBratescu = new Hotel("Conacul Bratescu", "", (float)4.3, 30, branCastle);
        Hotel casaMedievala = new Hotel("Pensiunea Casa Medievala", "", (float)4.6, 25, branCastle);

        Restaurant torzburg = new Restaurant("Restaurant Torzburg", (float)4.6, branCastle);
        Restaurant trattoriaAlGallo = new Restaurant("Trattoria Al Gallo", (float)4.9, branCastle);

        branCastle.addHotel(conaculBratescu);
        branCastle.addHotel(casaMedievala);
        branCastle.addRestaurant(torzburg);
        branCastle.addRestaurant(trattoriaAlGallo);


        for (int i = 1; i <= 5; i++) {
            attractionImages.add(String.format("../../static/img/bran_castle/attraction/bran%d.jpg", i));

            conaculBratescu.addImage(String.format("../../static/img/bran_castle/hotels/1/hotel%d.jpg", i));
            casaMedievala.addImage(String.format("../../static/img/bran_castle/hotels/2/hotel%d.jpg", i));

            torzburg.addImage(String.format("../../static/img/bran_castle/restaurants/1/restaurant%d.jpg", i));
            trattoriaAlGallo.addImage(String.format("../../static/img/bran_castle/restaurants/2/restaurant%d.jpg", i));
        }

        branCastle.setImages(attractionImages);

        applicationService.touristAttractionDao.add(branCastle);

        applicationService.hotelDao.add(conaculBratescu);
        applicationService.hotelDao.add(casaMedievala);

        applicationService.restaurantDao.add(torzburg);
        applicationService.restaurantDao.add(trattoriaAlGallo);
    }
}
