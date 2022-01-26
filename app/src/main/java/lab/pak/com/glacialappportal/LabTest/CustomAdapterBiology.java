package lab.pak.com.glacialappportal.LabTest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import lab.pak.com.glacialappportal.GetData;
import lab.pak.com.glacialappportal.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CustomAdapterBiology extends RecyclerView.Adapter<CustomAdapterBiology.MyViewHolder> {

    private final ArrayList<labtestcontents> mainob;
   Context context;
    private MyViewHolder vh;
ProgressBar progressBar;
    private String history,med,disease;
    private String aget;
    private String blood;

    public CustomAdapterBiology(Context context, ArrayList<labtestcontents> mainob) {
        this.context = context;
        this.mainob=mainob;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.labtest_chem, parent, false);

        vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

    View vl;
try {
int postion=position+1;
    String po= String.valueOf(mainob.get(position).getId());
    holder.serial.setText(po);
holder.parameter.setText(mainob.get(position).getName());
//holder.units.setText(mainob.get(position).getUnits());
//holder.glacialths.setText(mainob.get(position).getGlacialths());


    holder.qthsbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("QTHs value");
            alertDialog.setMessage("Value:  " + mainob.get(position).getGlacialths());
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();


        }
    });

    holder.result.addTextChangedListener(new TextWatcher() {

        public void afterTextChanged(Editable s) {
        }

        public void beforeTextChanged(CharSequence s, int start,
                                      int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            // TextView myOutputBox = (TextView) findViewById(R.id.myOutputBox);
            try{   long po= (int) (mainob.get(position).getId()-1);
                final StringBuilder sb = new StringBuilder(s.length());
                sb.append(s);
                String ui= sb.toString();
                int value = Integer.parseInt(ui);
              //  String value = mainob.get(position).getGlacialths();

              //  String[] arrayString = value.split("-");
               // String v1 = arrayString[0];
               // String v2 = arrayString[1];


                if (value<0.004){
                    Toast.makeText(context,"value limit warning" ,Toast.LENGTH_LONG ).show();

//                    Uri uri = Uri.parse("http://glacial.pk/api/mailapi.php"); // missing 'http://' will cause crashed
//                        Intent intent = new Intent(Intent.ACTION_SEND, uri);
//                        context.startActivity(intent);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Labtest","warning");
                    editor.apply();


                }


             //   Toast.makeText(context,"value limit warning" ,Toast.LENGTH_LONG ).show();
               // Toast.makeText(context, "warning value: id:  " +valueid + " n input value:" + sb.toString(), Toast.LENGTH_LONG).show();
            //    Uri uri = Uri.parse("http://glacial.pk/api/mailapi.php"); // missing 'http://' will cause crashed
            //    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            //    context.startActivity(intent);

                int i=0;
                i++;
                ((LabTestActivity)context).update(po,ui,mainob.get(position).getName());
            }catch (Exception e){String ee= String.valueOf(e);
                Toast.makeText(context,ee ,Toast.LENGTH_LONG ).show();
            }  }
    });
  holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


        //    Toast.makeText(context,e.toString() ,Toast.LENGTH_LONG ).show();
        }});

}catch (Exception e){String ee= String.valueOf(e);
//Toast.makeText(context,ee ,Toast.LENGTH_LONG ).show();
}
// implement setOnClickListener event on item view.

}



    private static final String BASE_URL = "http://glacial.pk/api/mailapi.php/"; //"http://www.nidshub.com/pet_api/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient(String baseUrl) {
        //    Log.d("response","okay");

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            GetData service = retrofit.create(GetData.class);
            Call s = service.getEmail("http://glacial.pk/api/mailapi.php");
            s.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    Log.d("response","okay");
                    //       Log.e("ERROR", "ok" );
                    //     Toast.makeText(,"ok",Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    Log.e("ERROR", "fail" );
                }
            });

        }
        return retrofit;
    }


public void notifemail(){

    Uri uri = Uri.parse("http://glacial.pk/api/mailapi.php"); // missing 'http://' will cause crashed
    Intent intent = new Intent(Intent.ACTION_SEND, uri);
    context.startActivity(intent);


}


    @Override
    public int getItemCount() {
        return mainob.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {



     //   private final TextView status;
        // init the item view's
        TextView parameter,glacialths,serial,units;
ProgressBar progressBar;
EditText result;
Button qthsbtn;
        public MyViewHolder(View itemView) {
            super(itemView);

         //   units=itemView.findViewById(R.id.unitstext);
            parameter=itemView.findViewById(R.id.parametertext);
// get status=itemView.findViewById(R.id.status);
          // glacialths=(TextView)itemView.findViewById(R.id.glacialthstext);
            serial=(TextView)itemView.findViewById(R.id.serialtext);
            result=itemView.findViewById(R.id.resulttext);
            qthsbtn=(Button)itemView.findViewById(R.id.infobtn);
        }
    }
}