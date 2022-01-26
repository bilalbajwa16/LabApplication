package lab.pak.com.glacialappportal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import lab.pak.com.glacialappportal.Notifications.ApiClient;
import lab.pak.com.glacialappportal.Notifications.ApiInterface;
import lab.pak.com.glacialappportal.Notifications.Adapter;
import lab.pak.com.glacialappportal.Notifications.Tasks;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    private List<Tasks> notif_list;
    lab.pak.com.glacialappportal.Notifications.ApiInterface apiInterface;
    Adapter.RecyclerViewClickListener listener;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {




                Intent intent = new Intent(NotificationList.this, TaskActivity.class);
                startActivity(intent);


                int id = notif_list.get(position).getId();
                String seen = "seen";
                String message = notif_list.get(position).getTaskmessage();
                if(message!="seen") {
                   addSeen("update", id, seen);
                }
            }


        };



    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }


    public void  addSeen(String key, int id, String seen){

        Call<Tasks> call = apiInterface.updateSeen(key,id,seen);
        call.enqueue(new Callback<Tasks>() {
            @Override
            public void onResponse(Call<Tasks> call, Response<Tasks> response) {

                Log.i(NotificationList.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getInfo();

                if (value.equals("1")) {
                    finish();
                } else {
                    Toast.makeText(NotificationList.this, message, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<Tasks> call, Throwable t) {
                //  progressDialog.dismiss();
                Toast.makeText(NotificationList.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void getPets(){


    SharedPreferences prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE);

    String userid = prefs.getString("id", null);
  //  Toast.makeText(NotificationList.this,userid,Toast.LENGTH_LONG).show();

    int id = Integer.parseInt(userid);
    Call<List<Tasks>> call = apiInterface.get(id);
    call.enqueue(new Callback<List<Tasks>>() {
        @Override
        public void onResponse(Call<List<Tasks>> call, Response<List<Tasks>> response) {

           progressBar.setVisibility(View.GONE);
            notif_list = response.body();
          //  Toast.makeText(NotificationList.this,response.body().toString(),Toast.LENGTH_LONG).show();

            if (response.body().toString() !="[]") {

                Log.i(NotificationList.class.getSimpleName(), response.body().toString());
                adapter = new Adapter(notif_list, NotificationList.this, listener);

                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }else {

                Toast.makeText(NotificationList.this,"No Notification yet",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(NotificationList.this, Menu.class);
                startActivity(intent);
            }
        }

        @Override
        public void onFailure(Call<List<Tasks>> call, Throwable t) {
            Toast.makeText(NotificationList.this, "rp :"+
                            t.getMessage().toString(),
                    Toast.LENGTH_SHORT).show();
        }
    });



    }

    @Override
    protected void onResume() {
        super.onResume();
        getPets();
    }

}
