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

        TouristAttraction branCastle = new TouristAttraction("Bran Castle", "Bran Castle is a slender stronghold with tile roofs erected in the 14th century, located not far from the city of Brasov in Romania. It commands the green hills on the edge of the Little Carpathians, a subrange of the great chain arcing across Romania.\n" +
                "\n" +
                "The Bran castle was built in the 14th century, a stronghold to protect the villagers and landowners of the plains beyond the Bucegi Mountains, although it is most famous for being the location of the famous vampire, Dracula.\n" +
                "\n" +
                "\n" +
                "It is the perfect setting for a vampire-lord. Tall, turreted, red-roofed, with a massive guard tower, it has thick walls with narrow openings for archers and cannons, iron grates over the portals, and secret passageways.\n" +
                "\n" +
                "The Bran castle had to be mended and refurbished over centuries of struggle with the Ottoman Empire. Once the two spacious inner courtyards had been flower gardens, and the white-washed walls of the high-ceilinged rooms had been covered with brilliant-hued carpets from the northern reaches of Moldavia.", (float)5.0);

        branCastle.setUrl("http://www.castelulbran.ro");

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
