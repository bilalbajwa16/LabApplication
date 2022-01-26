package lab.pak.com.glacialappportal;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import lab.pak.com.glacialappportal.task.ApiClient;
import lab.pak.com.glacialappportal.task.ApiInterface;
import lab.pak.com.glacialappportal.task.TaskAdapter;
import lab.pak.com.glacialappportal.task.Tasks;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TaskAdapter adapter;
    private List<Tasks> petsList;
    ApiInterface apiInterface;
    TaskAdapter.RecyclerViewClickListener listener;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listener = new TaskAdapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {

                Intent intent = new Intent(TaskActivity.this, ViewTask.class);
                intent.putExtra("id", petsList.get(position).getId());
                intent.putExtra("userid", petsList.get(position).getName());
                intent.putExtra("agent", petsList.get(position).getAgent());
                intent.putExtra("email", petsList.get(position).getSpecies());
                intent.putExtra("description", petsList.get(position).getBreed());
                intent.putExtra("accomplished", petsList.get(position).getDesignation());
                intent.putExtra("state", petsList.get(position).getType());
                //  intent.putExtra("dateattr", petsList.get(position).getBirth());
                startActivity(intent);
int id = petsList.get(position).getId();
String seen = "seen";
String state = petsList.get(position).getType();
if(state!="seen") {
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

                Log.i(TaskActivity.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")) {
                    finish();
                } else {
                    Toast.makeText(TaskActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Tasks> call, Throwable t) {
              //  progressDialog.dismiss();
                Toast.makeText(TaskActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
            });
    }


    public void getPets(){

        SharedPreferences prefs = getSharedPreferences("userdata", Context.MODE_PRIVATE);

        String userid = prefs.getString("id", null);
       // Toast.makeText(TaskActivity.this,userid,Toast.LENGTH_LONG).show();
        int id = Integer.parseInt(userid);

        Call<List<Tasks>> call = apiInterface.getPets(id);
        call.enqueue(new Callback<List<Tasks>>() {
            @Override
            public void onResponse(Call<List<Tasks>> call, Response<List<Tasks>> response) {
                progressBar.setVisibility(View.GONE);
                petsList = response.body();

             //   Toast.makeText(TaskActivity.this,response.body().toString(),Toast.LENGTH_LONG).show();


                Log.i(TaskActivity.class.getSimpleName(), response.body().toString());
                adapter = new TaskAdapter(petsList, TaskActivity.this, listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Tasks>> call, Throwable t) {
                Toast.makeText(TaskActivity.this, "rp :"+
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
