package lab.pak.com.glacialappportal.Notifications;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.List;

import lab.pak.com.glacialappportal.R;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>  {

    List<Tasks> pets, petsFilter;
    private Context context;
    private RecyclerViewClickListener mListener;

    public Adapter(List<Tasks> pets, Context context, RecyclerViewClickListener listener) {
        this.pets = pets;
        this.petsFilter = pets;
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_task, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.mName.setText( " ID:"+pets.get(position).getId()+".  " + pets.get(position).getTaskmessage() );
      //  holder.mType.setText( " Date: " +pets.get(position).getDateattribute() + " | Task Id:   "
       //      + pets.get(position).getTid());
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private ImageView mLove;
        private TextView mName, mType, mDate;
        private RelativeLayout mRowContainer;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mName = itemView.findViewById(R.id.name);
            mType = itemView.findViewById(R.id.type);
            mLove = itemView.findViewById(R.id.love);
            //  mDate = itemView.findViewById(R.id.date);
            mRowContainer = itemView.findViewById(R.id.row_container);

            mListener = listener;
            mRowContainer.setOnClickListener(this);
            mLove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
          /*      case R.id.love:
                    mListener.onLoveClick(mLove, getAdapterPosition());
                    break;  */
                default:
                    break;
            }
        }
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
      //  void onLoveClick(View view, int position);
    }

}
