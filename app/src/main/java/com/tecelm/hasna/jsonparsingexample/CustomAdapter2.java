package com.tecelm.hasna.jsonparsingexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 23/02/2018.
 */

public class CustomAdapter2 extends BaseAdapter {
    ArrayList<Earthquake> listearth;
    private Context mContext;

    public CustomAdapter2(Context context, ArrayList<Earthquake> earth) {
        this.listearth = earth;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return listearth.size();
    }

    @Override
    public Object getItem(int position) {
        return listearth.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {

        private TextView mag;
        private TextView location;
        private TextView time;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null, true);
            holder.mag = (TextView) convertView.findViewById(R.id.name);
            holder.location = (TextView) convertView.findViewById(R.id.email);
            holder.time = (TextView) convertView.findViewById(R.id.mobile);
            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        Earthquake earthquake = listearth.get(position);
        holder.mag.setText(earthquake.getMagnitude());
        holder.location.setText(earthquake.getLocation());
        holder.time.setText(earthquake.getDate());

        return convertView;
    }
}
