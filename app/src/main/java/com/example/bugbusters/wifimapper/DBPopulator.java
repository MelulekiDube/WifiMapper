package com.example.bugbusters.wifimapper;

import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DBPopulator {
    private final static String TAG ="DB";

     static void populateDB(List<LatLng> list, String name){
        Log.d(TAG, "DB test ");
        ArrayList<LatLng> coordinates = new ArrayList<>(list);
        Area testArea = new Area(0,0,coordinates,name);
        //DatabaseUtils.addArea(testArea);

    }

    static void addSegments(GoogleMap mMap){

        Random rand=new Random();
        Polygon Pre_PD = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.954874, 18.460117).toGoogleLatLng(),new LatLng(-33.954874, 18.460732).toGoogleLatLng(),
                        new LatLng(-33.955385, 18.460748).toGoogleLatLng(), new LatLng(-33.955384, 18.460117).toGoogleLatLng())
                .fillColor(Color.argb(100,0,0,100))
                .strokeWidth(0)
        );

        List<LatLng> preList = Arrays.asList(new LatLng(-33.954874, 18.460117),new LatLng(-33.954874, 18.460732),
                new LatLng(-33.955385, 18.460748), new LatLng(-33.955384, 18.460117));
        populateDB(preList,"Pre_PD");

        Polygon PD = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.955384, 18.460117).toGoogleLatLng(),new LatLng(-33.955385, 18.460748).toGoogleLatLng(),
                        new LatLng(-33.956498, 18.460750).toGoogleLatLng(), new LatLng(-33.956450,  18.459849).toGoogleLatLng())
                .fillColor(Color.argb(100,0,150,0))
                .strokeWidth(0)
        );

        List<LatLng> pdList = Arrays.asList(new LatLng(-33.955384, 18.460117),new LatLng(-33.955385, 18.460748),
                new LatLng(-33.956498, 18.460750), new LatLng(-33.956450,  18.459849));
        populateDB(preList,"PD");

        Polygon Career_Service = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.956450,  18.459849).toGoogleLatLng(),new LatLng(-33.956475,  18.460444).toGoogleLatLng(),
                        new LatLng(-33.956950, 18.460371).toGoogleLatLng(), new LatLng(-33.956872, 18.459690).toGoogleLatLng())
                .fillColor(Color.argb(100,200,0,0))
                .strokeWidth(0)
        );

        List<LatLng> CareerList = Arrays.asList(new LatLng(-33.956450,  18.459849),new LatLng(-33.956475,  18.460444),
                new LatLng(-33.956950, 18.460371), new LatLng(-33.956872, 18.459690));
        populateDB(CareerList,"Career_Service");



        Polygon Climate_sys_Group = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.956872, 18.459690).toGoogleLatLng(),new LatLng(-33.956950, 18.460371).toGoogleLatLng(),
                        new LatLng(-33.958305, 18.459975).toGoogleLatLng(), new LatLng(-33.958123, 18.459178).toGoogleLatLng())
                .fillColor(Color.argb(100,100,80,40))
                .strokeWidth(0)
        );

        List<LatLng> ClimateList = Arrays.asList(new LatLng(-33.956872, 18.459690),new LatLng(-33.956950, 18.460371),
                new LatLng(-33.958305, 18.459975), new LatLng(-33.958123, 18.459178));
        populateDB(ClimateList,"Climate_sys_Group");

        Polygon NEB = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.958123, 18.459178).toGoogleLatLng(),new LatLng(-33.958305, 18.459975).toGoogleLatLng(),
                        new LatLng(-33.959899, 18.459160).toGoogleLatLng(),new LatLng(-33.959118, 18.458773).toGoogleLatLng())
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        List<LatLng> NEBList = Arrays.asList(new LatLng(-33.958123, 18.459178),new LatLng(-33.958305, 18.459975),
                new LatLng(-33.959899, 18.459160),new LatLng(-33.959118, 18.458773));
        populateDB(NEBList,"NEB");


        Polygon NSLT = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.954874, 18.460732).toGoogleLatLng(),new LatLng(-33.954990, 18.461412).toGoogleLatLng(),
                        new LatLng(-33.955658, 18.461396).toGoogleLatLng(),new LatLng(-33.955658, 18.460750).toGoogleLatLng())
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        List<LatLng> NSLTList = Arrays.asList(new LatLng(-33.954874, 18.460732),new LatLng(-33.954990, 18.461412),
                new LatLng(-33.955658, 18.461396),new LatLng(-33.955658, 18.460750));
        populateDB(NSLTList,"NSLT");

        Polygon Chris_Hani = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.955658, 18.460750).toGoogleLatLng(),new LatLng(-33.955658, 18.461396).toGoogleLatLng(),
                        new LatLng(-33.955925, 18.461399).toGoogleLatLng(),new LatLng(-33.955925, 18.460750).toGoogleLatLng())
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        List<LatLng> ChrisList = Arrays.asList(new LatLng(-33.955658, 18.460750),new LatLng(-33.955658, 18.461396),
                new LatLng(-33.955925, 18.461399),new LatLng(-33.955925, 18.460750));
        populateDB(ChrisList,"Chris Hani");

        Polygon FitzPatrick_Institute = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.955925, 18.460750).toGoogleLatLng(),new LatLng(-33.955925, 18.461399).toGoogleLatLng(),
                        new LatLng(-33.956520, 18.461399).toGoogleLatLng(),new LatLng(-33.956498, 18.460750).toGoogleLatLng())
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );
        List<LatLng> piListtzList = Arrays.asList(new LatLng(-33.955925, 18.460750),new LatLng(-33.955925, 18.461399),
                new LatLng(-33.956520, 18.461399),new LatLng(-33.956498, 18.460750));
        populateDB(piListtzList,"FitzPatrick Institute");

        Polygon FoodCourt = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.956475,  18.460444).toGoogleLatLng(),
                        new LatLng(-33.956950, 18.460371).toGoogleLatLng(),
                        new LatLng(-33.957077, 18.460340).toGoogleLatLng(),
                        new LatLng(-33.957141, 18.460817).toGoogleLatLng(),
                        new LatLng(-33.956506, 18.460900).toGoogleLatLng())
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        List<LatLng> foodList = Arrays.asList(new LatLng(-33.956475,  18.460444),
                new LatLng(-33.956950, 18.460371),
                new LatLng(-33.957077, 18.460340),
                new LatLng(-33.957141, 18.460817),
                new LatLng(-33.956506, 18.460900));
        populateDB(foodList,"Food Court");





        Polygon CompSci = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.956506, 18.460900).toGoogleLatLng(), new LatLng(-33.956520, 18.461399).toGoogleLatLng(),
                        new LatLng(-33.957211, 18.461331).toGoogleLatLng(), new LatLng(-33.957141, 18.460817).toGoogleLatLng()
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        List<LatLng> cscList = Arrays.asList(new LatLng(-33.956506, 18.460900), new LatLng(-33.956520, 18.461399),
                new LatLng(-33.957211, 18.461331), new LatLng(-33.957141, 18.460817));
        populateDB(cscList,"Computer Science");




        Polygon Library = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.957077, 18.460340).toGoogleLatLng(), new LatLng(-33.957211, 18.461331).toGoogleLatLng(),
                        new LatLng(-33.957500, 18.461281).toGoogleLatLng(), new LatLng(-33.957365, 18.460250).toGoogleLatLng()
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );



        List<LatLng> libList = Arrays.asList(new LatLng(-33.957077, 18.460340), new LatLng(-33.957211, 18.461331),
                new LatLng(-33.957500, 18.461281), new LatLng(-33.957365, 18.460250));
        populateDB(libList,"Library");










        Polygon Jameson = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.957365, 18.460250).toGoogleLatLng(), new LatLng(-33.957500, 18.461281).toGoogleLatLng(),
                        new LatLng(-33.958330, 18.461081).toGoogleLatLng(), new LatLng(-33.958121, 18.460031).toGoogleLatLng()
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );


        List<LatLng> jList = Arrays.asList(new LatLng(-33.957365, 18.460250), new LatLng(-33.957500, 18.461281),
                new LatLng(-33.958330, 18.461081), new LatLng(-33.958121, 18.460031));
        populateDB(jList,"Jameson Hall");






        Polygon Menzies = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.958121, 18.460031).toGoogleLatLng(),
                        new LatLng(-33.958305, 18.459975).toGoogleLatLng(),
                        new LatLng(-33.958922, 18.459650).toGoogleLatLng(),
                        new LatLng(-33.959250, 18.460750).toGoogleLatLng(),
                        new LatLng(-33.958330, 18.461081).toGoogleLatLng()
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );


        List<LatLng> menziesList = Arrays.asList(new LatLng(-33.958121, 18.460031),
                new LatLng(-33.958305, 18.459975),
                new LatLng(-33.958922, 18.459650),
                new LatLng(-33.959250, 18.460750),
                new LatLng(-33.958330, 18.461081));
        populateDB(menziesList,"Menzies");





        Polygon Leslie = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.958922, 18.459650).toGoogleLatLng(),
                        new LatLng(-33.959899, 18.459160).toGoogleLatLng(),
                        new LatLng(-33.960763, 18.459929).toGoogleLatLng(),
                        new LatLng(-33.960435, 18.460415).toGoogleLatLng(),
                        new LatLng(-33.960266, 18.460231).toGoogleLatLng(),
                        new LatLng(-33.959250, 18.460750).toGoogleLatLng()
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        List<LatLng> leslieList = Arrays.asList(new LatLng(-33.958922, 18.459650),
                new LatLng(-33.959899, 18.459160),
                new LatLng(-33.960763, 18.459929),
                new LatLng(-33.960435, 18.460415),
                new LatLng(-33.960266, 18.460231),
                new LatLng(-33.959250, 18.460750));
        populateDB(leslieList,"Leslie Social");






        Polygon Centlivres = mMap.addPolygon(new PolygonOptions()
                        .add(new LatLng(-33.960435, 18.460415).toGoogleLatLng(),
                                new LatLng(-33.960266, 18.460231).toGoogleLatLng(),
                                new LatLng(-33.959250, 18.460750).toGoogleLatLng(),
                                new LatLng(-33.959360, 18.461190).toGoogleLatLng(),
                                new LatLng(-33.959928, 18.460924).toGoogleLatLng(),
                                new LatLng(-33.959982, 18.460955).toGoogleLatLng(),
                                new LatLng(-33.960150, 18.460800).toGoogleLatLng()
//                        new LatLng(-33.960447, 18.460394)
                        )
                        .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                        .strokeWidth(0)
        );


        List<LatLng> centList = Arrays.asList(new LatLng(-33.960435, 18.460415),
                new LatLng(-33.960266, 18.460231),
                new LatLng(-33.959250, 18.460750),
                new LatLng(-33.959360, 18.461190),
                new LatLng(-33.959928, 18.460924),
                new LatLng(-33.959982, 18.460955),
                new LatLng(-33.960150, 18.460800));
        populateDB(centList,"Centilevers");






        Polygon Beattie = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.958588, 18.460990).toGoogleLatLng(),new LatLng(-33.958700, 18.461458).toGoogleLatLng(),
                        new LatLng(-33.959360, 18.461190).toGoogleLatLng(),new LatLng(-33.959250, 18.460750).toGoogleLatLng()
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );




        List<LatLng> beattieList = Arrays.asList(new LatLng(-33.958588, 18.460990),new LatLng(-33.958700, 18.461458),
                new LatLng(-33.959360, 18.461190),new LatLng(-33.959250, 18.460750));
        populateDB(beattieList,"Beattie");


        Polygon AC_Jordan = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.957740, 18.461225).toGoogleLatLng(),new LatLng(-33.957809, 18.461715).toGoogleLatLng(),
                        new LatLng(-33.958315, 18.461569).toGoogleLatLng(),
                        new LatLng(-33.958700, 18.461458).toGoogleLatLng(),
                        new LatLng(-33.958588, 18.460990).toGoogleLatLng(),
                        new LatLng(-33.958330, 18.461081).toGoogleLatLng()
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );


        List<LatLng> jordanList = Arrays.asList(new LatLng(-33.957740, 18.461225),new LatLng(-33.957809, 18.461715),
                new LatLng(-33.958315, 18.461569),
                new LatLng(-33.958700, 18.461458),
                new LatLng(-33.958588, 18.460990),
                new LatLng(-33.958330, 18.461081));
        populateDB(jordanList,"AC Jordan");



        Polygon Math_Bldng = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.957740, 18.461225).toGoogleLatLng(),new LatLng(-33.957809, 18.461715).toGoogleLatLng(),
                        new LatLng(-33.956825, 18.461858).toGoogleLatLng(),
                        new LatLng(-33.956807, 18.461372).toGoogleLatLng(),
                        new LatLng(-33.957500, 18.461281).toGoogleLatLng()
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        List<LatLng> mathList = Arrays.asList(new LatLng(-33.957740, 18.461225),new LatLng(-33.957809, 18.461715),
                new LatLng(-33.956825, 18.461858),
                new LatLng(-33.956807, 18.461372),
                new LatLng(-33.957500, 18.461281));
        populateDB(mathList,"Math Building");



        Polygon Herbariuam_Library = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.956825, 18.461858).toGoogleLatLng(),
                        new LatLng(-33.956807, 18.461372).toGoogleLatLng(),
                        new LatLng(-33.956525, 18.461398).toGoogleLatLng(),
                        new LatLng(-33.955987, 18.461400).toGoogleLatLng(),
                        new LatLng(-33.955973, 18.461884).toGoogleLatLng()
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );

        List<LatLng> herbList = Arrays.asList(new LatLng(-33.956825, 18.461858),
                new LatLng(-33.956807, 18.461372),
                new LatLng(-33.956525, 18.461398),
                new LatLng(-33.955987, 18.461400),
                new LatLng(-33.955973, 18.461884));
        populateDB(herbList,"Herbariuam Library");



        Polygon RW_James = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-33.955987, 18.461400).toGoogleLatLng(),
                        new LatLng(-33.955973, 18.461906).toGoogleLatLng(),
                        new LatLng(-33.955243, 18.461922).toGoogleLatLng(),
                        new LatLng(-33.955260, 18.461400).toGoogleLatLng()
                )
                .fillColor(Color.argb(100,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)))
                .strokeWidth(0)
        );
    }



}
