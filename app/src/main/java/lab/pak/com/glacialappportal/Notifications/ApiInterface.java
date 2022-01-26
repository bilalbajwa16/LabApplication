package lab.pak.com.glacialappportal.Notifications;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by haerul on 15/03/18.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("get_notif.php")
    Call<List<Tasks>> get(

            @Field("userid") int id
    );

    @FormUrlEncoded
    @POST("update_seen.php")
    Call<Tasks> updateSeen(
            @Field("key") String key,
            @Field("id") int id,
            @Field("message") String status);
}
