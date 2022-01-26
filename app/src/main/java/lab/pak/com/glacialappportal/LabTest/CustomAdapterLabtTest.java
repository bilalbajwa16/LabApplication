package lab.pak.com.glacialappportal.LabTest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lab.pak.com.glacialappportal.R;


public class CustomAdapterLabtTest extends RecyclerView.Adapter<CustomAdapterLabtTest.MyViewHolder> {
List<Object> mainob;
    private final List<labtestcontents> lab;
   Context context;
    private MyViewHolder vh;
ProgressBar progressBar;
    private String history,med,disease;
    private String aget;
    private String blood;

    public CustomAdapterLabtTest(Context context,List<labtestcontents> lab, List<Object> main) {
        this.context = context;
        this.mainob=main;
        this.lab=lab;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.labtest, parent, false);

        vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

    View vl;
try {
    int s = lab.size();
    String resul = new Gson().toJson(mainob.get(position));
    JSONObject resu = new JSONObject(resul);
    if (resu!=null) {
        try {
            JSONObject result = new JSONObject(resul);

            //   Toast.makeText(Main.this, resul.toString(), Toast.LENGTH_LONG).show();

            String name = result.getString(lab.get(position).getName());
            //String id = result.getString("id");

            holder.serial.setText(lab.get(position).getName());
                    }catch (Exception e){Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();}}

    holder.itemView.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
try{
            for (int u = 0; u < lab.size(); u++) {
                JSONObject res = new JSONObject(resul);
                if (res != null) {
                    try {
                        JSONObject result = new JSONObject(resul);
                      //  Toast.makeText(context, result.toString(), Toast.LENGTH_LONG).show();

                        //   Toast.makeText(Main.this, resul.toString(), Toast.LENGTH_LONG).show()
                        String title = lab.get(u).getName();
                        String name = result.getString(lab.get(u).getName());
                        //
                        Toast.makeText(context, title + " " + name, Toast.LENGTH_LONG).show();
                    }catch (Exception e){Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();}}
            }}catch (Exception e){}
        }});//
//                            );
      //for(int i=0;i<s;i++) {
     //     listnew.ad)d(result
     // }
}catch (Exception e){String ee= String.valueOf(e);
Toast.makeText(context,ee ,Toast.LENGTH_LONG ).show();
        }  }




// implement setOnClickListener event on item view.






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
        public MyViewHolder(View itemView) {
            super(itemView);

          //  units=itemView.findViewById(R.id.unitstext);
            parameter=itemView.findViewById(R.id.parametertext);
// get status=itemView.findViewById(R.id.status);
        //   glacialths=(TextView)itemView.findViewById(R.id.glacialthstext);
            serial=(TextView)itemView.findViewById(R.id.serialtext);
            result=itemView.findViewById(R.id.resulttext);

        }
    }
}