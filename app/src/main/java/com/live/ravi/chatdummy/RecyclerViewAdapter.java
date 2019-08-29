package com.live.ravi.chatdummy;

/**
 * Created by HP on 30-12-2018.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<ImageUploadInfo> MainImageUploadInfoList;

    public RecyclerViewAdapter(Context context, List<ImageUploadInfo> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageUploadInfo UploadInfo = MainImageUploadInfoList.get(position);

        holder.imageNameTextView.setText(UploadInfo.getImageName());
       holder.timedate.setText(UploadInfo.getDt());
        //Loading image from Glide library.
    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        public ImageView imageView;
        public TextView imageNameTextView,timedate;
        final Context contextt = itemView.getContext();
        public ViewHolder(View itemView) {
            super(itemView);
          timedate=(TextView) itemView.findViewById(R.id.watcherCounterTextView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            imageNameTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String pdflink = MainImageUploadInfoList.get(getAdapterPosition()).getImageURL();
            Intent i=new Intent(contextt,PdfviewActivity.class);
            Bundle extras = new Bundle();
            extras.putString("pdflink",pdflink);

            i.putExtras(extras);
            contextt.startActivity(i);


        }
    }
}
