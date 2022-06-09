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
        List<String> danubeDeltaImages = new ArrayList<>();

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

        TouristAttraction danubeDelta = new TouristAttraction("Danube Delta", "The Danube Delta is Europe’s largest remaining natural wetland – a truly unique ecosystem. The delta region includes extensive examples of unaltered rivers, lakes, reed beds, marshes, steppes, dunes, sandbars, coasts, lagoons, salt marshes and climax forests. Local communities in the Danube Delta (the Danube Delta Sub-Basin District supports more than one million people) have little knowledge and limited capacity to use ecosystems in an economically and ecologically sustainable way, directly impacting living standards and biodiversity. With the delta acting as a historic crossroads, this is an extremely diverse region in terms of nationalities, with each preserving its customs and traditions.", (float)4.7);

        /*
         *        SET URL AND LOCATION
         */

        //  Bran
        branCastle.setUrl("http://www.castelulbran.ro");
        branCastle.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2795.7603746299565!2d25.364975015939425!3d45.51490217910141!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b347e5a415de31%3A0xcf922792d921ab7f!2sCastelul%20Bran!5e0!3m2!1sro!2sro!4v1654683774551!5m2!1sro!2sro");

        //  Athenaeum
        romanianAthenaeum.setUrl("https://www.fge.org.ro/");
        romanianAthenaeum.setLocation("https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d11394.430915949784!2d26.0970678!3d44.4412082!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x5a73af5fe07876f2!2sAteneul%20Rom%C3%A2n!5e0!3m2!1sro!2sro!4v1654760562146!5m2!1sro!2sro");

        // Danube Delta
        danubeDelta.setUrl("https://www.info-delta.ro/delta-dunarii-17/");
        danubeDelta.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d13381.537522669758!2d29.305103827361!3d45.16438472695206!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b9c353489712f1%3A0xebbd01956395baca!2sDelta%20Dun%C4%83rii!5e0!3m2!1sro!2sro!4v1654789116898!5m2!1sro!2sro");

        /*
         *       SET SCHEDULE
         */

        //  Bran
        Map<String, String> branSchedule = new HashMap<>();
        branSchedule.put("monday", "12-18");
        branSchedule.put("tuesday", "9-18");
        branSchedule.put("wednesday", "9-18");
        branSchedule.put("thursday", "9-18");
        branSchedule.put("friday", "9-18");

        branCastle.setSchedule(branSchedule);


        //  Athenaeum
        Map<String, String> romanianAthenaeumSchedule = new HashMap<>();
        romanianAthenaeumSchedule.put("monday", "12-21:30");
        romanianAthenaeumSchedule.put("tuesday", "12-21:30");
        romanianAthenaeumSchedule.put("wednesday", "12-21:30");
        romanianAthenaeumSchedule.put("thursday", "12-21:30");
        romanianAthenaeumSchedule.put("friday", "12-21:30");

        romanianAthenaeum.setSchedule(romanianAthenaeumSchedule);


        // Danube Delta
        Map<String, String> danubeDeltaSchedule = new HashMap<>();
        danubeDeltaSchedule.put("monday", "OPEN 24 Hours");
        danubeDeltaSchedule.put("tuesday", "OPEN 24 Hours");
        danubeDeltaSchedule.put("wednesday", "OPEN 24 Hours");
        danubeDeltaSchedule.put("thursday", "OPEN 24 Hours");
        danubeDeltaSchedule.put("friday", "OPEN 24 Hours");

        danubeDelta.setSchedule(danubeDeltaSchedule);


        /*
         *       HOTELS
         */

        //  Bran Castel

        Hotel conaculBratescu = new Hotel("Conacul Bratescu", "", (float)4.8, 30, branCastle);
        Hotel casaMedievala = new Hotel("Pensiunea Casa Medievala", "", (float)4.6, 25, branCastle);
        Hotel transylvaniaMountainExclusive = new Hotel("Transylvania Mountain Exclusive", "", (float)5.0, 44, branCastle);
        Hotel branChalet = new Hotel("Bran Chalet", "", (float)4.3, 24, branCastle);

        branCastle.addHotel(conaculBratescu);
        branCastle.addHotel(casaMedievala);
        branCastle.addHotel(transylvaniaMountainExclusive);
        branCastle.addHotel(branChalet);

        // Romanian Athenaeum

        Hotel sunriseApartments = new Hotel("Sunrise Apartments", "", (float)5.0, 52, romanianAthenaeum);
        Hotel ussrApartments = new Hotel("USSR Apartments", "", (float)4.8, 46, romanianAthenaeum);
        Hotel atheneePalaceHilton = new Hotel("Athenee Palace Hilton", "", (float)4.6, 38, romanianAthenaeum);
        Hotel parliament = new Hotel("Hotel Parliament", "", (float)4.2, 26, romanianAthenaeum);

        romanianAthenaeum.addHotel(sunriseApartments);
        romanianAthenaeum.addHotel(ussrApartments);
        romanianAthenaeum.addHotel(atheneePalaceHilton);
        romanianAthenaeum.addHotel(parliament);

        // Danube Delta

        Hotel califarulAlb = new Hotel("Hotel Califarul Alb", "", (float)4.3, 23, danubeDelta);
        Hotel pensiuneaSharaiman = new Hotel("Pensiunea Sharaiman", "", (float)4.5, 34, danubeDelta);
        Hotel casaDianas = new Hotel("Casa Dianas", "", (float)4.6, 42, danubeDelta);
        Hotel pensiuneaDanubiu = new Hotel("Pensiunea Danubiu", "", (float)4.0, 30, danubeDelta);

        danubeDelta.addHotel(califarulAlb);
        danubeDelta.addHotel(pensiuneaSharaiman);
        danubeDelta.addHotel(casaDianas);
        danubeDelta.addHotel(pensiuneaDanubiu);

        /*
         *       RESTAURANTS
         */

        //  Bran Castle
        
        Restaurant torzburg = new Restaurant("Restaurant Torzburg", (float)4.4, branCastle);
        Restaurant trattoriaAlGallo = new Restaurant("Trattoria Al Gallo", (float)4.0, branCastle);
        Restaurant laLupi = new Restaurant("Restaurant La Lupi", (float)4.1, branCastle);
        Restaurant laTrasuraCuBucate = new Restaurant("La Trasura cu Bucate", (float)4.9, branCastle);

        branCastle.addRestaurant(torzburg);
        branCastle.addRestaurant(trattoriaAlGallo);
        branCastle.addRestaurant(laLupi);
        branCastle.addRestaurant(laTrasuraCuBucate);

        // Romanian Athenaeum
        
        Restaurant tuya = new Restaurant("Restaurant Tuya", (float)4.7, romanianAthenaeum);
        Restaurant tratorriaIlCalcio = new Restaurant("Trattoria Il Calcio Ateneu", (float)4.2, romanianAthenaeum);
        Restaurant relaisChateauxBistro = new Restaurant("Relais & Chateaux Bistro Ateneu", (float)4.6, romanianAthenaeum);
        Restaurant cafeAthenee = new Restaurant("Cafe Athenee", (float)4.5, romanianAthenaeum);

        romanianAthenaeum.addRestaurant(tuya);
        romanianAthenaeum.addRestaurant(tratorriaIlCalcio);
        romanianAthenaeum.addRestaurant(relaisChateauxBistro);
        romanianAthenaeum.addRestaurant(cafeAthenee);

        // Danube Delta

        Restaurant cherhanaResort = new Restaurant("Cherhana Resort", (float)4.4, danubeDelta);
        Restaurant laLiman = new Restaurant("La Liman", (float)4.2, danubeDelta);
        Restaurant restaurantCentral = new Restaurant("Restaurant Central", (float)4.0, danubeDelta);
        Restaurant ivanPescar = new Restaurant("Ivan Pescar - Fish bar", (float)4.7, danubeDelta);

        danubeDelta.addRestaurant(cherhanaResort);
        danubeDelta.addRestaurant(laLiman);
        danubeDelta.addRestaurant(restaurantCentral);
        danubeDelta.addRestaurant(ivanPescar);

        /*
         *      IMAGES
         */

        for (int i = 1; i <= 5; i++) {
            //  Bran
            branImages.add(String.format("../../static/img/bran_castle/attraction/bran%d.jpg", i));

            conaculBratescu.addImage(String.format("../../static/img/bran_castle/hotels/1/hotel%d.jpg", i));
            casaMedievala.addImage(String.format("../../static/img/bran_castle/hotels/2/hotel%d.jpg", i));
            transylvaniaMountainExclusive.addImage(String.format("../../static/img/bran_castle/hotels/3/hotel%d.jpg", i));
            branChalet.addImage(String.format("../../static/img/bran_castle/hotels/4/hotel%d.jpg", i));

            torzburg.addImage(String.format("../../static/img/bran_castle/restaurants/1/restaurant%d.jpg", i));
            trattoriaAlGallo.addImage(String.format("../../static/img/bran_castle/restaurants/2/restaurant%d.jpg", i));
            laLupi.addImage(String.format("../../static/img/bran_castle/restaurants/3/restaurant%d.jpg", i));
            laTrasuraCuBucate.addImage(String.format("../../static/img/bran_castle/restaurants/4/restaurant%d.jpg", i));


            // Athenaeum
            athenaeumImages.add(String.format("../../static/img/romanian_athenaeum/attraction/ateneu%d.jpg", i));

            sunriseApartments.addImage(String.format("../../static/img/romanian_athenaeum/hotels/1/hotel%d.jpg", i));
            ussrApartments.addImage(String.format("../../static/img/romanian_athenaeum/hotels/2/hotel%d.jpg", i));
            atheneePalaceHilton.addImage(String.format("../../static/img/romanian_athenaeum/hotels/3/hotel%d.jpg", i));
            parliament.addImage(String.format("../../static/img/romanian_athenaeum/hotels/4/hotel%d.jpg", i));

            tuya.addImage(String.format("../../static/img/romanian_athenaeum/restaurants/1/restaurant%d.jpg", i));
            tratorriaIlCalcio.addImage(String.format("../../static/img/romanian_athenaeum/restaurants/2/restaurant%d.jpg", i));
            relaisChateauxBistro.addImage(String.format("../../static/img/romanian_athenaeum/restaurants/3/restaurant%d.jpg", i));
            cafeAthenee.addImage(String.format("../../static/img/romanian_athenaeum/restaurants/4/restaurant%d.jpg", i));


            // Danube Delta
            danubeDeltaImages.add(String.format("../../static/img/danube_delta/attraction/delta%d.jpg", i));

            califarulAlb.addImage(String.format("../../static/img/danube_delta/hotels/1/hotel%d.jpg", i));
            pensiuneaSharaiman.addImage(String.format("../../static/img/danube_delta/hotels/2/hotel%d.jpg", i));
            casaDianas.addImage(String.format("../../static/img/danube_delta/hotels/3/hotel%d.jpg", i));
            pensiuneaDanubiu.addImage(String.format("../../static/img/danube_delta/hotels/4/hotel%d.jpg", i));

            cherhanaResort.addImage(String.format("../../static/img/danube_delta/restaurants/1/restaurant%d.jpg", i));
            laLiman.addImage(String.format("../../static/img/danube_delta/restaurants/2/restaurant%d.jpg", i));
            restaurantCentral.addImage(String.format("../../static/img/danube_delta/restaurants/3/restaurant%d.jpg", i));
            ivanPescar.addImage(String.format("../../static/img/danube_delta/restaurants/4/restaurant%d.jpg", i));
        }

        /*
         *      ADD TO DATABASE
         */

        //  Bran
        branCastle.setImages(branImages);
        applicationService.touristAttractionDao.add(branCastle);

        applicationService.hotelDao.add(conaculBratescu);
        applicationService.hotelDao.add(casaMedievala);
        applicationService.hotelDao.add(transylvaniaMountainExclusive);
        applicationService.hotelDao.add(branChalet);

        applicationService.restaurantDao.add(torzburg);
        applicationService.restaurantDao.add(trattoriaAlGallo);
        applicationService.restaurantDao.add(laLupi);
        applicationService.restaurantDao.add(laTrasuraCuBucate);



        //  Athenaeum
        romanianAthenaeum.setImages(athenaeumImages);
        applicationService.touristAttractionDao.add(romanianAthenaeum);

        applicationService.hotelDao.add(sunriseApartments);
        applicationService.hotelDao.add(ussrApartments);
        applicationService.hotelDao.add(atheneePalaceHilton);
        applicationService.hotelDao.add(parliament);

        applicationService.restaurantDao.add(tuya);
        applicationService.restaurantDao.add(tratorriaIlCalcio);
        applicationService.restaurantDao.add(relaisChateauxBistro);
        applicationService.restaurantDao.add(cafeAthenee);



        // Danube Delta
        danubeDelta.setImages(danubeDeltaImages);
        applicationService.touristAttractionDao.add(danubeDelta);

        applicationService.hotelDao.add(califarulAlb);
        applicationService.hotelDao.add(pensiuneaSharaiman);
        applicationService.hotelDao.add(casaDianas);
        applicationService.hotelDao.add(pensiuneaDanubiu);

        applicationService.restaurantDao.add(cherhanaResort);
        applicationService.restaurantDao.add(laLiman);
        applicationService.restaurantDao.add(restaurantCentral);
        applicationService.restaurantDao.add(ivanPescar);
    }
}
