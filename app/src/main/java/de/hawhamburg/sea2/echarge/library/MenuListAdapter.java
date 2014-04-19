package de.hawhamburg.sea2.echarge.library;

/**
 * Created by Simon on 11.03.14.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import de.hawhamburg.sea2.echarge.R;

public class MenuListAdapter extends BaseAdapter {
    private Context context;
    private String[] mTitle;
    private String[] mSubTitle;
    private int[] mIcon;
    private LayoutInflater inflater;

    public MenuListAdapter(Context pContext, String[] pTitle, String[] pSubtitle, int[] pIcon) {
        context = pContext;
        mTitle = pTitle;
        mSubTitle = pSubtitle;
        mIcon = pIcon;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_main_drawer_list_item, parent, false);

        TextView txtTitle = (TextView) itemView.findViewById(R.id.title);
        TextView txtSubTitle = (TextView) itemView.findViewById(R.id.subtitle);
        ImageView imgIcon = (ImageView) itemView.findViewById(R.id.icon);

        txtTitle.setText(mTitle[position]);
        txtSubTitle.setText(mSubTitle[position]);
        imgIcon.setImageResource(mIcon[position]);

        return itemView;
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public Object getItem(int position) {
        return mTitle[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
