package lab.pak.com.glacialappportal.ProductionNote;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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


public class CustomAdapterProduction extends RecyclerView.Adapter<CustomAdapterProduction.MyViewHolder> {

    private final ArrayList<productioncontents> mainob;
    Context context;
    private MyViewHolder vh;
    ProgressBar progressBar;
    private String history, med, disease;
    private String aget;
    private String blood;


    public CustomAdapterProduction(Context context, ArrayList<productioncontents> mainob) {
        this.context = context;
        this.mainob = mainob;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.productionnote, parent, false);

        vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        View vl;
        try {
            int postion = position + 1;
            String po = String.valueOf(mainob.get(position).getId());
            // holder.serial.setText(po);
            if (mainob.get(position).getTypenumeric().equals("single")) {

                if (mainob.get(position).getInputtype().equals("numeric")) {
                    holder.result.setVisibility(View.VISIBLE);
                } else {
                    holder.cross.setVisibility(View.VISIBLE);
                    holder.tick.setVisibility(View.VISIBLE);
                }


            } else {
                holder.tds.setVisibility(View.VISIBLE);

            }


            holder.cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.tick.setBackgroundResource(R.drawable.right);
                    holder.cross.setBackgroundResource(R.mipmap.cross);
//  Toast.makeText(context,sb.toString() ,Toast.LENGTH_LONG ).show();
                    int i = 0;
                    i++;

                    ((ProductionNote) context).update("", "false", mainob.get(position).getNamedatabase());
                }
            });


            holder.tick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.tick.setBackgroundResource(R.mipmap.tick);
                    holder.cross.setBackgroundResource(R.drawable.wrong);
//  Toast.makeText(context,sb.toString() ,Toast.LENGTH_LONG ).show();
                    int i = 0;
                    i++;

                    ((ProductionNote) context).update("", "true", mainob.get(position).getNamedatabase());

                }
            });

            int i = 0;
            i++;


            holder.name.setText(mainob.get(position).getName());
//holder..setText(mainob.get(position).getUnits());
//holder.glacialths.setText(mainob.get(position).getGlacialths());
            holder.indexno.setText(mainob.get(position).getId() + "");
            holder.result.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {

                    try {
                        final StringBuilder sb = new StringBuilder(s.length());
                        sb.append(s);
                        String ui = sb.toString();
                        int value = Integer.parseInt(ui);
                        String v = mainob.get(position).getId() + "";
                        int valueid = Integer.parseInt(v);

                        if (valueid == 6 && value < 15) {
                            // Toast.makeText(context, "warning value: id:  " +valueid + " n input value:" + sb.toString(), Toast.LENGTH_LONG).show();
                            // notifURL();

                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("ProductionE2","warning");
                            editor.apply();

                            final EditText input = new EditText(context);
                            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                            alertDialog.setTitle("Insert value");
                            alertDialog.setView(input);
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();

                        }
                        else if (valueid == 7 && value < 5) {
                            // Toast.makeText(context, "warning value: id:  " +valueid + " n input value:" + sb.toString(), Toast.LENGTH_LONG).show();

                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("ProductionE3","warning");
                            editor.apply();

                            //  notifURL();
                            final EditText input = new EditText(context);
                            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                            alertDialog.setTitle("Insert value");
                            alertDialog.setView(input);
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                        else if (valueid == 10 && value < 10) {
                            //  Toast.makeText(context, "warning value: id:  " +valueid + " n input value:" + sb.toString(), Toast.LENGTH_LONG).show();

                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("ProductionE4","warning");
                            editor.apply();
                            // notifURL();
                            final EditText input = new EditText(context);
                            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                            alertDialog.setTitle("Insert value");
                            alertDialog.setView(input);
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();

                        }
                        else if (valueid == 11 && value < 20) {
                            //  Toast.makeText(context, "warning value: id:  " +valueid + " n input value:" + sb.toString(), Toast.LENGTH_LONG).show();
                            //  notifURL();

                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("ProductionE5","warning");
                            editor.apply();

                            final EditText input = new EditText(context);
                            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                            alertDialog.setTitle("Insert value");
                            alertDialog.setView(input);
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                        else{
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

                            String w1 = preferences.getString("ProductionE2", "");
                            String w2 = preferences.getString("ProductionE3", "");
                            String w3 = preferences.getString("ProductionE4", "");
                            String w4 = preferences.getString("ProductionE5", "");

                            w1="";
                            w2="";
                            w3="";
                            w4="";

                        }

                    }catch (Exception e){

                    }

                    }

                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    // TextView myOutputBox = (TextView) findViewById(R.id.myOutputBox);
                    try {
                        final StringBuilder sb = new StringBuilder(s.length());
                        sb.append(s);
                        String ui = sb.toString();
                        int value = Integer.parseInt(ui);
                        String v = mainob.get(position).getId() + "";
                        int valueid = Integer.parseInt(v);
                        if (valueid == 15 && 7 > value) {

                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("ProductionE1","warning");
                            editor.apply();

                         //   notifURL();
                            //  Toast.makeText(context, "warning value: id:  " +valueid + " n input value:" + sb.toString(), Toast.LENGTH_LONG).show();

                        }else {

                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

                            String email = preferences.getString("ProductionE1", "");

                         //   String pass = preferences.getString("pass","");

                            email = "";
                        //    pass = "";

                        }

  /*                      else if (valueid == 6 && value < 15) {
                            // Toast.makeText(context, "warning value: id:  " +valueid + " n input value:" + sb.toString(), Toast.LENGTH_LONG).show();
                           // notifURL();

                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("ProductionE2","warning");
                            editor.apply();

                            final EditText input = new EditText(context);
                            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                            alertDialog.setTitle("Insert value");
                            alertDialog.setView(input);
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();

                        }
                        else if (valueid == 7 && value < 5) {
                            // Toast.makeText(context, "warning value: id:  " +valueid + " n input value:" + sb.toString(), Toast.LENGTH_LONG).show();

                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("ProductionE3","warning");
                            editor.apply();

                            //  notifURL();
                            final EditText input = new EditText(context);
                            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                            alertDialog.setTitle("Insert value");
                            alertDialog.setView(input);
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                        else if (valueid == 10 && value < 10) {
                            //  Toast.makeText(context, "warning value: id:  " +valueid + " n input value:" + sb.toString(), Toast.LENGTH_LONG).show();

                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("ProductionE4","warning");
                            editor.apply();
                            // notifURL();
                            final EditText input = new EditText(context);
                            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                            alertDialog.setTitle("Insert value");
                            alertDialog.setView(input);
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();

                        }
                        else if (valueid == 11 && value < 20) {
                            //  Toast.makeText(context, "warning value: id:  " +valueid + " n input value:" + sb.toString(), Toast.LENGTH_LONG).show();
                          //  notifURL();

                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("ProductionE5","warning");
                            editor.apply();

                            final EditText input = new EditText(context);
                            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                            alertDialog.setTitle("Insert value");
                            alertDialog.setView(input);
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
*/
                        //  Toast.makeText(context,sb.toString() ,Toast.LENGTH_LONG ).show();
                        int i = 0;
                        String val = mainob.get(position).getNamedatabase() + ",";
                        i++;
                        ((ProductionNote) context).update("1", ui, mainob.get(position).getNamedatabase());
                    } catch (Exception e) {
                        String ee = String.valueOf(e);
                        Toast.makeText(context, ee, Toast.LENGTH_LONG).show();
                    }
                }
            });
            holder.hightds.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                }

                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    // TextView myOutputBox = (TextView) findViewById(R.id.myOutputBox);
                    try {
                        final StringBuilder sb = new StringBuilder(s.length());
                        sb.append(s);
                        String ui = sb.toString();
                        //  Toast.makeText(context,sb.toString() ,Toast.LENGTH_LONG ).show();
                        int i = 0;
                        i++;
                        String val = mainob.get(position).getNamedatabase();

                        ((ProductionNote) context).update("79299", ui, val);
                    } catch (Exception e) {
                        String ee = String.valueOf(e);
                        Toast.makeText(context, ee, Toast.LENGTH_LONG).show();
                    }
                }
            });

            holder.lowtds.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                }

                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                    // TextView myOutputBox = (TextView) findViewById(R.id.myOutputBox);
                    try {
                        final StringBuilder sb = new StringBuilder(s.length());
                        sb.append(s);
                        String ui = sb.toString();
                        //  Toast.makeText(context,sb.toString() ,Toast.LENGTH_LONG ).show();
                        int i = 0;
                        i++;
                        ((ProductionNote) context).update("79294", ui, mainob.get(position).getNamedatabase());
                    } catch (Exception e) {
                        String ee = String.valueOf(e);
                        Toast.makeText(context, ee, Toast.LENGTH_LONG).show();
                    }
                }
            });


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    //    Toast.makeText(context,e.toString() ,Toast.LENGTH_LONG ).show();
                }
            });

        } catch (Exception e) {
            String ee = String.valueOf(e);
//Toast.makeText(context,ee ,Toast.LENGTH_LONG ).show();
        }
// implement setOnClickListener event on item view.

    }


    private static final String BASE_URL = "http://glacial.pk/api/mail1api.php/"; //"http://www.nidshub.com/pet_api/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient(String baseUrl) {
    //    Log.d("response","okay");

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            GetData service = retrofit.create(GetData.class);
            Call s = service.getEmail("http://glacial.pk/api/mail1api.php");
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

    public void notifURL() {

getApiClient(BASE_URL);
      //  Toast.makeText(context,"ok",Toast.LENGTH_SHORT).show();

    /*    try {
            URL url = new URL("http://glacial.pk/api/mailapi.php");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
               BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
*/
    }



    @Override
    public int getItemCount() {
        return mainob.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {



     //   private final TextView status;
        // init the item view's
        TextView name, indexno;
        ImageView tick,cross;
     RelativeLayout tds;
        EditText lowtds,hightds,result;
ProgressBar progressBar;
        public MyViewHolder(View itemView) {
            super(itemView);
indexno = itemView.findViewById(R.id.index);
            name=itemView.findViewById(R.id.parametertext);
            tds=itemView.findViewById(R.id.tds);
lowtds=itemView.findViewById(R.id.lowerds);
hightds=itemView.findViewById(R.id.hightds);
// get status=itemView.findViewById(R.id.status);
           tick=(ImageView) itemView.findViewById(R.id.tick);
            cross=(ImageView) itemView.findViewById(R.id.cross);
            result=itemView.findViewById(R.id.result);

        }


    }
}