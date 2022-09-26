package com.travel.around.config;

import com.travel.around.model.Hotel;
import com.travel.around.model.Restaurant;
import com.travel.around.model.TouristAttraction;
import com.travel.around.service.ApplicationService;

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

        Hotel conaculBratescu = new Hotel("Conacul Bratescu", "Each room here comes with a minibar, a seating area, a cable TV, and a private bathroom with a shower or a bath and free toiletries. Some units enjoy mountain views and some are equipped with a balcony.", (float)4.8, 30, branCastle);
        Hotel casaMedievala = new Hotel("Pensiunea Casa Medievala", "The guest house has a large dining room and a courtyard with a parking area. Enjoy the landscapes!", (float)4.6, 25, branCastle);
        Hotel transylvaniaMountainExclusive = new Hotel("Transylvania Mountain Exclusive", "It offers accommodation with a shared lounge and free private parking. At the accommodation, guests have access to a shared kitchen.", (float)5.0, 44, branCastle);
        Hotel branChalet = new Hotel("Bran Chalet", "Guests can enjoy a delicious continental breakfast at Bran Chalet every morning and opt for quiet family rooms.", (float)4.3, 24, branCastle);

        conaculBratescu.setLocation("https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d11183.467246038106!2d25.3735059!3d45.5127597!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x5c07be20ae432f11!2sBr%C4%83tescu%20Mansion!5e0!3m2!1sen!2sro!4v1654846290304!5m2!1sen!2sro");
        casaMedievala.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2795.58787480297!2d25.36854061542026!3d45.51837427910169!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b347e6486801f7%3A0xa07dab3967429331!2sPensiunea%20Casa%20Medievala%2C%20DN73%201%2C%20Bran%20507025!5e0!3m2!1sen!2sro!4v1654846327322!5m2!1sen!2sro");
        transylvaniaMountainExclusive.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2795.58787480297!2d25.36854061542026!3d45.51837427910169!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b347aa0a927e25%3A0x39d0246cdcb00!2sTransylvania%20Mountain%20Exclusive!5e0!3m2!1sen!2sro!4v1654846364142!5m2!1sen!2sro");
        branChalet.setLocation("https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d11182.11778303676!2d25.369997!3d45.5195503!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x794cf684f2433c3f!2sBran%20Chalet!5e0!3m2!1sen!2sro!4v1654846397626!5m2!1sen!2sro");

        branCastle.addHotel(conaculBratescu);
        branCastle.addHotel(casaMedievala);
        branCastle.addHotel(transylvaniaMountainExclusive);
        branCastle.addHotel(branChalet);

        // Romanian Athenaeum

        Hotel sunriseApartments = new Hotel("Sunrise Apartments", "This is our guests' favourite part of Bucharest, according to independent reviews. Each unit also comes with a satellite flat-screen TV, ironing facilities, desk and a seating area with a sofa.", (float)5.0, 52, romanianAthenaeum);
        Hotel ussrApartments = new Hotel("USSR Apartments", "Each apartment has 1 dormitory, kitchen with fridge, microwave and laundry machine. It's at 400 metres of the Bucharest National Theatre.", (float)4.8, 46, romanianAthenaeum);
        Hotel atheneePalaceHilton = new Hotel("Athenee Palace Hilton", "A landmark hotel that dates back to 1914 and is less than 500 meters from Revolution Square. There’s a great range of shopping and dining opportunities in the area, and we have a heated indoor pool, whirlpool, beauty salon, and fitness center, as well as two restaurants and a summer terrace.", (float)4.6, 38, romanianAthenaeum);
        Hotel parliament = new Hotel("Hotel Parliament", "All rooms are air conditioned and feature cable TV, a minibar and a seating area. Most of the rooms view the Parliament Palace. Wheelchair accessible rooms are offered as well.", (float)4.2, 26, romanianAthenaeum);

        sunriseApartments.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2848.75750627024!2d26.096871129681773!3d44.438136212260545!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b1ff37ca1764db%3A0x9152f781ef784e02!2sSunrise%20Apartments!5e0!3m2!1sro!2sro!4v1654846515601!5m2!1sro!2sro ");
        ussrApartments.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2848.7859218548533!2d26.09878441548569!3d44.43755337910227!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b1ffb22e06ca09%3A0xcad0ae1dd3e0ebbd!2sUSSR%20APARTMENT!5e0!3m2!1sro!2sro!4v1654846636013!5m2!1sro!2sro");
        atheneePalaceHilton.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2848.618602829982!2d26.093535215485826!3d44.44098517910221!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b1ff409bd2d6d9%3A0x24e1439c27394d92!2sAth%C3%A9n%C3%A9e%20Palace%20Hilton%20Bucharest!5e0!3m2!1sro!2sro!4v1654846699679!5m2!1sro!2sro");
        parliament.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2849.1883856538693!2d26.078207415485426!3d44.42929777910242!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b1ff427bee28c1%3A0x7206c490e340e027!2sHotel%20Parliament!5e0!3m2!1sro!2sro!4v1654846735884!5m2!1sro!2sro");

        romanianAthenaeum.addHotel(sunriseApartments);
        romanianAthenaeum.addHotel(ussrApartments);
        romanianAthenaeum.addHotel(atheneePalaceHilton);
        romanianAthenaeum.addHotel(parliament);

        // Danube Delta

        Hotel califarulAlb = new Hotel("Hotel Califarul Alb", "The motel provides family rooms with wardrobes. Guests can play pool here and the surroundings are designated for fishing amateurs.", (float)4.3, 23, danubeDelta);
        Hotel pensiuneaSharaiman = new Hotel("Pensiunea Sharaiman", "The facility provides an arranged covered garden for all the guests with sunbeds. Here, guests can rent boats.", (float)4.5, 34, danubeDelta);
        Hotel casaDianas = new Hotel("Casa Dianas", "Casa Dianas features a bar, garden and terrace in Dunavăţu de Jos. The guest house has family rooms.", (float)4.6, 42, danubeDelta);
        Hotel pensiuneaDanubiu = new Hotel("Pensiunea Danubiu", "At 3-minute distance from the sea shores, this property provides a restaurants, an open-air swimming-pool, a bar and a common lounge.", (float)4.0, 30, danubeDelta);

        califarulAlb.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2800.2609858049696!2d29.285738215416902!3d45.424240079100585!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b9d48ab7abd34d%3A0x6a77723192b6c90c!2sCalifarul%20Alb!5e0!3m2!1sen!2sro!4v1654846598056!5m2!1sen!2sro");
        pensiuneaSharaiman.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2821.6948371968933!2d29.214448315401334!3d44.99050997909809!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b9f2a6c2a2c30f%3A0xeafdad36329d0414!2sPensiunea%20Sharaiman!5e0!3m2!1sen!2sro!4v1654846626651!5m2!1sen!2sro");
        casaDianas.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d29400.58074099788!2d29.616302083312426!3d45.15710925844697!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b9bb1dbd1f3f81%3A0x8a933606976d52c9!2sCasa%20Diana!5e0!3m2!1sen!2sro!4v1654846673586!5m2!1sen!2sro");
        pensiuneaDanubiu.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2818.694675855792!2d29.147273228974516!3d45.051417412243794!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b9ee89e00ba61d%3A0x17d0c7c06698a28e!2sPensiunea%20Danubiu!5e0!3m2!1sen!2sro!4v1654846707888!5m2!1sen!2sro");

        danubeDelta.addHotel(califarulAlb);
        danubeDelta.addHotel(pensiuneaSharaiman);
        danubeDelta.addHotel(casaDianas);
        danubeDelta.addHotel(pensiuneaDanubiu);

        /*
         *       RESTAURANTS
         */

        //  Bran Castle
        
        Restaurant torzburg = new Restaurant("Restaurant Torzburg", "The Törzburg Mansion offers a buffet breakfast every morning. Guests enjoy a terrace with wonderful views over Bran.", (float)4.4, branCastle);
        Restaurant trattoriaAlGallo = new Restaurant("Trattoria Al Gallo", "Al Gallo offers many benefits for its customers, such as games, a children's room, Italian food and a snack bar.", (float)4.0, branCastle);
        Restaurant laLupi = new Restaurant("Restaurant La Lupi", "Huge parking, extra activities and the best food in town. One of the most authentic places in the area.",(float)4.1, branCastle);
        Restaurant laTrasuraCuBucate = new Restaurant("La Trasura cu Bucate", "Here you can experience the authentic country life, you can taste inviting dishes, prepared by the kindest locals.", (float)4.9, branCastle);

        torzburg.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2795.6726976505815!2d25.36630831542027!3d45.516666979101686!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b347e5d983e50f%3A0x5c18f92a303abff9!2sConacul%20T%C3%B6rzburg!5e0!3m2!1sen!2sro!4v1654846446105!5m2!1sen!2sro");
        trattoriaAlGallo.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2795.6239727275743!2d25.362472729118398!3d45.517647712257975!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b347e5d43881b3%3A0x7394f9f557576762!2sTrattoria%20Al%20Gallo!5e0!3m2!1sen!2sro!4v1654846475456!5m2!1sen!2sro");
        laLupi.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2794.9097791266886!2d25.37061571542069!3d45.53202107910182!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b347d7973bfdab%3A0x802a8112613fca99!2sLa%20Lupi!5e0!3m2!1sen!2sro!4v1654846528369!5m2!1sen!2sro");
        laTrasuraCuBucate.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2795.69839127681!2d25.365900029117835!3d45.51614981225785!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b3474a4bebae97%3A0x4efbc54a4cb7b9cb!2sLa%20Tr%C4%83sura%20cu%20Bucate!5e0!3m2!1sen!2sro!4v1654846556713!5m2!1sen!2sro");

        branCastle.addRestaurant(torzburg);
        branCastle.addRestaurant(trattoriaAlGallo);
        branCastle.addRestaurant(laLupi);
        branCastle.addRestaurant(laTrasuraCuBucate);

        // Romanian Athenaeum
        
        Restaurant tuya = new Restaurant("Restaurant Tuya", "Made after the traditional one from Vienna, the Bucharest version provides mixed specialities both international & traditional cuisine.", (float)4.7, romanianAthenaeum);
        Restaurant tratorriaIlCalcio = new Restaurant("Trattoria Il Calcio Ateneu", "This restaurant from the Heart of Bucharest brings back the flavor of the italian kitchen together with its heartwarming athmosphere.", (float)4.2, romanianAthenaeum);
        Restaurant relaisChateauxBistro = new Restaurant("Relais & Chateaux Bistro Ateneu", "Le Bistrot Français creates an alchemy of French and Romanian cultures. The ambience is undeniably that of Central Europe.", (float)4.6, romanianAthenaeum);
        Restaurant cafeAthenee = new Restaurant("Cafe Athenee", "Delightful pastries, sandwiches, coffee and drinks , excellent com.travel.around.service, good place to watch people and be seen during the Enescu Classical Music Festival.", (float)4.5, romanianAthenaeum);

        tuya.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2846.950869261811!2d26.085314329692483!3d44.47517981225845!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b2035bbb0a8ffd%3A0xb2e0338b388ce99a!2sTUYA%20Bucharest!5e0!3m2!1sro!2sro!4v1654846812945!5m2!1sro!2sro");
        tratorriaIlCalcio.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2848.61710111521!2d26.095358815485827!3d44.44101597910216!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b1ff4f7ab7cd5b%3A0xbdc7180db2935165!2sTrattoria%20Il%20Calcio%20Ateneu!5e0!3m2!1sro!2sro!4v1654846859419!5m2!1sro!2sro");
        relaisChateauxBistro.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2848.58394128797!2d26.094580515485827!3d44.44169607910215!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b1ff5b44ed8f6b%3A0xd494171a49bf0d16!2sRelais%20%26%20Ch%C3%A2teaux%20Bistro%20Ateneu!5e0!3m2!1sro!2sro!4v1654846962417!5m2!1sro!2sro");
        cafeAthenee.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2848.621250331622!2d26.093444015485776!3d44.44093087910221!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b1ff4f85ffc179%3A0x92298917f1a1691b!2sCafe%20Athenee!5e0!3m2!1sro!2sro!4v1654846899072!5m2!1sro!2sro");

        romanianAthenaeum.addRestaurant(tuya);
        romanianAthenaeum.addRestaurant(tratorriaIlCalcio);
        romanianAthenaeum.addRestaurant(relaisChateauxBistro);
        romanianAthenaeum.addRestaurant(cafeAthenee);

        // Danube Delta

        Restaurant cherhanaResort = new Restaurant("Cherhana Resort", "A tourist resort highly rated, it's considered to be the best place for your next trip. The staff are extremely cautious around and the food is delicious.", (float)4.4, danubeDelta);
        Restaurant laLiman = new Restaurant("La Liman", "The 'La Liman' restaurant reshapes the romanian heartwarming athmosphere, represented by people and cultures that are still upon Dobruja.",  (float)4.2, danubeDelta);
        Restaurant restaurantCentral = new Restaurant("Restaurant Central", "Situated in the center of Tulcea county, this local provides top rated dishes in the region, along with the danubian specific.", (float)4.0, danubeDelta);
        Restaurant ivanPescar = new Restaurant("Ivan Pescar - Fish bar", "Located on the Danube Promenade, it's considered to be the best place where fish can be served without a doubt.", (float)4.7, danubeDelta);

        cherhanaResort.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2812.7489901220197!2d29.343739115407775!3d45.17193257909854!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b9c112b7dd90e5%3A0x9b3c20d3dd5968e6!2sCherhana%20Resort!5e0!3m2!1sen!2sro!4v1654846741096!5m2!1sen!2sro");
        laLiman.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2812.294164760557!2d28.787912229014477!3d45.18114121224539!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b7595a41f21cf3%3A0xa252fdef9884f7f6!2sLa%20Liman!5e0!3m2!1sen!2sro!4v1654846784945!5m2!1sen!2sro");
        restaurantCentral.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d103938.88993849978!2d29.013926862641362!3d45.200428556981485!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b759469a9beb21%3A0xb46c7e38aecd39a8!2sRestaurant%20Central!5e0!3m2!1sen!2sro!4v1654846827684!5m2!1sen!2sro");
        ivanPescar.setLocation("https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2812.3564085284424!2d28.7960266154081!3d45.17988107909864!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40b759454640e38f%3A0x5f44d4874b05364a!2sIvan%20Pescar%20-%20Fish%20Bar!5e0!3m2!1sen!2sro!4v1654846861449!5m2!1sen!2sro");

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
