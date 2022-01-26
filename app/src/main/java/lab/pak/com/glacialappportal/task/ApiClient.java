package lab.pak.com.glacialappportal.task;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by haerul on 15/03/18.
 */

public class ApiClient {

    private static final String BASE_URL = "http://glacial.pk/api/apitask/"; //"http://www.nidshub.com/pet_api/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient(){

        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

}
