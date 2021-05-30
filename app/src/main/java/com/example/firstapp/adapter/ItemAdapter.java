package com.example.firstapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firstapp.R;
import com.example.firstapp.model.PersonActivity;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<PersonActivity> {

    private Context context;
    private int resoure;
    private List<PersonActivity> activityList;

    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<PersonActivity> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resoure = resource;
        this.activityList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_activities, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvItemId = (TextView) convertView.findViewById(R.id.tv_item_id);
            viewHolder.tvItemName = (TextView) convertView.findViewById(R.id.tv_item_name);
            viewHolder.tvItemLocate = (TextView) convertView.findViewById(R.id.tv_item_locate);
            viewHolder.tvItemDate = (TextView) convertView.findViewById(R.id.tv_item_date);
            viewHolder.tvItemTime = (TextView) convertView.findViewById(R.id.tv_item_time);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PersonActivity activity = activityList.get(position);
        viewHolder.tvItemId.setText(activity.getmId()+"");
        viewHolder.tvItemName.setText(activity.getmName());
        viewHolder.tvItemLocate.setText(activity.getmLocate());
        viewHolder.tvItemDate.setText(activity.getmDate());
        viewHolder.tvItemTime.setText(activity.getmTime());

        return convertView;
    }

    public class ViewHolder{
        private TextView tvItemId, tvItemName, tvItemLocate, tvItemDate, tvItemTime;
    }
}
