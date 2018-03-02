package com.tecelm.hasna.jsonparsingexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Created by Admin on 23/02/2018.
 */

public class CustomRepoAdapter extends BaseAdapter {

    private Context mContext;
    List<Repository> repoList = new ArrayList<Repository>();

    public CustomRepoAdapter(Context mContext, List<Repository> repoList) {
        this.mContext = mContext;
        this.repoList = repoList;
    }
//
private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

    public static String format(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }
    //
    @Override
    public int getCount() {
        return repoList.size();
    }

    @Override
    public Object getItem(int position) {
        return repoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder {

        private TextView name;
        private TextView owner;
        private TextView desc;
        private TextView stars;
        private ImageView avatar;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_list, null, true);
            holder.name = (TextView) convertView.findViewById(R.id.reponame);
            holder.owner= (TextView) convertView.findViewById(R.id.ownername);
            holder.desc = (TextView) convertView.findViewById(R.id.description);
            holder.stars = (TextView)convertView.findViewById(R.id.numberstars);
            holder.avatar = (ImageView)convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        Repository repository = repoList.get(position);
        holder.name.setText(repository.getReponame());
        holder.desc.setText(repository.getDescription());
        holder.owner.setText(repository.getOwnername());
        holder.stars.setText(format(repository.getStarscount()));
        Glide.with(holder.avatar.getContext()).load(repository.getAvatarurl()).centerCrop().into(holder.avatar);
        return convertView;
    }
}
