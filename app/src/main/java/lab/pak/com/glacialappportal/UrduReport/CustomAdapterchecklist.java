package lab.pak.com.glacialappportal.UrduReport;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import lab.pak.com.glacialappportal.R;


public class CustomAdapterchecklist extends RecyclerView.Adapter<CustomAdapterchecklist.MyViewHolder> {

    private final ArrayList<checklistcontents> mainob;
   Context context;
    private MyViewHolder vh;

    public CustomAdapterchecklist(Context context, ArrayList<checklistcontents> mainob) {
        this.context = context;
        this.mainob=mainob;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.checklist, parent, false);

        vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

    View vl;
try {
    holder.name.setText(mainob.get(position).getUrducontent());

    holder.indexno.setText(mainob.get(position).getId()+"");

    holder.cross.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            holder.tick.setBackgroundResource(R.drawable.right);
            holder.cross.setBackgroundResource(R.mipmap.cross);
//  Toast.makeText(context,sb.toString() ,Toast.LENGTH_LONG ).show();
            int i=0;
            i++;

            ((CheckList)context).update("","false",mainob.get(position).getName());
        }
    });



holder.tick.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        holder.tick.setBackgroundResource(R.mipmap.tick);
        holder.cross.setBackgroundResource(R.drawable.wrong);
//  Toast.makeText(context,sb.toString() ,Toast.LENGTH_LONG ).show();
        int i=0;
        i++;

        ((CheckList)context).update("","true",mainob.get(position).getName());

    }
});
/*
    holder.name.setText(mainob.get(position).getName());
//holder..setText(mainob.get(position).getUnits());
//holder.glacialths.setText(mainob.get(position).getGlacialths());
    holder.result.addTextChangedListener(new TextWatcher() {

        public void afterTextChanged(Editable s) {
        }

        public void beforeTextChanged(CharSequence s, int start,
                                      int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
            // TextView myOutputBox = (TextView) findViewById(R.id.myOutputBox);
            try{
                final StringBuilder sb = new StringBuilder(s.length());
                sb.append(s);
                String ui= sb.toString();
              //  Toast.makeText(context,sb.toString() ,Toast.LENGTH_LONG ).show();
                int i=0;
                String val=mainob.get(position).getNamedatabase()+",";
                i++;
                ((ProductionNote)context).update("1",ui,mainob.get(position).getNamedatabase());
            }catch (Exception e){String ee= String.valueOf(e);
                Toast.makeText(context,ee ,Toast.LENGTH_LONG ).show();
            }  }
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
            try{
                final StringBuilder sb = new StringBuilder(s.length());
                sb.append(s);
                String ui= sb.toString();
                //  Toast.makeText(context,sb.toString() ,Toast.LENGTH_LONG ).show();
                int i=0;
                i++;
                String val=mainob.get(position).getNamedatabase();

                ((ProductionNote)context).update("79299",ui,val);
            }catch (Exception e){String ee= String.valueOf(e);
                Toast.makeText(context,ee ,Toast.LENGTH_LONG ).show();
            }  }
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
            try{
                final StringBuilder sb = new StringBuilder(s.length());
                sb.append(s);
                String ui= sb.toString();
                //  Toast.makeText(context,sb.toString() ,Toast.LENGTH_LONG ).show();
                int i=0;
                i++;
                ((ProductionNote)context).update("79294",ui,mainob.get(position).getNamedatabase());
            }catch (Exception e){String ee= String.valueOf(e);
                Toast.makeText(context,ee ,Toast.LENGTH_LONG ).show();
            }  }
    });



holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


        //    Toast.makeText(context,e.toString() ,Toast.LENGTH_LONG ).show();
        }});
*/
}catch (Exception e){String ee= String.valueOf(e);
//Toast.makeText(context,ee ,Toast.LENGTH_LONG ).show();
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
        TextView name,indexno;
        ImageView tick,cross;
     RelativeLayout tds;
        EditText lowtds,hightds,result;
ProgressBar progressBar;
        public MyViewHolder(View itemView) {
            super(itemView);
            indexno=itemView.findViewById(R.id.index);
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