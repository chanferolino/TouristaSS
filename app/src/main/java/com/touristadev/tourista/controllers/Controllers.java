package com.touristadev.tourista.controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.firebase.auth.FirebaseUser;
import com.touristadev.tourista.R;
import com.touristadev.tourista.dataModels.BookedPackages;
import com.touristadev.tourista.dataModels.Categories;
import com.touristadev.tourista.dataModels.CustomizedPackage;
import com.touristadev.tourista.dataModels.Itinerary;
import com.touristadev.tourista.dataModels.RatingTG;
import com.touristadev.tourista.dataModels.TouristaPackages;
import com.touristadev.tourista.dataModels.Spots;
import com.touristadev.tourista.dataModels.TourRequest;
import com.touristadev.tourista.dataModels.Tribes;
import com.touristadev.tourista.models.TourGuideModel;
import com.touristadev.tourista.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Controllers {

    private static ArrayList<Spots> spotList = new ArrayList<>();
    private static ArrayList<Spots> finalSpotList = new ArrayList<>();
    private static String transactionID;
    private static ArrayList<TouristaPackages> packageList = new ArrayList<>();
    private static ArrayList<CustomizedPackage> customizedPackageList = new ArrayList<>();
    private static ArrayList<BookedPackages> BookedList = new ArrayList<>();
    private static String finurl = "http://192.168.1.5:8888/";
    private static ArrayList<TouristaPackages> RecentList = new ArrayList<>();
    private static FirebaseUser user;
    private static LatLng currentLocation;
    private static String currentUser;
    private static ArrayList<TouristaPackages> RequestList = new ArrayList<>();
    private static ArrayList<TouristaPackages> WishList = new ArrayList<>();
    private static ArrayList<TourGuideModel> tourguideList = new ArrayList<>();
    private static ArrayList<Spots> spotIt3 = new ArrayList<>();
    private static int positionwew;
    private static float ratPack;
   private static GoogleAccountCredential mCredentials = null;
    private static ArrayList<RatingTG> ratTG = new ArrayList<>();
    private static boolean tourguidemode;
    private static BookedPackages CurrPackage = new BookedPackages();
    private static ArrayList<TourGuideModel> currTourguideList = new ArrayList<>();

    public static void Controllers() {


    }
    public static void addCustomizedPackage(CustomizedPackage pack){
        customizedPackageList.add(pack);

    }
    public static void addRatingPackage(float ratingPack){
        ratPack = ratingPack;
    }
    public static void addRatingTG(RatingTG rat){
        ratTG.add(rat);
    }
    public static float getPackRate(){
        return ratPack;
    }
    public static ArrayList<RatingTG> getRatingTG(){
        return ratTG;
    }
    public static double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return km;
    }
    public static void setCredentials(GoogleAccountCredential cred){
        mCredentials = cred;
    }
    public static GoogleAccountCredential getCredentials(){
        return mCredentials;
    }
    public static void addSpotToPackage(Spots spots,int pos){
        Log.d("SpotActivitychan","Controller : "+pos);
        Log.d("SpotActivitychan","Controller : "+customizedPackageList.size());

        Log.d("SpotActivitychan","Controller : "+ customizedPackageList.get(pos).getPackageItinerary().size());
        customizedPackageList.get(pos).getPackageItinerary().add(new Itinerary(spots.getSpotName(),spots.getSpotOpeningTime(),spots.getSpotClosingTime()));

    }
    public static void removeCustomizedPackage(int index){
        customizedPackageList.remove(index);

    }
    public static ArrayList<CustomizedPackage> getCustomizedPackageList(){
//        customizedPackageList.clear();
//        try {
//            new GetCustomPackageList().execute().get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        return customizedPackageList;
    }


    public static String getCurrentUserID(){

        return currentUser;
    }
    public static void setCurrentUserID(String id){

        currentUser = id;
    }
    public static ArrayList<TouristaPackages> getControllerPackaaes()
    {
        packageList.clear();


        ArrayList<TouristaPackages> L1= new ArrayList<>();
        ArrayList<Categories> categories1 = new ArrayList<>();
        ArrayList<Tribes> tribes1 = new ArrayList<>();
        ArrayList<Spots> spotIti = new ArrayList<>();
        ArrayList<Itinerary> itineraries1 = new ArrayList<>();

        ArrayList<TouristaPackages> L2= new ArrayList<>();
        ArrayList<Categories> categories2 = new ArrayList<>();
        ArrayList<Tribes> tribes2 = new ArrayList<>();
        ArrayList<Spots> spotIt2 = new ArrayList<>();
        ArrayList<Itinerary> itineraries2 = new ArrayList<>();

        ArrayList<TouristaPackages> L3= new ArrayList<>();
        ArrayList<Categories> categories3 = new ArrayList<>();
        ArrayList<Tribes> tribes3 = new ArrayList<>();
        ArrayList<Itinerary> itineraries3 = new ArrayList<>();


        ArrayList<Spots> spotIt4 = new ArrayList<>();
        ArrayList<Itinerary> itineraries4 = new ArrayList<>();


        /// PACKAGE ////////////////////////////////////////////////////////////////////////////////

        categories1.add(new Categories("Adventure"));
        categories1.add(new Categories("Nature"));
        categories1.add(new Categories("Beaches and Resorts"));
        categories1.add(new Categories("Beaches and Resorts"));

        tribes1.add(new Tribes("Thrill-seeker"));
        tribes1.add(new Tribes("Backpacker"));
        tribes1.add(new Tribes("Escapist"));
        spotIti.add(finalSpotList.get(1));
        spotIti.add(finalSpotList.get(0));
        itineraries1.add(new Itinerary("Pick up location","05:00","08:00"));
        itineraries1.add(new Itinerary(finalSpotList.get(1).getSpotName(),"08:00","11:00"));
        itineraries1.add(new Itinerary("Lunch","11:00","13:00"));
        itineraries1.add(new Itinerary(finalSpotList.get(0).getSpotName(),"13:00","16:00"));

        L1.add(new TouristaPackages("asfa231","South Cebu Tours",itineraries1,"Local",4,2,8,R.mipmap.spot_cebusouthtour,spotIti,"DESCRIPTION","1705","Colt Travel and Tours"));

        /// PACKAGE ////////////////////////////////////////////////////////////////////////////////


        categories2.add(new Categories("Art Galleries"));
        categories2.add(new Categories("Church"));
        categories2.add(new Categories("Historical"));
        categories2.add(new Categories("Museums"));

        tribes2.add(new Tribes("Collector"));
        tribes2.add(new Tribes("Escapist"));
        tribes2.add(new Tribes("The Self-Improver"));
        tribes2.add(new Tribes("Pilgrim"));
        tribes2.add(new Tribes("Genuinely Curious"));

        spotIt2.add(finalSpotList.get(4));
        spotIt2.add(finalSpotList.get(3));
        spotIt2.add(finalSpotList.get(2));
        itineraries2.add(new Itinerary("Pick up location","06:00","09:00"));
        itineraries2.add(new Itinerary(finalSpotList.get(2).getSpotName(),"09:00","12:00"));
        itineraries2.add(new Itinerary("Lunch","12:00","13:00"));
        itineraries2.add(new Itinerary(finalSpotList.get(3).getSpotAddress(),"13:00","15:00"));
        itineraries2.add(new Itinerary(finalSpotList.get(4).getSpotName(),"15:00","17:00"));

        L2.add(new TouristaPackages("14as25","Cebu Pilgrimage",itineraries2,"Local",4,3,8,R.mipmap.spot_stonino,spotIt2,"DESCRIPTION","1500","Colt Travel and Tours"));


        /// PACKAGE ////////////////////////////////////////////////////////////////////////////////



        categories3.add(new Categories("Adventure"));
        categories3.add(new Categories("Aquaria"));
        categories3.add(new Categories("Nature"));

        tribes3.add(new Tribes("Thrill-Seeker"));
        tribes3.add(new Tribes("Escapist"));
        tribes3.add(new Tribes("The Self-Improver"));
        tribes3.add(new Tribes("Genuinely Curious"));





        spotIt3.add(finalSpotList.get(4));
        spotIt3.add(finalSpotList.get(1));
        spotIt3.add(finalSpotList.get(5));
        spotIt3.add(finalSpotList.get(0));
        itineraries3.add(new Itinerary("Pick up location","05:00","08:00"));
        itineraries3.add(new Itinerary(finalSpotList.get(1).getSpotName(),"08:00","08:30"));
        itineraries3.add(new Itinerary("Short briefing then proceed to Whale Shark Watching / Snorkelin","08:30","11:00"));
        itineraries3.add(new Itinerary("Lunch","11:00","12:00"));
        itineraries3.add(new Itinerary(finalSpotList.get(5).getSpotName(),"12:00","15:00"));
        itineraries3.add(new Itinerary("Departure Time","15:00","17:00"));

        L3.add(new TouristaPackages("24asf","Oslob Whale Watching",itineraries3,"Local",4,2,12,R.mipmap.spot_oslobwhalshark,spotIt3,"DESCRIPTION","1250","Colt Travel and Tours"));

        /// PACKAGE ////////////////////////////////////////////////////////////////////////////////

        packageList.clear();
        packageList.add(L1.get(0));
        packageList.add(L2.get(0));
        packageList.add(L3.get(0));

       return packageList;
    }

    public static ArrayList<Spots> getControllerSpots(){
        finalSpotList.clear();

        Log.d("packTG","Controllers ");
        ArrayList<String> activities0 = new ArrayList<>();
        ArrayList<Categories> categories0 = new ArrayList<>();
        ArrayList<Tribes> tribes0 = new ArrayList<>();

        activities0.add("Swimming");

        categories0.add(new Categories("Beaches and Resorts"));
        categories0.add(new Categories("Nature"));

        tribes0.add(new Tribes("Comformist"));
        tribes0.add(new Tribes("Thrill- Seeker"));
        tribes0.add(new Tribes("Self- Improver"));
        finalSpotList.clear();

        finalSpotList.add(new Spots("0","Masters Resort Cebu","4044 Oslob, Cebu, Philippines",
                "8:00","21:00","The southern part of the province is one of the areas where you can enjoy various sorts of seawater activities, do adventurous trips, have fun and cherish the tranquil ambiance and surroundings."
                ,"9.459556960067692","123.37731275707483",4, R.mipmap.spot_oslobwhalshark));

        // SPOT ////////////////////////////////////////////////////////////////////////////////////

        ArrayList<String> activities1 = new ArrayList<>();
        ArrayList<Categories> categories1 = new ArrayList<>();
        ArrayList<Tribes> tribes1 = new ArrayList<>();

        activities1.add("Whale watching");
        activities1.add("Scuba Diving");

        categories1.add(new Categories("Nature"));
        categories1.add(new Categories("Aquaria"));
        categories1.add(new Categories("Beaches and Resorts"));

        tribes1.add(new Tribes("Comformist"));
        tribes1.add(new Tribes("Thrill- Seeker"));
        tribes1.add(new Tribes("Self- Improver"));

        finalSpotList.add(new Spots("1","Oslob Whale Shark Watching","Tan-awan Oslob, Cebu, Ph",
                "8:00","12:00","Oslob Whalesharks watching was born to give information about whale shark interaction in Oslob, we are here to organize day tour trips on whale shark encounters with side trip to Tumalog waterfalls. We"
               ,"9.47008451293314","123.38275127112864",4,R.mipmap.spot_oslobwhalshark));


        // SPOT ////////////////////////////////////////////////////////////////////////////////////

        ArrayList<String> activities2 = new ArrayList<>();
        ArrayList<Categories> categories2 = new ArrayList<>();
        ArrayList<Tribes> tribes2 = new ArrayList<>();


        activities2.add("Attend Mass");
        activities2.add("Visit Magellan's Cross");

        categories2.add(new Categories("Church"));
        categories2.add(new Categories("Historical"));
        categories2.add(new Categories("Museum"));

        tribes2.add(new Tribes("Collector"));
        tribes2.add(new Tribes("The Self-Improver"));
        tribes2.add(new Tribes("Pilgrim"));

        finalSpotList.add(new Spots("2","Basilica del Santo Niño","Santo Nino Chapel Lane, Cebu City, Cebu"
                ,"5:00","20:00","The oldest Roman Catholic church in the country, it is built on the spot where the image of the Santo Niño de Cebú was found during the expedition of Miguel López de Legazpi. "
              ,"10.294194","123.902106",4,R.mipmap.stnino));


        // SPOT ////////////////////////////////////////////////////////////////////////////////////
        ArrayList<String> activities3 = new ArrayList<>();
        ArrayList<Categories> categories3 = new ArrayList<>();
        ArrayList<Tribes> tribes3 = new ArrayList<>();


        activities3.add("Dicover Ancient cebu stories.");
        activities3.add("Visist historical Artifacts.");

        categories3.add(new Categories("Historical"));
        categories3.add(new Categories("Museum"));

        tribes3.add(new Tribes("Collector"));
        tribes3.add(new Tribes("The Self-Improver"));
        tribes3.add(new Tribes("Pilgrim"));
        tribes3.add(new Tribes("Genuinely Curious"));

        finalSpotList.add(new Spots("3", "Museo Sugbo", "M. J. Cuenco Ave, Cebu City, 6000 Cebu",
                "9:00","17:30","Museo Sugbo is the Cebu Provincial Museum located in the former Cebu Provincial Detention and Rehabilitation Center, four blocks from Plaza Independencia."
                , "10.303781",  "123.906758",
               4,R.mipmap.msugbo));

        // SPOT ////////////////////////////////////////////////////////////////////////////////////

        ArrayList<String> activities4 = new ArrayList<>();
        ArrayList<Categories> categories4 = new ArrayList<>();
        ArrayList<Tribes> tribes4 = new ArrayList<>();

        activities4.add("Explore Museum");

        categories4.add(new Categories("Historical"));
        categories4.add(new Categories("Nature"));
        categories4.add(new Categories("Art Galleries"));
        categories4.add(new Categories("Museums"));
        categories4.add(new Categories("Park"));

        tribes4.add(new Tribes("Collector"));
        tribes4.add(new Tribes("The Self-Improver"));
        tribes4.add(new Tribes("Pilgrim"));
        tribes4.add(new Tribes("Genuinely Curious"));

        finalSpotList.add( new Spots("4","Fort San Pedro", "A. Pigafetta Street, Cebu City, 6000"
                ,"7:00", "19:00", "One of the city’s historical attractions is Fort San Pedro which is known as the smallest and oldest fort in the Philippines.",
                "10.292499","123.905828",4,R.mipmap.fsanpedro));

        // SPOT ////////////////////////////////////////////////////////////////////////////////////

        ArrayList<String> activities5 = new ArrayList<>();
        ArrayList<Categories> categories5 = new ArrayList<>();
        ArrayList<Tribes> tribes5 = new ArrayList<>();

        activities5.add("Swimming");
        activities5.add("Scuba Diving");

        categories5.add(new Categories("Adventure"));
        categories5.add(new Categories("Aquaria"));
        categories5.add(new Categories("Beaches and Resorts"));
        categories5.add(new Categories("Nature"));

        tribes5.add(new Tribes("Thrill-Seeker"));
        tribes5.add(new Tribes("Escapist"));
        tribes5.add(new Tribes("The Self-Improver"));
        tribes5.add(new Tribes("Genuinely Curious"));

        finalSpotList.add(new Spots("5","Tumalog Falls","Cebu South Rd, Oslob, Cebu",
                "5:00","22:00","Tumalog Falls (also called the “Toslob Falls” or “Mag-ambak Falls”) is situated in the town of Oslob, Cebu. It is just about 15-20 minutes ride from the whale-watching site.",
               "9.486560"," 123.369264",54,R.mipmap.tml));

        // SPOT ////////////////////////////////////////////////////////////////////////////////////

        ArrayList<String> activities6 = new ArrayList<>();
        ArrayList<Categories> categories6 = new ArrayList<>();
        ArrayList<Tribes> tribes6 = new ArrayList<>();

        activities6.add("Swimming");

        categories6.add(new Categories("Adventure"));
        categories6.add(new Categories("Nature"));


        tribes6.add(new Tribes("Thrill-Seeker"));
        tribes6.add(new Tribes("The Self-Improver"));
        tribes6.add(new Tribes("Genuinely Curious"));

        finalSpotList.add(new Spots("6","Kawasan Falls","Matutinao,, Badian, 6031 Cebu",
                "5:00","22:00","Cebu kawasan falls Cebu is a peaceful natural place where you can enjoy many waterfalls of natural spring water located near the southern tip of Cebu Philippines.. A gentle hush of rushing ice cool water."
                ,"9.811219", "123.374875",4,R.mipmap.kws));


        // SPOT ////////////////////////////////////////////////////////////////////////////////////


        ArrayList<String> activities7 = new ArrayList<>();
        ArrayList<Categories> categories7 = new ArrayList<>();
        ArrayList<Tribes> tribes7 = new ArrayList<>();

        activities7.add("Cultural exhibits near a historic church.");
        activities7.add("Iconic citadel and with a hero's memorial.");

        categories7.add(new Categories("Art Galleries"));
        categories7.add(new Categories("Building and Structure"));
        categories7.add(new Categories("Church"));
        categories7.add(new Categories("Historical"));
        categories7.add(new Categories("Museums"));

        tribes7.add(new Tribes("Thrill-Seeker"));
        tribes7.add(new Tribes("Collector"));
        tribes7.add(new Tribes("Genuinely Curious"));
        tribes7.add(new Tribes("Pilgrim"));
        tribes7.add(new Tribes("Pilgrim"));

        finalSpotList.add(new Spots("7","Intrauros","Bonifacio Dr & Padre Burgos St, Manila, Luzon 1002, Philippines",
                "8:00 AM","10:00 PM","Intramuros is the oldest district and historic core of Manila, Philippines. ... Map of Metro Manila showing the location of Intramuros."
                ,"9.811219", "123.374875",4,R.mipmap.kws));


        return finalSpotList;
    }
    public static void addUser(FirebaseUser us)
    {
        user = us;


    }public static FirebaseUser getUser()
    {

        return user;

    }

    public static void removeWishPackage(int pos)
    {
        WishList.remove(pos);

    }
    public static void addWishPackage(TouristaPackages pos)
    {
        WishList.add(pos);

    }
    public static void removeBookedPackages(int pos)
    {
        BookedList.remove(pos);

    } public static void addBookPackages(BookedPackages pos)
    {
        BookedList.add(pos);

    }
    public static void removeRequestPackage(int pos)
    {
        RequestList.remove(pos);

    }
    public static ArrayList<BookedPackages> getBookedList()
    {



        return BookedList;

    }
    public static ArrayList<TouristaPackages> getRecentList(){
        RecentList.clear();



        return packageList;
    }

    public static ArrayList<TouristaPackages> getWishList()
    {
        return WishList;
    }

    public static ArrayList<TourGuideModel> getTourguideList(){
        tourguideList.clear();
        tourguideList.add(new TourGuideModel("Christian Ferolino", R.mipmap.ic_launcher, "Im gonna make you smile :)", 5, "18"));
        tourguideList.add(new TourGuideModel("Rey Manigos", R.mipmap.ic_launcher, "Your lovely companion :) ", 5, "17"));

        return tourguideList;
    }
    public static void setPosition(int position2){
        positionwew = position2;
    }
    public static int getPosition(){
        return positionwew;
    }
    public static void setCurrentPackage(BookedPackages pack){

        pack = new BookedPackages();
        CurrPackage = pack;
    }
    public static void setCurrTourGuide(ArrayList<TourGuideModel> tgMod){
        currTourguideList.clear();
        for(int x = 0 ; x < tgMod.size();x++){
            currTourguideList.add(new TourGuideModel(tgMod.get(x)));
        }
        if(currTourguideList == null){
            currTourguideList = tgMod;
        }
    }
    public static BookedPackages getCurrentPackage(){

            return CurrPackage;

    }



    public static ArrayList<TourGuideModel> getCurrentTourguideList(){
        currTourguideList.clear();
        currTourguideList.add(new TourGuideModel("Christian Ferolino", R.mipmap.ic_launcher, "Im gonna make you smile :)", 5, "18"));
        currTourguideList.add(new TourGuideModel("Rey Manigos", R.mipmap.ic_launcher, "Your lovely companion :) ", 5, "17"));

        return currTourguideList;

    }
    public static void postToDb(String url , JSONObject obj){
        HttpUtils.POST(finurl+""+url,obj);
    }
    public static void postToDb(String url , JSONArray obj){
        HttpUtils.POST(finurl+""+url,obj);
    }
    public static String getCurrentTransactionID(){

        return transactionID;
    }
   public static LatLng getCurrentLocation(){
       return currentLocation;
   }
    public static void setCurrentLocation(LatLng temp){

        currentLocation = temp;
    }



//    static  class GetPackages extends AsyncTask<Void, Void, ArrayList<TouristaPackages>> {
//
//        static  ArrayList<Itinerary> itineraries4 = new ArrayList<>();
//
//        @Override
//        protected ArrayList<TouristaPackages> doInBackground(Void... voids) {
//            try {
//                JSONArray json = new JSONArray(HttpUtils.GET(finurl+"api/get-best-tours"));
//                for(int n = 0; n < json.length(); n++)
//                {
//                    JSONObject object = json.getJSONObject(n);
//                    JSONArray jarray = object.getJSONArray("itineraries");
//                    for(int z = 0 ; z < jarray.length();z++){
//                        JSONObject j = jarray.getJSONObject(z);
//
//                        itineraries4.add(new Itinerary(j.getString("spotName")+" "+j.getString("description"),j.getString("startTime"),j.getString("endTime")));
//
//                    }
//                    packageList.add(new TouristaPackages(object.getString("packageId"),object.getString("packageName"),itineraries4,"Local",Integer.parseInt(object.getString("rating")),Integer.parseInt(object.getString("numOfSpots")),Integer.parseInt(object.getString("duration")),R.mipmap.ic_tourista,spotIt3,object.getString("description"),object.getString("payment")));
//                    // do some stuff....
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                Log.d("changwapo",e+" package");
//            }
//            return packageList;
//        }
//
//
//    }
//    static class GetCustomPackageList extends AsyncTask<Void, Void, ArrayList<TouristaPackages>> {
//
//        ArrayList<Itinerary> itineraries4 = new ArrayList<>();
//
//        @Override
//        protected ArrayList<TouristaPackages> doInBackground(Void... voids) {
//            Controllers con = new Controllers();
//            try {
//                JSONArray json = new JSONArray(HttpUtils.GET(finurl+"api/get-custom-package-tours?userId="+con.getCurrentUserID()+"&status=Request"));
//
//
//                for(int n = 0; n < json.length(); n++)
//                {
//                    JSONObject object = json.getJSONObject(n);
//                    JSONArray jarray = object.getJSONArray("itineraries");
//                    for(int z = 0 ; z < jarray.length();z++){
//                        JSONObject j = jarray.getJSONObject(z);
//
//                        itineraries4.add(new Itinerary(j.getString("spotName")+" "+j.getString("description"),j.getString("startTime"),j.getString("endTime")));
//
//                    }
//                    WishList.add(new TouristaPackages(object.getString("packageId"),object.getString("packageName"),itineraries4,"Local",Integer.parseInt(object.getString("rating")),Integer.parseInt(object.getString("numOfSpots")),Integer.parseInt(object.getString("duration")),R.mipmap.ic_tourista,spotIt3,object.getString("description"),object.getString("payment")));
//                    // do some stuff....
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                Log.d("changwapo",e+" package");
//            }
//            return WishList;
//        }
//
//    }
//    static class GetSpots extends AsyncTask<Void, Void, ArrayList<Spots>> {
//
//
//        @Override
//        protected ArrayList<Spots> doInBackground(Void... voids) {
//            try {
//                JSONArray json = new JSONArray(HttpUtils.GET(finurl+"api/get-featured-spots"));
//                for(int n = 0; n < json.length(); n++)
//                {
//                    JSONObject object = json.getJSONObject(n);
//
//                    finalSpotList.add(new Spots(object.getString("spotId"),object.getString("spotName"),object.getString("streetAddress"),object.getString("opening"),object.getString("closing"),object.getString("description"),object.getString("LONGITUDE"),object.getString("LATITUDE"),5,R.mipmap.ic_launcher));
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//
//                Log.d("changwapo",e+" spots");
//            }
//            return finalSpotList;
//        }
//
//
//    }
//    static class GetWishList extends AsyncTask<Void, Void, ArrayList<TouristaPackages>> {
//
//        ArrayList<Itinerary> itineraries4 = new ArrayList<>();
//
//        @Override
//        protected ArrayList<TouristaPackages> doInBackground(Void... voids) {
//            Controllers con = new Controllers();
//            try {
//                JSONArray json = new JSONArray(HttpUtils.GET(finurl+"api/get-best-tours?userId="+con.getCurrentUserID()+"&status=Request"));
//
//
//                for(int n = 0; n < json.length(); n++)
//                {
//                    JSONObject object = json.getJSONObject(n);
//                    JSONArray jarray = object.getJSONArray("itineraries");
//                    for(int z = 0 ; z < jarray.length();z++){
//                        JSONObject j = jarray.getJSONObject(z);
//
//                        itineraries4.add(new Itinerary(j.getString("spotName")+" "+j.getString("description"),j.getString("startTime"),j.getString("endTime")));
//
//                    }
//                    WishList.add(new TouristaPackages(object.getString("packageId"),object.getString("packageName"),itineraries4,"Local",Integer.parseInt(object.getString("rating")),Integer.parseInt(object.getString("numOfSpots")),Integer.parseInt(object.getString("duration")),R.mipmap.ic_tourista,spotIt3,object.getString("description"),object.getString("payment")));
//                    // do some stuff....
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                Log.d("changwapo",e+" package");
//            }
//            return WishList;
//        }
//
//    }
//    static class GetBookedTours extends AsyncTask<Void, Void, ArrayList<BookedPackages>> {
//
//        ArrayList<Itinerary> itineraries4 = new ArrayList<>();
//
//        @Override
//        protected ArrayList<BookedPackages> doInBackground(Void... voids) {
//            Controllers con = new Controllers();
//            try {
//                JSONArray json = new JSONArray(HttpUtils.GET(finurl+"api/get-best-tours?userId="+con.getCurrentUserID()+"&status=Confirmed"));
//
//                for(int n = 0; n < json.length(); n++)
//                {
//                    JSONObject object = json.getJSONObject(n);
//                    transactionID = object.getString("tourTransactionId");
//
//                    JSONArray jarray = object.getJSONArray("itineraries");
//                    for(int z = 0 ; z < jarray.length();z++){
//                        JSONObject j = jarray.getJSONObject(z);
//
//                        itineraries4.add(new Itinerary(j.getString("spotName")+" "+j.getString("description"),j.getString("startTime"),j.getString("endTime")));
//
//                    }
//                    BookedList.add(new BookedPackages(object.getString("packageId"),object.getString("packageName"),itineraries4,"Local",Integer.parseInt(object.getString("rating")),Integer.parseInt(object.getString("numOfSpots")),Integer.parseInt(object.getString("duration")),R.mipmap.ic_tourista,spotIt3,object.getString("description"),object.getString("payment"),object.getString("reserveDate")));
//                    // do some stuff....
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                Log.d("changwapo",e+" package");
//            }
//            return BookedList;
//        }
//
//    }
//    static class GetRecentList extends AsyncTask<Void, Void, ArrayList<TouristaPackages>> {
//
//        ArrayList<Itinerary> itineraries4 = new ArrayList<>();
//
//        @Override
//        protected ArrayList<TouristaPackages> doInBackground(Void... voids) {
//            Controllers con = new Controllers();
//            try {
//                JSONArray json = new JSONArray(HttpUtils.GET(finurl+"api/get-friends-activity?userId=4WsRc7IsriQIyuA7zraN24Cgcl12"));
//
//                for(int n = 0; n < json.length(); n++)
//                {
//                    JSONObject object = json.getJSONObject(n);
//                    transactionID = object.getString("tourTransactionId");
//                    JSONArray jary = object.getJSONArray("package");
//                    for(int y = 0 ; y <jary.length();y++) {
//                        JSONObject jobj = jary.getJSONObject(y);
//                        JSONArray jarray = jobj.getJSONArray("itineraries");
//                        for (int z = 0; z < jarray.length(); z++) {
//                            JSONObject j = jarray.getJSONObject(z);
//
//                            itineraries4.add(new Itinerary(j.getString("spotName") + " " + j.getString("description"), j.getString("startTime"), j.getString("endTime")));
//
//                        }
//                        RecentList.add(new TouristaPackages(object.getString("packageId"), jobj.getString("packageName"), itineraries4, "Local", Integer.parseInt(jobj.getString("rating")), Integer.parseInt(jobj.getString("numOfSpots")), Integer.parseInt(jobj.getString("duration")), R.mipmap.ic_tourista, spotIt3, jobj.getString("description"), jobj.getString("payment")));
//                        // do some stuff....
//                    }
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                Log.d("changwapo",e+" package");
//            }
//            return RecentList;
//        }
//
//    }



}