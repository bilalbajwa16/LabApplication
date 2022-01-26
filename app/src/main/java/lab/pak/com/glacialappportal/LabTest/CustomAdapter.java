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

import java.util.ArrayList;

import lab.pak.com.glacialappportal.R;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final ArrayList<labtestcontents> mainob;
   Context context;
    private MyViewHolder vh;
ProgressBar progressBar;
    private String history,med,disease;
    private String aget;
    private String blood;

    public CustomAdapter(Context context, ArrayList<labtestcontents> mainob) {
        this.context = context;
        this.mainob=mainob;


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
int postion=position+1;
    String po= String.valueOf(mainob.get(position).getId());
    holder.serial.setText(po);
    holder.parameter.setText(mainob.get(position).getName()+"\n("+mainob.get(position).getUnits()+")");
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
              //  Toast.makeText(context,sb.toString() ,Toast.LENGTH_LONG ).show();
                int i=0;
                i++;
                ((LabTestActivity)context).update(po,ui,mainob.get(position).getName());
            }catch (Exception e){String ee= String.valueOf(e);
              //  Toast.makeText(context,ee ,Toast.LENGTH_LONG ).show();
            }  }
    });
holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


        //    Toast.makeText(context,e.toString() ,Toast.LENGTH_LONG ).show();
        }});

}catch (Exception e){String ee= String.valueOf(e);
Toast.makeText(context,ee ,Toast.LENGTH_LONG ).show();
}
// implement setOnClickListener event on item view.

}





    @Override
    public int getItemCount() {
        return mainob.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {



     //   private final TextView status;
        // init the item view's
        TextView parameter,serial;
ProgressBar progressBar;
EditText result;
        public MyViewHolder(View itemView) {
            super(itemView);

            //=itemView.findViewById(R.id.unitstext);
            parameter=itemView.findViewById(R.id.parametertext);
// get status=itemView.findViewById(R.id.status);
         ///  glacialths=(TextView)itemView.findViewById(R.id.glacialthstext);
            serial=(TextView)itemView.findViewById(R.id.serialtext);
            result=itemView.findViewById(R.id.resulttext);

        }
    }
}