package config;

import model.Hotel;
import model.Restaurant;
import model.TouristAttraction;
import service.ApplicationService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebListener
public class Initializer implements ServletContextListener {
    ApplicationService applicationService;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        applicationService = ApplicationService.getInstance();
        List<TouristAttraction> touristAttractions = applicationService.touristAttractionDao.getAll();

        if (touristAttractions.size() != 0) return;


        List<String> branImages = new ArrayList<>();
        List<String> athenaeumImages = new ArrayList<>();

        /*
        *       TOURIST ATTRACTIONS
        */

        TouristAttraction branCastle = new TouristAttraction("Bran Castle", "Bran Castle is a slender stronghold with tile roofs erected in the 14th century, located not far from the city of Brasov in Romania. It commands the green hills on the edge of the Little Carpathians, a subrange of the great chain arcing across Romania.\n" +
                "\n" +
                "The Bran castle was built in the 14th century, a stronghold to protect the villagers and landowners of the plains beyond the Bucegi Mountains, although it is most famous for being the location of the famous vampire, Dracula.\n" +
                "\n" +
                "\n" +
                "It is the perfect setting for a vampire-lord. Tall, turreted, red-roofed, with a massive guard tower, it has thick walls with narrow openings for archers and cannons, iron grates over the portals, and secret passageways.\n" +
                "\n" +
                "The Bran castle had to be mended and refurbished over centuries of struggle with the Ottoman Empire. Once the two spacious inner courtyards had been flower gardens, and the white-washed walls of the high-ceilinged rooms had been covered with brilliant-hued carpets from the northern reaches of Moldavia.", (float)4.4);

        TouristAttraction romanianAthenaeum = new TouristAttraction("Romanian Athenaeum", "The Romanian Athenaeum, nicknamed the Romanian temple of arts, is an architectural jewel whose uniqueness and merits were recognized by the fact that it was classified as a historical monument in 2004 and it is part of the European heritage since 2007, which is granted to sites that have a strong symbolic value for European history and heritage. Located on Calea Victoriei, the Romanian Athenaeum is an important cultural center and a must see architectural gem, but is also defined by the fact that it is one of the oldest and most important cultural institutions in Bucharest.\n" +
                "This edifice hosts remarkable events, such as George Enescu’s classic music festival, an international event for the lovers of classic music.", (float)4.8);


        /*
        *        SET URL AND LOCATION
        */

        branCastle.setUrl("http://www.castelulbran.ro");
        branCastle.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2795.7603746299565!2d25.364975015939425!3d45.51490217910141!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b347e5a415de31%3A0xcf922792d921ab7f!2sCastelul%20Bran!5e0!3m2!1sro!2sro!4v1654683774551!5m2!1sro!2sro");

        romanianAthenaeum.setUrl("https://www.fge.org.ro/");
        romanianAthenaeum.setLocation("https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d11394.430915949784!2d26.0970678!3d44.4412082!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x5a73af5fe07876f2!2sAteneul%20Rom%C3%A2n!5e0!3m2!1sro!2sro!4v1654760562146!5m2!1sro!2sro");


        /*
        *       SET SCHEDULE
        */

        Map<String, String> branSchedule = new HashMap<>();
        branSchedule.put("monday", "12-18");
        branSchedule.put("tuesday", "9-18");
        branSchedule.put("wednesday", "9-18");
        branSchedule.put("thursday", "9-18");
        branSchedule.put("friday", "9-18");

        branCastle.setSchedule(branSchedule);


        Map<String, String> romanianAthenaeumSchedule = new HashMap<>();
        romanianAthenaeumSchedule.put("monday", "OPEN 24 Hours");
        romanianAthenaeumSchedule.put("tuesday", "OPEN 24 Hours");
        romanianAthenaeumSchedule.put("wednesday", "OPEN 24 Hours");
        romanianAthenaeumSchedule.put("thursday", "OPEN 24 Hours");
        romanianAthenaeumSchedule.put("friday", "OPEN 24 Hours");

        romanianAthenaeum.setSchedule(romanianAthenaeumSchedule);


        /*
        *       HOTELS
        */

        //  Bran Castel

        Hotel conaculBratescu = new Hotel("Conacul Bratescu", "", (float)4.8, 30, branCastle);
        Hotel casaMedievala = new Hotel("Pensiunea Casa Medievala", "", (float)4.6, 25, branCastle);

        branCastle.addHotel(conaculBratescu);
        branCastle.addHotel(casaMedievala);

        // Romanian Athenaeum

        Hotel sunriseApartments = new Hotel("Sunrise Apartments", "", (float)5.0, 52, romanianAthenaeum);
        Hotel ussrApartments = new Hotel("USSR Apartments", "", (float)4.8, 46, romanianAthenaeum);
        
        romanianAthenaeum.addHotel(sunriseApartments);
        romanianAthenaeum.addHotel(ussrApartments);

        /*
        *       RESTAURANTS 
        */

        //  Bran Castle
        
        Restaurant torzburg = new Restaurant("Restaurant Torzburg", (float)4.4, branCastle);
        Restaurant trattoriaAlGallo = new Restaurant("Trattoria Al Gallo", (float)4.0, branCastle);

        branCastle.addRestaurant(torzburg);
        branCastle.addRestaurant(trattoriaAlGallo);

        // Romanian Athenaeum
        


        for (int i = 1; i <= 5; i++) {
            branImages.add(String.format("../../static/img/bran_castle/attraction/bran%d.jpg", i));
            athenaeumImages.add(String.format("../../static/img/romanian_athenaeum/attraction/ateneu%d.jpg", i));

            conaculBratescu.addImage(String.format("../../static/img/bran_castle/hotels/1/hotel%d.jpg", i));
            casaMedievala.addImage(String.format("../../static/img/bran_castle/hotels/2/hotel%d.jpg", i));

            sunriseApartments.addImage(String.format("../../static/img/romanian_athenaeum/hotels/1/hotel%d.jpg", i));
            ussrApartments.addImage(String.format("../../static/img/romanian_athenaeum/hotels/2/hotel%d.jpg", i));

            torzburg.addImage(String.format("../../static/img/bran_castle/restaurants/1/restaurant%d.jpg", i));
            trattoriaAlGallo.addImage(String.format("../../static/img/bran_castle/restaurants/2/restaurant%d.jpg", i));
        }

        branCastle.setImages(branImages);
        romanianAthenaeum.setImages(athenaeumImages);

        applicationService.touristAttractionDao.add(branCastle);
        applicationService.touristAttractionDao.add(romanianAthenaeum);

        applicationService.hotelDao.add(conaculBratescu);
        applicationService.hotelDao.add(casaMedievala);
        applicationService.hotelDao.add(sunriseApartments);
        applicationService.hotelDao.add(ussrApartments);

        applicationService.restaurantDao.add(torzburg);
        applicationService.restaurantDao.add(trattoriaAlGallo);
    }
}
